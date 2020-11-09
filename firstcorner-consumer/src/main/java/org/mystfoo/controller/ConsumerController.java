package org.mystfoo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/calc-cs")
public class ConsumerController {
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/jia")
    public double jia(double a, double b) {
        //第一种调用方法
//        double forObject = new RestTemplate().getForObject("http://EASY-CALC/calc/jia?a="+a+"&b="+b,Double.class);

        //第二种调用方法
        //根据服务名获取服务列表，根据算法选取某个服务，并访问这个服务的网络位置
        ServiceInstance serviceInstance=loadBalancerClient.choose("EASY-CALC");
        double forObject=new RestTemplate().getForObject("http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/calc/jia?a="+a+"&b="+b,Double.class);

        //第三种调用方法 需要restTemplate注入的方式
//        double forObject = restTemplate.getForObject("http://EASY-CALC/calc/jia?a="+a+"&b="+b,Double.class);
        return forObject;
    }
}
