import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class BarberingShop {
    private static final int MAX_CHAIR_COUNT = 5;
    private static final String MAIN_CHAIR_LABEL = "Chair 0";

    private final Random random = new Random();
    private final LinkedList<String> customerQueue = new LinkedList<>();
    private String currentCustomer = null;
    private int eventCounter = 0;

    public void

    startSimulation() {
        System.out.println("     X              + Event       +    State of shop     " );
        System.out.println("+-------------+--------------------+ ------------------------------------+ ");
        System.out.println("Press space to trigger events.");


        while (true) {
            char input = new Scanner(System.in).nextLine().charAt(0);
            if (input != ' ') {
                System.out.println("End of simulation.");
                break;
            }
            int eventType = random.nextInt(4);
            handleEvent(eventType);
            eventCounter++;

            System.out.printf("%4d  ---> ( %-7s )  [ %-5s : %-5s : %-5s : %-5s : %-5s : %-5s ]%n",
                    eventType, EventDescription.describeEvent(eventType, currentCustomer, customerQueue),
                    currentCustomer != null ? currentCustomer + " (" + MAIN_CHAIR_LABEL + ")" : "-----",
                    customerQueue.isEmpty() ? "-----" : customerQueue.get(0),
                    customerQueue.size() < 2 ? "-----" : customerQueue.get(1),
                    customerQueue.size() < 3 ? "-----" : customerQueue.get(2),
                    customerQueue.size() < 4 ? "-----" : customerQueue.get(3),
                    customerQueue.size() < 5 ? "-----" : customerQueue.get(4));
        }
    }

    private void handleEvent(int eventType) {
        switch (eventType) {
            case 0:
                serveCustomer();
                break;
            case 1:
                addVIPCustomer();
                break;
            default:
                addOrdinaryCustomer(eventType);
        }
    }

    private void serveCustomer() {
        if (currentCustomer == null) {
            return;
        }
        currentCustomer = null;
        if (!customerQueue.isEmpty()) {
            currentCustomer = customerQueue.pollFirst();
        }
    }

    private void addVIPCustomer() {
        if (currentCustomer == null && customerQueue.isEmpty()) {
            currentCustomer = "VIP" + (1);
            return;
        }
        if (customerQueue.isEmpty() || customerQueue.getFirst().startsWith("VIP")) {
            customerQueue.addFirst("VIP" + (customerQueue.size() + 1));
        } else {
            int index = 1;
            for (String customer : customerQueue) {
                if (customer.startsWith("VIP")) {
                    customerQueue.add(index, "VIP" + (customerQueue.size() + 1));
                    return;
                }
                index++;
            }
            customerQueue.addFirst("VIP" + (customerQueue.size() + 1));
        }
    }

    private void addOrdinaryCustomer(int eventType) {
        if (currentCustomer == null && customerQueue.size() < MAX_CHAIR_COUNT) {
            currentCustomer = "ORD" + (customerQueue.size() + 1);
            return;
        }
        if (customerQueue.size() < MAX_CHAIR_COUNT) {

            customerQueue.addLast("ORD" + (customerQueue.size() + 1));
        } else {
            System.out.println("+- " + "ORD" + (eventType - 1 + 1) + " (Customer left)");
        }
    }


}
