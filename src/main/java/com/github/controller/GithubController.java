package com.github.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.constants.APIConstants;
import com.github.models.Github;
import com.github.models.GithubRepository;
import com.github.service.GithubService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController()
@RequestMapping(APIConstants.BASE_PATH)
@RequiredArgsConstructor()
@Slf4j()
public class GithubController {

	@NonNull()
	private GithubService githubService;

	@GetMapping(APIConstants.GET_ALL_REPOSITORY)
	public List<GithubRepository> getGithubRepository() {
		log.info("Fetching all the repository present in github");
		return this.githubService.getGithubRepository();
	}

	@PostMapping(APIConstants.CREATE_REPOSITORY)
	public GithubRepository createRepository(@RequestBody() Github github) {
		log.info("Creating  the repository in github with name:-" + github.getName());
		return this.githubService.createGithubRepository(github);
	}

	@DeleteMapping(APIConstants.DELETE_REPOSITORY)
	public boolean deleteRepository(@RequestParam() String repoName) {
		log.info("Deleting the repository in github with name:-" + repoName);
		return this.githubService.deleteRepository(repoName);
	}

}
