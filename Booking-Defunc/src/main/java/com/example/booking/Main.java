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

import Models.Publishers;

import java.util.*;
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

    /**
     * Creates a scanner to take user inputs
     */
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        LOGGER.fine("Creating EntityManagerFactory and EntityManager");
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("Main");
        EntityManager manager = factory.createEntityManager();

        // Create an instance of Books
        Main booking = new Main(manager);

        LOGGER.fine("Begin of Transaction");
        EntityTransaction tx = manager.getTransaction();

        tx.begin();

//        List<Publishers> publishers = new ArrayList<>();
//        List<> = new ArrayList<>();
        boolean input = false;
        while(!input){
            System.out.println("Menu:\n1: Add a new Authoring Entity\n2: List info" +
                    "\n3: Delete a Book\n4: Update a Book\n5: List all of the Primary Keys" +
                    "\n6: Exit");
            int user = scan.nextInt();

            switch(user){
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:
                    //Exit
                    input = true;
                    break;
            }
            //add new object
            /*
             * Writing Group
             * Individual Author
             * Ad Hoc Team
             * An Individual Author to an existing Ad Hoc Team
             */
            //list info about an object
            /*
             * Publisher
             * Book (include publisher and Authoring Entity
             * Writing Group
             */
            //Delete a book
            /*
             * prompt all elements of a candidate key
             * Make sure the book actually exists
             */
            //Update a book
            /*
             *Change the authoring entity for an existing book
             */
            //List the pk of all the rows
            /*
             * Publishers
             * Books (show the title and ISBN)
             * Authoring Entities (also show type of authoring entity)
             */
            //Exit
        }

        System.out.println("Interaction Complete");
        tx.commit();
        LOGGER.fine("End of Interaction");

    }//End of main method


    public void addObject(){
        String entityName;
        String email;
        System.out.print("Enter the name of Authoring Entity: ");
        entityName = scan.nextLine();
        System.out.print("Enter the email of the Authoring Entity: ");
        email = scan.nextLine();

    }

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
