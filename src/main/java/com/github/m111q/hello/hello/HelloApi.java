package com.github.m111q.hello.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
class HelloApi {
    private static final String REQ_PARAM_NAME = "name";
    private static final String REQ_PARAM_LANG = "lang";
    private final Logger logger = LoggerFactory.getLogger(HelloApi.class);

    private HelloService service;

    @Autowired
    HelloApi(HelloService service) {
        this.service = service;
    }

    @GetMapping
    ResponseEntity<String> getGreeting(@RequestParam Optional<String> name, @RequestParam Optional<String> lang) {

        Integer langParam = null;

        String nameParam = name.orElseGet(() -> null);

        if (lang.isPresent()){
            try {
                langParam = Integer.valueOf(lang.get());
            } catch (NumberFormatException e) {
                logger.warn("wrong langParam Id format (non numeric) " + lang.get());
            }
        }

        logger.info("Request got, parameters: nameParam = " + nameParam + " langParam = " + langParam);

        return ResponseEntity.ok(service.prepareGreeting(nameParam, langParam));
    }
}
