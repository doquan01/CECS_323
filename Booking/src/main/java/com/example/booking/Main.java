/**
 * A class that creates, adds, edits, and updates relevant book information.
 * Homework Assignment: JPA Booking
 *
 * @author John Apale
 * @author Matthew Chung
 * @author Quan Do
 * @version 1.0 11/02/2021
 */

package com.example.booking;

import java.util.List;
import java.util.logging.Logger;
import javax.persistence.*;

public class Main {

    /**
     * Declaration of the EntityManager type variable called entityManager.
     * Allow applications to manage and search for entities.
     */
    private EntityManager entityManager;

    /**
     * Declaration of the Logger type variable called LOGGER. Logs the program.
     */
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    /**
     * The constructor for the Main class. Stashes the provided EntityManager for use
     * later in the application.
     * @param manager The EntityManager that we will use.
     */
    public Main(EntityManager manager) {
        this.entityManager = manager;
    }

    public static void main(String[] args) {

    }//End of main method

    /**
     * Create and persist a list of objects to the database.
     * @param entities The list of entities to persist. These can be any object that has been
     *                 properly annotated in JPA and marked as "persistable."
     */
    public <E> void createEntity(List<E> entities) {
        for (E next : entities) {
            LOGGER.info("Persisting: " + next);
            // Use the CarClub entityManager instance variable to get our EntityManager.
            this.entityManager.persist(next);
        }

        // The auto generated ID (if present) is not passed in to the constructor since JPA will
        // generate a value.  So the previous for loop will not show a value for the ID.  But
        // now that the Entity has been persisted, JPA has generated the ID and filled that in.
        for (E next : entities) {
            LOGGER.info("Persisted object after flush (non-null id): " + next);
        }
    } // End of createEntity member method
}// End of Main class
