package org.houqian.springbootdemo.kafka.muti_thread;

import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.TopicPartition;

import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : houqian
 * @version : 1.0
 * @since : 2019-05-17
 */
public class Producer {

  private ConcurrentHashMap<TopicPartition, OffsetAndMetadata> offsets;

  private KafkaProducer<String, String> kafkaProducer;

  public Producer(ConcurrentHashMap<TopicPartition, OffsetAndMetadata> offsets) {
    this.offsets = offsets;
    this.kafkaProducer = new KafkaProducer<String, String>(this.config());
  }

  private Properties config() {

    return null;
  }


}
