package com.ty.controller;

import com.ty.car.dao.CarDao;
import com.ty.car.dto.Car;

public class SaveCar {

	public static void main(String[] args) {
		
		Car c = new Car();
		c.setId(1);
		c.setName("Tata Nexon");
		c.setCost(600000.98);
		
		CarDao ad = new CarDao();
		ad.saveCar(c);

	}

}
