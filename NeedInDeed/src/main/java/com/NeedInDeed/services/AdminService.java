package com.NeedInDeed.services;

import java.util.List;

import com.NeedInDeed.exceptions.AdminException;
import com.NeedInDeed.exceptions.CustomerException;
import com.NeedInDeed.models.Admin;
import com.NeedInDeed.models.Customer;

public interface AdminService {

	public Customer createCustomer(Customer customer) throws CustomerException;

	public Admin createAdmin(Admin admin) throws AdminException;

	public Admin updateAdmin(Admin admin, String key) throws AdminException;

	public Customer updateCustomer(Customer customer, String key) throws CustomerException;

	public String deleteAdminById(Integer adminId) throws AdminException;

	public List<Admin> getAllAdminDeatils() throws AdminException;

}
