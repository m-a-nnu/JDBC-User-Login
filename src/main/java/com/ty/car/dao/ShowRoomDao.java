package com.ty.car.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ty.car.dto.ShowRoom;
import com.ty.car.helper.ConnectionObject;

public class ShowRoomDao {
	public void saveShowRoom(ShowRoom sr) {
		String query = "insert into showroom values(?,?,?,?,?,?)";

		try {
			PreparedStatement pstmt = ConnectionObject.getConnection().prepareStatement(query);
			pstmt.setInt(1, sr.getId());
			pstmt.setString(2, sr.getName());
			pstmt.setString(3, sr.getCity());
			pstmt.setString(4, sr.getState());
			pstmt.setString(5, sr.getEmail());
			pstmt.setLong(6, sr.getPhone());

			pstmt.execute();

			System.out.println("ShowRoom Data Inserted");
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	List<ShowRoom> l = new ArrayList<ShowRoom>();

	public List<ShowRoom> getAllByCity(String city) {
		String query = "select * from showroom where city = ?";

		try {
			PreparedStatement pstmt = ConnectionObject.getConnection().prepareStatement(query);
			pstmt.setString(1, city);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				ShowRoom sr = new ShowRoom();
				sr.setId(rs.getInt(1));
				sr.setName(rs.getString(2));
				sr.setCity(rs.getString(3));
				sr.setState(rs.getString(4));
				sr.setEmail(rs.getString(5));
				sr.setPhone(rs.getLong(6));

				l.add(sr);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return l;
	}
}
