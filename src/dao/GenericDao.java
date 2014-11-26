package dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

public class GenericDao {
	private String databaseUrl;
	private String tableName;
	
	public GenericDao(String databaseUrl, String tableName) throws SQLException {

		DriverManager.registerDriver(new org.sqlite.JDBC());
		this.databaseUrl = databaseUrl;
		this.tableName = tableName;
	}
	
	private Object fixString(Object value) {
		return value instanceof String ? "'" + value + "'" : value;  	
	}
	
	/*Devolve toda a tabela*/
	public  ArrayList<String> all() throws SQLException {
		
		Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databaseUrl);
		Statement statement = connection.createStatement();
		
		ArrayList<String> result = new ArrayList<String>();
		ResultSet resultSet = statement.executeQuery("select * from " + tableName);
		int numberOfColumns = resultSet.getMetaData().getColumnCount();
		
		while(resultSet.next()) {
			String stringResult = "";
			int i;
			for(i = 1 ; i < numberOfColumns ; i++) {
				stringResult += resultSet.getString(i);
				stringResult += ",";
			}
			stringResult += resultSet.getString(i);
			result.add(stringResult);
		}
		
		statement.close();
		connection.close();
		return result;
	}
	
	/*Devolve todas os atributos*/
	public  ArrayList<String> all_atributo(String atributo) throws SQLException {
		
		Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databaseUrl);
		Statement statement = connection.createStatement();
		
		ArrayList<String> result = new ArrayList<String>();
		ResultSet resultSet = statement.executeQuery("select "+ atributo +" from " + tableName);
		int numberOfColumns = resultSet.getMetaData().getColumnCount();
		
		while(resultSet.next()) {
			String stringResult = "";
			int i;
			for(i = 1 ; i < numberOfColumns ; i++) {
				stringResult += resultSet.getString(i);
				stringResult += ",";
			}
			stringResult += resultSet.getString(i);
			result.add(stringResult);
		}
		
		statement.close();
		connection.close();
		return result;
	}
	

	/*Procura elementos na tabela*/
	public ArrayList<String> findBy(Map<String, Object> mapping) throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databaseUrl);
		Statement statement = connection.createStatement();
		
		int i;
		/* Where clause */
		String whereClause = "";
		Object keys[] = mapping.keySet().toArray();
		for(i = 0 ; i < keys.length - 1 ; i++) {
			whereClause = whereClause + keys[i] + " = " + fixString(mapping.get(keys[i])) + " AND ";
		}
		whereClause = whereClause + keys[i] + " = " + fixString(mapping.get(keys[i]));
		
		ArrayList<String> result = new ArrayList<String>();
		ResultSet resultSet = statement.executeQuery("select * from " + tableName + " where " + whereClause);
		int numberOfColumns = resultSet.getMetaData().getColumnCount();
		
		while(resultSet.next()) {
			String stringResult = "";
			for(i = 1 ; i < numberOfColumns ; i++) {
				stringResult += resultSet.getString(i);
				stringResult += ",";
			}
			stringResult += resultSet.getString(i);
			result.add(stringResult);
		}
		
		statement.close();
		connection.close();
		
		return result;
	}
	
	/*Insere elemento na tabela*/
	public boolean insert(Map<String, Object> mapping) throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databaseUrl);
		Statement statement = connection.createStatement();
		
		int i;
		String values = "";
		String collumns = "";
		Object keys[] = mapping.keySet().toArray();
		for(i = 0 ; i < keys.length ; i++) {
			collumns = collumns + keys[i];
			values = values + fixString(mapping.get(keys[i]));
			
			if(i < keys.length - 1)
			{
				collumns = collumns + ",";
				values = values + ",";
			}
		}
		
		boolean result = statement.execute("insert into " + tableName + " (" + collumns + ") values(" + values + ")");
		
		statement.close();
		connection.close();
		
		return result;
	}
	
	/*Remove elemento na tabela*/
	public boolean remove(Map<String, Object> mapping) throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databaseUrl);
		Statement statement = connection.createStatement();
		
		int i;
		String whereClause = "";
		Object keys[] = mapping.keySet().toArray();
		for(i = 0 ; i < keys.length ; i++) {
			whereClause = whereClause + keys[i] + " = " + fixString(mapping.get(keys[i]));
			
			if(i < keys.length - 1)
			{
				whereClause = whereClause + " AND ";
			}
		}
		
		boolean result = statement.execute("delete from " + tableName + " where " + whereClause);
		
		statement.close();
		connection.close();
		
		return result;
	}
	
	/*Update de elemento na tabela*/
	public boolean update(Map<String, Object> newValues, Map<String, Object> mapping) throws SQLException{
		Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databaseUrl);
		Statement statement = connection.createStatement();
		
		int i;
		String updateValues = "";
		Object keysValues[] = newValues.keySet().toArray();
		for(i = 0 ; i < keysValues.length ; i++) {
			updateValues = updateValues + keysValues[i] + " = " + fixString(newValues.get(keysValues[i]));
			
			if(i < keysValues.length - 1)
			{
				updateValues = updateValues + ", ";
			}
		}
		
		/* Where clause */
		String whereClause = "";
		Object keys[] = mapping.keySet().toArray();
		for(i = 0 ; i < keys.length ; i++) {
			whereClause = whereClause + keys[i] + " = " + fixString(mapping.get(keys[i]));
			
			if(i < keys.length - 1)
			{
				whereClause = whereClause + " AND ";
			}
		}
		
		boolean result = statement.execute("update " + tableName + " set " + updateValues + " where " + whereClause);
		
		statement.close();
		connection.close();
		
		return result;
	}
}
