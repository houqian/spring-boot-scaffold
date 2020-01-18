package org.houqian.springbootdemo;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@EnableApolloConfig("springbootdemo.yml")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class, scanBasePackages = {"org.houqian.springbootdemo"})
public class SpringBootDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoApplication.class, args);
	}
}
