package cpre339.ATMServer.json;

import org.bson.BsonDocument;
import org.bson.BsonDouble;
import org.bson.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Account implements DocumentObject
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
	
	public Document toDocument()
	{
		return new Document()
				.append("owner", owner)
				.append("balance", balance)
				.append("type", type)
				.append("name", name);
	}
	
	public BsonDocument toBsonDocument()
	{
		return new BsonDocument()
				.append("balance", new BsonDouble(balance));
	}
	
	public static Account fromDocument(Document doc)
	{
		Account account = new Account();
		account.owner = doc.getString("owner");
		account.balance = doc.getDouble("balance");
		account.type = doc.getString("type");
		account.name = doc.getString("name");
		
		return account;
	}
}
