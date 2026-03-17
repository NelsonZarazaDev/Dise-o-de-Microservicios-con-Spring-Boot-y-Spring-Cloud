package com.microservicios.report_listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReportListenerApplication {
//CMD -> CONFIG
//docker exec -it msg-broker bash
//kafka-topics --bootstrap-server msg-broker:9092 --create --topic test --partitions 5 --replication-factor 1


//	CONSUMER
//	kafka-console-consumer --bootstrap-server msg-broker:9092 --topic test --from-beginning
//	PRODUCER
//	kafka-console-producer --bootstrap-server msg-broker:9092 --topic test


//	kafka-topics --list --bootstrap-server msg-broker:9092
//	lista los topics

	public static void main(String[] args) {
		SpringApplication.run(ReportListenerApplication.class, args);
	}

}
