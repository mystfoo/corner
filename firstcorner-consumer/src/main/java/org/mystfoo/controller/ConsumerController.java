package org.mystfoo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.mystfoo.beans.TwoNum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Api(value = "简单计算服务消费", tags = "服务消费者")
@RestController
@RequestMapping("/calc-cs")
public class ConsumerController {
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private RestTemplate restTemplate;
//    @Autowired
//    private CalcService calcService;

    /**
     * @ApiImplicitParams：用在请求的方法上，表示一组参数说明
     * @ApiImplicitParam：用在@ApiImplicitParams注解中，指定一个请求参数的各个方面 name：参数名
     * value：参数的汉字说明、解释
     * required：参数是否必须传，默认false
     * paramType：参数放在哪个地方，查询参数类型，这里有几种形式：
     * header --> 请求参数的获取：@RequestHeader，参数在request headers 里边提交
     * query --> 请求参数的获取：@RequestParam，直接跟参数，完成自动映射赋值
     * path（用于restful接口）--> 请求参数的获取：@PathVariable，以地址的形式提交数据
     * body（不常用）--> 以流的形式提交 仅支持POST
     * form（不常用）--> 以form表单的形式提交 仅支持POST
     * dataType：参数类型，默认String，其它值dataType="Integer"
     * defaultValue：参数的默认值
     */
    @ApiOperation(value = "加法", notes = "计算：加")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "a", value = "加数a", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "b", value = "加数b", dataType = "String", paramType = "query")
    })
    @GetMapping("/jia")
    public double jia(String a, String b) {
        //第一种调用方法
//        double forObject = new RestTemplate().getForObject("http://EASY-CALC/calc/jia?a="+a+"&b="+b,Double.class);

        //第二种调用方法
        //根据服务名获取服务列表，根据算法选取某个服务，并访问这个服务的网络位置
        ServiceInstance serviceInstance = loadBalancerClient.choose("easy-calc");
        double forObject = new RestTemplate().getForObject("http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/calc/jia?a=" + a + "&b=" + b, Double.class);

        //第三种调用方法 需要restTemplate注入的方式
//        double forObject = restTemplate.getForObject("http://EASY-CALC/calc/jia?a="+a+"&b="+b,Double.class);

        return forObject;

//        return calcService.jia(a, b);
    }

    @ApiOperation(value = "减法", notes = "计算：减")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "a", value = "减数a", dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "b", value = "被减数b", dataType = "String", paramType = "path")
    })
    @GetMapping("/jian/{a}/{b}")
    public double jian(@PathVariable String a, @PathVariable String b) {

        ServiceInstance serviceInstance = loadBalancerClient.choose("easy-calc");
        double forObject = new RestTemplate().getForObject("http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/calc/jian?a=" + a + "&b=" + b, Double.class);

        return forObject;
    }

    @ApiOperation(value = "乘法", notes = "计算：乘")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "a", value = "乘数a", dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "b", value = "乘数b", dataType = "String", paramType = "header")
    })
    @GetMapping("/cheng")
    public double cheng(@RequestHeader String a, @RequestHeader String b) {

        ServiceInstance serviceInstance = loadBalancerClient.choose("easy-calc");
        double forObject = new RestTemplate().getForObject("http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/calc/cheng?a=" + a + "&b=" + b, Double.class);

        return forObject;
    }

    @ApiOperation(value = "除法", notes = "计算：除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "test", value = "除数a和b", dataType = "TwoNum", paramType = "body"),
    })
    @PostMapping("/chu")
    public double chu(@RequestBody TwoNum test) {

        double a = test.getA();
        double b = test.getB();
        ServiceInstance serviceInstance = loadBalancerClient.choose("easy-calc");
        double forObject = new RestTemplate().getForObject("http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/calc/chu?a=" + a + "&b=" + b, Double.class);

        return forObject;
    }

}

