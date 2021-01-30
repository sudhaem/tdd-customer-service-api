package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
       return customerService.getAll();
    }

    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer addCustomer(@RequestBody Customer customer) {
        return customerService.addCustomer(customer);

    }
    @GetMapping("/customers/{customerId}")
    public Customer getCustomerById(@PathVariable String customerId) {

        return customerService.findById(customerId).get();
    }

//    ObjectMapper mapper;
//    ArrayList<Customer> customerList;
//
//    String customersJsonPath = "src/test/data/customers.json"; // 4 customers
//    String customerJsonPath = "src/test/data/existingCustomer.json"; // 1 customer
//    String newCustomerJsonPath = "src/test/data/newCustomer.json"; // 1 customer
//
//
//    private void initializeCustomersData() throws IOException {
//        mapper = new ObjectMapper();
//        File customersFile = new File(customersJsonPath);
//        customerList = mapper.readValue(customersFile, new TypeReference<ArrayList<Customer>>() {});
//    }
//
//    @GetMapping("api/message")
//    public String getMessage() {
//        return "Hello there!";
//    }
//    @PostMapping("api/customers")
//    public Customer addCustomers(@RequestBody Customer customer) throws Exception {
//        ObjectMapper mapper;
//        ArrayList<Customer> customerList;
//
//
//        String newCustomerJsonPath = "src/test/data/newCustomer.json"; // 1 customer
//        mapper = new ObjectMapper();
//        File customersFile = new File(newCustomerJsonPath);
//        customerList = mapper.readValue(customersFile, new TypeReference<ArrayList<Customer>>() {});
//        mapper = new ObjectMapper();
//        File customerFile = new File(newCustomerJsonPath);
//        customer =  mapper.readValue(customerFile, Customer.class);
//        customerList.add(customer);
//        return customer;
//
//    }
}
