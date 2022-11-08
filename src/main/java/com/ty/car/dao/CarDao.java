package com.ty.car.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ty.car.dto.Car;
import com.ty.car.helper.ConnectionObject;

public class CarDao {
	
	Connection con = ConnectionObject.getConnection();
	public void saveCar(Car c)
	{
		
		
		String query = "insert into car values(?,?,?)";
		
		try 
		{
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, c.getId());
			pstmt.setString(2, c.getName());
			pstmt.setDouble(3, c.getCost());
			
			pstmt.executeUpdate();
			
			System.out.println("Car Data Inserted");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	
	public void saveCars(List<Car> cars)
	{
		
		
		String query = "insert into car values(?,?,?)";
		
		try 
		{
			PreparedStatement pstmt = con.prepareStatement(query);
			for (Car car : cars) {
			pstmt.setInt(1, car.getId());
			pstmt.setString(2, car.getName());
			pstmt.setDouble(3, car.getCost());
			
			pstmt.addBatch();
			}
			
			pstmt.executeBatch();
			
			
			System.out.println("Car Data Inserted");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public Car getCarById(int id)
	{
		String query = "select * from car where id = ?";
		
		try {
			PreparedStatement pstmt = ConnectionObject.getConnection().prepareStatement(query);
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				Car c = new Car();
				c.setId(rs.getInt(1));
				c.setName(rs.getString(2));
				c.setCost(rs.getDouble(3));
				
				return c;
			}
			
		} catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
		return null;
	}
	
	
	List<Car> l = new ArrayList<Car>();
	public List<Car> getAllCar()
	{
		String query = "select * from car";
		
		try {
			
			PreparedStatement pstmt = ConnectionObject.getConnection().prepareStatement(query);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				Car c = new Car();
				c.setId(rs.getInt(1));
				c.setName(rs.getString(2));
				c.setCost(rs.getDouble(3));
				
				l.add(c);	
			}
			
		} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
		
		return l;
	}
	
	


}
