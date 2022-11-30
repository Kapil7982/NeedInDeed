package com.NeedInDeed.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.NeedInDeed.exceptions.ProductNotFoundException;
import com.NeedInDeed.models.Product;
import com.NeedInDeed.repositories.ProductRepo;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepo pRepo;

	@Override
	public Product addProduct(Product product) throws ProductNotFoundException {

		if (pRepo.findById(product.getProductId()).isPresent()) {

			throw new ProductNotFoundException("Product already exist with this Product Id");
		} else {

			Product obj = pRepo.save(product);
			return obj;
		}

	}

	@Override
	public Product updateProduct(Product product) throws ProductNotFoundException {

		Optional<Product> opt = pRepo.findById(product.getProductId());

		if (opt.isPresent()) {

			return pRepo.save(product);

		} else {
			throw new ProductNotFoundException("No Product found with this ProductId");
		}

	}

	@Override
	public List<Product> getAllProducts() throws ProductNotFoundException {

		List<Product> list = pRepo.findAll();

		if (list.isEmpty()) {
			throw new ProductNotFoundException("No product found...");
		} else {
			return list;
		}

	}

	@Override
	public Product deleteProductById(Integer productId) throws ProductNotFoundException {

		Optional<Product> opt = pRepo.findById(productId);

		if (opt.isPresent()) {
			Product product = opt.get();
			pRepo.delete(product);

			return product;
		} else {
			throw new ProductNotFoundException("No product present with this ProductId");
		}

	}

	@Override
	public Product viewProductById(Integer productId) throws ProductNotFoundException {

		Optional<Product> product = pRepo.findById(productId);

		if (product.isPresent()) {

			return product.get();

		} else {
			throw new ProductNotFoundException("Product does not exist with this ProductId");
		}

	}

	@Override
	public List<Product> viewProductByName(String name) throws ProductNotFoundException {

		List<Product> list = pRepo.findByProductName(name);

		if (list.isEmpty()) {

			throw new ProductNotFoundException("Product does not exist with this Name");
		} else {
			return list;
		}

	}

	@Override
	public Product changeQuantityOfProductByProductId(Integer productid, Integer newQuantity)
			throws ProductNotFoundException {

		Optional<Product> opt = pRepo.findById(productid);

		if (opt.isPresent()) {
			Product product = opt.get();
			product.setStock(newQuantity);
			pRepo.save(product);
			return product;
		} else {

			throw new ProductNotFoundException("Product does not exist with this ProductId");
		}

	}

	@Override
	public Product changePriceOfProductByProductId(Integer productid, Double newPrice) throws ProductNotFoundException {

		Optional<Product> opt = pRepo.findById(productid);

		if (opt.isPresent()) {
			Product product = opt.get();
			product.setCost(newPrice);
			pRepo.save(product);
			return product;
		} else {

			throw new ProductNotFoundException("Product does not exist with this ProductId");
		}

	}

}
