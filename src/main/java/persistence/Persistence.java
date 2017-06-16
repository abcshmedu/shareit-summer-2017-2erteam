package persistence;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author Altvatter Robert, Groﬂbeck Thomas
 *
 */
public interface Persistence {
    
    /**
     * Exists the medium in the database.
     * @param <T> type of the medium
     * @param <K> key type
     * @param t type of the medium
     * @param key value
     * @return true if the Medium exists
     */
    <T, K extends Serializable> boolean exist(Class<T> t, K key);
    
    /**
     * Returns a medium from the database.
     * @param <T> type of the medium
     * @param <K> key type
     * @param t type of the medium
     * @param key value
     * @return the medium from the database
     */
    <T, K extends Serializable> T get(Class<T> t, K key);
    
    /**
     * Adds a medium to the database.
     * @param <T> type of the medium
     * @param elem to add
     */
    <T> void add(T elem);
    
    /**
     * Returns all mediums from the database.
     * @param <T> type of the medium
     * @param t type of the mediums to return
     * @return a list with all mediums from the type T
     */
    <T> List<T> getAll(Class<T> t);
    
    /**
     * Updates a medium.
     * @param <T> type of the medium to update
     * @param elem to update
     */
    <T> void update(T elem);
}
