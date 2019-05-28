package com.learning.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.learning.demo.exceptions.ProductNotfoundException;
import com.learning.demo.exceptions.WrongProductfoundException;
import com.learning.demo.resource.Product;

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
public class MyController {
	private static Map<String, Product> productRepo = new HashMap<>();
	static {
		Product honey = new Product();
		honey.setId("1");
		honey.setName("Honey");
		productRepo.put(honey.getId(), honey);

		Product almond = new Product();
		almond.setId("2");
		almond.setName("Almond");
		productRepo.put(almond.getId(), almond);
	}

	@RequestMapping(value = "/products/{id}")
	public ResponseEntity<Object> getProductExceptin(@PathVariable("id") Integer id) {
		System.out.println("getProduct Started");
		if (id == 0)
			throw new ProductNotfoundException();
		if (id == 2)
			throw new WrongProductfoundException();
		if (id == 3)
			throw new RuntimeException();
		System.out.println("getProduct Completed");
		return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
	}

	@RequestMapping(value = "/products")
	public ResponseEntity<Object> getProduct() {
		System.out.println("getProduct");
		return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
	}

	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public ResponseEntity<Object> createProduct(@RequestBody Product product) {
		productRepo.put(product.getId(), product);
		return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
	}

	@RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Product product) {
		productRepo.remove(id);
		product.setId(id);
		productRepo.put(id, product);
		return new ResponseEntity<>("Product is updated successsfully", HttpStatus.OK);
	}

	@RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@PathVariable("id") String id) {
		productRepo.remove(id);
		return new ResponseEntity<>("Product is deleted successsfully", HttpStatus.OK);
	}
}
