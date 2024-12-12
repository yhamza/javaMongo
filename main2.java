public class main2 package org.example;

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
        // Connecting to the MongoDB server
        try (MongoClient client = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = client.getDatabase("dbtp");
            MongoCollection<Document> collection = database.getCollection("movies");

            // Example of connecting to a replica set (optional)
            // Uncomment if needed
            // MongoClient replicaClient = MongoClients.create(
            //         Arrays.asList(new ServerAddress("localhost", 27017),
            //                 new ServerAddress("localhost", 27018),
            //                 new ServerAddress("localhost", 27019))
            // );

            // Ping the database to check connection
            Document ping = database.runCommand(new Document("ping", 1));
            if ((double) ping.get("ok") == 1.0) {
                System.out.println("Ping successful.");
            } else {
                System.out.println("Ping failed.");
            }

            // Inserting a document (Method 1)
            Document document = new Document("id", 200).append("title", "Logan");
            collection.insertOne(document);

            // Inserting a document (Method 2 with Gson)
            Gson gson = new Gson();
            Movie movie = new Movie("movie:100", 2017, "Logan");
            collection.insertOne(Document.parse(gson.toJson(movie)));

            // Replacing a document
            document.append("director", new Document("id", "a:1").append("fname", "John"));
            collection.replaceOne(Filters.eq("id", document.get("id")), document);

            // Fetching documents using a cursor
            try (MongoCursor<Document> cursor = collection.find().iterator()) {
                while (cursor.hasNext()) {
                    Document myDoc = cursor.next();
                    System.out.println(myDoc.toJson());
                }
            }

            // Fetching documents into a list
            List<Document> myDocs = new ArrayList<>();
            collection.find().into(myDocs);
            if (!myDocs.isEmpty()) {
                System.out.println(myDocs.get(0).toString());
            }

            // Finding the first document matching a query
            Document myDoc = collection.find(eq("year", 2017)).first();
            if (myDoc != null) {
                System.out.println(myDoc.toJson());
            } else {
                System.out.println("No document found for the year 2017.");
            }

            // Finding a document where "year" is between 2000 and 2017
            myDoc = collection.find(and(gt("year", 2000), lte("year", 2017))).first();
            if (myDoc != null) {
                System.out.println(myDoc.toJson());
            } else {
                System.out.println("No document found between the years 2001 and 2017.");
            }

            // Finding a document where "summary" contains the string "police"
            myDoc = collection.find(Filters.regex("summary", "police")).first();
            if (myDoc != null) {
                System.out.println(myDoc.toJson());
            } else {
                System.out.println("No document found with 'summary' containing 'police'.");
            }

            // Projecting certain fields
            Document proj = new Document("id", 1).append("title", 1);
            myDoc = collection.find().projection(proj).first();
            if (myDoc != null) {
                System.out.println(myDoc.toJson());
            } else {
                System.out.println("No document found for projection.");
            }

            // Updating one document
            collection.updateOne(eq("id", "movie:100"), set("title", "Vertigo"));

            // Updating many documents
            collection.updateMany(eq("genre", "SF"), set("genre", "Science Fiction"));

            // Dropping the collection (be careful with this in production)
            collection.drop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Example Movie class for Gson serialization
    public static class Movie {
        private String id;
        private int year;
        private String title;

        public Movie(String id, int year, String title) {
            this.id = id;
            this.year = year;
            this.title = title;
        }

        // Getters and setters (if needed)
    }
}
{
}
