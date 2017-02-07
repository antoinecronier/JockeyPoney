package com.cronier.jockeyponey;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Poney implements IDao<Poney> {
	private int id;
	private String name;
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
	 * @return the name
	 */
	protected String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	protected void setName(String name) {
		this.name = name;
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

	public Poney() {

	}

	public Poney(int id, String name, int weight) {
		super();
		this.id = id;
		this.name = name;
		this.weight = weight;
	}

	@Override
	public ArrayList<Poney> getAll() {
		ArrayList<Poney> poneys = new ArrayList<Poney>();
		try {
			ResultSet resultSet = MySQLAccess.getInstance().resultQuery(
					"SELECT * FROM poney;");
			while (resultSet.next()) {
				Poney poney = new Poney(resultSet.getInt("poney_id"),
						resultSet.getString("poney_name"), resultSet.getInt("poney_weight"));
				poneys.add(poney);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return poneys;
	}

	@Override
	public Poney getById(int id) {
		try {
			ResultSet resultSet = MySQLAccess.getInstance().resultQuery(
					"SELECT * FROM poney WHERE poney.poney_id = " + id + ";");
			while (resultSet.next()) {
				this.id = resultSet.getInt("poney_id");
				this.name = resultSet.getString("poney_name");
				this.weight = resultSet.getInt("poney_weight");
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
					"INSERT INTO poney VALUES (" + this.id + ",'" + this.name
							+ "'," + this.weight + ");");
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
		return "Poney [id=" + id + ", name=" + name + ", weight=" + weight
				+ "]";
	}
}
