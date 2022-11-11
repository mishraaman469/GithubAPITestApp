package com.github.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data()
@Setter()
@Getter()
@ToString()
public class Github {

	private String name;
	private String description;
}
