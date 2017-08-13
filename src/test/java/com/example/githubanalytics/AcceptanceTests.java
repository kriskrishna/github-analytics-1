package com.example.githubanalytics;

import org.assertj.core.api.BDDAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.StubTrigger;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.then;

/**
 * @author Marcin Grzejszczak
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GithubAnalyticsApplication.class,
		webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureStubRunner(ids = "com.example.github:github-webhook",
		repositoryRoot = "${REPO_WITH_BINARIES:http://localhost:8081/artifactory/libs-release-local}")
public class AcceptanceTests {

	@Autowired StubTrigger stubTrigger;
	@Autowired IssuesRepository repository;

	@Test
	public void should_store_a_new_issue() {
		assertThat(repository.count()).isEqualTo(0);

		stubTrigger.trigger("issue_created_v2");

		then(repository.count()).isEqualTo(1);
	}
}
