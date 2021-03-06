package com.atlp.rsgl;

import com.atlp.rsgl.common.base.BaseRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement //开启注解事务管理
@ServletComponentScan
@EnableJpaRepositories(basePackages = {"com.atlp.rsgl.repository"}, repositoryBaseClass = BaseRepositoryImpl.class)
public class RsglApplication {

    public static void main(String[] args) {
        //ceshi yix
        SpringApplication.run(RsglApplication.class, args);
    }
}
