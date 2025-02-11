import java.util.*;

class Task4_HotelReservationSystem {

    static class Room {
        int roomNumber;
        String category;
        boolean isAvailable;
        double price;

        Room(int roomNumber, String category, double price) {
            this.roomNumber = roomNumber;
            this.category = category;
            this.isAvailable = true;
            this.price = price;
        }

        @Override
        public String toString() {
            return String.format("Room %d | Category: %s | Price: $%.2f | %s",
                    roomNumber, category, price, isAvailable ? "Available" : "Booked");
        }
    }

    static class Reservation {
        String customerName;
        Room room;

        Reservation(String customerName, Room room) {
            this.customerName = customerName;
            this.room = room;
        }

        @Override
        public String toString() {
            return String.format("Reservation for %s | Room %d (%s) | Price: $%.2f",
                    customerName, room.roomNumber, room.category, room.price);
        }
    }

    private static final List<Room> rooms = new ArrayList<>();
    private static final List<Reservation> reservations = new ArrayList<>();

    public static void main(String[] args) {
        initializeRooms();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Hotel Reservation System!");

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Make a Reservation");
            System.out.println("3. View Reservations");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> viewAvailableRooms();
                case 2 -> makeReservation(scanner);
                case 3 -> viewReservations();
                case 4 -> {
                    System.out.println("Thank you for using the Hotel Reservation System!");
                    return;
                }
                default -> System.out.println("Invalid option! Please try again.");
            }
        }
    }

    private static void initializeRooms() {
        rooms.add(new Room(101, "Single", 100.00));
        rooms.add(new Room(102, "Single", 100.00));
        rooms.add(new Room(201, "Double", 150.00));
        rooms.add(new Room(202, "Double", 150.00));
        rooms.add(new Room(301, "Suite", 300.00));
    }

    private static void viewAvailableRooms() {
        System.out.println("\nAvailable Rooms:");
        for (Room room : rooms) {
            if (room.isAvailable) {
                System.out.println(room);
            }
        }
    }

    private static void makeReservation(Scanner scanner) {
        System.out.print("\nEnter your name: ");
        String customerName = scanner.nextLine();

        System.out.print("Enter room number to book: ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Room selectedRoom = null;
        for (Room room : rooms) {
            if (room.roomNumber == roomNumber && room.isAvailable) {
                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom != null) {
            selectedRoom.isAvailable = false;
            reservations.add(new Reservation(customerName, selectedRoom));
            System.out.printf("Reservation successful! Room %d is now booked for %s.%n", roomNumber, customerName);
        } else {
            System.out.println("Room is not available or does not exist. Please try again.");
        }
    }

    private static void viewReservations() {
        if (reservations.isEmpty()) {
            System.out.println("\nNo reservations found.");
        } else {
            System.out.println("\nReservations:");
            for (Reservation reservation : reservations) {
                System.out.println(reservation);
            }
        }
    }
}

