package com.example.githubanalytics;

import java.util.ArrayList;
import java.util.List;

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.mockito.BDDMockito;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * @author Marcin Grzejszczak
 */
public class BaseClass {

	@Before
	public void setup() {
		RestAssuredMockMvc.standaloneSetup(new IssuesController(service()));
	}

	private IssuesService service() {
		IssuesService service = mock(IssuesService.class);
		given(service.count()).willReturn(5L);
		given(service.list()).willReturn(issues());
		return service;
	}

	private List<IssueDto> issues() {
		List<IssueDto> dtos = new ArrayList<>();
		dtos.add(new IssueDto("foo", "spring-cloud/bar"));
		dtos.add(new IssueDto("bar", "spring-cloud/baz"));
		return dtos;
	}
}
