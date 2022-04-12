package com.example.demo.testing;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class APItest {
    @BeforeEach
    public void before()
    {
        RestAssured.baseURI="http://localhost:9090";
    }

    //@Test
    public void getAllStudent()
    {
        System.out.println("Testing");
        RestAssured.
                given().contentType("application/json").
                when().get("/student/list").
                then().log().all();
    }

    @Test
    public void getAllStudentTry()
    {
        System.out.println("Testing");

        String save= RestAssured.
                         given().contentType("application/json").
                            when().get("/student/list").
                                then().extract().path("[3].lastName");

        System.out.println(save);
    }

    //@Test
    public void getAllStudentwithPathparamQueryparam()
    {
        System.out.println("Testing");
        RestAssured.
                given().contentType("application/json")
                .pathParam("mykey","60")
                  .queryParam("programme","Computer Science")
                    .queryParam("limit","2").

                when().get("/student/{mykey}").
                prettyPrint();
    }

    //@Test
    public void getIndividualStudent()
    {
        System.out.println("Testing");

        RestAssured.
                given().baseUri("http://localhost:9090").contentType("application/json").
                when().get("/student/10").
                prettyPrint();
    }


   //@Test
    public void postStudent()
    {
        List<String> course= new ArrayList<>();
        course.add("math1");
        course.add("english1");

        StudentClass stud= new StudentClass();
        stud.setFirstName("VI");
        stud.setLastName("test");
        stud.setEmail("VI.test@sit.com");
        stud.setProgramme("Role");
        stud.setCourses(course);

        System.out.println("============="+stud.toString()+"=============");

        RestAssured.
                given().contentType("application/json").body(stud).
                when().post("/student").
                prettyPrint();
    }

   // @Test
    public void putStudent()
    {

        List<String> course= new ArrayList<>();
        course.add("math2");
        course.add("english2");

        StudentClass stud= new StudentClass();
        stud.setFirstName("Saurab");
        stud.setLastName("test");
        stud.setEmail("Saurab.test@sit.com");
        stud.setProgramme("Role");
        stud.setCourses(course);

        RestAssured.
                given().baseUri("http://localhost:9090").contentType("application/json").body(stud).
                when().put("/student/101").
                prettyPrint();
    }

   // @Test
    public void patchStudent()
    {
        StudentClass stud= new StudentClass();
        stud.setEmail("vittal.test@gmail.com");

        RestAssured.
                given().baseUri("http://localhost:9090").contentType("application/json").body(stud).
                when().patch("/student/101").
                prettyPrint();
    }

    //@Test
    public void deleteStudent()
    {
        RestAssured.
                given().baseUri("http://localhost:9090").contentType("application/json").
                when().delete("/student/101").
                prettyPrint();
    }

}
