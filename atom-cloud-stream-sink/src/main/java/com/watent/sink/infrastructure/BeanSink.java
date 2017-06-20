package com.watent.sink.infrastructure;

import com.watent.sink.moudel.Foo;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.SubscribableChannel;

/**
 * bean
 * <p>
 * /**
 * receive a bean json msg
 */
//@EnableBinding(BeanSink.Sink.class)
public class BeanSink {

    @StreamListener(Sink.SAMPLE)
    public void receive(Foo fooMessage) {

        System.out.println("******************");
        System.out.println("Received transformed message " + fooMessage.getValue() + " of type " + fooMessage.getClass());
    }

    public interface Sink {
        String SAMPLE = "sample-bean";

        @Input(SAMPLE)
        SubscribableChannel sampleSink();
    }
}
