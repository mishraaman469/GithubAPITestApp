package com.github.service;

import java.util.List;

import com.github.models.Github;
import com.github.models.GithubRepository;

public interface GithubService {

	List<GithubRepository> getGithubRepository();

	GithubRepository createGithubRepository(Github github);

	boolean deleteRepository(String repoName);

}
