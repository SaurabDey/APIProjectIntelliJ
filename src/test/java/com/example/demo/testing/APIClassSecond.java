package com.example.demo.testing;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static org.hamcrest.Matchers.*;

import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

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

    //@Test
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

    //@Test
    public void extractStudent() {
        Response  response=
                RestAssured.given().contentType("application/json").when().get("/student/list");

        String data= response.then().extract().path("email[2]");
        System.out.println(data);

        List<String> cour=  response.then().extract().path("courses[2]");
        System.out.println(cour);

        int si= response.then().extract().path("courses[2].size()");
        System.out.println(si);
    }

    //@Test
    public void assertStudent() {
        Response response =
                RestAssured.given().contentType("application/json").when().get("/student/list");

        String coimingdata = response.then().extract().path("email[2]");
        System.out.println(coimingdata);
//Junit
        Assertions.assertEquals("tincidunt.dui@ultricessit.co.uk", coimingdata,"All good");

//TestNG
        Assert.assertEquals(coimingdata, "tincidunt.dui@ultricessit.co.uk");

//Hamcrest
     //Hard
        /*response.then()
                .body("email[2]", Matchers.equalTo("tincidunt.dui@ultricessit.co.uk"))
                .body("programme[2]", Matchers.equalToIgnoringCase("computer science xxx"))
                .body("lastName[2]", Matchers.equalToIgnoringCase("Jason"))
                .body("courses[2]", Matchers.hasItem("Algorithms xxx"));*/

      //Soft
          response.then()
                 .body("email[2]", equalTo("tincidunt.dui@ultricessit.co.uk"),
                         "programme[2]", equalToIgnoringCase("computer science xxx"),
                                    "lastName[2]", equalToIgnoringCase("Jason"),
                                         "courses[2]", hasItem("Algorithms xxx")
                 );

    }

    @Test
    public void JsonAssertStudent() throws IOException, JSONException {
        Response response =
                RestAssured.given().queryParam("programme","Computer Science").queryParam("limit","5").
                        contentType("application/json").when().get("/student/list");

        String actualData=response.asString();
        System.out.println(actualData);

        String expectedData = new String(Files.readAllBytes(Paths.get("resource/Testdata.txt")));
        System.out.println(expectedData);

        //Assertions.assertEquals(expectedData,actualData);

        JSONAssert.assertEquals(expectedData,actualData, JSONCompareMode.LENIENT);

    }

}
