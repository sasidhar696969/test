package com.threeRiversProj.demo.serviceimpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.threeRiversProj.demo.bean.Transaction;
import com.threeRiversProj.demo.exception.ThreeRiversProjDemoException;
import com.threeRiversProj.demo.reository.TransactionRepository;
import com.threeRiversProj.demo.util.TransactionType;

@Component
public class TransactionServiceImpl{

	@Autowired
	private TransactionRepository transactionRepository;
	
	public Transaction createTransaction(Transaction transaction) {
		validateTransaction(transaction, false);
		
		return transactionRepository.save(transaction);
	}

	public Transaction updateTransaction(Transaction transaction) {
		validateTransaction(transaction, true);
		return transactionRepository.save(transaction);
	}

	public Transaction getTransactionById(Integer id) {
		if(id == 0 || id == null) {
			throw new ThreeRiversProjDemoException("Id cannot be null or 0 for Get request");
		}
		return transactionRepository.findById(id).get();
	}

	public List<Transaction> getTransactionByDateAndAccno(String startDate, String endDate, String accountNo, 
			Integer pageNum, Integer size) throws ParseException {
		int pageNumber=(null == pageNum) ? 0 : pageNum - 1;
		if (size == null || size == 0) {
			size = 100;
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
		Date formatedStartDate = formatter.parse(startDate);
		Date formatedEndDate = formatter.parse(endDate);
		return transactionRepository.getTransactionsBetween(formatter.format(formatedStartDate),
				formatter.format(formatedEndDate), accountNo, PageRequest.of(pageNumber, size));
	}
	
	public void deleteTransaction(Integer id) {
		if(id == 0 || id == null) {
			throw new ThreeRiversProjDemoException("Id cannot be null or 0 for delete request");
		}
		Optional<Transaction> enrolee = transactionRepository.findById(id);
		transactionRepository.delete(enrolee.get());		
	}
	
	private void validateTransaction(@Valid Transaction transaction, boolean isUpdate) {
		if(!StringUtils.hasText(transaction.getAccountNumber())) {
			throw new ThreeRiversProjDemoException("Account number cannot be empty or null");
		}
		if(transaction.getTransactionTs() == null) {
			throw new ThreeRiversProjDemoException("Transaction update time cannot be empty or null");
		}
		if(transaction.getAmount() == null) {
			throw new ThreeRiversProjDemoException("Amount cannot be empty or null");
		}
		if(isUpdate && (transaction.getId() == null || transaction.getId() == 0)) {
			throw new ThreeRiversProjDemoException("Id cannot be null or 0 for update");
		}
	}

	public List<Transaction> getTransactionByType(TransactionType type, Integer pageNum, Integer size) {
		int pageNumber=(null == pageNum) ? 0 : pageNum - 1;
		if (size == null || size == 0) {
			size = 100;
		}
		return transactionRepository.getTransactionsByType(type.getTransactionVal().trim(), PageRequest.of(pageNumber, size));		
	}
}