package com.revature.bean;

import java.util.Random;

import com.revature.exception.WithdrawTooBigException;

public class Account {

	int customer_id;
	String first;
	String last;
	String username;
	String password;
	int balance;
	int withdraw;
	int deposit;
	int accountNumber;

	public Account() {
	}

	public Account(int customer_id, String first, String last, String username, String password, int balance,
			int accountNumber) {
		this.customer_id = customer_id;
		this.first = first;
		this.last = last;
		this.username = username;
		this.password = password;
		this.balance = balance;
		this.accountNumber = accountNumber;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public double getWithdraw() {
		return withdraw;
	}

	public void withdraw(int withdrawAmount) throws WithdrawTooBigException {
		int maxWithdrawAmount = balance;
		if (withdrawAmount < 0) {
			System.out.println("Cannot withdraw negative number");
		} else if (withdrawAmount <= balance) {
			balance -= withdrawAmount;
			System.out.println("New Balance = " + balance);
		} else if (withdrawAmount > maxWithdrawAmount) {
			// throw a custom exception here
			throw new WithdrawTooBigException(
					withdrawAmount + " exceeds the max withdraw limit of: " + maxWithdrawAmount);
		}

	}

	public int getDeposit() {
		return deposit;
	}

	public void setDeposit(int deposit) {
		balance += deposit;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((last == null) ? 0 : last.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (last == null) {
			if (other.last != null)
				return false;
		} else if (!last.equals(other.last))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Customer id " + customer_id + ": [first=" + first + ", last=" + last + ", username=" + username
				+ ", password=" + password + ", balance=" + balance + ", accountNumber=" + accountNumber + "]";
	}

}
