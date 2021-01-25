package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.bean.Account;
import com.revature.bean.AdminBean;
import com.revature.dao.AdminDao;
import com.revature.util.ConnFactory;
import com.revature.daoimpl.AdminDaoImpl;

public interface AdminDao {

	public List<AdminBean> getAllAdmin() throws SQLException;

}
