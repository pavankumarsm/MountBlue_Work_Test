package tests;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;


public class PutPatchAndDeleteExample {
    @Test
    public void testPut(){
        baseURI="https://jsonplaceholder.typicode.com";
        JSONObject request = new JSONObject();
        request.put("name","pavan");
        request.put("job","developer");

        given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
                when().
                put("/users/1").
                then().
                statusCode(200)
                .log().all();
    }
    @Test
    public void testPatch(){
        baseURI="https://jsonplaceholder.typicode.com";
        JSONObject request = new JSONObject();
        request.put("name","pavan");
        request.put("job","developer1");

        given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
                when().
                patch("/users/1").
                then().
                statusCode(200)
                .log().all();

    }
    @Test
    public void testDelete(){
        baseURI="https://jsonplaceholder.typicode.com";

                when().
                delete("/users/1").
                then().
                statusCode(200)
                .log().all();

    }

}
