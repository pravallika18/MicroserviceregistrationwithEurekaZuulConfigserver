package com.sr.assesmentengine.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class HackathonWave20Team1Application {

	public static void main(String[] args) {
		SpringApplication.run(HackathonWave20Team1Application.class, args);
	}
}
