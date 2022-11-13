package model.entities;

import model.exceptions.BusinessException;

public class Account {
	private Integer number;
	private String holder;
	private Double balance;
	private Double withdrawLimit;
	
	public Account() {}
	
	public Account(Integer number, String holder, Double balance, Double withDrawLimit) {
		this.number = number;
		this.holder = holder;
		this.balance = balance;
		this.withdrawLimit = withDrawLimit;
	}
	
	//getters

	public Integer getNumber() {
		return number;
	}

	public String getHolder() {
		return holder;
	}

	public Double getBalance() {
		return balance;
	}
	
	public Double getWithdrawLimit() {
		return withdrawLimit;
	}
	
	//setters
	
	public void setNumber(Integer number) {
		this.number = number;
	}
	
	public void setHolder(String holder) {
		this.holder = holder;
	}

	public void setWithdrawLimit(Double withdrawLimit) {
		this.withdrawLimit = withdrawLimit;
	}
	
	//other methods
	
	public void deposit(Double amount) {
		balance+=amount;
	}
	
	public void withdraw(Double amount) {
		validateWithdraw(amount);
		balance-=amount;
	}
	
	private void validateWithdraw(Double amount) {
		if (amount > withdrawLimit) throw new BusinessException("Your withdraw is out of limit !");
		if (amount > balance) throw new BusinessException("Insuficient balance !");
	}
	
	
}
