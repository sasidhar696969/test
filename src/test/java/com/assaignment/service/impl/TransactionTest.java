package com.assaignment.service.impl;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import com.threeRiversProj.demo.bean.Transaction;
import com.threeRiversProj.demo.exception.ThreeRiversProjDemoException;
import com.threeRiversProj.demo.reository.TransactionRepository;
import com.threeRiversProj.demo.serviceimpl.TransactionServiceImpl;

public class TransactionTest {
	private TransactionServiceImpl transactionService;
	private TransactionRepository transactionRepository;
	
	@Before
	public void setup() {
		transactionService = new TransactionServiceImpl();
		transactionRepository = Mockito.mock(TransactionRepository.class);
		ReflectionTestUtils.setField(transactionService, "transactionRepository", transactionRepository);
	}
	
	@Test
	public void testValidRecordForCreate() {
		Mockito.when(transactionRepository.save(ArgumentMatchers.any())).thenReturn(dummyRecord());
		transactionService.createTransaction(dummyRecord());
		Mockito.verify(transactionRepository, Mockito.times(1)).save(ArgumentMatchers.any());
	}
	
	@Test(expected = ThreeRiversProjDemoException.class)
	public void testNullAccountNoForCreate() {
		Transaction transaction = dummyRecord();
		transaction.setAccountNumber(null);
		Mockito.when(transactionRepository.save(ArgumentMatchers.any())).thenReturn(dummyRecord());
		transactionService.createTransaction(transaction);
	}
	
	@Test(expected = ThreeRiversProjDemoException.class)
	public void testNullUpdateTimeForCreate() {
		Transaction transaction = dummyRecord();
		transaction.setTransactionTs(null);
		Mockito.when(transactionRepository.save(ArgumentMatchers.any())).thenReturn(dummyRecord());
		transactionService.createTransaction(transaction);
	}
	
	@Test(expected = ThreeRiversProjDemoException.class)
	public void testNullAmountForCreate() {
		Transaction transaction = dummyRecord();
		transaction.setAmount(null);
		Mockito.when(transactionRepository.save(ArgumentMatchers.any())).thenReturn(dummyRecord());
		transactionService.createTransaction(transaction);
	}

	@Test
	public void testValidRecordForUpdate() {
		Mockito.when(transactionRepository.save(ArgumentMatchers.any())).thenReturn(dummyRecord());
		transactionService.updateTransaction(dummyRecord());
		Mockito.verify(transactionRepository, Mockito.times(1)).save(ArgumentMatchers.any());
	}
	
	@Test(expected = ThreeRiversProjDemoException.class)
	public void testNullAccountForUpdate() {
		Transaction transaction = dummyRecord();
		transaction.setAccountNumber(null);
		Mockito.when(transactionRepository.save(ArgumentMatchers.any())).thenReturn(dummyRecord());
		transactionService.updateTransaction(transaction);
	}
	
	@Test(expected = ThreeRiversProjDemoException.class)
	public void testNullTransactionForUpdate() {
		Transaction transaction = dummyRecord();
		transaction.setTransactionTs(null);
		Mockito.when(transactionRepository.save(ArgumentMatchers.any())).thenReturn(dummyRecord());
		transactionService.updateTransaction(transaction);
	}
	
	private Transaction dummyRecord() {
		Transaction transaction = new Transaction();
		transaction.setTransactionTs(new Date());
		transaction.setId(1);
		transaction.setAccountNumber("Test");
		transaction.setAmount(123.32);
		return transaction;
	}
}

