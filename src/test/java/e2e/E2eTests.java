package e2e;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.BDDAssertions.then;
import static org.awaitility.Awaitility.await;

/**
 * @author Marcin Grzejszczak
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = E2eTests.Config.class,
		webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class E2eTests {

	@Value("${application.url}") String applicationUrl;
	@Value("${classpath:json/issue-created.json}") Resource json;

	RestTemplate restTemplate = new RestTemplate();

	@Test
	public void shouldStoreAMessageWhenGithubDataWasReceivedViaMessaging()
			throws IOException {
		final Integer countOfEntries = countGithubData();

		ResponseEntity<String> response = callData();
		then(response.getStatusCode().is2xxSuccessful()).isTrue();
		then(response.getBody()).isNotNull();

		await().until(() -> countGithubData() > countOfEntries);
	}

	private Integer countGithubData() {
		return this.restTemplate
				.getForObject("http://" + this.applicationUrl + "/issues/count", Integer.class);
	}

	private ResponseEntity<String> callData() throws IOException {
		return this.restTemplate.exchange(RequestEntity
				.post(URI.create("http://" +
						this.applicationUrl.replace("github-analytics", "github-webhook")))
				.contentType(MediaType.APPLICATION_JSON)
				.body(data()), String.class);
	}

	public String data() throws IOException {
		return new String(Files.readAllBytes(this.json.getFile().toPath()));
	}

	@Configuration
	@EnableAutoConfiguration
	static class Config {
	}
}
