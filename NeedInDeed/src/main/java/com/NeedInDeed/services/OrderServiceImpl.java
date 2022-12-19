package com.NeedInDeed.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.NeedInDeed.exceptions.CustomerException;
import com.NeedInDeed.exceptions.InsufficientQuantity;
import com.NeedInDeed.exceptions.OrderException;
import com.NeedInDeed.exceptions.ProductNotFoundException;
import com.NeedInDeed.models.CartDto;
import com.NeedInDeed.models.Orders;
import com.NeedInDeed.models.Product;
import com.NeedInDeed.repositories.OrderRepo;
import com.NeedInDeed.repositories.ProductRepo;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepo oRepo;

	@Autowired
	private ProductRepo pRepo;

	// product buying

	@Override
	public Orders buyProductByProductId(String sessionId, Integer productId,String productName, Integer quantity)
			throws CustomerException, ProductNotFoundException, InsufficientQuantity {

		Optional<Product> opt = pRepo.findById(productId);

		if (opt.isPresent()) {

			Product product = opt.get();
			if (product.getStock() < quantity) {

				throw new InsufficientQuantity("Product Stock is lesser than your quantity");
			} else {

				Orders order = new Orders();
				order.setDateAndTime(LocalDateTime.now());
				order.setProductId(productId);
				order.setProductName(productName);			
				order.setQuantity(quantity);
				order.setSessionId(sessionId);
				order.setTotalCost(quantity * product.getCost());

				Orders obj = oRepo.save(order);

				product.setStock(product.getStock() - quantity);
				pRepo.save(product);
				return obj;
			}

		} else {

			throw new ProductNotFoundException("No product found with this Id");
		}

	}

	@Override
	public CartDto visitYourCart(String customerKey) throws CustomerException, OrderException {

		List<Orders> list = oRepo.findBySessionId(customerKey);

		if (list.isEmpty()) {
			throw new OrderException("You do not have any item in your cart");
		}

		Double sum = 0.00;

		for (Orders order : list) {

			sum += order.getTotalCost();

		}

		CartDto cart = new CartDto();
		cart.setList(list);
		cart.setTotalBill(sum);

		return cart;

	}

	@Override
	public String payAmount(String customerKey, Double amount) throws CustomerException, OrderException {

		List<Orders> list = oRepo.findBySessionId(customerKey);
		Double sum = 0.00;

		for (Orders order : list) {

			sum += order.getTotalCost();
		}

		int x = Double.compare(amount, sum);

		if (x == 0) {
//			System.out.println(true);
//			oRepo.deleteBySessionId(customerKey);
			oRepo.deleteAll();
			return "Payment Successfully done";

		} else {

			throw new OrderException("Amount should be equal to : " + sum);
		}

	}

	@Override
	public Orders deleteProductByOrderId(String customerKey, Integer orderId) throws CustomerException, OrderException {

		Optional<Orders> opt = oRepo.findById(orderId);

		if (opt.isPresent()) {

			oRepo.deleteById(orderId);
			return opt.get();

		} else {

			throw new OrderException("No order Present with this Order Id");
		}

	}

}
