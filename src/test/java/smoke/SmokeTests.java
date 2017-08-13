package smoke;

import java.util.Map;

import org.assertj.core.api.BDDAssertions;
import org.awaitility.Awaitility;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.BDDAssertions.then;
import static org.awaitility.Awaitility.await;

/**
 * @author Marcin Grzejszczak
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SmokeTests.Config.class,
		webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class SmokeTests {

	@Value("${application.url}") String applicationUrl;
	@Value("${stubrunner.url}") String stubRunnerUrl;

	RestTemplate restTemplate = new RestTemplate();

	@Test
	public void should_store_a_message_when_github_data_was_received_via_messaging() {
		final long countOfEntries = countGithubData(); // 1

		ResponseEntity<Map> response = triggerMessage(); // trigger
		then(response.getStatusCode().is2xxSuccessful()).isTrue();

		await().until(() -> countGithubData() > countOfEntries);  // 2
	}

	private Long countGithubData() {
		return restTemplate.getForObject("http://" + applicationUrl + "/issues/count", Long.class);
	}

	private ResponseEntity<Map> triggerMessage() {
		return restTemplate.postForEntity("http://" + stubRunnerUrl + "/triggers/issue_created_v2", "", Map.class);
	}

	@Configuration
	@EnableAutoConfiguration
	static class Config {

	}

}

