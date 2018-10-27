package model;

import java.sql.Connection;
import java.util.ArrayList;

import dao.Database;

import dao.Project;
import dto.Users;
import dto.Words;

public class ProjectManager {
	// Returns all the users
	public ArrayList<Users> GetUsers()throws Exception {
		Connection connection=null;
		ArrayList<Users> users = null;
		try {
			    Database database= new Database();
			    connection = database.Get_Connection();
				Project project= new Project();
				users=project.GetUsers(connection);
		
		} catch (Exception e) {
			connection.close();
			throw e;
		}
		return users;
	}
	// Returns a specific user details
	public ArrayList<Users> GetUser(String email,String pwd)throws Exception {
		Connection connection=null;
		ArrayList<Users> user = null;
		try {
			    Database database= new Database();
			    connection = database.Get_Connection();
				Project project= new Project();
				user=project.GetUser(connection,email,pwd);
		
		} catch (Exception e) {
			connection.close();
			throw e;
		}
		return user;
	}
	// Creates a new user
	public String saveUSer(String fname,String lname,String email,String pwd) throws Exception{
		Connection connection=null;
		String message="";
		try{
			Database database= new Database();
		    connection = database.Get_Connection();
			Project project= new Project();
			message=project.SaveUser(connection,fname,lname,email,pwd);
		}catch(Exception e){
			throw e;
		}
		return message;
	}
	// Saves a new word
	public String saveWord(String word,String meaning,String sentence,String uniqueId) throws Exception{
		Connection connection=null;
		String message="";
		try{
			Database database= new Database();
		    connection = database.Get_Connection();
			Project project= new Project();
			message=project.SaveWord(connection,word,meaning,sentence,uniqueId);
		}catch(Exception e){
			throw e;
		}
		return message;
	}
	// Edits a saved word
	public String editWord(String word,String meaning,String sentence,String id) throws Exception{
		Connection connection=null;
		String message="";
		try{
			Database database= new Database();
		    connection = database.Get_Connection();
			Project project= new Project();
			message=project.EditWord(connection,word,meaning,sentence,id);
		}catch(Exception e){
			throw e;
		}
		return message;
	}
	// Returns list of all words
	public ArrayList<Words> GetWords(String date)throws Exception {
		Connection connection=null;
		ArrayList<Words> wordsList= null;
		try {
			    Database database= new Database();
			    connection = database.Get_Connection();
				Project project= new Project();
				wordsList=project.GetWords(connection,date);
		
		} catch (Exception e) {
			connection.close();
			throw e;
		}
		return wordsList;
	}
	// Sends email to the user
	public String sendEmail(String date,String email,String pwd) throws Exception{
		Connection connection=null;
		String message="";
		try{
			Database database= new Database();
		    connection = database.Get_Connection();
			Project project= new Project();
			message=project.sendEmail(connection,date,email,pwd);
		}catch(Exception e){
			throw e;
		}
		return message;
	}

}
