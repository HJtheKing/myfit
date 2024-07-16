package com.chj.myfit.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages="com.chj.myfit.model.dao")
public class DBConfig {

}
