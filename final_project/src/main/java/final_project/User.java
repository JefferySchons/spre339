package final_project;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User
{
	private String name;
	private String password;
	private List<String> accounts;
	
	public User()
	{
		accounts = new ArrayList<String>();
	}
	
	public User(String name, String password)
	{
		this.name = name;
		this.password = password;
		accounts = new ArrayList<String>();
	}
	
	@JsonProperty("name")
	public void setName(String name)
	{
		this.name = name;
	}
	
	@JsonProperty("name")
	public String getName()
	{
		return name;
	}
	
	@JsonProperty("password")
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	@JsonProperty("password")
	public String getPassword()
	{
		return password;
	}
	
	@JsonProperty("accounts")
	public void setAccounts(List<String> accounts)
	{
		this.accounts = accounts;
	}
	
	@JsonProperty("accounts")
	public List<String> getAccounts()
	{
		return accounts;
	}
}
