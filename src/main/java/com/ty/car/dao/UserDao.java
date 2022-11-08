package com.ty.car.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ty.car.dto.User;
import com.ty.car.helper.ConnectionObject;
import com.ty.car.helper.EncryptDecryptAlgo;

public class UserDao {

	public void saveUser(User u) {
		Connection con = ConnectionObject.getConnection();

		String query = "insert into user values(?,?,?,?,?)";

		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, u.getId());
			pstmt.setString(2, u.getName());
			pstmt.setString(3, u.getEmail());
			pstmt.setString(4, EncryptDecryptAlgo.encrypt(u.getPassword()));
			pstmt.setLong(5, u.getPhone());

			pstmt.executeUpdate();

			System.out.println("User Data Inserted");

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void saveUsers(List<User> users) {
		Connection con = ConnectionObject.getConnection();

		String query = "insert into user values(?,?,?,?,?)";

		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			for (User u : users) {
				pstmt.setInt(1, u.getId());
				pstmt.setString(2, u.getName());
				pstmt.setString(3, u.getEmail());
				pstmt.setString(4, EncryptDecryptAlgo.encrypt(u.getPassword()));
				pstmt.setLong(5, u.getPhone());

				pstmt.addBatch();
			}

			pstmt.executeBatch();

			System.out.println("User Data Inserted");

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public User getUserById(int id) {
		String query = "select * from user where id = ?";

		try {
			PreparedStatement pstmt = ConnectionObject.getConnection().prepareStatement(query);
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				User u = new User();
				u.setId(rs.getInt(1));
				u.setName(rs.getString(2));
				u.setEmail(rs.getString(3));
				u.setPassword(rs.getString(4));
				u.setPhone(rs.getLong(5));

				return u;
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;
	}

	List<User> l = new ArrayList<User>();

	public List<User> getAllUsers() {
		String query = "select * from user";

		try {

			PreparedStatement pstmt = ConnectionObject.getConnection().prepareStatement(query);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				User u = new User();
				u.setId(rs.getInt(1));
				u.setName(rs.getString(2));
				u.setEmail(rs.getString(3));
				u.setPassword(rs.getString(4));
				u.setPhone(rs.getLong(5));

				l.add(u);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return l;
	}

	public boolean validateUser(String email, String password) {
		String query = "select * from user where email = ? and password = ?";
		try {
			PreparedStatement pstmt = ConnectionObject.getConnection().prepareStatement(query);
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			return rs.next();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean validateUser(long phone, String password) {
		String query = "select * from user where phone = ? and password = ?";
		try {
			PreparedStatement pstmt = ConnectionObject.getConnection().prepareStatement(query);
			pstmt.setLong(1, phone);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
