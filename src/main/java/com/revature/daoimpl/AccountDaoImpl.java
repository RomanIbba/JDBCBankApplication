package com.revature.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.bean.Account;
import com.revature.dao.AccountDao;
import com.revature.util.ConnFactory;

public class AccountDaoImpl implements AccountDao {

	public static ConnFactory cf = ConnFactory.getInstance();

	// TODO figure out issue @Override
	public List<Account> getAllCustomers() throws SQLException {
		List<Account> customerList = new ArrayList<Account>();
		Connection conn = cf.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from \"bankaccount\"");
		Account a = null;
		while (rs.next()) {
			a = new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getInt(6), rs.getInt(7));
			customerList.add(a);
		}
		return customerList;
	}

	// @Override
	public List<Account> getCustomersById(int id) throws SQLException {
		List<Account> customerList = new ArrayList<Account>();
		Connection conn = cf.getConnection();
		String sql = "select * from \"bankaccount\" where \"customer_id\" = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		Account a = null;
		while (rs.next()) {
			a = new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getInt(6), rs.getInt(7));
			customerList.add(a);
		}
		return customerList;
	}

	public void insertAccount(String first, String last, String username, String password, int balance)
			throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "insert into bankaccount values(nextval (\'custseq\'),?,?,?,?,?, nextval(\'accseq\'))";
//		String sql = "insert into bankaccount values(?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, first);
		ps.setString(2, last);
		ps.setString(3, username);
		ps.setString(4, password);
		ps.setInt(5, balance);
		ps.executeUpdate();
	}

	public void updateBal(int amount, int id) throws SQLException {
		Connection conn = cf.getConnection();

		String sql = "update bankaccount set balance= ? where customer_id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, amount);
		ps.setInt(2, id);
		ps.executeUpdate();

	}

	public void delete(int id) throws SQLException {
		Connection conn = cf.getConnection();

		String sql = "delete from bankaccount where customer_id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ps.executeUpdate();
	}

	public void updateUsername(String username, int id) throws SQLException {
		Connection conn = cf.getConnection();

		String sql = "update bankaccount set username= ? where customer_id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		ps.setInt(2, id);
		ps.executeUpdate();

	}

	public void updatePassword(String password, int id) throws SQLException {
		Connection conn = cf.getConnection();

		String sql = "update bankaccount set password= ? where customer_id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, password);
		ps.setInt(2, id);
		ps.executeUpdate();

	}

	public void insertTransaction(int id, String transaction, int amount) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "insert into transactionhist values(nextval (\'transeq\'),?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		// ps.setInt(1, customer_id);
		ps.setInt(1, id);
		ps.setString(2, transaction);
		ps.setInt(3, amount);
		ps.executeUpdate();
	}

	public void transactionHistory(int id) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "select * from \"transactionhist\" where \"id\" = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();

		Account a = null;
		while (rs.next()) {
			rs.getInt(1);
			System.out.println("Customer id: " + rs.getInt(2) + " --" + rs.getString(3) + " " + rs.getInt(4));
			System.out.println("===================================");
		}
	}

}
