package com.example.demo;

import org.hibernate.annotations.Generated;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Customer {
    @Id
    //@GeneratedValue(strategy= GenerationType.AUTO)
    private String id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;

    public Customer() {}

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
       // this.id = UUID.randomUUID().toString();
    }

    public Customer(String firstName, String lastName, String phoneNumber, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        //this.id = UUID.randomUUID().toString();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

   public String getId() {
        return id;
    }

   public void setId(String id) {
       this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return String.format("{\"id\": \"%s\", \"firstName\": \"%s\", \"lastName\": \"%s\", \"address\": \"%s\", \"phoneNumber\": \"%s\"}", id, firstName, lastName, address, phoneNumber);
    }
}
