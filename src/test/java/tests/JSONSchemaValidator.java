package tests;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.hasItems;

public class JSONSchemaValidator {

    @Test
    public void testGet(){

        baseURI="https://jsonplaceholder.typicode.com";
        given().
                get("/posts").
                then().
                assertThat().body(matchesJsonSchemaInClasspath("schema.json")).
                statusCode(200);
    }
}
