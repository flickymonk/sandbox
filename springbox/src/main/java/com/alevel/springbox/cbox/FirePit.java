package com.alevel.springbox.cbox;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FirePit implements Incinerator {

    private static final Logger log = LoggerFactory.getLogger(FirePit.class);

    @Override
    public void burn(Object anything) {
        log.warn("{} has been thrown down the fire pit", anything);
    }
}
