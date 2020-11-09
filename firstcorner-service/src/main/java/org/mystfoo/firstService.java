package org.mystfoo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/calc")
public class firstService {
    @RequestMapping(value = "/jia")
    public double jia(double a, double b) {
        return a + b;
    }

    @RequestMapping(value = "/jian")
    public double jian(double a, double b) {
        return a - b;
    }

    @RequestMapping(value = "/cheng")
    public double cheng(double a, double b) {
        return a * b;
    }

    @RequestMapping(value = "/chu")
    public double chu(double a, double b) {
        return a / b;
    }
}
