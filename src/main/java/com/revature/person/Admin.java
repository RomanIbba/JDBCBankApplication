package com.revature.person;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import com.revature.bean.Account;
import com.revature.bean.AdminBean;
import com.revature.daoimpl.AccountDaoImpl;
import com.revature.daoimpl.AdminDaoImpl;
import com.revature.exception.WithdrawTooBigException;

public class Admin extends Employee {

	static ArrayList<Account> listOfCustomers = new ArrayList<Account>();
	static ArrayList<AdminBean> listOfAdmin = new ArrayList<AdminBean>();

	static ArrayList<Account> admin = new ArrayList<Account>();

	public static void viewEdit(ArrayList<Account> v) throws FileNotFoundException, IOException, SQLException {
		AccountDaoImpl sdi = new AccountDaoImpl();
		AdminDaoImpl adi = new AdminDaoImpl();

		try {
			adi.getAllAdmin();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		for (AdminBean cust : adi.getAllAdmin()) {
			listOfAdmin.add(cust);
		}

		for (Account loo : v) {
			listOfCustomers.add(loo);
		}

		System.out.println("Enter Admin username and Password: ");
		boolean found = false;
		while (found == false) {
			Scanner c = new Scanner(System.in);
			String username = c.next();
			String apassword = c.next();

			for (int i = 0; i < listOfAdmin.size(); i++) {

				if (listOfAdmin.get(i).getAdmin_username().equals(username)
						&& listOfAdmin.get(i).getAdmin_password().equals(apassword)) {
					System.out.println("Welcome, here are all active accounts: ");
					found = true;
					boolean cust = false;
					while (cust == false) {
						System.out.println(
								"Enter 1) To view all accounts \nEnter 2) To update/delete an account \nEnter 3) To create an account \nEnter 4) To log out");
						int in = c.nextInt();
						if (in == 1) {
							System.out.println("Here are all active accounts: ");
							for (Account a : listOfCustomers) {
								System.out.println(a);
								System.out.println("============================");
							}
						} else if (in == 2) {
							System.out.println("Enter customer id of account you want to update or delete");
							int cc = 0;
							int la = c.nextInt();
							for (int ii = 0; ii < listOfCustomers.size(); ii++) {
								if (listOfCustomers.get(ii).getCustomer_id() == la) {
									cc++;
									System.out.println(
											"Enter 1) to change username \nEnter 2) To change password \nEnter 3) To delete account \nEnter 4) To go back");
									int ll = c.nextInt();
									if (ll == 1) {
										System.out.println("Enter new username: ");
										String w = c.next();
										int id = listOfCustomers.get(ii).getCustomer_id();
										listOfCustomers.get(ii).setUsername(w);
										sdi.updateUsername(w, id);
										System.out.println("New username: " + listOfCustomers.get(ii).toString());
									} else if (ll == 2) {
										System.out.println("Enter new password: ");
										String p = c.next();
										int id = listOfCustomers.get(ii).getCustomer_id();
										listOfCustomers.get(ii).setPassword(p);
										sdi.updatePassword(p, id);
										System.out.println("New password: " + listOfCustomers.get(ii).toString());
									} else if (ll == 3) {
										System.out.println(
												"Are you sure you want to delete this account: \nEnter 1) For yes \nEnter 2) For no");
										Scanner f = new Scanner(System.in);
										int che = f.nextInt();
										if (che == 1) {
											// int ccc = 0;
											for (int check = 0; check < listOfCustomers.size(); check++) {
												if (listOfCustomers.get(check).getCustomer_id() == la) {
													System.out.println("Withdrawing remaining balance of: "
															+ listOfCustomers.get(check).getBalance());
													int bal = listOfCustomers.get(check).getBalance();
													try {
														listOfCustomers.get(check).withdraw(bal);
														System.out.println("Account deleted ");
														sdi.delete(la);
														listOfCustomers.remove(check);
													} catch (WithdrawTooBigException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													}

												}
											}
										}

									}
								}
							}
							if (cc == 0) {
								System.out.println("Account not found");
							}
						} else if (in == 3) {

							System.out.println(
									"Enter customer First name, Last name, Username, Password and first deposit amount ");
							Scanner ncust = new Scanner(System.in);
							String first = ncust.next();
							String last = ncust.next();
							String nusername = ncust.next();
							String password = ncust.next();
							int deposit = ncust.nextInt();

							sdi.insertAccount(first, last, nusername, password, deposit);
							System.out.println("Account sucessfuly created! ");

						} else if (in == 4) {
							cust = true;
							for (int r = 1; r <= listOfCustomers.size();) {
								listOfCustomers.remove(0);
							}
							for (int r = 1; r <= listOfAdmin.size();) {
								listOfAdmin.remove(0);
							}
						}

					}
				}

			}

		}
	}
}
