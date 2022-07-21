package com.threeRiversProj.demo.util;

import java.util.Arrays;
import org.springframework.util.StringUtils;
import com.threeRiversProj.demo.exception.ThreeRiversProjDemoException;

public enum TransactionType {

	WITHDRAW("WITHDRAW", "WITHDRAW"), DEPOSIT("DEPOSIT", "DEPOSIT");

	private final String transactionVal;
	private final String transactionName;

	public String getTransactionVal() {
		return transactionVal;
	}

	public String getTransactionName() {
		return transactionName;
	}

	private TransactionType(final String transactionName, final String transactionVal) {
		this.transactionVal = transactionVal;
		this.transactionName = transactionName;
	}

	public static void validateForAllowedValues(final String value) {
		if (StringUtils.hasText(value) && Arrays.stream(TransactionType.values())
				.noneMatch(transaction -> transaction.transactionName.equalsIgnoreCase(value))) {
			throw new ThreeRiversProjDemoException(
					"Invalid  Transaction for type " + value + ", Allowed values are " + Arrays.toString(values()));
		}
	}
}
