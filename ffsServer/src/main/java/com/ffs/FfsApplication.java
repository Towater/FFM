package com.ffs;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan("com.ffs.*")
@ServletComponentScan(basePackages = {"com.ffs.api.filter"})
public class FfsApplication {

    public static void main(String[] args) {
        SpringApplication.run(FfsApplication.class, args);
    }

    /**
     *  MP分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}
