package org.xupeng;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient  //开启服务注册发现功能
@EnableFeignClients    //表明该服务使用Openfeign
@MapperScan("org.xupeng.mapper")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

//
//    @Autowired
//    private  RestTemplate restTemplate;
//
//
//    @Bean
//    @LoadBalanced
//    public RestTemplate getRestTemplate(){
//        return new RestTemplate();
//    }
//
//    @GetMapping("/consumer")
//    public Object test() {
//        return restTemplate.getForObject("http://nacos-inventory/helloNacos",Object.class);
//    }

}
