package mongodb;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.eq;
//import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Date;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertManyResult;
import com.mongodb.client.result.UpdateResult;
//import com.mycompany.mavenproject1.FrontendGUI.Comment;
//import com.mycompany.mavenproject1.FrontendGUI.PostPanel;
//import com.mycompany.mavenproject1.FrontendGUI.PostPanel.*;
//import com.mycompany.mavenproject1.Leaderboard.*;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.bson.Document;
//import com.mongodb.client.MongoCursor;
//import com.mongodb.client.FindIterable;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

/**
 *
 * @author apple
 */
public class main {
    public MongoDatabase database;
    
    static main obj = new main();
    private main()
    {
        start();
    }
    
    public void start() 
    {
        // Start code here, similar to Start() in Unity
        Logger.getLogger( "org.mongodb.driver" ).setLevel(Level.WARNING);
    // TODO:
    //  Replace the placeholder connection string below with your
    // Altas cluster specifics. Be sure it includes
    // a valid username and password! Note that in a production environment,
    // you do not want to store your password in plain-text here.
    ConnectionString mongoUri = new ConnectionString("mongodb+srv://Rafay:123@Project1.qg6zefx.mongodb.net/?retryWrites=true&w=majority");

    // Provide the name of the database and collection you want to use.
    // If they don't already exist, the driver and Atlas will create them
    // automatically when you first write data.
    String dbName = "Project1";
    String collectionName = "Users";
    //String collectionName2 = "";
    // a CodecRegistry tells the Driver how to move data between Java POJOs (Plain Old Java Objects) and MongoDB documents
    CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
            fromProviders(PojoCodecProvider.builder().automatic(true).build()));

    // The MongoClient defines the connection to our MongoDB datastore instance (Atlas) using MongoClientSettings
    // You can create a MongoClientSettings with a Builder to configure codecRegistries, connection strings, and more
    MongoClientSettings settings = MongoClientSettings.builder()
            .codecRegistry(pojoCodecRegistry)
            .applyConnectionString(mongoUri).build();

    MongoClient mongoClient = null;
    try {
       mongoClient = MongoClients.create(settings);
    } catch (MongoException me) {
      System.err.println("Unable to connect to the MongoDB instance due to an error: " + me);
      System.exit(1);
    }

    // MongoDatabase defines a connection to a specific MongoDB database
     database = mongoClient.getDatabase(dbName);
    // MongoCollection defines a connection to a specific collection of documents in a specific database
    MongoCollection<Userdata> collection = database.getCollection(collectionName, Userdata.class);
   // MongoCollection<roomreservation> collection2 = database.getCollection(collectionName2, roomreservation.class);
    
   
    }
    
     
     
     
    
    
    
    
    public static class Userdata 
    {
   // public String name;
    public String username;
    //public String role;   
    //public String email;
    public String password;
    
    public Userdata()
    {
        username = null ;
    }
    
    public Userdata(String username, String password) 
    {
    //  this.name = name;
      this.username = username;
     // this.role = role;
     // this.email = email;
      this.password = password;

      
    }

    // empty constructor required when we fetch data from the database -- getters and setters are later used to
    // set values for member variables
    

    

//   public String getName() {
//      return name;
//    }
//
//    public void setName(String name) {
//      this.name = name;
//    }
//
//    
  }
    
      public static main getInstance()
    {
        return obj;
    }
    public void insertuserdataindb(Userdata data)
    {
        MongoCollection<Userdata> collection = database.getCollection("Users", Userdata.class);
        collection.insertOne(data);
    }
    
  

   public Userdata getuserfromdb(Userdata data) {
    MongoCollection<Userdata> collection = database.getCollection("Users", Userdata.class);
    
    // Assuming Userdata has a field called 'username' that you want to use for the query
    
    Bson filter = Filters.and(
        Filters.eq("username", data.username), Filters.eq("password", data.password));

    // Find the user based on the username
    return collection.find(filter).first();
}
}