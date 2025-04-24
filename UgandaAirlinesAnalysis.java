import java.io.*;
import java.util.*;

public class UgandaAirlinesAnalysis {
    public static void main(String[] args) {
        String fileName = "input.txt";
        int dubaiFlights = 0;
        String maxPassengerFlight = "";
        int maxPassengers = -1;

        String firstUnder100Flight = "";
        boolean foundUnder100 = false;

        Map<String, Integer> destinationPassengerTotals = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                if (parts.length != 3) continue;
                
                String airline = parts[0];
                String destination = parts[1];
                int passengers;

                try {
                    passengers = Integer.parseInt(parts[2]);
                } catch (NumberFormatException e) {
                    continue; // skip invalid passenger numbers
                }

                // Task 1: Count Dubai flights
                if (destination.equalsIgnoreCase("Dubai")) {
                    dubaiFlights++;
                }

                // Task 2: Max passenger flight
                if (passengers > maxPassengers) {
                    maxPassengers = passengers;
                    maxPassengerFlight = line;
                }

                // Task 3: First flight with < 100 passengers
                if (!foundUnder100 && passengers < 100) {
                    firstUnder100Flight = line;
                    foundUnder100 = true;
                }

                // Task 4: Accumulate passengers per destination
                destinationPassengerTotals.put(destination,
                        destinationPassengerTotals.getOrDefault(destination, 0) + passengers);
            }

            // Find most popular route
            String mostPopularDestination = "";
            int mostPassengers = -1;
            for (Map.Entry<String, Integer> entry : destinationPassengerTotals.entrySet()) {
                if (entry.getValue() > mostPassengers) {
                    mostPassengers = entry.getValue();
                    mostPopularDestination = entry.getKey();
                }
            }

            // Output
            System.out.println("1. Flights to Dubai: " + dubaiFlights);
            System.out.println("2. Flight with highest passengers: " + maxPassengerFlight);
            System.out.println("3. First flight with fewer than 100 passengers: " + firstUnder100Flight);
            System.out.println("4. Most popular route: " + mostPopularDestination + " with " + mostPassengers + " passengers");

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}