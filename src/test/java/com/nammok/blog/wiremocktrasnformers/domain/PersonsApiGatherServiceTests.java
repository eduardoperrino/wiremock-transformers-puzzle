package com.nammok.blog.wiremocktrasnformers.domain;

import com.github.tomakehurst.wiremock.core.Options;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import com.nammok.blog.wiremocktrasnformers.domain.data.PersonCollection;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.cloud.contract.wiremock.WireMockSpring;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(stubs="classpath:/stubs")
@Slf4j
@NoArgsConstructor
public class PersonsApiGatherServiceTests {

    @TestConfiguration
    static class WireMockTestContextConfiguration {
        @Bean
        Options options() {
            return WireMockSpring.options().extensions(new ResponseTemplateTransformer(false));
        }
    }

    @Autowired
    private PersonsApiGatherService personsApiGatherService;

    @Test
    public void givenSchool_whenGetPersons_thenPersonsAreReturned() {
        PersonCollection personCollection =
                personsApiGatherService.getPersons("Santa-Catalina").orElse(null);
        assertThat(personCollection, notNullValue());
    }
}
