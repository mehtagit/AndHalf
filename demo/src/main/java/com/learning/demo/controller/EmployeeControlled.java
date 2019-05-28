package com.learning.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.learning.demo.exceptions.ProductNotfoundException;
import com.learning.demo.exceptions.WrongProductfoundException;
import com.learning.demo.resource.Employee;
import com.learning.demo.resource.Product;
import com.learning.demo.services.EmployeeService;
import com.learning.demo.services.StudentService;

/**
 * 
 * Rest Controller The @RestController annotation is used to define the RESTful
 * web services. It serves JSON, XML and custom response. Its syntax is shown
 * below −
 * 
 * Request Mapping The @RequestMapping annotation is used to define the Request
 * URI to access the REST Endpoints. We can define Request method to consume and
 * produce object. The default request method is GET.
 * 
 * Request Body The @RequestBody annotation is used to define the request body
 * content type.
 * 
 * Path Variable The @PathVariable annotation is used to define the custom or
 * dynamic request URI. The Path variable in request URI is defined as curly
 * braces {} as shown below −
 * 
 * Request Parameter The @RequestParam annotation is used to read the request
 * parameters from the Request URL. By default, it is a required parameter.
 */

@RestController
public class EmployeeControlled {
	
	@Autowired
	EmployeeService employeeService;

	@Autowired
	StudentService studentService;
	@RequestMapping(value = "/employee/{id}")
	public ResponseEntity<Object> getProductExceptin(@PathVariable("id") Long id) {
		System.out.println("getProduct Started");
		return new ResponseEntity<>(employeeService.find(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/employee")
	public ResponseEntity<Object> getProduct() {
		System.out.println("getProduct");
		return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/employee", method = RequestMethod.POST)
	public ResponseEntity<Object> createProduct(@RequestBody Employee employee) {
		employeeService.save(employee);
		return new ResponseEntity<>("Employee is created successfully", HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/student")
	public ResponseEntity<Object> getstudent() {
		System.out.println("getProduct");
		return new ResponseEntity<>(studentService.viewAll(), HttpStatus.OK);
	}

}
