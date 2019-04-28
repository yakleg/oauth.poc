package com.example.oauth.poc;

import feign.Headers;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;

@FeignClient
public interface ResourcesService {
    @RequestLine("GET /resources")
    @Headers("Accept: " + MediaType.APPLICATION_JSON_VALUE)
    String getAll();
}
