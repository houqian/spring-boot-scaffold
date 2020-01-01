package org.houqian.springbootdemo.kafka.muti_thread;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;

/**
 * @author : houqian
 * @version : 1.0
 * @since : 2019-05-17
 */
public class DataPiConsumer extends ConsumerWithoutAutoCommit {

  private static ObjectMapper mapper = new ObjectMapper();

  public DataPiConsumer(String topic,
                        int threadNum,
                        ConcurrentHashMap<TopicPartition, OffsetAndMetadata> offsets) {
    super(topic, threadNum, offsets);
  }

  @Override
  protected Properties config() {
    Properties props = super.config();
    props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092, localhost:9093, localhost:9094");
    props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "test");
    return props;
  }

  @Override
  protected void submitJob(ExecutorService executorService, ConsumerRecord<String, String> record) {
    String value = record.value();
  }

}
