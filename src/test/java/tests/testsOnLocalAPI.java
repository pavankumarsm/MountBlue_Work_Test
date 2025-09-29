package tests;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class testsOnLocalAPI {

    @Test
    public void get(){
        baseURI = "http://localhost:3000";
        given().get("/users").then().statusCode(200).log().all();

    }

    //@Test
    public void post(){
        JSONObject req = new JSONObject();
//        req.put("id",11);
        req.put("firstName","Akash");
        req.put("lastName","Kumar");
        req.put("subjectId",2);

        baseURI= "http://localhost:3000";
        given().contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(req.toJSONString()).
                when().
                post("/users").then().statusCode(201).log().all();


    }
    //@Test
    public void put(){
        JSONObject req = new JSONObject();
        req.put("firstName","Pavan");
        req.put("lastName","Kumar");
        req.put("subjectId",2);

        baseURI= "http://localhost:3000";
        given().contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(req.toJSONString()).
                when().
                put("/users/7").then().statusCode(200).log().all();
    }

   // @Test
    public void patch(){
        JSONObject req = new JSONObject();

        req.put("lastName","ram");


        baseURI= "http://localhost:3000";
        given().contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(req.toJSONString()).
                when().
                patch("/users/7").then().statusCode(200).log().all();
    }

    @Test
    public void delete(){

        baseURI= "http://localhost:3000";

                when().
                delete("/users/4229").then().statusCode(200).log().all();
    }



}
