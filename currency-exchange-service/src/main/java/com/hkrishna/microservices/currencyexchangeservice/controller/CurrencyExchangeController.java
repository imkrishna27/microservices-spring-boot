package com.hkrishna.microservices.currencyexchangeservice.controller;

import com.hkrishna.microservices.currencyexchangeservice.bean.CurrencyExchange;
import com.hkrishna.microservices.currencyexchangeservice.repository.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CurrencyExchangeController {

    private final Environment environment;
    private final CurrencyExchangeRepository currencyExchangeRepository;

    @Autowired
    public CurrencyExchangeController(Environment environment, CurrencyExchangeRepository currencyExchangeRepository) {
        this.environment = environment;
        this.currencyExchangeRepository = currencyExchangeRepository;
    }

    @PostMapping("currency-exchange")
    public void save() {
        ArrayList<CurrencyExchange> currencyExchanges = new ArrayList<>();
        currencyExchanges.add(new CurrencyExchange(null,"USD","INR",new BigDecimal(87),""));
        currencyExchanges.add(new CurrencyExchange(null,"USD","GHJ",new BigDecimal(67),""));
        currencyExchanges.add(new CurrencyExchange(null,"USD","RUS",new BigDecimal(55),""));
        currencyExchanges.add(new CurrencyExchange(null,"USD","NPR",new BigDecimal(45),""));
        currencyExchangeRepository.saveAll(currencyExchanges);
    }
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange getExchangeValue(@PathVariable String from, @PathVariable String to) {
        Optional<CurrencyExchange> byFromAndTo = currencyExchangeRepository.findByFromAndTo(from, to);
        if(byFromAndTo.isPresent()) {
            byFromAndTo.get().setEnvironment(environment.getProperty("local.server.port"));
            return byFromAndTo.get();
        } else {
            throw new RuntimeException("No Data Found..");
        }
    }

}
