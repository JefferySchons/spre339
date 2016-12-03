package cpre339.ATMServer.json;

import java.util.ArrayList;
import java.util.List;

import org.bson.BsonArray;
import org.bson.BsonDocument;
import org.bson.BsonString;
import org.bson.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User implements DocumentObject
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
	
	public Document toDocument()
	{
		return new Document()
				.append("name", name)
				.append("accounts", accounts)
				.append("password", password);
	}
	
	public BsonDocument toBsonDocument()
	{
		BsonArray array = new BsonArray();
		for(String acct : accounts)
		{
			array.add(new BsonString(acct));
		}
		
		return new BsonDocument()
				.append("accounts", array);
	}
	
	@SuppressWarnings("unchecked")
	public static User fromDocument(Document doc)
	{
		User user = new User();
		user.name = doc.getString("name");
		user.password = doc.getString("password");
		user.accounts = (List<String>) doc.get("accounts");
		
		return user;
	}
}
