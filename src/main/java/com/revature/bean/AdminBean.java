package com.revature.bean;

public class AdminBean {

	private int admin_id;
	private String admin_username;
	private String admin_password;

	public AdminBean() {
		super();
	}

	public AdminBean(int admin_id, String admin_username, String admin_password) {
		super();
		this.admin_id = admin_id;
		this.admin_username = admin_username;
		this.admin_password = admin_password;
	}

	public int getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}

	public String getAdmin_username() {
		return admin_username;
	}

	public void setAdmin_username(String admin_username) {
		this.admin_username = admin_username;
	}

	public String getAdmin_password() {
		return admin_password;
	}

	public void setAdmin_password(String admin_password) {
		this.admin_password = admin_password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + admin_id;
		result = prime * result + ((admin_password == null) ? 0 : admin_password.hashCode());
		result = prime * result + ((admin_username == null) ? 0 : admin_username.hashCode());
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
		AdminBean other = (AdminBean) obj;
		if (admin_id != other.admin_id)
			return false;
		if (admin_password == null) {
			if (other.admin_password != null)
				return false;
		} else if (!admin_password.equals(other.admin_password))
			return false;
		if (admin_username == null) {
			if (other.admin_username != null)
				return false;
		} else if (!admin_username.equals(other.admin_username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AdminBean [admin_id=" + admin_id + ", admin_username=" + admin_username + ", admin_password="
				+ admin_password + "]";
	}

}
