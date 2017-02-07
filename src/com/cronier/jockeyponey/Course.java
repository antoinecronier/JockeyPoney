package com.cronier.jockeyponey;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Course implements IDao<Course>{

	private int id;
	private Date dateCourse;
	/**
	 * @return the id
	 */
	protected int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	protected void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the dateCourse
	 */
	protected Date getDateCourse() {
		return dateCourse;
	}
	/**
	 * @param dateCourse the dateCourse to set
	 */
	protected void setDateCourse(Date dateCourse) {
		this.dateCourse = dateCourse;
	}

	public Course() {
	}

	public Course(int id, Date dateCourse) {
		super();
		this.id = id;
		this.dateCourse = dateCourse;
	}

	@Override
	public ArrayList<Course> getAll() {
		ArrayList<Course> courses = new ArrayList<Course>();
		try {
			ResultSet resultSet = MySQLAccess.getInstance().resultQuery(
					"SELECT * FROM course;");
			while (resultSet.next()) {
				Course course = new Course(resultSet.getInt("id"),
						resultSet.getDate("dateCourse"));
				courses.add(course);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return courses;
	}

	@Override
	public Course getById(int id) {
		try {
			ResultSet resultSet = MySQLAccess.getInstance().resultQuery(
					"SELECT * FROM course WHERE course.course_id = " + id + ";");
			while (resultSet.next()) {
				this.id = resultSet.getInt("course_id");
				this.dateCourse = resultSet.getDate("dateCourse");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this;
	}

	@Override
	public void insert() {
		try {
			java.text.SimpleDateFormat sdf =
			     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			String currentTime = sdf.format(this.dateCourse);

			MySQLAccess.getInstance().updateQuery(
					"INSERT INTO course VALUES (" + this.id + ",'" + currentTime
							+ "');");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Course [id=" + id + ", dateCourse=" + dateCourse + "]";
	}


}
