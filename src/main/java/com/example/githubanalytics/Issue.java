package com.example.githubanalytics;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Marcin Grzejszczak
 */
@Entity
@Table(name = "issues")
@NoArgsConstructor
@Data
class Issue {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	private String username;
	@NotNull
	private String repo;

	Issue(String username, String repository) {
		this.username = username;
		this.repo = repository;
	}
}
