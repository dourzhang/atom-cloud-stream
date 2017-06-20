package com.watent.sink.infrastructure;

import com.watent.sink.moudel.EntityOne;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.SubscribableChannel;

/**
 * Setting autoCommitOffset false and relying on manual acking.
 */
@EnableBinding(Sink.class)
public class ManuallySink {

    private Logger logger = LoggerFactory.getLogger(getClass());

    //@StreamListener(MultiOutputSource.SAMPLE_ONE)
    public synchronized void test(EntityOne message) {

        logger.info("message : {}", message);
    }

    @StreamListener(Sink.INPUT)
    public synchronized void receive1(Message<?> message) {

        logger.info("Received message : {}", message.getPayload().toString());

        Acknowledgment acknowledgment = message.getHeaders().get(KafkaHeaders.ACKNOWLEDGMENT, Acknowledgment.class);
        if (acknowledgment == null) {
            logger.info("acknowledgment is null");
            return;
        }
        logger.info("acknowledge be invoked");
        acknowledgment.acknowledge();
    }

//    @StreamListener(MultiOutputSource.SAMPLE_TWO)
//    public synchronized void receive2(Message<?> message) {
//        Acknowledgment acknowledgment = message.getHeaders().get(KafkaHeaders.ACKNOWLEDGMENT, Acknowledgment.class);
//        if (acknowledgment == null) {
//            logger.info("acknowledgment is null");
//            return;
//        }
//        logger.info("acknowledge be invoked");
//        acknowledgment.acknowledge();
//
//        logger.info("Received message : {}", message.getPayload().toString());
//    }

    public interface MultiInputSink {

        String SAMPLE_ONE = "sample-one";

        String SAMPLE_TWO = "sample-two";

        @Input(SAMPLE_ONE)
        SubscribableChannel sampleOne();

        @Input(SAMPLE_TWO)
        SubscribableChannel sampleTwo();
    }

}