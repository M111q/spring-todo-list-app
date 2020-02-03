package com.github.m111q.hello.lang;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
class LangApi {

    private final Logger logger = LoggerFactory.getLogger(LangApi.class);
    private LangService service;

    LangApi(LangService service) {
        this.service = service;
    }

    @GetMapping("/langs")
    ResponseEntity<List<LangDTO>> findAllLangs() {
        logger.info("Request got");
        return ResponseEntity.ok(service.findAll());

    }
}
