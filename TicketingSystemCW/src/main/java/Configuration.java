import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Configuration {
    private static Logger logger = LogManager.getLogger("");

    private int totalTickets;
    private int ticketReleaseRate;
    private double customerRetrievalRate;
    private int maximumTicketCapacity;

    public Configuration(int totalTickets, int ticketReleaseRate, double customerRetrievalRate, int maximumTicketCapacity) {
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maximumTicketCapacity = maximumTicketCapacity;
    }
//    public int getTotalTickets() {
//        return totalTickets;
//    }
//
//    public void setTotalTickets(int totalTickets) {
//        this.totalTickets = totalTickets;
//    }
//
    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }
//
//    public void setTicketReleaseRate(Double ticketReleaseRate) {
//        this.ticketReleaseRate = ticketReleaseRate;
//    }
//
//    public double getCustomerRetrievalRate() {
//        return customerRetrievalRate;
//    }
//
//    public void setCustomerRetrievalRate(double customerRetrievalRate) {
//        this.customerRetrievalRate = customerRetrievalRate;
//    }
//
    public int getMaximumTicketCapacity() {
        return maximumTicketCapacity;
    }

    public  int getTotalTickets(){
        return totalTickets;
    }
//
//    public void setMaximumTicketCapacity(int maximumTicketCapacity) {
//        this.maximumTicketCapacity = maximumTicketCapacity;
//    }




}
