package webService;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import model.ProjectManager;

@Path("/EditService")
public class EditService {
	@POST
	@Path("{word}/{meaning}/{sentence}/{id}")
	@Produces("text/plain")
	public Response saveword(@PathParam("word") String word,@PathParam("meaning") String meaning,@PathParam("sentence") String sentence,@PathParam("id") String id){
		String message=null;
		System.out.println(word);
		System.out.println(meaning);
		System.out.println(sentence);
		System.out.println(id);
		try{
			ProjectManager projectManager= new ProjectManager();
			message = projectManager.editWord(word,meaning,sentence,id);
		}catch(Exception e){
			System.out.println("error");
		}
		Response response = Response.status(200).entity(message).header("Access-Control-Allow-Origin", "*").build();
        return response;
	}
}
