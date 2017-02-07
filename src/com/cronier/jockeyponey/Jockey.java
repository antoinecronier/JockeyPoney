package com.cronier.jockeyponey;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Jockey implements IDao<Jockey> {
	private int id;
	private String lastname;
	private String firstname;
	private int weight;

	/**
	 * @return the id
	 */
	protected int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	protected void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the lastname
	 */
	protected String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname
	 *            the lastname to set
	 */
	protected void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @return the firstname
	 */
	protected String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname
	 *            the firstname to set
	 */
	protected void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return the weight
	 */
	protected int getWeight() {
		return weight;
	}

	/**
	 * @param weight
	 *            the weight to set
	 */
	protected void setWeight(int weight) {
		this.weight = weight;
	}

	public Jockey() {
	}

	public Jockey(int id, String lastname, String firstname, int weight) {
		this.id = id;
		this.lastname = lastname;
		this.firstname = firstname;
		this.weight = weight;
	}

	@Override
	public ArrayList<Jockey> getAll() {
		ArrayList<Jockey> jockeys = new ArrayList<Jockey>();
		try {
			ResultSet resultSet = MySQLAccess.getInstance().resultQuery(
					"SELECT * FROM jockey;");
			while (resultSet.next()) {
				Jockey jockey = new Jockey(resultSet.getInt("jockey_id"),
						resultSet.getString("jockey_lastname"),
						resultSet.getString("jockey_firstname"),
						resultSet.getInt("jockey_weight"));
				jockeys.add(jockey);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jockeys;
	}

	@Override
	public Jockey getById(int id) {
		try {
			ResultSet resultSet = MySQLAccess.getInstance().resultQuery(
					"SELECT * FROM jockey WHERE jockey.jockey_id = " + id + ";");
			while (resultSet.next()) {
				this.id = resultSet.getInt("jockey_id");
				this.firstname = resultSet.getString("jockey_firstname");
				this.lastname = resultSet.getString("jockey_lastname");
				this.weight = resultSet.getInt("jockey_weight");
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
			MySQLAccess.getInstance().updateQuery(
					"INSERT INTO jockey VALUES (" + this.id + ",'"
							+ this.firstname + "','" + this.lastname + "',"
							+ this.weight + ");");
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
		return "Jockey [id=" + id + ", lastname=" + lastname + ", firstname="
				+ firstname + ", weight=" + weight + "]";
	}
}
