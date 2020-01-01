package org.houqian.springbootdemo.kafka.muti_thread;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author : houqian
 * @version : 1.0
 * @since : 2019-05-17
 */
public abstract class ConsumerWithoutAutoCommit {

  // 与handler线程共享的map，handler线程处理完消息会将offset保存到此
  protected ConcurrentHashMap<TopicPartition, OffsetAndMetadata> offsets;

  private KafkaConsumer<String, String> kafkaConsumer;

  private ExecutorService executorService;

  private AtomicBoolean flag = new AtomicBoolean(true);

  public ConsumerWithoutAutoCommit(String topic,
                                   int threadNum,
                                   ConcurrentHashMap<TopicPartition, OffsetAndMetadata> offsets) {
    this.kafkaConsumer = new KafkaConsumer<>(config());
    this.kafkaConsumer.subscribe(Collections.singletonList(topic));
    this.offsets = offsets;

    executorService = new ThreadPoolExecutor(threadNum, threadNum,
                                             0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(20),
                                             new ThreadPoolExecutor.CallerRunsPolicy());
  }

  protected Properties config() {
    Properties props = new Properties();
    //props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
    //props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupid);
    props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
    props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());


    props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
    return props;
  }

  public void start() {
    while (flag.get()) {
      ConsumerRecords<String, String> records = this.kafkaConsumer.poll(Duration.ofMillis(100));

      Set<TopicPartition> partitions = records.partitions();

      if (!records.isEmpty()) {
        for (ConsumerRecord<String, String> record : records) {
          submitJob(executorService, record);
        }
      }

      tryCommitOffsets();
    }
  }

  /**
   * 唯一给外部暴露的可以优雅停止Consumer的接口
   */
  public void shutdown() {
    // 使用Atomic的CAS操作确保
    boolean res = flag.compareAndSet(true, false);
    if (res) {
      kafkaConsumer.close();
    }
  }

  protected abstract void submitJob(ExecutorService executorService, ConsumerRecord<String, String> record);


  private void tryCommitOffsets() {
    this.kafkaConsumer.commitSync(this.offsets);
    this.offsets.clear();
  }


}

