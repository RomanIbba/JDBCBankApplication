package com.revature.daoimpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.bean.Account;
import com.revature.bean.AdminBean;
import com.revature.util.ConnFactory;
import com.revature.dao.AdminDao;

public class AdminDaoImpl {
	public static ConnFactory cf = ConnFactory.getInstance();

	public List<AdminBean> getAllAdmin() throws SQLException {
		List<AdminBean> adminList = new ArrayList<AdminBean>();
		Connection conn = cf.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from \"bankadmin\"");
		AdminBean a = null;
		while (rs.next()) {
			a = new AdminBean(rs.getInt(1), rs.getString(2), rs.getString(3));
			adminList.add(a);
		}
		return adminList;
	}

}
