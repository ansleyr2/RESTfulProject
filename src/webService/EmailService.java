package webService;


import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import model.ProjectManager;

@Path("/EmailService")
public class EmailService {

	@POST
	@Path("{date}/{email}/{pwd}")
	@Produces("text/plain")
	//@Consumes("application/json")
	public Response sendEmail(@PathParam("date") String date,@PathParam("email") String email,@PathParam("pwd") String pwd){
		String message=null;
		try{
			//SendEmail sendEmail= new SendEmail();
			//message = sendEmail.sendEmail(date);
			ProjectManager projectManager= new ProjectManager();
			message = projectManager.sendEmail(date,email,pwd);
		}catch(Exception e){
			System.out.println("error");
		}
		Response response = Response.status(200).entity(message).header("Access-Control-Allow-Origin", "*").build();
        return response;
	}
}

