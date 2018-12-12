package com.nammok.blog.wiremocktrasnformers.domain;

import com.nammok.blog.wiremocktrasnformers.domain.data.PersonCollection;
import io.vavr.control.Try;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;

@Service
@Slf4j
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class PersonsApiGatherService {

    @Autowired
    private RestTemplate restTemplate;
    @Value("${persons-api.endpoint}")
    private URL personsApiEndpoint;

    public Optional<PersonCollection> getPersons(@NotNull String school) {
        String url = personsApiEndpoint + "?school=" + school;
        return Try.of(() -> Optional.ofNullable(restTemplate.getForObject(url,
                PersonCollection.class))).getOrElse(Optional.empty());
    }

}
