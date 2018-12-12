package com.nammok.blog.wiremocktrasnformers.domain.data;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Data
@Slf4j
public class PersonCollection {

    private Map<String, String> metadata;
    private Collection<Person> persons;

    public enum Metadata {
        SCHOOL("school"),
        DATE("date");

        private final String name;

        Metadata(String s) {
            name = s;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    {
        metadata = new HashMap<>();
        persons = new ArrayList<>();
    }

}
