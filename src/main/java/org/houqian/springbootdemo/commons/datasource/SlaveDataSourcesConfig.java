package org.houqian.springbootdemo.commons.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;

@Component
@MapperScan(basePackages = {"org.houqian.springbootdemo.dao.slave"}, sqlSessionFactoryRef = "slaveSqlSessionFactory")
public class SlaveDataSourcesConfig {

  private static final String MAPPER_LOCAL            = "classpath:mapper/slave/*.xml";
  private final        String CONFIG_ON_APOLLO_PREFIX = "spring.datasource.druid.slave";

  @Value("${" + CONFIG_ON_APOLLO_PREFIX + ".url" + "}")
  private String url;
  @Value("${" + CONFIG_ON_APOLLO_PREFIX + ".username" + "}")
  private String username;
  @Value("${" + CONFIG_ON_APOLLO_PREFIX + ".password" + "}")
  private String password;


  @Bean(name = "slaveDataSource")
  public DruidDataSource druidDataSource() {
    DruidDataSource druidDataSource = new DruidDataSource();
    druidDataSource.setUrl(url);
    druidDataSource.setUsername(username);
    druidDataSource.setPassword(password);
    druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
    return druidDataSource;
  }

  @Bean(name = "slaveTransactionManager")
  public DataSourceTransactionManager slaveTransactionManager(@Qualifier("slaveDataSource") DruidDataSource druidDataSource) {
    return new DataSourceTransactionManager(druidDataSource);
  }

  @Bean(name = "slaveSqlSessionFactory")
  public SqlSessionFactory slaveSqlSessionFactory(@Qualifier("slaveDataSource") DruidDataSource dataSource) throws Exception {
    final SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
    sessionFactoryBean.setDataSource(dataSource);
//    sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCAL));
    return sessionFactoryBean.getObject();
  }
}