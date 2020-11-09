# corner
A corner for all the lonely

#2020-11-05
我到底想写什么？

#2020-11-06
底层考虑好

#2020-11-08
它不一定很好，但一定能更好，能一直拓展，而不是重构

#2020-11-09
初步定义
spring-cloud
corner-register-center:服务注册中心 port:11111
corner-service:服务提供者 port:3001++（服务实现，支持服务消费者）
corner-consumer:服务消费者 port:6001++ （服务负载均衡，面向应用）
一个服务注册中心，多个服务提供者，多个服务消费者，最终从服务消费者暴露服务接口


