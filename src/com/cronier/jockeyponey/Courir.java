package com.cronier.jockeyponey;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Courir {

	private int course_id;
	private int jockey_id;
	private int poney_id;
	private int rank;

	private Course course = new Course();
	private Map<Jockey, Poney> map = new HashMap<Jockey, Poney>();

	/**
	 * @return the course
	 */
	protected Course getCourse() {
		return course;
	}

	/**
	 * @param course
	 *            the course to set
	 */
	protected void setCourse(Course course) {
		this.course = course;
	}

	/**
	 * @return the map
	 */
	protected Map<Jockey, Poney> getMap() {
		return map;
	}

	/**
	 * @param map
	 *            the map to set
	 */
	protected void setMap(Map<Jockey, Poney> map) {
		this.map = map;
	}

	/**
	 * @return the course_id
	 */
	protected int getCourse_id() {
		return course_id;
	}

	/**
	 * @param course_id
	 *            the course_id to set
	 */
	protected void setCourse_id(int course_id) {
		this.course_id = course_id;
	}

	/**
	 * @return the jockey_id
	 */
	protected int getJockey_id() {
		return jockey_id;
	}

	/**
	 * @param jockey_id
	 *            the jockey_id to set
	 */
	protected void setJockey_id(int jockey_id) {
		this.jockey_id = jockey_id;
	}

	/**
	 * @return the poney_id
	 */
	protected int getPoney_id() {
		return poney_id;
	}

	/**
	 * @param poney_id
	 *            the poney_id to set
	 */
	protected void setPoney_id(int poney_id) {
		this.poney_id = poney_id;
	}

	/**
	 * @return the rank
	 */
	protected int getRank() {
		return rank;
	}

	/**
	 * @param rank
	 *            the rank to set
	 */
	protected void setRank(int rank) {
		this.rank = rank;
	}

	public Courir() {

	}

	public Courir(int course_id, int jockey_id, int poney_id, int rank) {
		super();
		this.course_id = course_id;
		this.jockey_id = jockey_id;
		this.poney_id = poney_id;
		this.rank = rank;
	}

	public void getAllByCourse(int courseId) {
		try {
			ResultSet resultSet = MySQLAccess
					.getInstance()
					.resultQuery(
							"SELECT * FROM courir "
									+ "INNER JOIN course ON courir.id_Course = course.course_id "
									+ "WHERE courir.id_Course = " + courseId
									+ ";");
			while (resultSet.next()) {
				this.course.setId(resultSet.getInt("course_id"));
				this.course.setDateCourse(resultSet.getDate("dateCourse"));
			}

			ResultSet resultSetList = MySQLAccess.getInstance().resultQuery(
					"SELECT jockey_id, lastname, firstname,  FROM courir, jockey, poney "
							+ "WHERE courir.id_Course = " + courseId + ";");
			while (resultSetList.next()) {
				map.put(new Jockey(resultSet.getInt("jockey_id"), resultSet
						.getString("jockey_lastname"), resultSet
						.getString("jockey_firstname"), resultSet.getInt("jockey_weight")),
						new Poney(resultSet.getInt("poney_id"), resultSet
								.getString("poney_name"), resultSet.getInt("poney_weight")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void insert() {
		try {
			MySQLAccess.getInstance().updateQuery(
					"INSERT INTO courir VALUES (null," + this.jockey_id + ","
							+ this.course_id + "," + this.poney_id + ");");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void updateRank(int idJockey, int idCourse, int idPoney, int rank) {
		try {
			MySQLAccess.getInstance().updateQuery(
					"UPDATE courir SET rank = " + rank
							+ " WHERE courir.id_Jockey = " + idJockey
							+ " courir.id_Course = " + idCourse
							+ " courir.id_Poney = " + idPoney + ";");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Courir [course_id=" + course_id + ", jockey_id=" + jockey_id
				+ ", poney_id=" + poney_id + ", rank=" + rank + "]";
	}

}
