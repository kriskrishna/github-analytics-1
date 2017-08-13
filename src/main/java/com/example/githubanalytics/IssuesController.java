package com.example.githubanalytics;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Marcin Grzejszczak
 */
@RestController
@RequestMapping("/issues")
class IssuesController {

	private final IssuesService service;

	IssuesController(IssuesService service) {
		this.service = service;
	}

	@GetMapping("/count")
	long count() {
		return service.count();
	}

	@GetMapping
	List<IssueDto> issues() {
		return service.list();
	}

	@PostMapping
	void save(IssueDto issue) {
		service.save(issue.getUserName(), issue.getRepository());
	}

}
