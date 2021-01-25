package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.bean.Account;
import com.revature.daoimpl.AccountDaoImpl;
import com.revature.util.ConnFactory;

public interface AccountDao {

	public List<Account> getAllCustomers() throws SQLException;

	public List<Account> getCustomersById(int id) throws SQLException;

	public void insertAccount(String first, String last, String username, String password, int balance)
			throws SQLException;

	public void updateBal(int amount, int id) throws SQLException;

	public void delete(int id) throws SQLException;

	public void updateUsername(String username, int id) throws SQLException;

	public void updatePassword(String password, int id) throws SQLException;

	public void insertTransaction(int id, String transaction, int amount) throws SQLException;

	public void transactionHistory(int id) throws SQLException;

}
