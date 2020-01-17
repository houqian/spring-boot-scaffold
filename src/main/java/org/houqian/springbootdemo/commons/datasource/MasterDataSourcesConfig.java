package org.houqian.springbootdemo.commons.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"org.houqian.springbootdemo.dao.master"}, sqlSessionFactoryRef = "masterSqlSessionFactory")
public class MasterDataSourcesConfig {

  // classpath:mapper/master/*.xml
//  private static final String MAPPER_LOCAL = "classpath:mapper/master/*.xml";

  @Primary
  @Bean(name = "masterDataSource")
  @ConfigurationProperties("spring.datasource.druid.master")
  public DruidDataSource druidDataSource() {
    return new DruidDataSource();
  }

  @Primary
  @Bean(name = "masterTransactionManager")
  public DataSourceTransactionManager masterTransactionManager() {
    return new DataSourceTransactionManager(druidDataSource());
  }

  @Primary
  @Bean(name = "masterSqlSessionFactory")
  public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource") DataSource dataSource) throws Exception {
    final SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
    sessionFactoryBean.setDataSource(dataSource);
//    sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCAL));
    return sessionFactoryBean.getObject();
  }
}