package com.cronier.jockeyponey;

import java.util.Date;
import java.util.Map;

public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Jockey j1 = new Jockey(1, "lastname", "firstname", 50);
		j1.insert();

		Jockey j2 = new Jockey(2, "lastname1", "firstname1", 500);
		j2.insert();

		Poney p1 = new Poney(1, "poney", 500);
		p1.insert();

		Poney p2 = new Poney(2, "poney1", 500);
		p2.insert();

		Course course = new Course(1,new Date());
		course.insert();

		Courir courir1 = new Courir(course.getId(),j1.getId(),p1.getId(),0);
		courir1.insert();

		Courir courir2 = new Courir(course.getId(),j2.getId(),p2.getId(),0);
		courir2.insert();

		Courir result = new Courir();
		result.getAllByCourse(1);

		System.out.println("------------------");

		for(Map.Entry<Jockey, Poney> entry : result.getMap().entrySet()) {
		    Jockey key = entry.getKey();
		    Poney value = entry.getValue();

		    System.out.println(key);
		    System.out.println(value);
		    System.out.println("------------------");
		}
	}
}
