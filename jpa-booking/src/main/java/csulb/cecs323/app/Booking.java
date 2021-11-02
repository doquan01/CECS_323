/**
 * A class that creates, adds, edits, and updates relevant book information.
 * Homework Assignment: JPA Booking
 *
 * @author John Apale
 * @author Matthew Chung
 * @author Quan Do
 * @version 1.0 11/02/2021
 */

package csulb.cecs323.app;


import java.awt.print.Book;
import java.util.*;
import java.util.logging.Logger;
import javax.persistence.*;
import csulb.cecs323.model.*;

public class Booking {

   /**
    * Declaration of the EntityManager type variable called entityManager.
    * Allow applications to manage and search for entities.
    */
   private EntityManager entityManager;

   /**
    * Declaration of the Logger type variable called LOGGER. Logs the program.
    */
   private static final Logger LOGGER = Logger.getLogger(Booking.class.getName());

   /**
    * The constructor for the Main class. Stashes the provided EntityManager for use
    * later in the application.
    * @param manager The EntityManager that we will use.
    */
   public Booking(EntityManager manager) {
      this.entityManager = manager;
   }

   /**
    * Creates a scanner to take user inputs
    */
   private static Scanner scan = new Scanner(System.in);

   public static void main(String[] args) {
      LOGGER.fine("Creating EntityManagerFactory and EntityManager");
      EntityManagerFactory factory = Persistence.createEntityManagerFactory("Booking");
      EntityManager manager = factory.createEntityManager();

      // Create an instance of Books
      Booking booking = new Booking(manager);

      LOGGER.fine("Begin of Transaction");
      EntityTransaction tx = manager.getTransaction();

      tx.begin();

      boolean input = false;
      while(!input){

         int user = menu();

         switch(user){
            case 1:
               //add new object
               /*
                * Writing Group
                * Individual Author
                * Ad Hoc Team
                * An Individual Author to an existing Ad Hoc Team
                */
               booking.addObject();
               break;
            case 2:
               //list info about an object
               /*
                * Publisher
                * Book (include publisher and Authoring Entity
                * Writing Group
                */
               booking.listInfo();
               break;
            case 3:
               //Delete a book
               /*
                * prompt all elements of a candidate key
                * Make sure the book actually exists
                */
               booking.removeBook();
               break;
            case 4:
               //Update a book
               /*
                *Change the authoring entity for an existing book
                */
               booking.updateBook();
               break;
            case 5:
               //List the pk of all the rows
               /*
                * Publishers
                * Books (show the title and ISBN)
                * Authoring Entities (also show type of authoring entity)
                */
               break;
            case 6:
               //Exit
               input = true;
               break;

            default:
               System.out.println("Invalid Response. Please try again.");
         }
      }

      System.out.println("Interaction Complete");
      tx.commit();
      LOGGER.fine("End of Interaction");

   }//End of main method

   public static int menu() {
      System.out.print("\nMenu:\n" +
              "1: Add a new Authoring Entity\n" +
              "2: List info \n" +
              "3: Remove a Book\n" +
              "4: Update a Book\n" +
              "5: List all of the Primary Keys\n" +
              "6: Exit\n" +
              "Enter a numeric value: ");

      return scan.nextInt();
   }

   public void addObject(){
      //add new object
      /*
       * Writing Group
       * Individual Author
       * Ad Hoc Team
       * An Individual Author to an existing Ad Hoc Team
       */
      int input;
      System.out.print("\nWhat type of Authoring Entity do you want to add:\n" +
              "1: Writing Group\n" +
              "2: Individual Author\n" +
              "3: Ad Hoc Team\n" +
              "4: Add an Individual Author to an Ad Hoc Team\n" +
              "Enter a numeric value: ");
      input = scan.nextInt();
      scan.nextLine();


      switch (input) {
         case 1:
            System.out.print("\nEnter the name of the Writing Group: ");
            String groupName = scan.nextLine();

            String email = addEmail();
            List<Writing_group> writingGroup = new ArrayList<>();

            System.out.print("\nEnter the name of the Head Writer: ");
            String headWriter = scan.nextLine();

            System.out.print("\nEnter the year that the Writing Group was formed: ");
            int year = scan.nextInt();
            scan.nextLine();

            writingGroup.add(new Writing_group(email, groupName, headWriter, year));
            try{
               this.createEntity(writingGroup);
            } catch (Exception e){
               System.out.println("Error adding to database.");
            }
            break;
         case 2:
            System.out.print("\nEnter the name of the Individual Author: ");
            String individualName = scan.nextLine();
            email = addEmail();
            List<Individual_author> individualAuthors = new ArrayList<>();

            individualAuthors.add(new Individual_author(email, individualName, null));
            try{
               this.createEntity(individualAuthors);
            } catch(Exception e){
               System.out.println("Error adding to database.");
            }

            break;
         case 3:
            System.out.print("\nEnter the name of the Ad Hoc Team that you want to add: ");
            String teamName = scan.nextLine();
            email = addEmail();
            List<Ad_hoc_teams> ad_hoc_teams = new ArrayList<>();
            ad_hoc_teams.add(new Ad_hoc_teams(email, teamName, null));
            try{
               this.createEntity(ad_hoc_teams);
            } catch(Exception e) {
               System.out.println("Error adding to database.");
            }
            break;
         case 4:
            addTeamMember();
            break;
      }
   }

   public void addTeamMember(){

      // get list of all teams
      List<Ad_hoc_teams> teams = this.entityManager.createNamedQuery("ReturnAllTeamInfo", Ad_hoc_teams.class).getResultList();

      // initialize a team
      Ad_hoc_teams selectedTeam = new Ad_hoc_teams();

      // print out list of teams
      System.out.print("\nList of teams: ");
      for (Ad_hoc_teams team : teams) {
         System.out.println(team.toString());
      }
      boolean valid = false;
      String inputTeam = "";

      // get user input for team they want to add to
      while (!valid) {
         System.out.print("\nEnter the enter the email of the team you want to add a member to: ");
         inputTeam = scan.nextLine();
         try {
            selectedTeam = entityManager.find(Ad_hoc_teams.class, inputTeam);
            valid = true;
         } catch (Exception e) {
            System.out.print("\nTeam does not exists.");
         }
      }

      // get the list of authors
      List<Individual_author> author = this.entityManager.createNamedQuery("ReturnAllIndividualInfo", Individual_author.class).getResultList();

      // initialize an individual
      Individual_author selectedIndividual = new Individual_author();

      // print list of all individual authors
      System.out.print("\nList of Individual Authors: ");
      for (Individual_author a : author) {
         System.out.println(a.toString());
      }
      boolean validIndividual = false;
      String inputIndividual = "";

      // get user input for author they want to add
      while (!validIndividual) {
         System.out.print("\nEnter the enter the email of the Individual Author you want to add a to a team: ");
         inputIndividual = scan.nextLine();
         try {
            selectedIndividual = entityManager.find(Individual_author.class, inputIndividual);
            validIndividual = true;
         } catch (Exception e) {
            System.out.println("Team does not exists.");
         }
      }
      selectedTeam.add_individual_authors(selectedIndividual);
   }

   public String addEmail(){
      boolean valid = false;
      String inputEmail = "";

      while (!valid) {
         System.out.print("\nEnter the enter the Authoring Entity's email: ");
         inputEmail = scan.nextLine();
         if(inputEmail.contains("@") && inputEmail.contains(".")){
            try {
               List<Authoring_Entities> authoringEntities = this.entityManager.createNamedQuery("ReturnAllEmails", Authoring_Entities.class).setParameter(1, inputEmail).getResultList();
               if(authoringEntities.size() == 0) {
                  valid = true;
               }
            } catch (Exception e) {
               System.out.println("Email exists.");
            }
         }
         else{
            System.out.println("Invalid Input.");
         }
      }
      return inputEmail;
   }

   public void listInfo(){
      boolean valid = false;
      int response = 0;
      while(!valid){
         System.out.println(  "Select which object information you would like to see.\n" +
                 "1. Publishers\n" +
                 "2. Books\n" +
                 "3. Authoring Entities");
         response = scan.nextInt();
         scan.nextLine();
         if (response > 0 && response < 4){
            valid = true;
         }
      }
      switch (response) {
         case 1:
            // print publishers
            System.out.println("List of Publishers: ");
            List <Publishers> publishers = this.entityManager.createNamedQuery("ReturnAllPublishers", Publishers.class).getResultList();
            for (Publishers publisher : publishers){
               System.out.println(publisher.toString());
            }
            break;
         case 2:
            // print books
            System.out.println("List of Books: ");
            List <Books> books = this.entityManager.createNamedQuery("ReturnAllBooks", Books.class).getResultList();
            for (Books book : books){
               System.out.println(book.toString());
            }
            break;
         case 3:
            // print authoring entities
            System.out.println("List of Authoring Entities: ");
            List <Authoring_Entities> authoring_entities = this.entityManager.createNamedQuery("ReturnAllAuthoringEntities", Authoring_Entities.class).getResultList();
            for (Authoring_Entities auth_ent : authoring_entities){
               System.out.println(auth_ent.toString());
            }
            break;
      }


   }

   public void removeBook(){
      // show list of books
      System.out.println("List of books:\n");
      List <Books> books = new ArrayList<>();
      books = this.entityManager.createNamedQuery("ReturnAllBooks", Books.class).getResultList();
      for (Books book : books) {
         System.out.println(book.toString());
      }
      boolean deleted = false;
      while(!deleted){
         System.out.print("\nEnter the ISBN of the book you would like to remove or press 1 to cancel: ");
         int selection = scan.nextInt();
         scan.nextLine();
         if(selection == 1){
            deleted = true;
         }
         try {
            // remove the user selection
            Books book = entityManager.find(Books.class, selection);
            this.entityManager.remove(book);
            deleted = true;
         } catch (Exception e) {
            System.out.println("Invalid ISBN.");
         }
      }
   }

   public void updateBook(){
      // show list of books
      System.out.println("List of books:\n");
      List <Books> books = new ArrayList<>();
      books = this.entityManager.createNamedQuery("ReturnAllBooks", Books.class).getResultList();
      for (Books book : books) {
         System.out.println(book.toString());
      }

      boolean updated = false;
      while(!updated){
         System.out.print("\nEnter the ISBN of the book you would like to update or 1 to cancel: ");
         int selection = scan.nextInt();
         scan.nextLine();
         if(selection == 1){
            updated = true;
         }
         try {
            Books book = entityManager.find(Books.class, selection);

            // Show list of authoring entity
            List <Authoring_Entities> authoring_entities = this.entityManager.createNamedQuery("ReturnAllAuthoringEntities", Authoring_Entities.class).getResultList();
            for (Authoring_Entities auth_ent : authoring_entities){
               System.out.println(auth_ent.toString());
            }
            // Prompt user to enter which authoring entity to select
            System.out.print("\nEnter the email of authoring entity would you like to update to? ");
            String email = scan.nextLine();

            try {
               Authoring_Entities newAuth = entityManager.find(Authoring_Entities.class, email);
               book.setAuthoringEntity(newAuth);
            } catch(Exception e) {
               System.out.println("Authoring entity does not exist.");
            }
         } catch (Exception e){
            System.out.println("Invalid ISBN.");
         }
      }
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
