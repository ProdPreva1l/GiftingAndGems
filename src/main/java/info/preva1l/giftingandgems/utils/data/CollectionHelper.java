package info.preva1l.giftingandgems.utils.data;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.conversions.Bson;

public class CollectionHelper {
    private final SimpleMongoHelper database;
    private final CacheHandler cacheHandler;

    /**
     * Init the collection helper
     * @param database Instance of {@link SimpleMongoHelper}
     * @param cacheHandler database Instance of {@link CacheHandler}
     */
    public CollectionHelper(SimpleMongoHelper database, CacheHandler cacheHandler) {
        this.database = database;
        this.cacheHandler = cacheHandler;
    }

    /**
     * Create a collection
     * @param collectionName the collection name
     */
    public void createCollection(String collectionName) {
        database.getDatabase().createCollection(collectionName);
        cacheHandler.updateCache(collectionName);
    }

    /**
     * Delete a collection
     * @param collectionName the collection name
     */
    public void deleteCollection(String collectionName) {
        database.getDatabase().createCollection(collectionName);
        cacheHandler.removeFromCache(collectionName);
    }

    /**
     * Get a collection
     * @param collectionName the collection name
     * @return MongoCollection<Document>
     */
    public MongoCollection<Document> getCollection(String collectionName) {
        if (cacheHandler.getCachedCollection(collectionName) == null) return null;
        return cacheHandler.getCachedCollection(collectionName);
    }

    /**
     * Add a document to a collection
     * @param collectionName collection to add to
     * @param document Document to add
     */
    public void insertDocument(String collectionName, Document document) {
        MongoCollection<Document> collection = database.getDatabase().getCollection(collectionName);
        collection.insertOne(document);
        cacheHandler.updateCache(collectionName);
    }

    /**
     * Update a document
     * @param collectionName collection the document is in
     * @param document filter of document
     * @param updates Bson of updates
     */
    public void updateDocument(String collectionName, Document document, Bson updates) {
        MongoCollection<Document> collection = database.getDatabase().getCollection(collectionName);
        collection.updateOne(document, updates);
        cacheHandler.updateCache(collectionName);
    }

    /**
     * Delete a document
     * @param collectionName collection the document is in
     * @param document filter to remove
     */
    public void deleteDocument(String collectionName, Document document) {
        MongoCollection<Document> collection = database.getDatabase().getCollection(collectionName);
        collection.deleteOne(document);
        cacheHandler.removeFromCache(collectionName);
        cacheHandler.updateCache(collectionName);
    }
}