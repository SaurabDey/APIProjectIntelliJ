package com.example.demo.testing;

import com.example.demo.config.AppConfig;
import com.example.demo.dao.DbRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = AppConfig.class)
public class ExampleClass {

    @Autowired
    DbRepository dbRepository;

    @Test
    public void xyz()
    {
        System.out.println("===XYZ==");
        System.out.println(dbRepository.findCustomers());
        System.out.println("===XYZ==");
    }
}
