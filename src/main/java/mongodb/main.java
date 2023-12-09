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
import org.bson.codecs.pojo.annotations.BsonProperty;

/**
 *
 * @author apple
 */
public class main {
    public MongoDatabase database;
    
    static main obj = new main();
    public main()
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
    //MongoCollection<Userdata> collection = database.getCollection(collectionName, Userdata.class);
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
    

    

   public String getName() {
      return username;
    }
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
   
    public List<Userdata> getAllUsersFromDb() {
    MongoCollection<Userdata> collection = database.getCollection("Users", Userdata.class);

    // Find all documents in the collection
    List<Userdata> allUsers = new ArrayList<>();
    collection.find().iterator().forEachRemaining(allUsers::add);

    return allUsers;
}
    
    public void deleteUserByUsername(String username) {
    MongoCollection<Userdata> collection = database.getCollection("Users", Userdata.class);

    // Create a filter to find the user by username
    Bson filter = Filters.eq("username", username);

    // Delete the user with the specified username
    DeleteResult result = collection.deleteOne(filter);

   
}
   
   
   public static class Movie {
    private int id;
    private String title;
    private boolean availability_status;
    private double rating;
    private String description;

    // Default constructor
    public Movie() {
    }

    // Parameterized constructor
    public Movie(int id, String title, boolean availability_status, double rating, String description) {
        this.id = id;
        this.title = title;
        this.availability_status = availability_status;
        this.rating = rating;
        this.description = description;
    }

    // Function to update attributes by matching the id number
    public void updateMovie(int targetId, String newTitle, boolean newAvailabilityStatus,
                            double newRating, String newDescription) {
        if (this.id == targetId) {
            this.title = newTitle;
           
            this.availability_status = newAvailabilityStatus;
            this.rating = newRating;
            this.description = newDescription;
            System.out.println("Movie updated successfully.");
        } else {
            System.out.println("Movie with ID " + targetId + " not found.");
        }
    }

    // Function to display the movie
    public void displayMovie() {
        System.out.println("Movie ID: " + id);
        System.out.println("Title: " + title);
        System.out.println("Availability Status: " + availability_status);
        System.out.println("Rating: " + rating);
        System.out.println("Description: " + description);
    }
     public String getTitle() {
        return title;
    }
     
    

   }
   
    public List<Movie> getAllMoviesFromDb() {
    MongoCollection<Movie> collection = database.getCollection("Movie", Movie.class);

    // Find all documents in the collection
    List<Movie> allMovies = new ArrayList<>();
    collection.find().iterator().forEachRemaining(allMovies::add);

    return allMovies;
}
    Movie Movies = new Movie();
     public Movie getInstanceMovie()
    {
        return Movies;
    }
     
     
      public void insertmoviedataindb(Movie data)
    {
        MongoCollection<Movie> collection = database.getCollection("Movie", Movie.class);
        collection.insertOne(data);
    }
     
     
     public static class Admin extends Userdata {
    private String adminRole;

    public Admin() {
        super();
        adminRole = null;
    }

    public Admin(String username, String password, String adminRole) {
        super(username, password);
        this.adminRole = adminRole;
    }

    public String getAdminRole() {
        return adminRole;
    }

    public void setAdminRole(String adminRole) {
        this.adminRole = adminRole;
    }

    public void displayAdminDetails() {
        System.out.println("Admin Details:");
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        System.out.println("Admin Role: " + adminRole);
    }
    
     public String getAName() {
      return username;
    }

   
}
     
     
       public Admin getadminfromdb(Userdata data) {
    MongoCollection<Admin> collection = database.getCollection("Admin", Admin.class);
    
    // Assuming Userdata has a field called 'username' that you want to use for the query
    
    Bson filter = Filters.and(
        Filters.eq("username", data.username), Filters.eq("password", data.password));

    // Find the user based on the username
    return collection.find(filter).first();
}
       
           public void insertadmindataindb(Admin data)
    {
        MongoCollection<Admin> collection = database.getCollection("Admin", Admin.class);
        collection.insertOne(data);
    }

    public void deleteAdminByUsername(String username) {
    MongoCollection<Admin> collection = database.getCollection("Admin", Admin.class);

    // Create a filter to find the user by username
    Bson filter = Filters.eq("username", username);

    // Delete the user with the specified username
    DeleteResult resul = collection.deleteOne(filter);

   
}
     
}