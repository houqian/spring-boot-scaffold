package org.houqian.springbootdemo.job.elasticjob;

import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

//@Configuration
//@ConditionalOnExpression("'${regCenter.serverList}'.length() > 0")
public class RegistryCenterConfig {

  @Bean(initMethod = "init")
  public ZookeeperRegistryCenter regCenter(@Value("${regCenter.serverList}") final String serverList,
                                           @Value("${regCenter.namespace}") final String namespace) {
    return new ZookeeperRegistryCenter(new ZookeeperConfiguration(serverList, namespace));
  }
}