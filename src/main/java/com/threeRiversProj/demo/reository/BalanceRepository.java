package com.threeRiversProj.demo.reository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.threeRiversProj.demo.bean.Balance;

public interface BalanceRepository extends JpaRepository<Balance, Integer>{
	
	@Query(value = "select * from balance where account_number like :accountNumber order by last_update_timestamp desc limit 1", nativeQuery = true)
	public Balance getLatestBalance(@Param("accountNumber") String accountNumber);

}
