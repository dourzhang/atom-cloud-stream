package com.watent.source.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;

/**
 *  send a json msg every five seconds
 */
//@EnableBinding(BeanSource.Source.class)
public class BeanSource {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private Long count = 0L;

    @Bean
    @InboundChannelAdapter(value = Source.SAMPLE, poller = @Poller(fixedDelay = "5000", maxMessagesPerPoll = "1"))
    public MessageSource<String> timerMessageSource() {
        return new MessageSource<String>() {
            public Message<String> receive() {
                String value = "{\"value\":\"" + ++count + "\"}";
                logger.info("value:{}", value);
                return MessageBuilder.withPayload((value)).setHeader(MessageHeaders.CONTENT_TYPE, "application/json").build();
            }
        };
    }

    public interface Source {

        String SAMPLE = "sample-bean";

        @Output(SAMPLE)
        MessageChannel sampleSource();
    }
}