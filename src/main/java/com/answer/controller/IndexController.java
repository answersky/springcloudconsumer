package com.answer.controller;

import com.answer.feign.HelloFeignService;
import com.answer.service.ValueAnnotateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.hypermedia.ServiceInstanceProvider;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

/**
 * created by liufeng
 * 2020/7/14
 */
@Controller
public class IndexController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private HelloFeignService helloFeignService;


    @RequestMapping("/index")
    @ResponseBody
    public String index(){
        //RestTemplate在微服务当中与注解@LoadBanlance注解结合使用
        ServiceInstance serviceInstance=loadBalancerClient.choose("cloud-server");
        String url=serviceInstance.getUri()+"/hello?name=aaa";
        restTemplate.getForObject(url,String.class);
        return restTemplate.getForObject(url,String.class);
    }

    //通过feign方式调用
    @RequestMapping("/findByFeign")
    @ResponseBody
    public String findByFeign(String name){
        return helloFeignService.index(name);
    }

}
