package com.answer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author liufeng
 * 2021/11/9 9:39
 */
@FeignClient(name = "cloud-server")
public interface HelloFeignService {

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String index(@RequestParam("name") String name);
}
