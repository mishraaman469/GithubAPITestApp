package com.github.constants;

public class APIConstants {
	private APIConstants() {}
	public static final String BASE_PATH = "/repository";
	public static final String GET_ALL_REPOSITORY = "/list-repo";
	public static final String CREATE_REPOSITORY = "/create";
	public static final String DELETE_REPOSITORY = "/delete";
	public static final String GITHUB_CREATE_REPOSITORY = "https://api.github.com/user/repos";
	public static final String GITHUB_REPOSITORY = "https://api.github.com/users/mishraaman469/repos";
	public static final String GITHUB_DELETE_REPOSITORY = "https://api.github.com/repos/mishraaman469/{repoName}";
	
}
