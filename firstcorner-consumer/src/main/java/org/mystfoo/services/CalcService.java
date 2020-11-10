package org.mystfoo.services;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @FeignClient: 去注册中心找一个名为name的服务
 */
@FeignClient(name = "easy-calc")
public interface CalcService {
    @GetMapping("/calc/jia")
    double jia(double a, double b);

}
