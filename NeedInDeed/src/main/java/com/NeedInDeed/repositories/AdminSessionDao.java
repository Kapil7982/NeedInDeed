package com.NeedInDeed.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.NeedInDeed.models.CurrentAdminSession;

public interface AdminSessionDao extends JpaRepository<CurrentAdminSession, Integer> {

	public CurrentAdminSession findByUuid(String uuid);
}
