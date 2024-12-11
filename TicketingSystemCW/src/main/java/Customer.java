import java.util.LinkedList;
import java.util.List;

public class Customer implements Runnable {
    private TicketPool ticketPool;
    private double retrievalRate;
    private String customerName;
    LinkedList<Customer> customerList = new LinkedList<>();
    private String eventName;
    private int maximumTicketCount;

    public Customer(TicketPool ticketPool, double retrievalRate) {
        this.ticketPool = ticketPool;
        this.retrievalRate = retrievalRate;
    }

    public Customer(){

    }
    public Customer(String customerName){
        this.customerName = customerName;
    }

    public Customer(String eventName, TicketPool ticketPool, int maximumTicketCount){
        this.eventName = eventName;
        this.ticketPool = ticketPool;
        this.maximumTicketCount = maximumTicketCount;
    }
    public void createCustomer(int customerCount){
        for (int i = 1; i <= customerCount; i++) {

            String customerName = "Customer" + (i);

            // Create a new Vendor object and add it to the list
            customerList.add(new Customer(customerName));
        }

        System.out.println("\nList of Customers:");
        for (Customer customer : customerList) {
            System.out.println(customer);
        }
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerName='" + customerName + '\'' +
                '}';
    }

    public void run(){
        List<String> purchasedTickets = ticketPool.buyTickets(eventName, maximumTicketCount);
        if (!purchasedTickets.isEmpty()) {
            System.out.println(Thread.currentThread().getName() + " purchased: " + purchasedTickets);
        }
    }

}
