package com.NeedInDeed.services;

import com.NeedInDeed.exceptions.CustomerException;
import com.NeedInDeed.exceptions.InsufficientQuantity;
import com.NeedInDeed.exceptions.OrderException;
import com.NeedInDeed.exceptions.ProductNotFoundException;
import com.NeedInDeed.models.CartDto;
import com.NeedInDeed.models.Orders;

public interface OrderService {

	public Orders buyProductByProductId(String sessionId, Integer productId, String productName, Integer quantity)
			throws CustomerException, ProductNotFoundException, InsufficientQuantity;

	public CartDto visitYourCart(String customerKey) throws CustomerException, OrderException;

	public String payAmount(String customerKey, Double amount) throws CustomerException, OrderException;

	public Orders deleteProductByOrderId(String customerKey, Integer orderId) throws CustomerException, OrderException;

}
