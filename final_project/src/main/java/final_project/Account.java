package final_project;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Account
{
	private String owner;
	private double balance;
	private String type;
	private String name;
	
	public Account()
	{
		
	}
	
	public Account(String owner, double balance, String type, String name)
	{
		this.owner = owner;
		this.balance = balance;
		this.type = type;
		this.name = name;
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
	
	@JsonProperty("owner")
	public void setOwner(String owner)
	{
		this.owner = owner;
	}
	
	@JsonProperty("owner")
	public String getOwner()
	{
		return owner;
	}
	
	@JsonProperty("balance")
	public void setBalance(double balance)
	{
		this.balance = balance;
	}
	
	@JsonProperty("balance")
	public double getBalance()
	{
		return balance;
	}
	
	@JsonProperty("type")
	public void setType(String type)
	{
		this.type = type;
	}
	
	@JsonProperty("type")
	public String getType()
	{
		return type;
	}

}
