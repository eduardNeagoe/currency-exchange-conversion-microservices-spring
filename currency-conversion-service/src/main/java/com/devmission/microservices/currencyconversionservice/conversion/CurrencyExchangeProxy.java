package com.devmission.microservices.currencyconversionservice.conversion;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "currency-exchange", url = "localhost:8000") // no load balancing, it directly references the instance using host:port
// with load balancing through Eureka (notice no host:port is referenced directly)
@FeignClient(name = "currency-exchange")
public interface CurrencyExchangeProxy {

    @GetMapping("/exchange/from/{srcCcy}/to/{destCcy}")
    ResponseEntity<CurrencyConversionDTO> getExchange(@PathVariable String srcCcy, @PathVariable String destCcy);

}
