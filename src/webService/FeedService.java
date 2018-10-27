package webService;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import model.ProjectManager;

import com.google.gson.Gson;

import dto.FeedObjects;
import dto.Users;
import dto.Words;

@Path("/WebService")
public class FeedService {
	
	@GET
	@Path("/GetUsers")
	@Produces("application/json")
	public Response feed()
	{
		String users  = null;
		try 
		{
			ArrayList<Users> userData = null;
			ProjectManager projectManager= new ProjectManager();
			userData = projectManager.GetUsers();
			Gson gson = new Gson();
			System.out.println(gson.toJson(userData));
			users = gson.toJson(userData);
		} catch (Exception e)
		{
			System.out.println("error");
		}
		//return feeds;
		 Response response = Response.status(200).
	                entity(users).
	                header("Access-Control-Allow-Origin", "*").build();

	        return response;
	}
//	@POST
//	@Path("{firstname}/{lastname}/{email}/{pwd}")
//	@Produces("text/plain")
//	//@Consumes("application/json")
//	public Response saveUser(@PathParam("firstname") String firstname,@PathParam("lastname") String lastname,@PathParam("email") String email,@PathParam("pwd") String pwd){
//		String message=null;
//		System.out.println(firstname);
//		System.out.println(lastname);
////		System.out.println(email);
//		System.out.println(pwd);
//		try{
//			ProjectManager projectManager= new ProjectManager();
//			message = projectManager.saveUSer(firstname,lastname,email,pwd);
//		}catch(Exception e){
//			System.out.println("error");
//		}
//		Response response = Response.status(200).entity(message).header("Access-Control-Allow-Origin", "*").build();
//        return response;
//	}
	@POST
	@Path("{word}/{meaning}/{uniqueId}/{sentence}")
	@Produces("text/plain")
	//@Consumes("application/json")
	public Response saveword(@PathParam("word") String word,@PathParam("meaning") String meaning,@PathParam("uniqueId") String uniqueId,@PathParam("sentence") String sentence){
		String message=null;
		System.out.println(word);
		System.out.println(meaning);
		System.out.println(uniqueId);
		System.out.println(sentence);
		try{
			ProjectManager projectManager= new ProjectManager();
			message = projectManager.saveWord(word,meaning,sentence,uniqueId);
		}catch(Exception e){
			System.out.println("error");
		}
		Response response = Response.status(200).entity(message).header("Access-Control-Allow-Origin", "*").build();
        return response;
	}
	
	@GET
	@Path("{email}/{pwd}")
	@Produces("application/json")
	//@Consumes("application/json")
	public Response getUser(@PathParam("email") String email,@PathParam("pwd") String pwd){
		String user  = null;
		System.out.println(email);
		System.out.println(pwd);
		try 
		{
			ArrayList<Users> userData = null;
			ProjectManager projectManager= new ProjectManager();
			userData = projectManager.GetUser(email,pwd);
			Gson gson = new Gson();
			System.out.println(gson.toJson(userData));
			user = gson.toJson(userData);
		} catch (Exception e)
		{
			System.out.println("error");
		}
		//return feeds;
		 Response response = Response.status(200).entity(user).header("Access-Control-Allow-Origin", "*").build();
		 return response;
	}
	
	@GET
	@Path("{date}")
	@Produces("application/json")
	//@Consumes("application/json")
	public Response getWords(@PathParam("date") String date){
		String wordListFinal  = null;
		System.out.println(date);
		//System.out.println(pwd);
		try 
		{
			ArrayList<Words> wordList = null;
			ProjectManager projectManager= new ProjectManager();
			wordList = projectManager.GetWords(date);
			Gson gson = new Gson();
			System.out.println(gson.toJson(wordList));
			wordListFinal = gson.toJson(wordList);
		} catch (Exception e)
		{
			System.out.println("error");
		}
		//return feeds;
		 Response response = Response.status(200).entity(wordListFinal).header("Access-Control-Allow-Origin", "*").build();
		 return response;
	}
}
