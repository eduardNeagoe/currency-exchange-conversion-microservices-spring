package com.devmission.microservices.currencyexchange.exchange;


import com.devmission.microservices.currencyexchange.exchange.exceptions.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CurrencyExchangeService {

    private final CurrencyExchangeRepository currencyExchangeRepository;
    private final CurrencyExchangeMapper currencyExchangeMapper;
    private final Environment environment;


    public CurrencyExchangeDTO getExchange(String srcCcy, String destCcy) {
        String port = environment.getProperty("local.server.port");

        return currencyExchangeRepository.findBySrcCcyAndDestCcy(srcCcy, destCcy).map(currencyExchange -> {
                    currencyExchange.setEnvironment(port);
                    return currencyExchange;
                })
                .map(currencyExchangeMapper::map).orElseThrow(() -> new EntityNotFoundException("Currency exchange not found!"));
    }
}
