package com.example.demo.testing;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class APItestOneStepAhead {

    @BeforeEach
    public void before()
    {
        RestAssured.baseURI="http://localhost:7070";
    }

    //@Test
    public void getAllStudent()
    {
        System.out.println("Testing");

        RestAssured.
                given().contentType("application/json").
                when().get("/student/list").
                prettyPrint();
    }

    //@Test
    public void getIndividualStudent()
    {
        System.out.println("Testing");

        RestAssured.
                given().contentType("application/json").
                when().get("/student/10").
                prettyPrint();
    }


    @Test
    public void postStudent()
    {

        List<String> sub1= new ArrayList<>();
        sub1.add("math1");
        sub1.add("english1");

        List<String> sub2= new ArrayList<>();
        sub2.add("math2");
        sub2.add("english2");

      /*  CoursesClass cou= new CoursesClass();
        cou.setSubject1(sub1);
        cou.setSubject2(sub2);

        CoursesClass cou2= new CoursesClass();
        cou2.setSubject1(sub1);
        cou2.setSubject2(sub2);*/

      /*  List<CoursesClass> course= new ArrayList<>();
        course.add(cou);
        course.add(cou2);*/

        StudentClass stud= new StudentClass();
        stud.setFirstName("VI");
        stud.setLastName("test");
        stud.setEmail("VI.test@sit.com");
        stud.setProgramme("Role");
       // stud.setCourses(course);

        System.out.println(stud.toString());

//        RestAssured.
//                given().contentType("application/json").body(stud).
//                when().post("/student").
//                prettyPrint();
    }

   // @Test
    public void putStudent()
    {

        String student="{\n" +
                "    \"firstName\": \"Vi\",\n" +
                "    \"lastName\": \"test\",\n" +
                "    \"email\": \"Vi.test@sit.com\",\n" +
                "    \"programme\": \"Role\",\n" +
                "    \"courses\": [\n" +
                "        \"Machine Learning\",\n" +
                "        \"Testing\",\n" +
                "        \"Manager\"\n" +
                "    ]\n" +
                "}";
        RestAssured.
                given().contentType("application/json").body(student).
                when().put("/student/101").
                prettyPrint();
    }

    //@Test
    public void deleteStudent()
    {
        RestAssured.
                given().contentType("application/json").
                when().delete("/student/101").
                prettyPrint();
    }

}
