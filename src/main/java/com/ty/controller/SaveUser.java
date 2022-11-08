package com.ty.controller;

import java.util.ArrayList;
import java.util.List;

import com.ty.car.dao.UserDao;
import com.ty.car.dto.User;

public class SaveUser {

	public static void main(String[] args) {
	
		List<User> l = new ArrayList<User>();
		/*User u = new User();
		u.setId(1);
		u.setName("Manisha");
		u.setEmail("mannu@gmail.com");
		u.setPassword("mannu");
		u.setPhone(8217698976l);*/
		
		l.add(new User(2,"Vasantha","vassu@gmail.com","vassu",8050792413l));
		l.add(new User(3,"Rajan","raj@gmail.com","raj",9945239967l));
		
		
		
		
		
		UserDao ud = new UserDao();
		//ud.saveUser(u);
		ud.saveUsers(l);

	}

}
