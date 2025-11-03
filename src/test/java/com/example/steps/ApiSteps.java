package com.example.steps;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;

@Slf4j
public class ApiSteps {
    private Response response;
    private final static String Url = "https://openlibrary.org/authors/OL1A.json";

    @Given("I call the authors endpoint for OL1A")
    public void call_api() {
        log.info("Calling the Get API {}", Url);
        response = RestAssured.get(Url).then().extract().response();
        String prettyXml = response.getBody().asPrettyString();
        log.info("Response Body {}", prettyXml);
    }

    @Then("Check the response status code is {int}")
    public void verify_status(int code) {
        assertThat(response.statusCode(), is(code));
    }

    @Then("Verify the person name {string} is {string}")
    public void verify_field_equals(String field, String expected) {
        String actual = response.jsonPath().getString(field);
        assertThat(actual, is(expected));
    }

    @Then("Verify the alternate name {string} is {string}")
    public void verify_list_contains(String field, String expected) {
        List<String> list = response.jsonPath().getList(field);
        assertThat(list, hasItem(expected));
    }
}
