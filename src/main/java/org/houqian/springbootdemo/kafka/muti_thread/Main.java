package org.houqian.springbootdemo.kafka.muti_thread;

import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : houqian
 * @version : 1.0
 * @since : 2019-05-20
 */
public class Main {
  public static void main(String[] args) {
    ConcurrentHashMap<TopicPartition, OffsetAndMetadata> offsets = new ConcurrentHashMap<>(100);
    new DataPiConsumer("crawler_data", 8, offsets).start();
  }
}
