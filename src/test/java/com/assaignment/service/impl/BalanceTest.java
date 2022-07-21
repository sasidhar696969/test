package com.assaignment.service.impl;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import com.threeRiversProj.demo.bean.Balance;
import com.threeRiversProj.demo.exception.ThreeRiversProjDemoException;
import com.threeRiversProj.demo.reository.BalanceRepository;
import com.threeRiversProj.demo.serviceimpl.BalanceServiceImpl;

public class BalanceTest {
	private BalanceServiceImpl balanceService;
	private BalanceRepository balanceRepository;
	
	@Before
	public void setup() {
		balanceService = new BalanceServiceImpl();
		balanceRepository = Mockito.mock(BalanceRepository.class);
		ReflectionTestUtils.setField(balanceService, "balanceRepository", balanceRepository);
	}
	
	@Test
	public void testValidRecordForCreate() {
		Mockito.when(balanceRepository.save(ArgumentMatchers.any())).thenReturn(dummyRecord());
		balanceService.createBalance(dummyRecord());
		Mockito.verify(balanceRepository, Mockito.times(1)).save(ArgumentMatchers.any());
	}
	
	@Test(expected = ThreeRiversProjDemoException.class)
	public void testNullAccountForCreate() {
		Balance balance = dummyRecord();
		balance.setAccountNumber(null);
		Mockito.when(balanceRepository.save(ArgumentMatchers.any())).thenReturn(dummyRecord());
		balanceService.createBalance(balance);
	}
	
	@Test(expected = ThreeRiversProjDemoException.class)
	public void testNullUpdateTimeForCreate() {
		Balance balance = dummyRecord();
		balance.setLastUpdateTimestamp(null);
		Mockito.when(balanceRepository.save(ArgumentMatchers.any())).thenReturn(dummyRecord());
		balanceService.createBalance(balance);
	}

	@Test
	public void testValidRecordForUpdate() {
		Mockito.when(balanceRepository.save(ArgumentMatchers.any())).thenReturn(dummyRecord());
		balanceService.updateBalance(dummyRecord());
		Mockito.verify(balanceRepository, Mockito.times(1)).save(ArgumentMatchers.any());
	}
	
	@Test(expected = ThreeRiversProjDemoException.class)
	public void testNullaccountnoForUpdate() {
		Balance balance = dummyRecord();
		balance.setAccountNumber(null);
		Mockito.when(balanceRepository.save(ArgumentMatchers.any())).thenReturn(dummyRecord());
		balanceService.updateBalance(balance);
	}
	
	@Test(expected = ThreeRiversProjDemoException.class)
	public void testNullUpdateTimeForUpdate() {
		Balance balance = dummyRecord();
		balance.setLastUpdateTimestamp(null);
		Mockito.when(balanceRepository.save(ArgumentMatchers.any())).thenReturn(dummyRecord());
		balanceService.updateBalance(balance);
	}
	
	private Balance dummyRecord() {
		Balance balance = new Balance();
		balance.setLastUpdateTimestamp(new Date());
		balance.setId(1);
		balance.setAccountNumber("Test");
		return balance;
	}
}

