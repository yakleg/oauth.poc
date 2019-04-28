package com.example.oauth.poc.configuration;

import com.example.oauth.poc.ResourcesService;
import feign.Feign;
import feign.RequestInterceptor;
import feign.okhttp.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import java.util.Collections;

@Configuration
public class FeignConfig {

    @Bean
    public OAuth2RestTemplate oAuth2RestTemplate() {
        ClientCredentialsResourceDetails details = new ClientCredentialsResourceDetails();
        details.setClientId("myClientID");
        details.setClientSecret("SECRET");

        details.setAccessTokenUri("http://localhost:8082/oauth/token");
        details.setScope(Collections.singletonList("all"));

        return new OAuth2RestTemplate(details);
    }

    @Bean
    public RequestInterceptor oauthRequestInterceptor(OAuth2RestTemplate auth2RestTemplate) {
        return template -> {
            OAuth2AccessToken accessToken = auth2RestTemplate.getAccessToken();
            String token = String.format("%s %s", accessToken.getTokenType(), accessToken.getValue());
            template.header(HttpHeaders.AUTHORIZATION, token);
        };
    }

    @Bean
    public ResourcesService resourcesService(RequestInterceptor oauthRequestInterceptor) {
        return Feign.builder()
                .client(new OkHttpClient())
                .requestInterceptor(oauthRequestInterceptor)
                .target(ResourcesService.class, "http://localhost:8080");
    }
}
