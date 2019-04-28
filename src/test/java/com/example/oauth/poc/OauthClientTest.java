package com.example.oauth.poc;

import com.example.oauth.poc.configuration.FeignConfig;
import com.example.test.oauth.authorization.server.AuthorizationServerApplication;
import com.example.test.oauth.resource.application.ResourceServerApplication;
import feign.Feign;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(
        classes = {
                FeignConfig.class
        }
)
public class OauthClientTest {

    @Autowired
    private ResourcesService resourcesService;

    @BeforeClass
    public static void before() {
        ConfigurableApplicationContext authorizationServerApplicationContext =
                new SpringApplicationBuilder()
                        .sources(AuthorizationServerApplication.class)
                        .properties("spring.config.location=classpath:authorization-server/")
                        .run();

        ConfigurableApplicationContext resourceServerApplicationContext =
                new SpringApplicationBuilder()
                        .sources(ResourceServerApplication.class)
                        .properties("spring.config.location=classpath:resource-server/")
                        .run();
    }

    @Test
    public void test() throws InterruptedException {
//        Thread.sleep(1000000);
        String data = resourcesService.getAll();

    }
}
