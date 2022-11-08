package com.github.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.constants.APIConstants;
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
		
	
}
