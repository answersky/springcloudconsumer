package com.answer.controller;

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

    @RequestMapping("/index")
    @ResponseBody
    public String index(){
        ServiceInstance serviceInstance=loadBalancerClient.choose("cloud-server");
        String url=serviceInstance.getUri()+"/hello?name=aaa";
        restTemplate.getForObject(url,String.class);
        return restTemplate.getForObject(url,String.class);
    }

}
