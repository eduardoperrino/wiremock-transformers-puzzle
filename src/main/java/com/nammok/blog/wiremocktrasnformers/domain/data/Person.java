package com.nammok.blog.wiremocktrasnformers.domain.data;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class Person {

    private String name;
    private Integer age;
    private String school;

}
