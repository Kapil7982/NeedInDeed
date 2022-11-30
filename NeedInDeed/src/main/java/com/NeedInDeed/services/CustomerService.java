package com.NeedInDeed.services;

import java.util.List;

import com.NeedInDeed.exceptions.AdminException;
import com.NeedInDeed.exceptions.CustomerException;
import com.NeedInDeed.models.Customer;

public interface CustomerService {

	public Customer createCustomer(Customer customer) throws CustomerException;

	public Customer updateCustomer(Customer customer, String key) throws CustomerException;

	public String deleteCustomerById(Integer customerId) throws CustomerException;

	public List<Customer> getAllCustomersDeatils(String key) throws CustomerException, AdminException;

}
