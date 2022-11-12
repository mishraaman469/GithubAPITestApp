package com.github.service.external;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RestApiService {

	@NonNull
	private RestTemplate restTemplate;

	public RestApiService() {
		this.restTemplate = new RestTemplate();
	}

	public <T> ResponseEntity<T> callRestApi(String url, String serviceName, HttpMethod methodType, Object requestBody,
			ParameterizedTypeReference<T> parameterizedTypeReference, HttpHeaders headers, Object... uriVariable) {
		HttpEntity<Object> entity = new HttpEntity<>(requestBody, headers);
		// add url based on the service name
		if (log.isTraceEnabled())
			log.trace("Making a [{}] rest template call to the service [{}] with url as [{}] and http entity ids {}",
					methodType, serviceName, url, entity);
		return restTemplate.exchange(url, methodType, entity, parameterizedTypeReference, uriVariable);
	}

}
