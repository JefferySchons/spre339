package cpre339.ATMServer.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.google.gson.Gson;

import cpre339.ATMServer.MongoDBManager;
import cpre339.ATMServer.json.User;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource 
{
	@GET
	public Response listUsers()
	{
		List<User> users = MongoDBManager.instance().getCollection("user", User.class);
		List<String> usernames = new ArrayList<String>();
		
		for(User u : users)
		{
			usernames.add(u.getName());
		}
				
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("users", usernames);
		return Response.ok(new Gson().toJson(map)).build();
	}
	
	@GET
	@Path("{uid}")
	public Response getUser(@PathParam("uid") String userId)
	{
		User user = queryForUser(userId);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", user.getName());
		map.put("password", user.getPassword());
		map.put("accounts", user.getAccounts());
		
		return Response.ok(new Gson().toJson(map)).build();
	}
	
	private User queryForUser(String userId)
	{
		return MongoDBManager.instance().performQuery("user", "name", userId, User.class);
	}
	
	@GET
	@Path("{uid}/auth")
	public Response authenticateUser(@PathParam("uid") String userId, @QueryParam("password") String password)
	{
		User user = queryForUser(userId);
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(user.getPassword().equals(password))
		{
			map.put("status", 200);
		}
		else
		{
			map.put("status", 401);
		}
		
		return Response.ok(new Gson().toJson(map)).build();
	}
	
	@PUT
	@Path("{uid}")
	public Response updateUser(@PathParam("uid") String userId, User user)
	{
		MongoDBManager.instance().updateObject("user", "name", userId, user);
		return Response.ok().build();
	}
	
	@POST
	public Response addUser(User user)
	{
		MongoDBManager.instance().writeToCollection("user", user);
		return Response.ok().build();
	}
}
