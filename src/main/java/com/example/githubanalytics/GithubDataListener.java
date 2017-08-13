package com.example.githubanalytics;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

/**
 * @author Marcin Grzejszczak
 */
@Component
@Slf4j
class GithubDataListener {

	private final IssuesService service;

	GithubDataListener(IssuesService service) {
		this.service = service;
	}

	@StreamListener(Sink.INPUT)
	void listen(GithubDatum githubDatum) {
		service.save(githubDatum.getUsername(), githubDatum.getRepository());
	}
}
