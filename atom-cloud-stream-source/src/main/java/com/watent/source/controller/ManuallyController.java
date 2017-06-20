package com.watent.source.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.watent.source.infrastructure.ManualSource;
import com.watent.source.moudel.EntityOne;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * Created by Atom on 2017/6/20.
 */
@RestController
public class ManuallyController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private ManualSource manualSource;

    @RequestMapping(value = "/send/{msg}", method = RequestMethod.GET)
    public void send(@PathVariable("msg") String msg) {
        manualSource.sendMessage(msg);
    }

    @RequestMapping(value = "/bean", method = RequestMethod.GET)
    public void sendBean() {

        EntityOne entityOne = new EntityOne();
        entityOne.setId(1L);
        entityOne.setName(UUID.randomUUID().toString());
        //entityOne.setDateTime(LocalDateTime.now());

        String payload;
        try {
            payload = objectMapper.writeValueAsString(entityOne);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage(), e);
            return;
        }
        logger.info("Sending value : {} ", entityOne);

        manualSource.sendMessage(payload);
    }

}
