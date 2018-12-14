package com.alevel.springbox.cbox;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class CompanionCube {

    private static Logger log = LoggerFactory.getLogger(CompanionCube.class);

    private final Incinerator incinerator;

    @Value("${server.port}") private int port;

    @Autowired
    public CompanionCube(Incinerator incinerator) {
        this.incinerator = incinerator;
    }

    @PostConstruct
    public void init() {
        log.info("Rolling on port " + port);
    }

    @PreDestroy
    public void tearDown() {
        log.warn("Don't destroy me!!! (((");
        incinerator.burn(this);
    }

    @Override
    public String toString() {
        return "Cube from " + port;
    }
}
