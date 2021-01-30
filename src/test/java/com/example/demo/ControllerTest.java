package com.example.demo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

//import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ControllerTest {

    ObjectMapper mapper;
    ArrayList<Customer> customerList;

    String customersJsonPath = "src/test/data/customers.json"; // 4 customers
    String customerJsonPath = "src/test/data/existingCustomer.json"; // 1 customer
    String newCustomerJsonPath = "src/test/data/newCustomer.json"; // 1 customer

    @BeforeEach
    void setUp() throws IOException {
        initializeCustomersData();
    }
    private void initializeCustomersData() throws IOException {
        mapper = new ObjectMapper();
        File customersFile = new File(customersJsonPath);
        customerList = mapper.readValue(customersFile, new TypeReference<ArrayList<Customer>>() {});
    }
    private String createCustomerJsonString() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File customerFile = new File(newCustomerJsonPath);
        Customer customer = mapper.readValue(customerFile, Customer.class);
        return mapper.writeValueAsString(customer);
    }
    @Autowired
    MockMvc mockMvc;

    @Autowired
    CustomerController customerController;

    @Test
    public void getAllCustomers() throws Exception{
        mockMvc.perform(get("/customers")).andExpect(status().isOk())
         .andExpect(jsonPath("$").isEmpty());


    }
    @Test
    public void addNewCustomer() throws Exception{
        mockMvc.perform(post("/customers").contentType(MediaType.APPLICATION_JSON)
                .content(createCustomerJsonString()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("Araminta"))
                .andExpect(jsonPath("$.lastName").value("Ross"));


    }
    @Test
    public void getCustomerById() throws Exception{
        String customerId = "b8a504e8-7cbd-4a54-9a24-dc1832558162";
        mockMvc.perform(get("/customers/"+ customerId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Qin"))
                .andExpect(jsonPath("$.lastName").value("Zhang"));




    }




}
