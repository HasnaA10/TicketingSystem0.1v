import java.util.*;

public class TicketPool {
    private List<String> tickets = Collections.synchronizedList(new LinkedList<>());
    private Configuration maximumTicketCapacity;
    private String eventName;

    public TicketPool() {
    }

    public TicketPool(Configuration maximumTicketCapacity) {
        this.maximumTicketCapacity = maximumTicketCapacity;
    }

    public String getEventName() {
        return eventName;
    }

    public List<String> getTickets() {
        return tickets;
    }

    public void addTickets(List<String> newTickets) {
        synchronized (tickets) {
            tickets.addAll(newTickets);
            tickets.notifyAll();
        }
    }

    public List<String> buyTickets(String eventName, int maxTicketCount){
        synchronized (tickets) {
            List<String> purchasedTickets = new ArrayList<>();
            Iterator<String> iterator =tickets.iterator();

            while (iterator.hasNext() && purchasedTickets.size() < maxTicketCount) {
                String ticket =iterator.next();
                if (ticket.contains("Event: " + eventName)) {
                    purchasedTickets.add(ticket);
                    iterator.remove();
                }
            }
            if (purchasedTickets.isEmpty()) {
                System.err.println(Thread.currentThread().getName() + " - Tickets unavailable for " + eventName);
            } else if (purchasedTickets.size() < maxTicketCount) {
                System.out.println(Thread.currentThread().getName() + " - Only " + purchasedTickets.size() + " tickets available for " + eventName);
            }
            return purchasedTickets;
        }
    }

    public List<String> getTickets(String eventName) {
        synchronized (tickets) {
            List<String> eventTicket =new ArrayList<>();
            for (String ticket : tickets) {
                if(ticket.contains("Event " + eventName)){
                    eventTicket.add(ticket);
                }
            }
            return eventTicket;
        }
    }

}







