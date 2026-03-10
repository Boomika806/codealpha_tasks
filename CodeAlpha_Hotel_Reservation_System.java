import java.io.*;
import java.util.*;

class Room {
    int id;
    String type;
    double price;
    boolean isAvailable;

    Room(int id, String type, double price) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.isAvailable = true;
    }

    @Override
    public String toString() {
        return String.format("Room %d [%-10s] $%.2f - %s", 
            id, type, price, (isAvailable ? "Available" : "BOOKED"));
    }
}

public class CodeAlpha_Hotel_Reservation_System {
    private static List<Room> rooms = new ArrayList<>();
    private static final String FILE_NAME = "hotel_data.txt";

    public static void main(String[] args) {
        setupRooms();
        loadData();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- HOTEL MANAGEMENT ---");
            System.out.println("1. Search Rooms\n2. Book Room\n3. Cancel Room\n4. Exit");
            System.out.print("Select: ");
            
            
            if (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                sc.next(); 
                continue;
            }
            
            int choice = sc.nextInt();

            if (choice == 1) {
                rooms.forEach(System.out::println);
            } else if (choice == 2 || choice == 3) {
                System.out.print("Enter Room ID: ");
                if (sc.hasNextInt()) {
                    int id = sc.nextInt();
                    processBooking(id, choice == 3);
                } else {
                    System.out.println("Invalid Room ID.");
                    sc.next();
                }
            } else if (choice == 4) {
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }
        System.out.println("Goodbye!");
        sc.close();
    }

    private static void setupRooms() {
        rooms.add(new Room(101, "Standard", 80.0));
        rooms.add(new Room(201, "Deluxe", 150.0));
        rooms.add(new Room(301, "Suite", 350.0));
    }

    private static void processBooking(int id, boolean isCancelling) {
        boolean found = false;
        for (Room r : rooms) {
            if (r.id == id) {
                found = true;
                if (!isCancelling && r.isAvailable) {
                    r.isAvailable = false;
                    System.out.println("Booking Confirmed for Room " + id);
                } else if (isCancelling && !r.isAvailable) {
                    r.isAvailable = true;
                    System.out.println("Cancellation Successful.");
                } else {
                    System.out.println("Operation failed. Check room status.");
                }
                saveData();
                break;
            }
        }
        if (!found) System.out.println("Room ID not found.");
    }

    private static void saveData() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Room r : rooms) {
                writer.println(r.id + "," + r.isAvailable);
            }
        } catch (IOException e) { System.err.println("Error saving data."); }
    }

    private static void loadData() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;
        try (Scanner fsc = new Scanner(file)) {
            while (fsc.hasNextLine()) {
                String[] parts = fsc.nextLine().split(",");
                int id = Integer.parseInt(parts[0]);
                boolean status = Boolean.parseBoolean(parts[1]);
                for (Room r : rooms) if (r.id == id) r.isAvailable = status;
            }
        } catch (Exception e) { }
    }

}

