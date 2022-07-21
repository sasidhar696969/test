package com.threeRiversProj.demo.reository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.threeRiversProj.demo.bean.Transaction;
import com.threeRiversProj.demo.util.TransactionType;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer>{
	@Query(value = "select * from transaction where transaction_ts between :startDate and :endDate and accountNumber like :accountNo", nativeQuery = true)
	public List<Transaction> getTransactionsBetween(@Param("startDate") String startDate, 
			@Param("endDate") String endDate, @Param("accountNo") String accountNo, Pageable pageable);
	
	@Query(value = "select * from transaction where type like :type", nativeQuery = true)
	public List<Transaction> getTransactionsByType(@Param("type") String type, Pageable pageable);
}


