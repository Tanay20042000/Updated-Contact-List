package com.techlab.finalpro.test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ContactListTest 
{
	public static void main(String[] args) throws InstantiationException,IllegalAccessException,ClassNotFoundException,SQLException
	{
		String Head;
		String Tail;
		String Phone;
		String EMail;
		
		String choice;
		
		Scanner s = new Scanner(System.in);
		
		do
		{
			System.out.println("Hello User,Let me first present me your options : ");
			System.out.println("1. Add : Adds your required contact in your contact table .");
			System.out.println("2. Delete : Deletes your required contact from your contact table .");
			System.out.println("3. View : Views your required contacts from your contact table .");
			System.out.println("4. Exit : Exits your processess of your contact table .");
			System.out.println("5. Update : Updates the name by the phone number .");
			System.out.println("Now User you can enter your required option : ");
			choice = s.next();
			
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver");
				
			conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/swabhav techlabs","root","root");
			
			switch(choice)
			{
			case "Add":
			
				System.out.println("Enter the first name of the contact : ");
				Head = s.next();
				System.out.println("Enter the Surname of the contact : ");
				Tail = s.next();
				System.out.println("Enter the phone number of the contact : ");
				Phone = s.next();
				System.out.println("Enter the E Mail ID of the contact : ");
				EMail = s.next();
		
				try
				{
					System.out.println(Head + " " + Tail + " has been added to your contact table .");
					java.sql.PreparedStatement stmt = conn.prepareStatement("insert into contacttable2(fname,lname,pnumber,email) " + "values(?,?,?,?);");
					stmt.setString(1, Head);
					stmt.setString(2, Tail);
					stmt.setString(3, Phone);
					stmt.setString(4, EMail);
						
					stmt.execute();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
				
				finally
				{
					conn.close();
				}
				break;
			
			case "Delete":
			
				System.out.println("Tell me user which contact needs to be deleted from the contact table : ");
				Head = s.next();
				
				System.out.println(Head + " has been deleted from your contact list .");
				try
				{
					java.sql.PreparedStatement stmt1 = conn.prepareStatement("delete from contacttable2 where fname = ?");
					stmt1.setString(1, Head);
					stmt1.execute();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
				
				finally
				{
					conn.close();
				}
				break;
				
			case "View":
			
				System.out.println("\nYour contact information is as shown as below : \n");
				try
				{
					java.sql.Statement stmt11 = conn.createStatement();
					ResultSet rs = stmt11.executeQuery("select * from contacttable2");
					
					System.out.println("First Name     Last Name     Phone Number     Email ID");
						
					while(rs.next())
					{
						System.out.println(rs.getString(1) + "           " + rs.getString(2) + "          " + rs.getString(3) + "     " + rs.getString(4));
					}
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
				
				finally
				{
					conn.close();
				}
				break;
				
			case "Update":
				
				try
				{
					System.out.println("Enter the phone number of whose the contact needs to be updated : ");
					String change = s.next();
					System.out.println("Enter the modified first name : ");
					String newFname = s.next();
					System.out.println("Enter the modified surname : ");
					String newLname = s.next();
					System.out.println("Enter the modified emil id : ");
					String newEmail = s.next();
					
					java.sql.PreparedStatement stmt1 = conn.prepareStatement("update contacttable2 set fname=?,lname=?,email=? where pnumber=?");
					stmt1.setString(1, newFname);
					stmt1.setString(2, newLname);
					stmt1.setString(3, newEmail);
					stmt1.setString(4, change);
					stmt1.execute();
					System.out.println(newFname+" "+newLname+"'s information has been updated successfully .");
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
				
				finally
				{
					conn.close();
				}
				break;
				
			
			case "Exit":
			
				System.out.println("Thank You User for your responses . Hope you enjoyed it .");
				break;
			
			
			default:
			
				System.out.println("Sorry user , Invalid choice . Please change it .");
				break;
			}
		}while(choice != "Exit");	
		}
}
