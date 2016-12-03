package cpre339.ATMServer.json;

import org.bson.BsonDocument;
import org.bson.Document;

public interface DocumentObject 
{
	Document toDocument();
	BsonDocument toBsonDocument();
}
