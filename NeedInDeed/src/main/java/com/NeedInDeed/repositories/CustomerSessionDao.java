package com.NeedInDeed.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.NeedInDeed.models.CurrentCustomerSession;

public interface CustomerSessionDao extends JpaRepository<CurrentCustomerSession, Integer> {

	public CurrentCustomerSession findByUuid(String uuid);
}
