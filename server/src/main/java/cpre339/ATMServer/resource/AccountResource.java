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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import cpre339.ATMServer.MongoDBManager;
import cpre339.ATMServer.json.Account;

@Path("/account")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AccountResource 
{
	@GET
	public Response listAccounts()
	{
		List<Account> accounts = MongoDBManager.instance().getCollection("account", Account.class);
		List<String> accountNames = new ArrayList<String>();
		
		for(Account a : accounts)
		{
			accountNames.add(a.getName());
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("accounts", accounts);
		return Response.ok(new Gson().toJson(map)).build();
	}
	
	@GET
	@Path("{aid}")
	public Response getAccount(@PathParam("aid") String accountId)
	{
		Account account = queryForAccount(accountId);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", account.getName());
		map.put("balance", account.getBalance());
		map.put("owner", account.getOwner());
		map.put("type", account.getType());
		
		return Response.ok(new Gson().toJson(map)).build();
	}
	
	private Account queryForAccount(String accountId)
	{
		return MongoDBManager.instance().performQuery("account", "name", accountId, Account.class);
	}
	
	@PUT
	@Path("{aid}")
	public Response updateAccount(@PathParam("aid") String accountId, Account account)
	{
		MongoDBManager.instance().updateObject("account", "name", accountId, account);
		return Response.ok().build();
	}
	
	@POST
	public Response addAccount(Account account)
	{
		MongoDBManager.instance().writeToCollection("account", account);
		return Response.ok().build();
	}
}
