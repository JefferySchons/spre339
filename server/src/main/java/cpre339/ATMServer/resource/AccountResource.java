package cpre339.ATMServer.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import cpre339.ATMServer.json.Account;

@Path("/account")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AccountResource 
{
	@GET
	public Response listAccounts()
	{
		return null;
	}
	
	@GET
	@Path("{aid}")
	public Response getUser(@PathParam("aid") String accountId)
	{
		return null;
	}

	@PUT
	@Path("{aid}")
	public Response updateAccount(@PathParam("aid") String accountId, Account account)
	{
		return null;
	}
	
	@POST
	public Response addAccount(Account account)
	{
		return null;
	}
}
