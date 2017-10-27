package com.sr.assesmentengine.microservice.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.sr.assesmentengine.microservice.domain.Model;
import com.sr.assesmentengine.microservice.exceptions.KafkaUnavialableException;




@Service
public class KafkaConsumer {
	private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

	
	Model model;
	
	@Autowired
	public void setModel(Model model) {
		this.model = model;
	}

	@Value("${topic}")
	String kafkaTopic;
	
	@KafkaListener(topics="${topic}")
	
    public void processMessage(String content) {
		log.info("received content = '{}'", content + " For Topic : " + kafkaTopic);
		model.setValue(content);
		//System.out.println(content);
    }
}
