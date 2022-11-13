package com.github.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.github.constants.APIConstants;
import com.github.models.Github;
import com.github.models.GithubRepository;
import com.github.service.external.RestApiService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class GithubServiceImpl implements GithubService {
	@Value("${accessToken}")
	private String accessToken;

	@NonNull
	private RestApiService restAPIService;

	private static final String AUTHORIZATION = "Authorization";

	@Override
	public List<GithubRepository> getGithubRepository() {
		HttpHeaders headers = getHeaders();
		ResponseEntity<List<GithubRepository>> repos = this.restAPIService.callRestApi(null,
				"fetch-all-repository", HttpMethod.GET, null, new ParameterizedTypeReference<List<GithubRepository>>() {
				}, headers);
		return repos.getBody();
	}

	@Override
	public GithubRepository createGithubRepository(Github github) {
		HttpHeaders headers = getHeaders();
		ResponseEntity<GithubRepository> repos = this.restAPIService.callRestApi(null,
				"create-repository", HttpMethod.POST, github, new ParameterizedTypeReference<GithubRepository>() {
				}, headers);
		return repos.getBody();
	}

	@Override
	public boolean deleteRepository(String repoName) {
		ResponseEntity<String> repos = this.restAPIService.callRestApi(null,
				"delete-repository", HttpMethod.DELETE, null, new ParameterizedTypeReference<String>() {
				}, getHeaders(), repoName);
		return repos.getStatusCode() == HttpStatus.NO_CONTENT;

	}

	private HttpHeaders getHeaders() {
		log.info("Adding configuration to the header to access the account");
		HttpHeaders headers = new HttpHeaders();
		headers.set(AUTHORIZATION, accessToken);
		return headers;
	}

}
