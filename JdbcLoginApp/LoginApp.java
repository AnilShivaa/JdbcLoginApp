package com.anil.loginApp_To_Show_Sql_Injection_In_Statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class LoginApp {


	public static void main(String[] args) {
		
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		
		try {
			sc=new Scanner(System.in);
			String un=null;
			String pw=null;
			if(sc!=null) {
				System.out.println("Enter user name: ");
				un=sc.next();
				
				System.out.println("Enter user password: ");
				pw=sc.next();
			}
			//convert user input 
			un="'"+un+"'";
			pw="'"+pw+"'";
			
			oracle.jdbc.driver.OracleDriver driver=new oracle.jdbc.driver.OracleDriver();
			
			DriverManager.registerDriver(driver);
			
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "anil", "anilg");
			
			if(con!=null) {
				st=con.createStatement();
			}
			
			//SELECT COUNT(*) FROM loginapp WHERE username='raja' and password='rani';
			String qry="SELECT COUNT(*) FROM loginapp WHERE username="+un+" and password="+pw+" ";
			System.out.println(qry);
			System.out.println("\n");
		
			if(st!=null) {
				rs=st.executeQuery(qry);
			}
			
			int count=0;
			if(rs!=null) {
				if(rs.next()) {
					count=rs.getInt(1);
				}
			}
			
			if(count==0) {
				System.out.println("Invalid username or password");
			}
			else {
				System.out.println("Login Success");
			}
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}//catch
		
		finally {
			try {
				if(rs!=null)
					rs.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}//rs close
			try {
				if(st!=null)
					st.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}//st close
			try {
				if(con!=null)
					con.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}//con close
			try {
				if(sc!=null)
					sc.close();
			}catch(Exception se) {
				se.printStackTrace();
			}//sc close
			
		}//finally
		
	}//main

}//class
