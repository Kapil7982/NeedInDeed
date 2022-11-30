package com.NeedInDeed.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.NeedInDeed.exceptions.AdminException;
import com.NeedInDeed.exceptions.ProductNotFoundException;
import com.NeedInDeed.models.CurrentAdminSession;
import com.NeedInDeed.models.Product;
import com.NeedInDeed.repositories.AdminSessionDao;
import com.NeedInDeed.services.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService pService;

	@Autowired
	private AdminSessionDao asDao;

//	adding new product in database

	@PostMapping("admin/products/{adminkey}")
	public ResponseEntity<Product> addProductHandler(@PathVariable("adminkey") String key,
			@Valid @RequestBody Product product) throws ProductNotFoundException, AdminException {

		CurrentAdminSession loggedInAdmin = asDao.findByUuid(key);

		if (loggedInAdmin == null) {
			throw new AdminException("Please provide a valid key");
		} else {

			Product sObj = pService.addProduct(product);

			return new ResponseEntity<Product>(sObj, HttpStatus.CREATED);
		}

	}

	// updating existing product details

	@PutMapping("admin/products/{adminkey}")
	public ResponseEntity<Product> updateProductHandler(@Valid @RequestBody Product product,
			@PathVariable("adminkey") String key) throws ProductNotFoundException, AdminException {
		CurrentAdminSession loggedInAdmin = asDao.findByUuid(key);

		if (loggedInAdmin == null) {
			throw new AdminException("Please provide a valid key");
		} else {
			Product sObj = pService.updateProduct(product);

			return new ResponseEntity<Product>(sObj, HttpStatus.ACCEPTED);
		}

	}

//	getting all products details from database

	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProductsHandler() throws ProductNotFoundException {

		List<Product> products = pService.getAllProducts();

		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	// delete existing product by ProductId

	@DeleteMapping("admin/products/{id}/{adminkey}")
	public ResponseEntity<Product> deleteProductByIdHandler(@PathVariable("id") Integer productId,
			@PathVariable("adminkey") String key) throws ProductNotFoundException, AdminException {
		CurrentAdminSession loggedInAdmin = asDao.findByUuid(key);

		if (loggedInAdmin == null) {
			throw new AdminException("Please provide a valid key");
		} else {

			Product product = pService.deleteProductById(productId);

			return new ResponseEntity<Product>(product, HttpStatus.OK);

		}
	}

	// view product by ProductId

	@GetMapping("/products/{id}")
	public ResponseEntity<Product> viewProductByIdHandler(@PathVariable("id") Integer productId)
			throws ProductNotFoundException {

		Product product = pService.viewProductById(productId);

		return new ResponseEntity<Product>(product, HttpStatus.OK);

	}

	// view Product by Product name

	@GetMapping("/product/{name}")
	public ResponseEntity<List<Product>> viewProductByProductNameHandler(@PathVariable("name") String name)
			throws ProductNotFoundException {

		List<Product> list = pService.viewProductByName(name);

		return new ResponseEntity<List<Product>>(list, HttpStatus.OK);

	}

	// set new Quantity of product

	@PutMapping("admin/setProductQuantity/{id}/{quantity}/{adminkey}")
	public ResponseEntity<Product> setProductQuantityByProductIdHandler(@PathVariable("adminkey") String key,
			@PathVariable("id") Integer productid, @PathVariable("quantity") Integer quantity)
			throws ProductNotFoundException, AdminException {
		CurrentAdminSession loggedInAdmin = asDao.findByUuid(key);
		if (loggedInAdmin == null) {
			throw new AdminException("Please provide a valid key");
		} else {

			Product product = pService.changeQuantityOfProductByProductId(productid, quantity);

			return new ResponseEntity<Product>(product, HttpStatus.OK);

		}
	}

	// set new Price of product

	@PutMapping("admin/setProductPrice/{id}/{price}/{adminkey}")
	public ResponseEntity<Product> setProductPriceByProductIdHandler(@PathVariable("adminkey") String key,
			@PathVariable("id") Integer productid, @PathVariable("price") Double price)
			throws ProductNotFoundException, AdminException {
		CurrentAdminSession loggedInAdmin = asDao.findByUuid(key);
		if (loggedInAdmin == null) {
			throw new AdminException("Please provide a valid key");
		} else {

			Product product = pService.changePriceOfProductByProductId(productid, price);

			return new ResponseEntity<Product>(product, HttpStatus.OK);
		}
	}

}
