import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Vendor implements Runnable {
    private TicketPool tickets;
    private TicketPool ticketPool;
    private Configuration configuration;
    private Random random = new Random();

    LinkedList<Vendor> vendorList = new LinkedList<>();
    private String vendorName;
    private String eventName;

    public Vendor(TicketPool tickets) {
        this.tickets = tickets;

    }


    public Vendor(String vendorName, String eventName){
        this.vendorName = vendorName;
        this.eventName = eventName;
    }

    public Vendor(String eventName, TicketPool ticketPool, Configuration configuration){
        this.eventName = eventName;
        this.ticketPool = ticketPool;
        this.configuration = configuration;
    }

    public Vendor(){

    }

//    public Vendor(Configuration configuration){
//        this.configuration = configuration;
//    }
    public TicketPool getTickets() {
        return tickets;
    }


    public void createVendors (int vendorCount){
        for (int i = 1; i <= vendorCount; i++) {

            String vendorName = "Vendor" + (i);
            String eventName = "Event" + (i);

            // Create a new Vendor object and add it to the list
            vendorList.add(new Vendor(vendorName, eventName));
        }

        System.out.println("\nList of Vendors:");
        for (Vendor vendor : vendorList) {
            System.out.println(vendor);
        }
    }

    @Override
    public String toString() {
        return "Vendor{name='" + vendorName + "', eventName='" + eventName + "'}";
    }

    public void run(){
        if (configuration == null) {
            throw new IllegalStateException("Configuration is not set for this vendor.");
        }

        int ticketsAdded = 0;
        while (configuration.getTotalTickets() >= ticketsAdded){
            int add = Math.min(random.nextInt(5) + 1, configuration.getTotalTickets() - ticketsAdded);

            List<String> newTickets = new ArrayList<>();
            for (int i = 0; i < add ; i++) {
                newTickets.add("TicketID-" + (ticketsAdded + i + 1) + " for Event: " + eventName);
            }
            ticketPool.addTickets(newTickets);
            ticketsAdded =+ add;

            System.out.println(Thread.currentThread().getName() + " added " + add + " tickets to " + eventName
            + ". Total tickets added: " + ticketsAdded);

            try {
                Thread.sleep(random.nextInt(100) + 50);
            } catch(InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(Thread.currentThread().getName() + " completed adding tickets for " + eventName);
    }


}


