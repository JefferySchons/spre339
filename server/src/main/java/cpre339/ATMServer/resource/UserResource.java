package cpre339.ATMServer.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import cpre339.ATMServer.json.User;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource 
{
	@GET
	public Response listUsers()
	{
		return null;
	}
	
	@GET
	@Path("{uid}")
	public Response getUser(@PathParam("uid") String userId)
	{
		return null;
	}
	
	@GET
	@Path("{uid}/auth")
	public Response authenticateUser(@PathParam("uid") String userId, @QueryParam("password") String password)
	{
		return null;
	}
	
	@PUT
	@Path("{uid}")
	public Response updateUser(@PathParam("uid") String userId, User user)
	{
		return null;
	}
	
	@POST
	public Response addUser(User user)
	{
		return null;
	}
}
