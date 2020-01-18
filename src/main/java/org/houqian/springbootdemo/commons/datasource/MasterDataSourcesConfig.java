package org.houqian.springbootdemo.commons.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Slf4j
@Component
@MapperScan(basePackages = {"org.houqian.springbootdemo.dao.master"}, sqlSessionFactoryRef = "masterSqlSessionFactory")
public class MasterDataSourcesConfig {

  // classpath:mapper/master/*.xml
//  private static final String MAPPER_LOCAL = "classpath:mapper/master/*.xml";

  private final String CONFIG_ON_APOLLO_PREFIX = "spring.datasource.druid.master";

  @Value("${" + CONFIG_ON_APOLLO_PREFIX + ".url" + "}")
  private String url;
  @Value("${" + CONFIG_ON_APOLLO_PREFIX + ".username" + "}")
  private String username;
  @Value("${" + CONFIG_ON_APOLLO_PREFIX + ".password" + "}")
  private String password;

  @Primary
  @Bean(name = "masterDataSource")
  public DruidDataSource druidDataSource() {
    DruidDataSource druidDataSource = new DruidDataSource();
    druidDataSource.setUrl(url);
    druidDataSource.setUsername(username);
    druidDataSource.setPassword(password);
    druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
    return druidDataSource;
  }

  @Primary
  @Bean(name = "masterTransactionManager")
  public DataSourceTransactionManager masterTransactionManager(@Qualifier("masterDataSource") DruidDataSource druidDataSource) {
    return new DataSourceTransactionManager(druidDataSource);
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