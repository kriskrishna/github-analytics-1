package com.example.githubanalytics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.metrics.export.prometheus.EnablePrometheusMetrics;

@SpringBootApplication
@EnableBinding(Sink.class)
@EnablePrometheusMetrics
public class GithubAnalyticsApplication {

	public static void main(String[] args) {
		SpringApplication.run(GithubAnalyticsApplication.class, args);
	}
}
