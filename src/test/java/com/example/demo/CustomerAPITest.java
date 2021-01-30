package com.example.demo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomerAPITest {
    ObjectMapper mapper;
    ArrayList<Customer> customerList;

    String customersJsonPath = "src/test/data/customers.json"; // 4 customers
    String customerJsonPath = "src/test/data/existingCustomer.json"; // 1 customer
    String newCustomerJsonPath = "src/test/data/newCustomer.json"; // 1 customer

    @BeforeEach
    void setUp() throws IOException {
        initializeCustomersData();
    }

    // TEST UTILITIES ----------------------------------------------------

    private void initializeCustomersData() throws IOException {
        mapper = new ObjectMapper();
        File customersFile = new File(customersJsonPath);
        customerList = mapper.readValue(customersFile, new TypeReference<ArrayList<Customer>>() {});
    }

    private String getCustomerJsonString() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File customerFile = new File(customerJsonPath);
        Customer customer = mapper.readValue(customerFile, Customer.class);
        return mapper.writeValueAsString(customer);
    }

    private String createCustomerJsonString() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File customerFile = new File(newCustomerJsonPath);
        Customer customer = mapper.readValue(customerFile, Customer.class);
        return mapper.writeValueAsString(customer);
    }
    @Test
    public void whenGetCustomersList(){
        try {
            initializeCustomersData();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            assertEquals(200, 0);
        }

        assertEquals(4,customerList.size());
    }
    @Test
    public void whenAddNewCustomersList(){
        try {
            initializeCustomersData();
            ObjectMapper mapper = new ObjectMapper();
            File customerFile = new File(newCustomerJsonPath);
            Customer customer =  mapper.readValue(customerFile, Customer.class);
            customerList.add(customer);
            assertEquals(5,customerList.size());
        }
        catch (Exception ex) {
            ex.printStackTrace();
            assertEquals(201, 0);
        }


    }

//    @Test
//    public void whenGetCustomerById() {
//        try {
//            initializeCustomersData();
//            ObjectMapper mapper = new ObjectMapper();
//            File customerFile = new File(customerJsonPath);
//            Customer customer =  mapper.readValue(customerFile, Customer.class);
//            Customer result = customerList.stream().filter(c -> c.getId()
//                    .equals(customer.getId()))
//                    .collect(Collectors.toList()).get(0);
//
//            assertTrue(customer.toString().equals(result.toString()));
//            assertEquals(4,customerList.size());
//        }
//        catch (Exception ex) {
//            ex.printStackTrace();
//            assertEquals(201, 0);
//        }
//
//    }
//    @Test
//    public void whenUpdateNewCustomersList(){
//        try {
//            initializeCustomersData();
//            ObjectMapper mapper = new ObjectMapper();
//            File customerFile = new File(customerJsonPath);
//            Customer customer =  mapper.readValue(customerFile, Customer.class);
//            Customer result = customerList.stream().filter(c -> c.getId()
//                    .equals(customer.getId()))
//                    .collect(Collectors.toList()).get(0);
//            customerList.add(result);
//            assertEquals(5,customerList.size());
//        }
//        catch (Exception ex) {
//            ex.printStackTrace();
//            assertEquals(200, 0);
//        }
//
//    }
//    @Test
//    public void whenDeleteCustomersList() {
//        try {
//            initializeCustomersData();
//            ObjectMapper mapper = new ObjectMapper();
//            File customerFile = new File(customerJsonPath);
//            Customer customer = mapper.readValue(customerFile, Customer.class);
//            Customer result = customerList.stream().filter(c -> c.getId()
//                    .equals(customer.getId()))
//                    .collect(Collectors.toList()).get(0);
//            //System.out.println(customerList.size());
//            customerList.remove(result);
//            assertEquals(3, customerList.size());
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            assertEquals(204, 0);
//        }
//    }

}
