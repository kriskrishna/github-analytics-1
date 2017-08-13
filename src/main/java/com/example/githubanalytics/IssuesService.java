package com.example.githubanalytics;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.metrics.instrument.MeterRegistry;
import org.springframework.stereotype.Service;

/**
 * @author Marcin Grzejszczak
 */
@Service
@Slf4j
class IssuesService {

	private final IssuesRepository repository;

	IssuesService(IssuesRepository repository, MeterRegistry meterRegistry) {
		this.repository = repository;
		meterRegistry.gauge("issues", this, IssuesService::count);
	}

	void save(String user, String repo) {
		log.info("Saving user [{}] and repo [{}]", user, repo);
		repository.save(new Issue(user, repo));
	}

	long count() {
		return repository.count();
	}

	void deleteAll() {
		repository.deleteAll();
	}

	List<IssueDto> list() {
		List<IssueDto> issues = new ArrayList<>();
		repository.findAll().forEach(i ->
			issues.add(new IssueDto(i.getUsername(), i.getRepo())));
		return issues;
	}
}
