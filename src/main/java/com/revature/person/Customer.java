package com.revature.person;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import com.revature.bean.Account;
import com.revature.daoimpl.AccountDaoImpl;
import com.revature.driver.Driver;
import com.revature.exception.WithdrawTooBigException;

public class Customer extends Account {

	static ArrayList<Account> listOfCustomers = new ArrayList<Account>();

	public static void logIn(ArrayList<Account> cus) throws FileNotFoundException, IOException, SQLException {
		AccountDaoImpl sdi = new AccountDaoImpl();
		for (Account cust : cus) {
			listOfCustomers.add(cust);
		}

		Account update = new Account();

		System.out.println("\nEnter username and password: ");
		Account the_one = null;
		boolean found = false;
		while (found == false) {
			Scanner c = new Scanner(System.in);
			String username = c.next();
			String password = c.next();

			for (int i = 0; i < listOfCustomers.size(); i++) {

				if (listOfCustomers.get(i).getUsername().equals(username)
						&& listOfCustomers.get(i).getPassword().equals(password)) {
					System.out.println(listOfCustomers.get(i));
					the_one = listOfCustomers.get(i);
					int idn = listOfCustomers.get(i).getCustomer_id();
					found = true;
					boolean cust = false;
					while (cust == false) {
						System.out.println(
								"Enter 1) To withdraw \nEnter 2) To deposit \nEnter 3) To create an account \nEnter 4) To delete account \nEnter 5) To view transaction history \nEnter 6) To logout");
						Scanner n = new Scanner(System.in);
						int num = n.nextInt();
						if (num == 1) {
							System.out.println("Enter amount to withdraw: ");
							num = n.nextInt();
							int id = the_one.getCustomer_id();
							try {
								listOfCustomers.get(i).withdraw(num);
								int bal = listOfCustomers.get(i).getBalance();
								sdi.updateBal(bal, id);
								sdi.insertTransaction(idn, "withdrew", num);

							} catch (WithdrawTooBigException e) {
								e.printStackTrace();
							}
						} else if (num == 2) {
							System.out.println("Enter amout to deposit: ");
							num = n.nextInt();
							listOfCustomers.get(i).setDeposit(num);
							int bal = listOfCustomers.get(i).getBalance();
							int id = listOfCustomers.get(i).getCustomer_id();
							sdi.updateBal(bal, id);
							System.out.println(listOfCustomers.get(i).toString());
							sdi.insertTransaction(idn, "deposited", num);

						} else if (num == 3) {
							System.out.println(
									"Enter the First name, Last name, Username, Password and First deposit amount for new account ");
							Scanner cc = new Scanner(System.in);
							String first = cc.next();
							String last = cc.next();
							String username2 = cc.next();
							String password2 = cc.next();
							int deposit = cc.nextInt();
							sdi.insertAccount(first, last, username2, password2, deposit);
							System.out.println("Your application successfully submitted!");
							sdi.insertTransaction(idn, "created account", 0);

						} else if (num == 4) {
							System.out.println("Are you sure you want to delete you account? Enter 1 for yes 2 for no");
							Scanner f = new Scanner(System.in);
							int sure = f.nextInt();
							if (sure == 1) {
								System.out.println(
										"Withdrawing remaining balance of: " + listOfCustomers.get(i).getBalance());
								int bal = listOfCustomers.get(i).getBalance();
								try {
									listOfCustomers.get(i).withdraw(bal);
									System.out.println("Account now at 0 ");
									int id = listOfCustomers.get(i).getCustomer_id();
									sdi.delete(id);
								} catch (WithdrawTooBigException e) {
									e.printStackTrace();
								}

							}

						} else if (num == 5) {
							sdi.transactionHistory(idn);

						} else if (num == 6) {

							cust = true;
							for (int r = 1; r <= listOfCustomers.size();) {
								listOfCustomers.remove(0);
							}
							System.out.println("Have a good rest of the day!");
						} else {
							System.out.println("Invalid entry");
						}
					}

				}
			}
			if (the_one == null) {
				System.out.println("User not found");
				System.out.println("Enter username and password");
			}
		}

	}

	public static void apply(ArrayList<Account> app) throws FileNotFoundException, IOException, SQLException {

		AccountDaoImpl sdi = new AccountDaoImpl();
		System.out.println("Enter your First name, Last name, Username, Password and First deposit amount ");
		Scanner c = new Scanner(System.in);
		String first = c.next();
		String last = c.next();
		String username = c.next();
		String password = c.next();
		int deposit = c.nextInt();
		sdi.insertAccount(first, last, username, password, deposit);
		System.out.println("Your application successfully submitted!");

	}
}