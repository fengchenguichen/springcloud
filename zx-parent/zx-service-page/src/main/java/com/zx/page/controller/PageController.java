package com.zx.page.controller;

import com.netflix.appinfo.InstanceInfo;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zx.comment.pojo.Products;
import com.zx.page.feign.ProductFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("page")
public class PageController {

    @Autowired
    private ProductFeign productFeign;

    @GetMapping("select/{id}")
    public Products selectById(@PathVariable Integer id){

/*        // .获得Eureka中注册的lagou-service-product实例集合
        List<ServiceInstance> instances = discoveryClient.getInstances("zx-service-product");
        //2.获得实例集合中的第一个
        ServiceInstance instance = instances.get(0);
        //3.根据实例信息拼接IP地址
        String host = instance.getHost();
        int port = instance.getPort();*/

        /*String url = "http://zx-service-product/product/select/"+id;

        Products product = restTemplate.getForObject(url, Products.class);
        return product;*/
        return productFeign.selectById(id);
    }

    @GetMapping("getPort")
    public String getProductServerPort(){
        /*String url = "http://zx-service-product/server/select";
        String result = restTemplate.getForObject(url, String.class);
        return result;*/
        return productFeign.selectServerPort();
    }

    // 使用@HystrixCommand注解进行熔断控制
    @HystrixCommand(
            // 线程池标识，要保持唯一，不唯一的话就共用了
            threadPoolKey = "getProductServerPort2",
            // 线程池细节属性配置
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "1"),
                    @HystrixProperty(name = "maxQueueSize", value = "20")
            },
            // commandProperties熔断的一些细节属性配置
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
            }

    )
    @GetMapping("getPort2")
    public String getProductServerPort2(){
        return productFeign.selectServerPort();
    }

    @HystrixCommand(
            commandProperties = {
                    // 熔断判断时间
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
                    // 统计时间窗口定义
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "8000"),
                    // 统计时间窗口内的最小请求数
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2"),
                    // 统计时间窗口内的错误数量百分比阈值
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
                    // 自我修复时的活动窗口长度
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "3000")
            },
            fallbackMethod = "getProductServerPortFallBack"
    )
    @GetMapping("getPort3")
    public String getProductServerPort3(){
        return productFeign.selectServerPort();
    }

    public  String getProductServerPortFallBack(){
        return "-1";
    }
}
