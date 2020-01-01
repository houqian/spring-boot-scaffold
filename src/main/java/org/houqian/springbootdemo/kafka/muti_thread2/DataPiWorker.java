package org.houqian.springbootdemo.kafka.muti_thread2;

import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;

/**
 * @author : houqian
 * @version : 1.0
 * @since : 2019-05-26
 */
public class DataPiWorker<T> {

  T msgType;

  String threadPoolName;

  ExecutorService executorService;

  /*
  kafka consumer非线程安全，所以不可以将consumer传递给每个处理器进行单个消息提交
  因此折衷以下，在woker和hander中间用一个共享的线程安全map来保存topic、patition、offset，
  在每次poll开始，提交一次共享map中的偏移量
   */
  protected ConcurrentHashMap<TopicPartition, OffsetAndMetadata> offsets;

  public String getThreadPoolName() {
    String bizMsgName = msgType.getClass().getSimpleName();
    String workerType = this.getClass().getSimpleName();
    return String.format("%s_%s", workerType, bizMsgName);
  }
}
