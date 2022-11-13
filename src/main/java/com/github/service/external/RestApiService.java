package com.github.service.external;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.github.constants.MessageKeyConstant;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RestApiService {

	@NonNull
	private RestTemplate restTemplate;

	@Value("${create-repo}")
	private String createRepoURl;

	@Value("${delete-repo}")
	private String deleteRepoURl;

	@Value("${fetch-repo}")
	private String fetchAllRepoURl;

	public RestApiService() {
		this.restTemplate = new RestTemplate();
	}

	public <T> ResponseEntity<T> callRestApi(String url, String serviceName, HttpMethod methodType, Object requestBody,
			ParameterizedTypeReference<T> parameterizedTypeReference, HttpHeaders headers, Object... uriVariable) {
		HttpEntity<Object> entity = new HttpEntity<>(requestBody, headers);
		url = getUrl(serviceName);
		if (log.isTraceEnabled())
			log.trace("Making a [{}] rest template call to the service [{}] with url as [{}] and http entity ids {}",
					methodType, serviceName, url, entity);
		return restTemplate.exchange(url, methodType, entity, parameterizedTypeReference, uriVariable);
	}

	public String getUrl(String servicename) {
		switch (servicename) {
		case MessageKeyConstant.FETCH_ALL_REPOSITORY:
			return fetchAllRepoURl;
		case MessageKeyConstant.CREATE_REPOSITORY:
			return createRepoURl;
		case MessageKeyConstant.DELETE_REPOSITORY:
			return deleteRepoURl;
		default:
			break;
		}
		return null;
	}

}
