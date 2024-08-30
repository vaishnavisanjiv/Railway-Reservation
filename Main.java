import java.util.*;

class Train {
    private String trainNumber;
    private String trainName;
    private int totalSeats;
    private int availableSeats;

    public Train(String trainNumber, String trainName, int totalSeats) {
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public String getTrainName() {
        return trainName;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void bookSeats(int numSeats) {
        if (numSeats <= availableSeats) {
            availableSeats -= numSeats;
            System.out.println(numSeats + " seat(s) booked successfully on train " + trainNumber);
        } else {
            System.out.println("Insufficient seats available on train " + trainNumber);
        }
    }

    public void cancelSeats(int numSeats) {
        if (numSeats <= (totalSeats - availableSeats)) {
            availableSeats += numSeats;
            System.out.println(numSeats + " seat(s) canceled successfully on train " + trainNumber);
        } else {
            System.out.println("Cannot cancel more seats than booked.");
        }
    }

    public void displayTrainInfo() {
        System.out.println("Train Number: " + trainNumber);
        System.out.println("Train Name: " + trainName);
        System.out.println("Total Seats: " + totalSeats);
        System.out.println("Available Seats: " + availableSeats);
        System.out.println("------------------------------------");
    }
}

/*--------------------------------------------------------------------------------------------------------*/

class RailwayReservationSystem {
    private ArrayList<Train> trains;

    public RailwayReservationSystem() {
        trains = new ArrayList<>();
    }

    public void addTrain(String trainNumber, String trainName, int totalSeats) {
        Train train = new Train(trainNumber, trainName, totalSeats);
        trains.add(train);
    }

    public Train findTrain(String trainNumber) {
        for (Train train : trains) {
            if (train.getTrainNumber().equals(trainNumber)) {
                return train;
            }
        }
        return null;
    }

    public void bookSeats(String trainNumber, int numSeats) {
        Train train = findTrain(trainNumber);
        if (train != null) {
            train.bookSeats(numSeats);
        } else {
            System.out.println("Train not found.");
        }
    }

    public void cancelSeats(String trainNumber, int numSeats) {
        Train train = findTrain(trainNumber);
        if (train != null) {
            train.cancelSeats(numSeats);
        } else {
            System.out.println("Train not found.");
        }
    }

    public void displayAllTrains() {
        for (Train train : trains) {
            train.displayTrainInfo();
            System.out.println();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        RailwayReservationSystem reservationSystem = new RailwayReservationSystem();

        // Adding some sample trains
        reservationSystem.addTrain("12345", "Indrayani Express", 100);
        reservationSystem.addTrain("22105", "Deccan Queen Express", 190);
        reservationSystem.addTrain("56890", "Daund-Indore", 200);
        reservationSystem.addTrain("11943", "Intercity", 300);
        reservationSystem.addTrain("66490", "Vande Bharat", 250);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Railway Reservation System Menu:");
            System.out.println("1. Book Seats");
            System.out.println("2. Cancel Seats");
            System.out.println("3. Display All Trains");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Train Number: ");
                    String trainNumber = scanner.next();
                    System.out.print("Enter Number of Seats to Book: ");
                    int numSeats = scanner.nextInt();
                    reservationSystem.bookSeats(trainNumber, numSeats);
                    break;
                case 2:
                    System.out.print("Enter Train Number: ");
                    trainNumber = scanner.next();
                    System.out.print("Enter Number of Seats to Cancel: ");
                    numSeats = scanner.nextInt();
                    reservationSystem.cancelSeats(trainNumber, numSeats);
                    break;
                case 3:
                    reservationSystem.displayAllTrains();
                    break;
                case 4:
                    System.out.println("Exiting Railway Reservation System.");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
