package tests;

import io.restassured.http.ContentType;
import io.restassured.matcher.RestAssuredMatchers;
import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasXPath;

public class XMLSchemaValidation {

    @Test
    public void schemaValidation() throws IOException {

        // Load SOAP request
        File file = new File("./SoapRequest/Add.xml");
        String requestBody = IOUtils.toString(new FileInputStream(file), "UTF-8");

        // Set base URI
        baseURI = "http://www.dneonline.com";

        given()
                .contentType("text/xml; charset=utf-8")
                .accept(ContentType.XML)
                .header("SOAPAction", "http://tempuri.org/Add")
                .body(requestBody)
                .when()
                .post("/calculator.asmx")
                .then()
                .statusCode(200)
                .log().all()
                .and()
                .body(hasXPath("//*[local-name()='AddResult']", equalTo("15")))
                .and()
                .assertThat()
                .body(RestAssuredMatchers.matchesXsdInClasspath("Calculator.xsd")); // XSD from classpath
    }
}
