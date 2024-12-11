import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TicketingSystem {
    private static Logger logger = LogManager.getLogger(TicketingSystem.class);

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please Enter maximum Ticket capacity: ");
        int maximumTicketCapacity = scanner.nextInt();
        System.out.println("Please enter Total number of Tickets: ");
        int totalTickets = scanner.nextInt();
        System.out.println("Please Enter the Ticket Release Rate: ");
        int ticketReleaseRate = scanner.nextInt();
        System.out.println("Please Enter the Customer Retrieval Rate: ");
        Double customerRetrievalRate = scanner.nextDouble();

        Configuration configuration = new Configuration(totalTickets, ticketReleaseRate, customerRetrievalRate, maximumTicketCapacity);


        try {
            logger.info("Processing...");
            Vendor vendor = new Vendor();

            System.out.println("Enter number of Vendors to be created: ");
            int vendorCount = scanner.nextInt();
            vendor.createVendors(vendorCount);
            logger.info("Vendors Created.");

            Customer customer = new Customer();

            System.out.println("Enter number of Customers to be created: ");
            int customerCount = scanner.nextInt();
            customer.createCustomer(customerCount);
            logger.info("Customers Created.");

            //Ticket Creation
            //vendor.addTickets(new TicketPool(), maximumTicketCapacity);

            TicketPool ticketPool = new TicketPool();

            List<Thread> vendorThreads = new ArrayList<>();
            for (int i=1; i <= vendorCount; i++) {
               String eventName = "Event-" + i;
               Vendor vendor1 = new Vendor(eventName, ticketPool, configuration);
               Thread thread =new Thread(vendor1, "Vendor-" + i);
               vendorThreads.add(thread);
               thread.start();
            }

            List<Thread> customers = new ArrayList<>();
            int maximumTicketCount = 5;
            for (int i=1; i < customerCount; i++) {
                String eventName = "Event-" + ((i - 1) % vendorCount + 1);
                Customer customer1 = new Customer(eventName, ticketPool, maximumTicketCount);
                Thread thread = new Thread(customer1, "Customer-" + i);
                customers.add(thread);
                thread.start();
            }

            for (Thread thread : vendorThreads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            for (Thread thread : customers) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            System.out.println("\nFinal Ticket List: ");
            for (int i=1; i <= vendorCount; i++){
                String eventName ="Event-"+ i;
                List<String> remainingTickets = ticketPool.getTickets(eventName);
                System.out.println(eventName + ": " + remainingTickets);
            }

        } catch (Exception e) {
            logger.error("An error occurred while processing.");
        }
        logger.info("Process Completed Successfully.");
    }
}

