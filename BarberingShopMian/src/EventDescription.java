import java.util.LinkedList;

public class EventDescription {
    public static String describeEvent(int event, String currentCustomer, LinkedList<String> queue) {
        switch (event) {
            case 0:
                return "-- " + currentCustomer;
            case 1:
                return "++ VIP" + (queue.size() + 1);
            case 2, 3:
                return "++ ORD" + (queue.size() + 1);
            default:
                return "";
        }
    }
}
