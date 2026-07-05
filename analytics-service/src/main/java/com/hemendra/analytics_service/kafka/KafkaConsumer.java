package com.hemendra.analytics_service.kafka;

import com.google.protobuf.InvalidProtocolBufferException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import patient.events.PatientEvent;


@Service
public class KafkaConsumer {


    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics ="patient", groupId = "analytics-service-test")
    public void consumerEvent(byte[] event) {


        try {
            PatientEvent patientEvent = PatientEvent.parseFrom(event);
            log.info("PatientEvent is received with patient id : {}, patient name: {}",patientEvent.getPatientId(),patientEvent.getName());
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }
    }
}
