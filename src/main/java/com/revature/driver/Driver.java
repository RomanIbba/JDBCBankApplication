package com.revature.driver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.revature.bean.Account;
import com.revature.daoimpl.AccountDaoImpl;
import com.revature.exception.WithdrawTooBigException;
import com.revature.person.Admin;
//import com.revature.account.Admin;
//import com.revature.account.Customer;
//import com.revature.account.Employee;
import com.revature.person.Customer;

public class Driver {

	public static void main(String[] args)
			throws SQLException, FileNotFoundException, IOException, WithdrawTooBigException {
		ArrayList<Account> listOfCustomers = new ArrayList<Account>();
		AccountDaoImpl adi = new AccountDaoImpl();


		for (Account cust : adi.getAllCustomers()) {
			listOfCustomers.add(cust);
		}

		// Allows to log in as Customer, Employee or Admin
		boolean found = false;
		while (found == false) {
			System.out.println("Enter 1) If Customer \nEnter 2) If Admin \nEnter 3) To exit");
			Scanner in = new Scanner(System.in);
			int num = in.nextInt();
			if (num == 1) {
				System.out.println("Welcome!");
				System.out.println("Enter 1) To log in \nEnter 2) To apply for a personal account");
				int n = in.nextInt();
				if (n == 1) {
					Customer.logIn(listOfCustomers);
				}
				if (n == 2) {
					Customer.apply(listOfCustomers);
				}
			} else if (num == 2) {
				System.out.println("Welcome Admin!");
				Admin.viewEdit(listOfCustomers);
			} else if (num == 3) {
				found = true;
				System.out.println("Ended. Have a good day!");

			} else {
				System.out.println("Entered an incorect number, try again.");
			}

		}
	}

}
