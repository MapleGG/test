package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conn {
	static Connection con; //connection对象
	static java.sql.Statement sql; //statement对象
	static ResultSet res;
	
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			con=DriverManager.getConnection("jdbc:mysql://192.168.16.20:3306/scdsfc","root","root");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	public static void main(String[] args) {
		Conn c=new Conn();
		con=c.getConnection();
		try {
			sql=con.createStatement();
			res=sql.executeQuery("select *from employee ");
			System.out.println("工号\t姓名\t职位\t部门");
			while(res.next()) {
				String id=res.getString("employee_code");
				String name=res.getString("employee_name");
				String bumen=res.getString("dept_line");
				String zhiwei=res.getString("employee_title");
				
				
				System.out.println(id+"\t==="+name+"\t===="+bumen);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
