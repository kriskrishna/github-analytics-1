package com.example.githubanalytics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Marcin Grzejszczak
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GithubDatum {
	private String username;
	private String repository;
}
