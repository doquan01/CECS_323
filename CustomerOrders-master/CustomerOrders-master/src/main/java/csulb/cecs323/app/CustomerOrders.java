/**
 * A class that creates, adds, edits, and updates customer order information
 * Homework Assignment: JPA Customer Orders
 *
 * @author John Apale
 * @author Matthew Chung
 * @author Quan Do
 * @version 1.0 10/19/2021
 */

/*
 *
 * Licensed under the Academic Free License (AFL 3.0).
 *     http://opensource.org/licenses/AFL-3.0
 *
 *  This code is distributed to CSULB students in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE, other than educational.
 *
 *  2018 Alvaro Monge <alvaro.monge@csulb.edu>
 *
 */

package csulb.cecs323.app;

// Import all of the entity classes that we have written for this application.

import csulb.cecs323.model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

/**
 * A simple application to demonstrate how to persist an object in JPA.
 * <p>
 * This is for demonstration and educational purposes only.
 * </p>
 * <p>
 *     Originally provided by Dr. Alvaro Monge of CSULB, and subsequently modified by Dave Brown.
 * </p>
 * Licensed under the Academic Free License (AFL 3.0).
 *     http://opensource.org/licenses/AFL-3.0
 *
 *  This code is distributed to CSULB students in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE, other than educational.
 *
 *  2021 David Brown <david.brown@csulb.edu>
 *
 */
public class CustomerOrders {
    /**
     * You will likely need the entityManager in a great many functions throughout your application.
     * Rather than make this a global variable, we will make it an instance variable within the CustomerOrders
     * class, and create an instance of CustomerOrders in the main.
     */
    private EntityManager entityManager;

    /**
     * The Logger can easily be configured to log to a file, rather than, or in addition to, the console.
     * We use it because it is easy to control how much or how little logging gets done without having to
     * go through the application and comment out/uncomment code and run the risk of introducing a bug.
     * Here also, we want to make sure that the one Logger instance is readily available throughout the
     * application, without resorting to creating a global variable.
     */
    private static final Logger LOGGER = Logger.getLogger(CustomerOrders.class.getName());

    /**
     * Creates a scanner to take user inputs
     */
    private static Scanner scan = new Scanner(System.in);

    /**
     * The constructor for the CustomerOrders class.  All that it does is stash the provided EntityManager
     * for use later in the application.
     * @param manager    The EntityManager that we will use.
     */
    public CustomerOrders(EntityManager manager) {
        this.entityManager = manager;
    }

    public static void main(String[] args) {
        LOGGER.fine("Creating EntityManagerFactory and EntityManager");
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("CustomerOrders");
        EntityManager manager = factory.createEntityManager();
        // Create an instance of CustomerOrders and store our new EntityManager as an instance variable.
        CustomerOrders customerOrders = new CustomerOrders(manager);


        // Any changes to the database need to be done within a transaction.
        // See: https://en.wikibooks.org/wiki/Java_Persistence/Transactions

        LOGGER.fine("Begin of Transaction");
        EntityTransaction tx = manager.getTransaction();

        tx.begin();
        // List of Products that I want to persist.  I could just as easily done this with the seed-data.sql
        List<Products> products = new ArrayList<>();
        List<Customers> customers = new ArrayList<>();
        // Load up my List with the Entities that I want to persist.  Note, this does not put them
        // into the database.
        products.add(new Products("076174517163", "16 oz. hickory hammer", "Stanely Tools", "1", 9.97, 50));
        products.add(new Products("72527273070", "Dewalt", "Cordless Impact Driver", "42", 59.99, 100));
        products.add(new Products("72527273071", "Dewalt", "Cordless Saw", "43", 49.99, 100));
        products.add(new Products("72527273072", "Dewalt", "6 in. angle grinder", "44", 213.20, 100));
        products.add(new Products("72527273073", "Milwaukee", "Metal Cutting Circular Saw", "25", 128.99, 50));
        products.add(new Products("72527273074", "Milwaukee", "M18 Hammer Drill/Impact Combo Kit", "26", 329.00, 40));
        customers.add(new Customers("Apale", "John", "1600 Pennsylvania Ave.", "10000", "714-888-9000"));
        customers.add(new Customers("Chung", "Matthew", "1671 Pennsylvania Ave.", "12000", "714-777-9000"));
        customers.add(new Customers("Do", "Quan", "1700 Washington Lane", "12300", "714-888-9999"));
        // Create the list of owners in the database.
        customerOrders.createEntity(products);
        customerOrders.createEntity(customers);
        Scanner scan = new Scanner(System.in);
        // prompt to choose customer
        Customers customer = customerOrders.selectCustomer();

        // prompt to choose products
        Map<Products, Integer> selectedProducts = customerOrders.selectProduct();


        // check if map is empty
        if (selectedProducts.isEmpty()) {
            System.out.println("No selected products. Order has been aborted.");
        } else {

            // get order date and time
            LocalDateTime orderDate;
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
            System.out.print("Enter order date (MM/dd/yyyy HH:mm:ss): ");
            String date = scan.nextLine();

            try {
                orderDate = LocalDateTime.parse(date, dateFormat);
                if (orderDate.isAfter(LocalDateTime.now())) {
                    System.out.println("Future date was entered. Current date will be used.\n");
                    orderDate = LocalDateTime.now();
                }
            } catch (Exception e) {
                System.out.print("Invalid date and time. Current date will be used.\n");
                orderDate = LocalDateTime.now();
            }
            // check if order already exists
            boolean valid = true;
            List<Orders> orders = customerOrders.entityManager.createNamedQuery("ReturnAllOrders", Orders.class).getResultList();
            for (Orders o : orders) {
                if ((o.getOrder_date().isEqual(orderDate)) && (o.getCustomer().getCustomer_id() == customer.getCustomer_id())) {
                    System.out.println("Order already exists.");
                    valid = false;
                }
            }
            if (valid) {
                // create a new order
                Orders newOrder = new Orders(customer, orderDate, "Home Depot");
                customerOrders.setOrder(newOrder, selectedProducts);
            }
        }

        System.out.println("Order complete.");
        // Commit the changes so that the new data persists and is visible to other users.
        tx.commit();
        LOGGER.fine("End of Transaction");

    } // End of the main method

    /**
     * Create and persist a list of objects to the database.
     * @param entities   The list of entities to persist.  These can be any object that has been
     *                   properly annotated in JPA and marked as "persistable."  I specifically
     *                   used a Java generic so that I did not have to write this over and over.
     */
    public <E> void createEntity(List<E> entities) {
        for (E next : entities) {
            LOGGER.info("Persisting: " + next);
            // Use the CustomerOrders entityManager instance variable to get our EntityManager.
            this.entityManager.persist(next);
        }

        // The auto generated ID (if present) is not passed in to the constructor since JPA will
        // generate a value.  So the previous for loop will not show a value for the ID.  But
        // now that the Entity has been persisted, JPA has generated the ID and filled that in.
        for (E next : entities) {
            LOGGER.info("Persisted object after flush (non-null id): " + next);
        }
    } // End of createEntity member method

    /**
     * Displays all the available customers for selection and allows user to select a customer.
     * @return The user selected customer.
     */
    public Customers selectCustomer() {
        // Get list of all the customers
        List<Customers> customers = this.entityManager.createNamedQuery("ReturnAllCustomers", Customers.class).getResultList();
        Customers selectedCustomer = new Customers();
        boolean valid = false;
        while (!valid) {
            // print each customer
            for (Customers c : customers) {
                System.out.println(c.toString());
            }
            System.out.println("Enter the customer ID you want to select: ");
            String inputSelection = scan.nextLine();
            // convert input to int
            try {
                int selection = Integer.parseInt(inputSelection);
                // get customer from list
                try {
                    selectedCustomer = customers.get(selection);
                    valid = true;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Customer ID does not exist.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
            }
        }
        return selectedCustomer;
    }

    /**
     * Displays all the available products for selection and allows user to select a product.
     * @return The selected a hashmap containing the products and the quantity of that product.
     */
    public Map<Products, Integer> selectProduct() {
        // get list of products
        List<Products> products = this.entityManager.createNamedQuery("ReturnAllProducts", Products.class).getResultList();
        // create a new list for selected products
        HashMap<Products, Integer> selectedProducts = new HashMap<>();
        boolean finished = false;
        while (!finished) {
            String inputSelection;
            boolean response = false;
            boolean valid = false;
            boolean amountValid = false;
            // print list of products
            for (Products p : products) {
                System.out.println(p.toString());
            }
            System.out.print("Enter the UPC of the product: ");
            inputSelection = scan.next();
            // check if input is in the product list
            for (Products p : products) {
                if (p.getUPC().equals(inputSelection)) {// check if product is already part of the selected list
                    if (selectedProducts.containsKey(p)) {
                        System.out.println("Product is already selected.");
                    } else {// prompt for amount they want to order
                        while (!amountValid) {
                            System.out.print("Enter the amount you wish to order: ");
                            int amount = scan.nextInt();
                            // check validity of user input
                            if ((amount <= p.getUnits_in_stock()) && (amount > 0)) {
                                p.setUnits_in_stock(p.getUnits_in_stock() - amount);  // subtract the product stock
                                selectedProducts.put(p, amount); // add product and amount to hashmap
                                // change bool for amount to exit loop
                                amountValid = true;
                                valid = true;
                            } else {
                                System.out.println("Invalid amount. Check the amount again.");
                            }
                        }
                    }
                }
            }
            if (valid) { // prompt user if they want to add more products
                while (!response) {
                    System.out.print("Would you like to add more products (Y/N)? ");
                    String cont = scan.next();
                    // valid user response
                    if (cont.equalsIgnoreCase("Y")) {
                        response = true;
                    } else if (cont.equalsIgnoreCase("N")) {
                        response = true;
                        finished = true;
                    } else {
                        System.out.println("Invalid response. Try again.");
                    }
                }
            } else {
                System.out.println("Check UPC input again.");
            }
        }
        return selectedProducts;
    }

    /**
     * Creates the customer's order and inserts that order into the database.
     * @param order         The order of a customer that the user created.
     * @param products      The list of products with the quantity of products corresponding to the order.
     */
    public void setOrder(Orders order, Map<Products, Integer> products) {
        List<Order_lines> order_lines = new ArrayList<>();
        // create a variable for total costs
        double totalPrice = 0;
        // iterate through map, create a orderline, then add to the list of orderlines
        for (Map.Entry<Products, Integer> p : products.entrySet()) {
            Order_lines orderLine = new Order_lines();
            orderLine.setOrder(order);
            orderLine.setProduct(p.getKey());
            orderLine.setQuantity(p.getValue());
            orderLine.setUnit_sale_price(p.getKey().getUnit_list_price());
            // add to total cost
            totalPrice += (orderLine.getQuantity() * orderLine.getUnit_sale_price());
            // add to orderlines list
            order_lines.add(orderLine);
        }
        // add order to arraylist to add to entity
        List<Orders> orderList = new ArrayList<>();
        orderList.add(order);
        String total = String.format("%.2f", totalPrice);
        System.out.println("Order: " + order + "\n Total price: " + total);
        boolean valid = false;
        while (!valid) {
            System.out.print("\n Do you wish to confirm this order (Y/N)? ");
            Scanner scan = new Scanner(System.in);
            String response = scan.next().toUpperCase();
            if (response.equals("Y")) {
                // create entity for order and order_line
                this.createEntity(orderList);
                this.createEntity((order_lines));
                valid = true;
            } else if (response.equals("N")) {
                // add stock back to database
                this.resetStock(order_lines);
                System.out.println("Order cancelled.");
                valid = true;
            } else {
                System.out.println("Invalid Response. Try again.");
            }
        }
    }

    /**
     * Resets the quantity of a product from an order that was canceled.
     * @param order_lines   The list of products placed in a customer's order.
     */
    public void resetStock(List<Order_lines> order_lines) {
        // get list of products
        List<Products> products = this.entityManager.createNamedQuery("ReturnAllProducts", Products.class).getResultList();
        for (Products p : products) {
            for (Order_lines order_line : order_lines) {
                if (p.equals(order_line.getProduct())) {
                    p.setUnits_in_stock(p.getUnits_in_stock() + order_line.getQuantity());
                }
            }
        }
    }

    /**
     * Think of this as a simple map from a String to an instance of Products that has the
     * same name, as the string that you pass in.  To create a new Cars instance, you need to pass
     * in an instance of Products to satisfy the foreign key constraint, not just a string
     * representing the name of the style.
     * @param UPC        The name of the product that you are looking for.
     * @return The Products instance corresponding to that UPC.
     */
    public Products getProduct(String UPC) {
        // Run the native query that we defined in the Products entity to find the right style.
        List<Products> products = this.entityManager.createNamedQuery("ReturnProduct",
                Products.class).setParameter(1, UPC).getResultList();
        if (products.size() == 0) {
            // Invalid style name passed in.
            return null;
        } else {
            // Return the style object that they asked for.
            return products.get(0);
        }
    }// End of the getStyle method
} // End of CustomerOrders class
