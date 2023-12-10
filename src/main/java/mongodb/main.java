package mongodb;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.eq;
//import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Date;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
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
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.pojo.annotations.BsonProperty;


/**
 *
 * @author apple
 */
public class main {

    public static Object getInstanceReview() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public MongoDatabase database;
    public static String userlogger ;
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

    public void insertreviewdataindb(Review data) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
    
    
    
       public static class User extends Userdata 
    {
          String fav_genre;
          SavedList saved;
    
    public User()
    {
        username = null ;
    }
    
    public User(String username, String password) 
    {
      this.username = username;
      this.password = password;

      
    }
   public String getName() {
       userlogger=username;
      return  username;
    }
  }
    
      public static main getInstance()
    {
        return obj;
    }
    public void insertuserdataindb(User data)
    {
        MongoCollection<User> collection = database.getCollection("Users", User.class);
        collection.insertOne(data);
    }
    
  

   public User getuserfromdb(User data) {
    MongoCollection<User> collection = database.getCollection("Users", User.class);
    
    // Assuming Userdata has a field called 'username' that you want to use for the query
    
    Bson filter = Filters.and(
        Filters.eq("username", data.username), Filters.eq("password", data.password));

    // Find the user based on the username
    return collection.find(filter).first();
}
   
    public List<User> getAllUsersFromDb() {
    MongoCollection<User> collection = database.getCollection("Users", User.class);

    // Find all documents in the collection
    List<User> allUsers = new ArrayList<>();
    collection.find().iterator().forEachRemaining(allUsers::add);

    return allUsers;
}
    
    public void deleteUserByUsername(String username) {
    MongoCollection<User> collection = database.getCollection("Users", User.class);

    // Create a filter to find the user by username
    Bson filter = Filters.eq("username", username);

    // Delete the user with the specified username
    DeleteResult result = collection.deleteOne(filter);

   
}
   public String getsingleUserFromDb() {
    MongoCollection<User> collection = database.getCollection("recent", User.class);

    // Find the first document in the collection
    User singleUser = collection.find().first();

    return singleUser.getName();
}
   public void addSingleValueToRecentCollection(String singleValue) {
    MongoCollection<Document> collection = database.getCollection("recent");

    // Create a document with a single field and the provided value
    Document document = new Document("username", singleValue);

    // Insert the document into the collection
    collection.insertOne(document);
}
   public void deleteSingleValueFromRecentCollection() {
    MongoCollection<Document> collection = database.getCollection("recent");

    // Delete the first document in the collection
    collection.deleteOne(new Document());
}

   
   public static class Movie {
  
    public String title;
    

    // Default constructor
    public Movie() {
    }

    // Parameterized constructor
    public Movie(String title) {
        
        this.title = title;
        
       
    }

    // Function to update attributes by matching the id number
   

    // Function to display the movie
    public void displayMovie() {
      
        System.out.println("Title: " + title);
        
    }
     public String getTitle() {
        return title;
    }
     
      

    public void setTitle(String title) {
        this.title = title;
    }

   

   }
   
    public List<Movie> getAllMoviesFromDb() {
        MongoCollection<Document> collection = database.getCollection("Movie");

        List<Movie> allMovies = new ArrayList<>();
        for (Document doc : collection.find()) {
            Movie movie = new Movie();
           
            movie.setTitle(doc.getString("title"));
            
            
            allMovies.add(movie);
        }

        return allMovies;
    }
    
    public List<Movie> getAllTimeFromDb() {
        MongoCollection<Document> collection = database.getCollection("alltimerated");

        List<Movie> allMovies = new ArrayList<>();
        for (Document doc : collection.find()) {
            Movie movie = new Movie();
           
            movie.setTitle(doc.getString("title"));
            
            
            allMovies.add(movie);
        }

        return allMovies;
    }
    
     public void removealltimebyname(String movieName) {
        MongoCollection<Document> collection = database.getCollection("alltimerated");

        // Create a filter to find the document with the specified title
        Bson filter = Filters.eq("title", movieName);

        // Delete the document that matches the filter
        DeleteResult result = collection.deleteOne(filter);

        // Check if the document was successfully deleted
    }
     
     public void insertalltimeindb(String data) {
        MongoCollection<Document> collection = database.getCollection("alltimerated");

        // Create a new document with a "title" field and set it to the provided data
        Document document = new Document("title", data);

        // Insert the document into the collection
        collection.insertOne(document);
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
    
    

    
    
     public static class Review {
   
    public String title;
    public String rating;
         
    //private Movie mov;     
    public String rev;

    
    
    
     public Review()
    {
        title = null ;
        rating=null;
    }
//    
   

    // Parameterized constructor
    public Review(String title,String rating, String rev) {
       
        this.title = title;
      
        this.rating = rating;
        this.rev = rev;
    }
  
    // Function to update attributes by matching the id number
    
     
    // Function to display the movie
    public void displayReview() {
       
        System.out.println("title: " + title);
        
        System.out.println("rating: " + rating);
        System.out.println("rev: " + rev);
    }
     public String getTitle() {
        return title;
    }
     public String getrating() {
        return rating;
    }
      public String getrev() {
        return rev;
    }

       
     }
   
     
    public List<Review> getAllReviewFromDb() {
    MongoCollection<Review> collection = database.getCollection("Review", Review.class);

    // Find all documents in the collection
    List<Review> allreview = new ArrayList<>();
    collection.find().iterator().forEachRemaining(allreview::add);

    return allreview;
}
   // Review Review = new Review();
     public static main getInstanceRevieww()
    {
        return obj;
    }
     
     
      public void insertreviewwdataindb(Review data)
    {
        MongoCollection<Review> collection = database.getCollection("Review", Review.class);
        collection.insertOne(data);
    }
      
      
     
     public void displayOrders() {
        // Assuming you have a collection named "orders_restaurant"
        MongoCollection<Review> collection3 = database.getCollection("Review", Review.class);

        // Fetch all documents from the collection
        FindIterable<Review> orders = collection3.find();

        // Prepare data for the table
        List<Review> data = new ArrayList<>();
        for (Review order : orders) {
            data.add(order);
        }

        // Define column names
        Vector<String> columnNames = new Vector<>();
        columnNames.add("Title");
         columnNames.add("rating");
        columnNames.add("rev");
        // Add more columns as needed based on your document structure

        // Create a JTable with the fetched data
        JTable table = new JTable(buildTableModel(data, columnNames));

        // Optionally, you can embed the JTable in a JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);

        // Display the table in a JFrame or another suitable container
        JFrame frame = new JFrame("addreview");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(scrollPane);
        frame.pack();
        frame.setVisible(true);
    }

    // Helper method to convert the list of restaurantHandler objects into a TableModel
    private DefaultTableModel buildTableModel(List<Review> data, Vector<String> columnNames) {
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Review order : data) {
            Vector<Object> row = new Vector<>();
            row.add(order.getTitle());
            row.add(order.getrating());
            row.add(order.getrev());
            // Add more columns as needed based on your restaurantHandler class
            model.addRow(row);
        }

        return model;
    }
   public static class SavedList {
    private User user;
    private List<Movie> movieList;
    

    public SavedList()
    {
  
    }

    public SavedList(User user, List<Movie> movieList) {
        this.user = user;
        this.movieList = movieList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }
    
    public String getuser()
    {
        return user.getName();
    }
    
    
    
} 
   
   
   public static class WishList {
    private User user;
    private List<Movie> movieList;
    

    public WishList()
    {
  
    }

    public WishList(User user, List<Movie> movieList) {
        this.user = user;
        this.movieList = movieList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }
    
    public String getuser()
    {
        return user.getName();
    }
    
    
    
} 
   
   
   
 public List<String> getsavedmovieforuser(String userlog) {
    List<String> movieList = new ArrayList<>();

    // Assuming you have a global or shared instance of the MongoDatabase named 'database'
    MongoCollection<Document> collection = database.getCollection("Users");

    Document filter = new Document("username", userlog);

    try (MongoCursor<Document> cursor = collection.find(filter).iterator()) {
        while (cursor.hasNext()) {
            Document document = cursor.next();

            if (document.containsKey("movie")) {
                Object movies = document.get("movie");
                if (movies instanceof java.util.ArrayList) {
                    java.util.ArrayList movieArray = (java.util.ArrayList) movies;
                    for (Object movie : movieArray) {
                        movieList.add(movie.toString());
                    }
                }
            }
        }
    }

    return movieList;
}
 
 public void addtosaved(String username, String newMovie) {
    // Assuming you have a global or shared instance of the MongoDatabase named 'database'
    MongoCollection<Document> collection = database.getCollection("Users");

    Document filter = new Document("username", username);

    // Check if the user exists
    Document userDocument = collection.find(filter).first();
    if (userDocument != null) {
        List<String> movieList = userDocument.get("movie", List.class);

        // If the "movie" field is null or not an array, create a new array
        if (movieList == null) {
            movieList = new ArrayList<>();
        }

        // If the list size is less than 5, add the new movie to the next available index
        if (movieList.size() < 4) {
            movieList.add(newMovie);
        } else {
            // If the list size exceeds 5, remove the first element and add the new movie
             movieList.set(0, newMovie);
        }

        // Update the user document with the modified movie list
        collection.updateOne(filter, new Document("$set", new Document("movie", movieList)));
    } else {
        System.out.println("User not found");
    }
}
 
 
 
 public List<String> getwishmovieforuser(String userlog) {
    List<String> movieList = new ArrayList<>();

    // Assuming you have a global or shared instance of the MongoDatabase named 'database'
    MongoCollection<Document> collection = database.getCollection("Users");

    Document filter = new Document("username", userlog);

    try (MongoCursor<Document> cursor = collection.find(filter).iterator()) {
        while (cursor.hasNext()) {
            Document document = cursor.next();

            if (document.containsKey("wlist")) {
                Object movies = document.get("wlist");
                if (movies instanceof java.util.ArrayList) {
                    java.util.ArrayList movieArray = (java.util.ArrayList) movies;
                    for (Object movie : movieArray) {
                        movieList.add(movie.toString());
                    }
                }
            }
        }
    }

    return movieList;
}
 
 public void addtowish(String username, String newMovie) {
    // Assuming you have a global or shared instance of the MongoDatabase named 'database'
    MongoCollection<Document> collection = database.getCollection("Users");

    Document filter = new Document("username", username);

    // Check if the user exists
    Document userDocument = collection.find(filter).first();
    if (userDocument != null) {
        List<String> movieList = userDocument.get("wlist", List.class);

        // If the "movie" field is null or not an array, create a new array
        if (movieList == null) {
            movieList = new ArrayList<>();
        }

        // If the list size is less than 5, add the new movie to the next available index
        if (movieList.size() < 4) {
            movieList.add(newMovie);
        } else {
            // If the list size exceeds 5, remove the first element and add the new movie
             movieList.set(0, newMovie);
        }

        // Update the user document with the modified movie list
        collection.updateOne(filter, new Document("$set", new Document("wlist", movieList)));
    } else {
        System.out.println("User not found");
    }
}
 
 
 public void removeFromlists(String username, String movieToRemove , String key) {
    // Assuming you have a global or shared instance of the MongoDatabase named 'database'
    MongoCollection<Document> collection = database.getCollection("Users");

    Document filter = new Document("username", username);

    // Check if the user exists
    Document userDocument = collection.find(filter).first();
    if (userDocument != null) {
        List<String> movieList = userDocument.get(key, List.class);

        // If the "movie" field is null or not an array, do nothing
        if (movieList != null && !movieList.isEmpty()) {
            // Remove the first occurrence of the movieToRemove string from the list
            movieList.remove(movieToRemove);

            // Update the user document with the modified movie list
            collection.updateOne(filter, new Document("$set", new Document(key, movieList)));
        } else {
            System.out.println("Movie list is empty or null");
        }
    } else {
        System.out.println("User not found");
    }
}
 
 public int changePassword(String username, String password, String newPassword) {
    // Assuming you have a global or shared instance of the MongoDatabase named 'database'
    MongoCollection<Document> collection = database.getCollection("Users");

    Document filter = new Document("username", username)
            .append("password", password);

    // Check if the user with the specified username and password exists
    Document userDocument = collection.find(filter).first();
    if (userDocument != null) {
        // Update the user document with the new password
        collection.updateOne(filter, new Document("$set", new Document("password", newPassword)));
        return 1; // Password successfully changed
    } else {
        return 0; // User not found or password doesn't match
    }
}








}
