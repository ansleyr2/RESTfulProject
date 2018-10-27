package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import dto.Users;
import dto.Words;
import dao.SendEmail;


public class Project {
	public ArrayList<Users> GetUsers(Connection connection) throws Exception
	{
		ArrayList<Users> userData = new ArrayList<Users>();
		try
		{
			//this.SaveUser(connection);
			Statement s = connection.createStatement();
			String selTable = "SELECT * FROM Users";
	        s.execute(selTable);
	        ResultSet rs = s.getResultSet();
			
			while(rs.next())
			{
				Users user = new Users();
				user.setEmail(rs.getString("email"));
				user.setFirstName(rs.getString("firstName"));
				user.setLastName(rs.getString("lastName"));
				user.setPassword(rs.getString("password"));
				userData.add(user);
			}
			s.close();
			connection.close();
			return userData;
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	public ArrayList<Users> GetUser(Connection connection,String email,String pwd) throws Exception
	{
		ArrayList<Users> userData = new ArrayList<Users>();
		try
		{
	        Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM Users where email = '"+email+"' and password = '"+pwd+"'");
			
			while(rs.next())
			{
				Users user = new Users();
				user.setEmail(rs.getString("email"));
				user.setFirstName(rs.getString("firstName"));
				user.setLastName(rs.getString("lastName"));
				user.setPassword(rs.getString("password"));
				userData.add(user);
			}
			s.close();
			connection.close();
			return userData;
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	public String SaveUser(Connection connection,String fname,String lname,String email,String pwd) throws Exception
	{
		/*String name= "Leonard";
		String lname="Dlima";
		String pwd="Leo@123";
		String email="dlima006@gmail.com";*/
		Boolean existingUser=this.checkExistingUser(connection,email);
		if(!existingUser){
			int rowCount = this.GetCount(connection);
			int id= rowCount+1;
			try{
				Statement s = connection.createStatement();
				String insertUser="INSERT INTO Users VALUES('"+id+"','"+fname+"','"+lname+"','"+pwd+"','"+email+"')"; 
				s.execute(insertUser);
				s.close();
				connection.close();
				System.out.println("Signup successfull");
				return "Signup successfull";
			}catch(Exception e){
				throw e;
			}
		}else{
			connection.close();
			System.out.println("User with email '"+email+"' already exists");
			return "User with email '"+email+"' already exists";
		}	
	}
	public String SaveWord(Connection connection,String word,String meaning,String sentence,String id) throws Exception
	{
		Boolean existingWord=this.checkExistingWord(connection,word);
		if(!existingWord){
			Date date = new Date();
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            //to convert Date to String, use format method of SimpleDateFormat class.
            String r_date = dateFormat.format(date);
            System.out.println("Date converted to String: " + r_date);
			try{
				Statement s = connection.createStatement();
				String insertWord="INSERT INTO GreWords (word_id,word,meaning,sentence,record_date) values('"+id+"','"+word+"','"+meaning+"','"+sentence+"','"+r_date+"')";
				String insIntoWord="Insert into Words(word_id,word,record_date) values('"+id+"','"+word+"','"+r_date+"')";
				String insIntoMeaning="Insert into Meanings(meaning_id,meaning,record_date) values('"+id+"','"+meaning+"','"+r_date+"')";
				String insIntoSentence="Insert into Sentence(sentence_id,sentence,record_date) values('"+id+"','"+sentence+"','"+r_date+"')";
				
				System.out.println(insertWord);
				s.execute(insertWord);
				s.execute(insIntoWord);
				s.execute(insIntoMeaning);
				s.execute(insIntoSentence);
				s.close();
				connection.close();
				System.out.println("Word Saved");
				return "Saved";
			}catch(Exception e){
				throw e;
			}
		}else{
			connection.close();
			System.out.println("Word '"+word+"' already exists");
			return "Word '"+word+"' already exists";
		}	
	}
	
	public String EditWord(Connection connection,String word,String meaning,String sentence,String id) throws Exception
	{
		try{
			Statement s = connection.createStatement();
			String updateWord="UPDATE GreWords set meaning = '"+meaning+"',sentence = '"+sentence+"' where word_id='"+id+"'";
			String updIntoMeaning="UPDATE Meanings set meaning='"+meaning+"' where meaning_id='"+id+"'";
			String updIntoSentence ="UPDATE Sentence set sentence='"+sentence+"' where sentence_id='"+id+"'";
			
			System.out.println(updateWord);
			s.execute(updateWord);
			System.out.println(updIntoMeaning);
			s.execute(updIntoMeaning);
			System.out.println(updIntoSentence);
			s.execute(updIntoSentence);
			s.close();
			connection.close();
			System.out.println("Word Saved");
			return "Saved";
		}catch(Exception e){
			throw e;
		}
	}
	
	public int GetCount(Connection connection) throws Exception{
		int count=0;
		try{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery("SELECT COUNT(*) AS COUNT FROM Users");
	        while(rs.next()){
	        	count = rs.getInt("COUNT");
	        }
	        s.close();
			return count;
		}catch(Exception e){
			throw e;
		}
	}
	public boolean checkExistingUser(Connection connection,String email) throws Exception{
		Boolean userExists = false;
		try{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery("SELECT COUNT(*) AS COUNT FROM Users where email = '"+email+"'");
			 while(rs.next()) {
			    if(rs.getInt("COUNT") > 0){
					userExists= true;	
				}
			 }
			System.out.println(userExists);
			s.close();
			return userExists;
		}catch(Exception e){
			throw e;
		}
	}
	public boolean checkExistingWord(Connection connection,String word) throws Exception{
		Boolean wordExists = false;
		try{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery("SELECT COUNT(*) AS COUNT FROM GreWords where word = '"+word+"'");
			 while(rs.next()) {
			    if(rs.getInt("COUNT") > 0){
					wordExists= true;	
				}
			 }
			System.out.println(wordExists);
			s.close();
			return wordExists;
		}catch(Exception e){
			throw e;
		}
	}
	
	public ArrayList<Words> GetWords(Connection connection,String date) throws Exception
	{
		ArrayList<Words> wordsList = new ArrayList<Words>();
		boolean dateRange=false;
		boolean wordSearch=false;
		boolean byAlphabet=false;
		int Page_Number=0;
		int Row_Per_Page=0;
		int TotRows=0;
		int TotRowsForSelect=0;
		String startDate=null,endDate=null;
		if(date.length() == 1){
			byAlphabet=true;
		}else if(date.indexOf(':') >= 0){
			dateRange=true;
			String datesArray[]=date.split(":");
			startDate=datesArray[0];
			endDate=datesArray[1];
			if(datesArray.length == 2){
				Page_Number=0;
				Row_Per_Page=0;
			}else{
				Page_Number=Integer.parseInt(datesArray[2]);
				Row_Per_Page=Integer.parseInt(datesArray[3]);
			}	
		}else{
			if(date.indexOf('-') >=0){
				wordSearch=false;
			}else{
				wordSearch=true;
			}
		}
		
		try{
			Statement s = connection.createStatement();
			ResultSet rs = null;
			if(Page_Number == 0){
				if(byAlphabet){
					String q="SELECT word_id,word,meaning,sentence FROM GreWords where word like '"+ date +"%'"; 
					rs = s.executeQuery(q);
				}else if(dateRange){
					rs = s.executeQuery("SELECT word_id,word,meaning,sentence FROM GreWords where CDate(record_date) between #"+startDate+"# and #"+endDate+"#");
				}else{
					if(wordSearch){
						String q="SELECT word_id,word,meaning,sentence FROM GreWords where word = '"+ date +"'"; 
						rs = s.executeQuery(q);
					}else{
						rs = s.executeQuery("SELECT word_id,word,meaning,sentence FROM GreWords where record_date = #"+date+"#");
					}
				}
				while(rs.next()) {
					Words word = new Words();
					word.setWord_id(rs.getString("word_id"));
					word.setWord(rs.getString("word"));
					word.setMeaning(rs.getString("meaning"));
					word.setSentence(rs.getString("sentence"));
					wordsList.add(word);
				}
			}else{
				if(dateRange){
					ResultSet rs2 = null;
					rs2 = s.executeQuery("SELECT COUNT(*) AS COUNT FROM GreWords where CDate(record_date) between #"+startDate+"# and #"+endDate+"#");
					while(rs2.next()) {
						//System.out.println("in");
						TotRows=rs2.getInt("COUNT");
					}
					TotRowsForSelect= (TotRows - ((Page_Number - 1) * Row_Per_Page));
					String selTable="Select TOP "+Row_Per_Page+" * FROM (Select  TOP "+TotRowsForSelect+" * From GreWords where record_date between #"+startDate+"# and #"+endDate+"# order by word_id desc) order by word_id asc";
					s.execute(selTable);
			        ResultSet rs1 = s.getResultSet();
					System.out.println(rs1);
					while(rs1.next())
					{
						//System.out.println("in1");
						Words word = new Words();
						word.setWord_id(rs1.getString("word_id"));
						word.setWord(rs1.getString("word"));
						word.setMeaning(rs1.getString("meaning"));
						word.setSentence(rs1.getString("sentence"));
						wordsList.add(word);
					}
				}else{
					rs = s.executeQuery("SELECT word_id,word,meaning,sentence FROM GreWords where record_date = #"+date+"#");
					while(rs.next()) {
						Words word = new Words();
						word.setWord_id(rs.getString("word_id"));
						word.setWord(rs.getString("word"));
						word.setMeaning(rs.getString("meaning"));
						word.setSentence(rs.getString("sentence"));
						wordsList.add(word);
					}
				}
				
			}
			s.close();
	        connection.close();
			return wordsList;
		}catch(Exception e){
			throw e;
		}
	}
	
	public String sendEmail(Connection connection,String date,String email,String pwd) throws Exception{
		String template="";
		String status="";
		try{
			template=generateTemplate(connection,date);
		}catch(Exception e){
			throw e;
		}
		if(template == "no_words_found"){
			status="No words found";
		}else{
			SendEmail sendEmail=new SendEmail();
			status = sendEmail.sendEmail(template,date,email,pwd);
		}
		
		return status;
	}
	
	public String generateTemplate(Connection connection,String date) throws Exception{
		String template="<ul>";
		boolean wordsFound=false;
		try{
			Statement s = connection.createStatement();
			ResultSet rsForCount = null;
			ResultSet rsForTemplate = null;
			rsForCount= s.executeQuery("SELECT COUNT(*) AS COUNT FROM GreWords where record_date = #"+date+"#");
			while(rsForCount.next()) {
			    if(rsForCount.getInt("COUNT") > 0){
					wordsFound= true;	
				}
			 }
			
			if(wordsFound){
				rsForTemplate = s.executeQuery("SELECT word_id,word,meaning,sentence FROM GreWords where record_date = #"+date+"#");
				while(rsForTemplate.next()) {
		//			word.setWord_id(rs.getString("word_id"));
		//			word.setWord(rs.getString("word"));
		//			word.setMeaning(rs.getString("meaning"));
		//			word.setSentence(rs.getString("sentence"));
					
					String EachWord="<li>"+ rsForTemplate.getString("word") +"</li>";
					template=template+EachWord;
		
				}
				template=template+"</ul>";
				s.close();
		        connection.close();
				return template;
			}else{
				return "no_words_found";
			}
		}catch(Exception e){
			throw e;
		}
	}
}
