package com.nammok.blog.wiremocktrasnformers.domain;

import com.github.tomakehurst.wiremock.core.Options;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import com.nammok.blog.wiremocktrasnformers.domain.data.PersonCollection;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.cloud.contract.wiremock.WireMockSpring;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(JUnitParamsRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(stubs="classpath:/stubs")
@Slf4j
@NoArgsConstructor
public class PersonsApiGatherServiceTestsWithJParams {

    @TestConfiguration
    static class WireMockTestContextConfiguration {
        @Bean
        Options options() {
            return WireMockSpring.options().extensions(new ResponseTemplateTransformer(false));
        }
    }

    @Autowired
    private PersonsApiGatherService personsApiGatherService;

    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Test
    @Parameters({"Santa-Catalina"})
    public void givenSchool_whenGetPersons_thenPersonsAreReturned(String school) {
        PersonCollection personCollection =
                personsApiGatherService.getPersons(school).orElse(null);
        assertThat(personCollection, notNullValue());
        assertThat(personCollection.getMetadata(), notNullValue());
        assertThat(personCollection.getMetadata().get(PersonCollection.Metadata.SCHOOL.toString()), equalToIgnoringCase(school));
        assertThat(personCollection.getMetadata().get(PersonCollection.Metadata.DATE.toString()), notNullValue());

    }
}
