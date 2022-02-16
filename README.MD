## This is a microservice-based application built with Spring Boot and Spring Cloud. 

### Detailed tech stack:
- Spring Cloud Netflix Eureka Server/Client for service discovery and for balancing the load among the service instances
- Spring Cloud OpenFeign for inter-service communication and load balancing - between the currency and exchange services
- Spring Cloud Config Server for reading configuration profiles stored remotely in a git repository
- Spring Cloud Gateway for routing incoming requests to the appropriate services
- Resilience4j for mechanisms like retry, circuit breaker, rate limiter, bulkhead 
- Spring Data JPA
- H2 Database
- Lombok
- Mapstruct

## Start-up order:
1. Naming Server
2. Spring Cloud Config Server
3. Any

### Naming Server - Eureka
- http://localhost:8761/

### Limits Service
- http://localhost:8080/limits

### API Gateway
- http://localhost:8765/exchange/from/USD/to/EUR
- http://localhost:8765/conversion/from/USD/to/EUR/quantity/10
- http://localhost:8765/conversion-new/from/USD/to/EUR/quantity/10

### Currency Exchange Service - bypassing gateway
- http://localhost:8000/exchange/from/USD/to/EUR

### Currency Conversion Service - bypassing gateway
- http://localhost:8100/conversion/from/USD/to/EUR/quantity/10

### Fault tolerance - Resilience4j:
- http://localhost:8000/sample-api-retry
- http://localhost:8000/sample-api-circuit-breaker - must send multiple requests per second to see effect
- http://localhost:8000/sample-api-rate-limiter - must send multiple requests per second to see effect
- http://localhost:8000/sample-api-bulkhead - must send multiple requests per second to see effect

## Architecture overview
![cloud-config-server](./cloud-config-server.png)
![Naming Server](./naming-server.png)
![load-balancer](./load-balancer.png)


##Ports
![load-balancer](./ports.png)

