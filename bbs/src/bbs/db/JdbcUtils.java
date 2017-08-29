package bbs.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtils {

	/**
	 * 释放一个Connection
	 * @param connection
	 */
	public static void releaseConnection(Connection connection){
		try{
			if(connection != null){
				connection.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void releaseOther(ResultSet resultSet,PreparedStatement preparedStatement){
		try{
			if(resultSet != null){
				resultSet.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		try{
			if(preparedStatement != null){
				preparedStatement.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private static DataSource dataSource = null;
	
	static{
		dataSource = new ComboPooledDataSource("bbs");
	}
	
	/**
	 * 返回数据源的一个Connection对象
	 * @return
	 * @throws SQLException 
	 */
	public static Connection getConnection() throws SQLException{
//		String driverClass = "com.mysql.jdbc.Driver";
//		String url = "jdbc:mysql://localhost:3306/atguigu";
//		String user = "root";
//		String password = "Betough3";
//		Connection connection= null;
//		try{
//			Class.forName(driverClass);
//			
//			connection = DriverManager.getConnection(url, user, password);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
		
		return dataSource.getConnection();
	}
}
