package cpre339.ATMServer;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import cpre339.ATMServer.json.Account;
import cpre339.ATMServer.json.DocumentObject;
import cpre339.ATMServer.json.User;

public class MongoDBManager 
{
	private static MongoDBManager instance;
	
	private MongoClient mongo;
	private MongoDatabase database;
	
	private MongoDBManager()
	{
		//I know this isn't secure at all, but it doesn't really matter for this class project
		MongoClientURI uri = new MongoClientURI("mongodb://user:password@ds159737.mlab.com:59737/339atm");
		mongo = new MongoClient(uri);
		database = mongo.getDatabase(uri.getDatabase());
	}
	
	public void writeToCollection(String collectionName, DocumentObject data)
	{
		MongoCollection<Document> collection = database.getCollection(collectionName);
		collection.insertOne(data.toDocument());
	}
	
	public <T extends DocumentObject> List<T> getCollection(String collectionName, final Class type)
	{
		final ArrayList<T> documents = new ArrayList<T>();
		
		MongoCollection<Document> collection = database.getCollection(collectionName);
		FindIterable<Document> iterable = collection.find();
		
		iterable.forEach(new Block<Document>()
				{
					@SuppressWarnings("unchecked")
					public void apply(Document doc) 
					{
						documents.add((T) ((type == Account.class) ? Account.fromDocument(doc) : User.fromDocument(doc)));
						
					}
				});
		
		return documents;
	}
	
	public static MongoDBManager instance()
	{
		return instance;
	}
	
	static
	{
		instance = new MongoDBManager();
	}
}
