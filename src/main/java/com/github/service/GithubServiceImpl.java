package com.github.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.github.constants.APIConstants;
import com.github.models.Github;
import com.github.models.GithubRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service()
@RequiredArgsConstructor()
@Slf4j
public class GithubServiceImpl implements GithubService {

	@NonNull()
	private RestTemplate restTemplate;

	@Value("${accessToken}")
	private String accessToken;

	private static final String AUTHORIZATION = "Authorization";

	@Override
	public List<GithubRepository> getGithubRepository() {
		log.info("Adding configuration to the header to access the account");
		HttpHeaders headers = new HttpHeaders();
		headers.set(AUTHORIZATION, accessToken);
		HttpEntity<String> entity = new HttpEntity<>(headers);
		log.info("Making an external api call to the github");
		ResponseEntity<List<GithubRepository>> repos = restTemplate.exchange(APIConstants.GITHUB_REPOSITORY,
				HttpMethod.GET, entity, new ParameterizedTypeReference<List<GithubRepository>>() {
				});
		return repos.getBody();
	}

	@Override
	public GithubRepository createGithubRepository(Github github) {
		log.info("Adding configuration to the header to access the account");
		HttpHeaders headers = new HttpHeaders();
		headers.set(AUTHORIZATION, accessToken);
		HttpEntity<Github> entity = new HttpEntity<>(github, headers);
		log.info("Making an external api call to the github");
		ResponseEntity<GithubRepository> repos = restTemplate.exchange(APIConstants.GITHUB_CREATE_REPOSITORY,
				HttpMethod.POST, entity, new ParameterizedTypeReference<GithubRepository>() {
				});
		return repos.getBody();
	}

	@Override
	public boolean deleteRepository(String repoName) {
		log.info("Adding configuration to the header to access the account");
		HttpHeaders headers = new HttpHeaders();
		headers.set(AUTHORIZATION, accessToken);
		HttpEntity<String> entity = new HttpEntity<>(headers);
		log.info("Making an external api call to the github");
		ResponseEntity<String> repos = restTemplate.exchange(APIConstants.GITHUB_DELETE_REPOSITORY,
				HttpMethod.DELETE, entity, new ParameterizedTypeReference<String>() {
				},repoName);
		return repos.getStatusCode()==HttpStatus.NO_CONTENT;
		
	}

}
