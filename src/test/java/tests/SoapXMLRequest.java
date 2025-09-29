package tests;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasXPath;

import io.restassured.http.ContentType;
import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SoapXMLRequest {

    @Test
    public void validateSoapXML() throws IOException {

        File file = new File("./SoapRequest/Add.xml");

        FileInputStream fileInputStream = new FileInputStream(file);
        String requestBody = IOUtils.toString(fileInputStream,"UTF-8");

       baseURI =  "http://www.dneonline.com/";
       given().contentType("text/xml; charset=utf-8")
                .accept(ContentType.XML)
                .header("SOAPAction", "http://tempuri.org/Add")
                .body(requestBody).
               when().
               post("/calculator.asmx").
               then().
               statusCode(200).log().all()
               .and().body(hasXPath("//*[local-name()='AddResult']", equalTo("15")));;

    }

}
