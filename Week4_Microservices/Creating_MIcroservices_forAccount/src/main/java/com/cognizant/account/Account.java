package com.cognizant.account;

public class Account {
		private String number;
		private String type;
		private double balance;
		
		public Account(String number,String type,double balance) {
			this.number = number;
			this.type = type;
			this.balance = balance;
		}
		
		
		public String getNumber() {
			return number;
		}
		
		public String getType() {
			return type;
		}
		
		public double getBalance() {
			return balance;
		}
		
		
}
