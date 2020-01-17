package org.houqian.springbootdemo.commons.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@MapperScan(basePackages = {"org.houqian.springbootdemo.dao.slave"}, sqlSessionFactoryRef = "slaveSqlSessionFactory")
public class SlaveDataSourcesConfig {

  private static final String MAPPER_LOCAL = "classpath:mapper/slave/*.xml";

  @Bean(name = "slaveDataSource")
  @ConfigurationProperties("spring.datasource.druid.slave")
  public DruidDataSource druidDataSource() {
    return new DruidDataSource();
  }

  @Bean(name = "slaveTransactionManager")
  public DataSourceTransactionManager slaveTransactionManager() {
    return new DataSourceTransactionManager(druidDataSource());
  }

  @Bean(name = "slaveSqlSessionFactory")
  public SqlSessionFactory slaveSqlSessionFactory(@Qualifier("slaveDataSource") DruidDataSource dataSource) throws Exception {
    final SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
    sessionFactoryBean.setDataSource(dataSource);
//    sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCAL));
    return sessionFactoryBean.getObject();
  }
}