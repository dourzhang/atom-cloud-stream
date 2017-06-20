package com.watent.source.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;

/**
 *  send a simple String msg every ten seconds
 */
//@EnableBinding(Source.class)
public class GreetingSource {

    Logger logger = LoggerFactory.getLogger(getClass());

    private long count = 0;

    @InboundChannelAdapter(value = Source.OUTPUT, poller = @Poller(fixedDelay = "10000"))
    public String greet() {
        logger.info("greet count : {}", ++count);
        return "hello world " + count;
    }
}