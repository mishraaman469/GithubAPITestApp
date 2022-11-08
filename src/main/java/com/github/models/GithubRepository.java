package com.github.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter()
@Setter()
@Data()
public class GithubRepository {

		private String id;
		private String name;
		@JsonProperty("full_name")
		private String fullName;
		@JsonProperty("private")
		private boolean isPrivate;
		@JsonProperty("clone_url")
		private String httpUrl;
		@JsonProperty("svn_url")
		private String webUrl;
		private String language;
		@JsonProperty("default_branch")
		private String defaultBranch;
}
