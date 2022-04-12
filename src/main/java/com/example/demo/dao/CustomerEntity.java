package com.example.demo.dao;

import javax.persistence.*;

@Entity
@Table(name = "Customers")
public class CustomerEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
}
