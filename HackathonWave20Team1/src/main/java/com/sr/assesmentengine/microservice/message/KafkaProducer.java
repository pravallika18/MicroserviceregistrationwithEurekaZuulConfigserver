package com.sr.assesmentengine.microservice.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.sr.assesmentengine.microservice.exceptions.KafkaUnavialableException;

@Service
public class KafkaProducer {
	private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);
	
	
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Autowired
	public void setKafkaTemplate(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	@Value("${topic}")
	String kafkaTopic;
	
	public void send(String message)  throws KafkaUnavialableException{
	    log.info("sending data='{}'", message);
	    try {
	    kafkaTemplate.send(kafkaTopic, message);
	    
	    }
	    catch(Exception ex) {
	    	throw new KafkaUnavialableException("Kafka is temporarily unavailable");
	    }
	}
}
