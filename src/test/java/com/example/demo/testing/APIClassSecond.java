package com.example.demo.testing;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class APIClassSecond {

    @BeforeEach
    public void before()
    {
        RestAssured.baseURI="http://localhost:9090";
    }
    //@Test
    public void getStudent()
    {
        System.out.println("Testing");
        RestAssured.given().contentType("application/json").when().get("/student/list").then().log().all();
    }

    @Test
    public void getAllStudent()
    {

        RequestSpecification reqSep=RestAssured.given();
        Response  response= reqSep.contentType("application/json").when().get("/student/list");
        ValidatableResponse vr= response.then();

       vr.log().all();
       vr.statusCode(200);
       String data= vr.extract().path("[3].lastName");
       System.out.println(data);
    }
}
