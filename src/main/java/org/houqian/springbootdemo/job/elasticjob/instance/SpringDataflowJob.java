package org.houqian.springbootdemo.job.elasticjob.instance;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class SpringDataflowJob implements DataflowJob<String> {


  @Override
  public List<String> fetchData(final ShardingContext shardingContext) {
    System.out.println(String.format("Item: %s | Time: %s | Thread: %s | %s",
            shardingContext.getShardingItem(), new SimpleDateFormat("HH:mm:ss").format(new Date()), Thread.currentThread().getId(), "DATAFLOW FETCH"));
    return Arrays.asList("1", "2");
  }

  @Override
  public void processData(final ShardingContext shardingContext, final List<String> data) {
    System.out.println(String.format("Item: %s | Time: %s | Thread: %s | %s",
            shardingContext.getShardingItem(), new SimpleDateFormat("HH:mm:ss").format(new Date()), Thread.currentThread().getId(), "DATAFLOW PROCESS"));
    
  }
}