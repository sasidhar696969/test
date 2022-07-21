package com.threeRiversProj.demo.serviceimpl;

import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.threeRiversProj.demo.bean.Balance;
import com.threeRiversProj.demo.exception.ThreeRiversProjDemoException;
import com.threeRiversProj.demo.reository.BalanceRepository;

@Component
public class BalanceServiceImpl {

	@Autowired
	private BalanceRepository balanceRepository;
	public Balance createBalance(Balance balance)throws ThreeRiversProjDemoException {
		validateBalance(balance, false);
		return balanceRepository.save(balance);		
	}

	public Balance updateBalance(Balance balance) {
		validateBalance(balance, true);
		return balanceRepository.save(balance);		
	}

	public Balance getBalanceById(Integer balanceId) {
		if(balanceId == 0 || balanceId == null) {
			throw new ThreeRiversProjDemoException("Id cannot be null or 0 for Get request");
		}
		return balanceRepository.findById(balanceId).get();
	}

	public Balance getBalanceByAccountNo(String accountno) {
		return balanceRepository.getLatestBalance(accountno);
	}
	
	public void deleteBalance(Integer balanceId) {
		if(balanceId == 0 || balanceId == null) {
			throw new ThreeRiversProjDemoException("Id cannot be null or 0 for delete");
		}
		Optional<Balance> balance = balanceRepository.findById(balanceId);
		balanceRepository.delete(balance.get());	
	}
	
	private void validateBalance(@Valid Balance balance, boolean isUpdate) {
		if(!StringUtils.hasText(balance.getAccountNumber())) {
			throw new ThreeRiversProjDemoException("Account number cannot be empty or null");
		}
		if(balance.getLastUpdateTimestamp() == null) {
			throw new ThreeRiversProjDemoException("Balance last Update time cannot be empty or null");
		}		
		if(isUpdate && (balance.getId() == null || balance.getId() == 0)) {
			throw new ThreeRiversProjDemoException("Id cannot be null or 0 for update");
		}
	}
}