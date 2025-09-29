package tests;

import io.restassured.http.ContentType;
import io.restassured.internal.mapping.JsonbMapper;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class GetAndPostExamples {

    @Test
    public void testGet(){

        baseURI="https://reqres.in/api";
        given().get("/users?page=2").then()
                .statusCode(200).
                body("data[2].first_name",equalTo("Tobias")).
                body("data.first_name",hasItems("Tobias","Byron"));
     }

     @Test
    public void testPost(){
        Map<String, Object> map = new HashMap<String,Object>();
//        map.put("name","pavan");
//        map.put("job","Prompt Engineer");

         baseURI="https://jsonplaceholder.typicode.com";
         JSONObject request = new JSONObject();
         request.put("name","pavan");
         request.put("username","Pavan");

         System.out.println(request.toJSONString());


         given().
                 header("Content-Type", "application/json").
                 contentType(ContentType.JSON).
                 accept(ContentType.JSON).
                 body(request.toJSONString()).
            when().
                 post("/users").
            then().
                 statusCode(201)
                 .log().all();

     }



















}
