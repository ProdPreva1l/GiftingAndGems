package info.preva1l.giftingandgems.utils.data;

import com.mongodb.client.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collections;

@Getter
public class SimpleMongoHelper {
    private final MongoClient mongoClient;
    private final MongoDatabase database;

    /**
     * Initiate a connection to a Mongo Server
     * @param host The IP/Host Name of the Mongo Server
     * @param port The Port of the Mongo Server
     * @param username The Username of the user with the appropriate permissions
     * @param password The Password of the user with the appropriate permissions
     * @param databaseName The database to use.
     * @param authDb The database to authenticate with.
     */
    public SimpleMongoHelper(String host, int port, String username, String password, String databaseName, String authDb) {
        ServerAddress serverAddress = new ServerAddress(host, port);
        MongoCredential credential = MongoCredential.createCredential(username, authDb, password.toCharArray());

        MongoClientSettings settings = MongoClientSettings.builder()
                .credential(credential)
                .applyToClusterSettings(builder -> builder.hosts(Collections.singletonList(serverAddress)))
                .build();

        this.mongoClient = MongoClients.create(settings);
        this.database = mongoClient.getDatabase(databaseName);
    }

    /**
     * Initiate a connection to a Mongo Server
     * @param uri MongoDB Connection URI
     * @param dbName The database to connect to
     */
    public SimpleMongoHelper(String uri, String dbName) {
        this.mongoClient = MongoClients.create(uri);
        this.database = mongoClient.getDatabase(dbName);
    }

    /**
     * Close the connection with the database
     */
    public void closeConnection() {
        if (this.mongoClient != null) {
            this.mongoClient.close();
        }
    }
}