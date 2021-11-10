package cn.com.fl.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication()
@ServletComponentScan
@EnableDiscoveryClient
@EnableEurekaClient
@EnableTransactionManagement
@ComponentScan(basePackages={"cn.com"})
@MapperScan(
        basePackages = {"cn.com.*.mapper"}
)
public class PlatApplication {
//    @Bean
//    @LoadBalanced
//    public RestTemplate restTemplate(RestTemplateBuilder builder) {
//        return builder.build();
//    }

    public static void main(String[] args) {
        SpringApplication.run(PlatApplication.class, args);
    }
}


