package com.threeRiversProj.demo.controller;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.threeRiversProj.demo.bean.Transaction;
import com.threeRiversProj.demo.serviceimpl.TransactionServiceImpl;
import com.threeRiversProj.demo.util.TransactionType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Validated
@Api(tags = "Transaction APIs")
@RestController
@RequestMapping(value = "/transaction")
public class TransactionController {
	
	@Autowired
	private TransactionServiceImpl transactionService;

	@GetMapping(value="/{enroleeId}")
	@ApiOperation(value = "Get Transaction details by id", notes = "Get Transaction details by id(Id as path parameter)")
	public ResponseEntity<Transaction> getTransaction(@PathVariable Integer transactionId) {
		Transaction response =  transactionService.getTransactionById(transactionId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	} 
	
	@PostMapping
	@ApiOperation(value = "Create Transaction details ", notes = "Create Transaction.")
	public ResponseEntity<Transaction> createTransaction(@RequestBody @Valid Transaction transaction) {
		Transaction response = transactionService.createTransaction(transaction);
		return new ResponseEntity<>(response, HttpStatus.OK);
	} 
	
	@PutMapping
	@ApiOperation(value = "Update Transaction details ", notes = "Update Transaction details.")
	public ResponseEntity<Transaction> updateTransaction(@RequestBody @Valid Transaction transaction) {
		Transaction response = transactionService.updateTransaction(transaction);
		return new ResponseEntity<>(response, HttpStatus.OK);
	} 
	
	@DeleteMapping(value="/{transactionId}")
	@ApiOperation(value = "Delete Transaction details ", notes = "Delete Transaction details.")
	public ResponseEntity<String> deleteTransaction(@PathVariable Integer transactionId) {
		transactionService.deleteTransaction(transactionId);
		return new ResponseEntity<>("Deleted transaction", HttpStatus.OK);
	}
	
	@GetMapping(value="getTransactionsBetweenDates/{accountNo}/{startDate}/{endDate}")
	@ApiOperation(value = "Get Transaction details between given dates", notes = "Get Transaction details between given dates")
	public ResponseEntity<List<Transaction>> getTransactionByDate(@PathVariable String startDate, @PathVariable String endDate, @PathVariable String accountNo,
			@Min(value = 1, message = "Offset must be greater than or equal to 1") @RequestParam(required = false) Integer offset,
		      @RequestParam(required = false) Integer limit) throws ParseException {
		List<Transaction> response =  transactionService.getTransactionByDateAndAccno(startDate, endDate, accountNo, offset, limit);
		return new ResponseEntity<>(response, HttpStatus.OK);
	} 
	
	@GetMapping(value="getTransactionsByType/{type}")
	@ApiOperation(value = "Get Transaction details by id", notes = "Get Transaction details by type (WITHDRAW, DEPOSIT)")
	public ResponseEntity<List<Transaction>> getTransactionByType(@PathVariable TransactionType type,
			@Min(value = 1, message = "Offset must be greater than or equal to 1") @RequestParam(required = false) Integer offset,
		      @RequestParam(required = false) Integer limit) {
		List<Transaction> response =  transactionService.getTransactionByType(type, offset, limit);
		return new ResponseEntity<>(response, HttpStatus.OK);
	} 
}