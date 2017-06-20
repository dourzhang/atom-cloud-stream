package com.watent.source.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.binding.BinderAwareChannelResolver;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.router.ExpressionEvaluatingRouter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.Collections;

/**
 * a bean msg for manual receive
 * <p>
 * Created by Atom on 2017/6/20.
 */
@EnableBinding(Source.class)
public class ManualSource {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private BinderAwareChannelResolver resolver;

    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private Source source;

    public void sendMessage(String msg) {
        try {
            source.output().send(MessageBuilder.withPayload(msg).build());
            routerChannel().send(MessageBuilder.createMessage(msg,
                    new MessageHeaders(Collections.singletonMap(MessageHeaders.CONTENT_TYPE, "application/json"))));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Bean(name = "routerChannel")
    public MessageChannel routerChannel() {
        return new DirectChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "routerChannel")
    public ExpressionEvaluatingRouter router() {
        ExpressionEvaluatingRouter router =
                new ExpressionEvaluatingRouter(new SpelExpressionParser().parseExpression("payload.id"));

        // ExpressionEvaluatingRouter router = new ExpressionEvaluatingRouter(null);
        //router.setDefaultOutputChannelName("default-output");
        router.setDefaultOutputChannelName("sample-one");
        router.setChannelResolver(resolver);
        return router;
    }

}
