package org.houqian.springbootdemo.kafka.muti_thread;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : houqian
 * @version : 1.0
 * @since : 2019-05-20
 */
public class RecordHandler<T> implements Runnable {

  private ConsumerRecord<String, String> record;

  private ConcurrentHashMap<TopicPartition, OffsetAndMetadata> offsets;

  public RecordHandler(ConsumerRecord<String, String> record,
                       ConcurrentHashMap<TopicPartition, OffsetAndMetadata> offsets) {
    this.record = record;
  }

  @Override
  public void run() {

  }
}
