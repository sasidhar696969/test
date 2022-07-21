package com.threeRiversProj.demo.controller;

import javax.validation.Valid;
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
import org.springframework.web.bind.annotation.RestController;

import com.threeRiversProj.demo.bean.Balance;
import com.threeRiversProj.demo.exception.ThreeRiversProjDemoException;
import com.threeRiversProj.demo.serviceimpl.BalanceServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Validated
@Api(tags = "Balance APIs")
@RestController
@RequestMapping(value = "/balance")
public class BalanceController {
	
	@Autowired
	private BalanceServiceImpl balanceService;
	 
	@PostMapping
	@ApiOperation(value = "Create Balance ", notes = "Create Balance")
	 public ResponseEntity<Balance> createBalance(@RequestBody Balance balance) {
		Balance response = balanceService.createBalance(balance);
		return new ResponseEntity<>(response, HttpStatus.OK);
	} 

	@PutMapping
	@ApiOperation(value = "Update Balance details ", notes = "Update Balance details.")
	 public ResponseEntity<Balance> updateBalance(@RequestBody @Valid Balance balance) {
		Balance response = balanceService.updateBalance(balance);
		return new ResponseEntity<>(response, HttpStatus.OK);
	} 
	
	@DeleteMapping(value="/{balanceId}")
	@ApiOperation(value = "Delete Balance details ", notes = "Delete Balance details.")
	 public ResponseEntity<String> deleteBalance(@PathVariable Integer balanceId) {
		balanceService.deleteBalance(balanceId);
		return new ResponseEntity<>("Deleted balance", HttpStatus.OK);
	}

	@GetMapping(value="/{balanceId}")
	@ApiOperation(value = "Get Balance details by id", notes = "Get Balance details by id(Id as path parameter)")
	 public ResponseEntity<Balance> getBalance(@PathVariable Integer balanceId) {
		Balance response = balanceService.getBalanceById(balanceId);
		return new ResponseEntity<>(response, HttpStatus.OK);		
	}
	
	@GetMapping(value="/getBalanceByAccountno/{accountNo}")
	@ApiOperation(value = "Get Balance details by id", notes = "Get Balance details by id(Id as path parameter)")
	 public ResponseEntity<Balance> getBalanceByAccountno(@PathVariable String accountNo) {
		Balance response = balanceService.getBalanceByAccountNo(accountNo);
		return new ResponseEntity<>(response, HttpStatus.OK);		
	}
	
}
