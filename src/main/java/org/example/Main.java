package org.example;
import com.google.gson.Gson;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.set;

public class Main {
    public static void main(String[] args) {

        // Connect to the database
        try (MongoClient client = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = client.getDatabase("dbtp");
            

            // Get collections
            MongoCollection<Document> moviesCollection = database.getCollection("movies");
            MongoCollection<Document> moviesRefCollection = database.getCollection("moviesRef");
            MongoCollection<Document> artistsCollection = database.getCollection("artists");

            // verify the connection
            System.out.println("Connected to database: " + database.getName());
            System.out.println("Movies Collection: " + moviesCollection.getNamespace());
            System.out.println("MoviesRef Collection: " + moviesRefCollection.getNamespace());
            System.out.println("Artists Collection: " + artistsCollection.getNamespace());

            //  Fetching documents from the movies collection
            try (MongoCursor<Document> cursor = moviesCollection.find().iterator()) {
                System.out.println("Movies Collection: \n" );

                while (cursor.hasNext()) {
                    Document moviesDoc = cursor.next();
                    System.out.println(moviesDoc.toJson());
                }
            }

            //  Fetching documents from the moviesRef collection
            try (MongoCursor<Document> cursor = moviesCollection.find().iterator()) {
                System.out.println("MoviesRef Collection: \n" );
                while (cursor.hasNext()) {
                    Document moviesRefDoc = cursor.next();
                    System.out.println(moviesRefDoc.toJson());
                }
            }



            //  Fetching documents from the artists collection
            try (MongoCursor<Document> cursor = artistsCollection.find().iterator()) {
                while (cursor.hasNext()) {
                    System.out.println("Artists Collection: \n" );
                    Document artistDoc = cursor.next();
                    System.out.println(artistDoc.toJson());
                }
            }


        //client connecting check
         try{
             Document ping = database.runCommand(new Document("ping", 1));
             if((double)ping.get("ok")==1.0)
             {System.out.println("client connected");}
             else{System.out.println("error client connecting");}
         }catch (Exception e) {
             e.printStackTrace();
         }





            // Insertion d’un document : premiere methode

             Document document = new Document(" id", 200).append("title", "Logan");
             moviesCollection.insertOne(document);
             if (moviesCollection.countDocuments() > 0) {
                 System.out.println("New line added successfully");
                 System.out.println( document.toJson());
             } else {
                 System.out.println("Error adding new line");
             }

            // Insertion : deuxieme methode
             Gson gson = new Gson();
             Movie movie = new Movie("movie:100", "2017", 192, "drama", "", "","", new ArrayList<>());
             moviesCollection.insertOne(Document.parse(gson.toJson(movie)));
             if (moviesCollection.countDocuments() > 0) {
                 System.out.println("New line added successfully");
                 System.out.println(gson.toJson(movie));
             } else {
                 System.out.println("Error adding new line");
             }






            //Parcours des resultats
             Document myDoc = new Document();
             MongoCursor<Document> cursor = moviesCollection.find().iterator();
             try {
                 System.out.println("Parcours des resultats ");
                 while (cursor.hasNext()) {
                     myDoc = cursor.next();
                     System.out.println(myDoc.toJson());
                 }
             }
             finally {
                 cursor.close();
             }
             List<Document> myDocs = new ArrayList<>();
             moviesCollection.find().into(myDocs);
             System.out.println(myDocs.get(0).toString());
             myDoc = moviesCollection.find().first();
             System.out.println("first document :"+myDoc.toJson());

            //  Trouver un film dont le champ "year" est égal à 2017
             myDoc = moviesCollection.find(eq("year", 2017)).first();
             if (myDoc != null) {
                 System.out.println("Trouver un film dont le champ \"year\" est égal à 2017 : \n");
                 System.out.println(myDoc.toJson());
             } else {
                 System.out.println("No movie found for the year 2017");
             }

             // Trouver un film dont le champ "year" est entre 2000 et 2017
             myDoc = moviesCollection.find(and(gt("year", 2000), lte("year", 2017))).first();
             if (myDoc != null) {
                 System.out.println("Trouver un film dont le champ \"year\" est entre 2000 et 2017 : \n");
                 System.out.println(myDoc.toJson());
             } else {
                 System.out.println("No movie found between the years 2000 and 2017");
             }

            //  Trouver un film dont le champ summary contient la chaîne "police"
             myDoc = moviesCollection.find(Filters.regex("summary", "police")).first();
             if (myDoc != null) {
                 System.out.println("Trouver un film dont le champ summary contient la chaîne \"police\" : \n");
                 System.out.println(myDoc.toJson());
             } else {
                 System.out.println("No movie found with 'police' in the summary");
             }


            // //Projection :
             Document projectionFields = new Document("id", 1).append("title", 1);
            //   Fetch multiple documents with projection
             List<Document> docs = moviesCollection.find().projection(projectionFields).into(new ArrayList<>());
             System.out.println("Projection:\n");
             if (!docs.isEmpty()) {
                 for (Document doc : docs) {
                     System.out.println(doc.toJson());
                 }
             } else {
                 System.out.println("No documents found with the given projection.");
             }


            // Update
             moviesCollection.updateOne(eq(" id", "movie:100"), set("title", "vertigo"));
             System.out.println("movie updated successufuly");
             moviesCollection.updateMany(eq("genre", "SF"), set("genre", "Science Fiction"));
             System.out.println("movies updated successufuly");



            //drop collection
            //moviesCollection.drop();


        } catch (Exception e) {
            e.printStackTrace();
        }










        System.out.println("GoodNight!");
    }
}
