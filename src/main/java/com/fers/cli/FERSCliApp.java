package com.fers.cli;

import com.fers.db.IncidentDAO;
import com.fers.model.CheckIn;
import com.fers.model.Incident;
import com.fers.model.User;
import com.fers.db.UserDAO;
import com.fers.service.CheckInService;
import com.fers.util.DatabaseInitializer;

import java.util.List;
import java.util.Scanner;

/**
 * Main command-line interface (CLI) for the FERS Emergency System.
 *
 * This class provides a simple text-based menu that allows users
 * to interact with the system by performing operations such as:
 *  - Creating incidents
 *  - Registering users
 *  - Checking users into incidents
 *  - Viewing incident check-in summaries
 *
 * The CLI communicates with the Service layer, which then interacts
 * with the DAO layer and the SQLite database.
 */
public class FERSCliApp {

    // Scanner object used to read input from the command line
    private static final Scanner scanner = new Scanner(System.in);

    // Service responsible for handling check-in logic
    private static final CheckInService checkInService = new CheckInService();

    /**
     * Main entry point of the CLI application.
     * Initializes the database and starts the menu loop.
     */
    public static void main(String[] args) {

        // Ensure that required database tables exist
        DatabaseInitializer.initialize();

        boolean running = true;

        // Main application loop (continues until the user exits)
        while (running) {

            printMenu();

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {

                case 1:
                    createIncident();
                    break;

                case 2:
                    registerUser();
                    break;

                case 3:
                    checkInUser();
                    break;

                case 4:
                    viewIncidentSummary();
                    break;

                case 5:
                    running = false;
                    System.out.println("System exiting...");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    /**
     * Prints the main CLI menu.
     * This menu allows the user to choose an operation to perform.
     */
    private static void printMenu() {

        System.out.println("\n===== FERS Emergency System =====");
        System.out.println("1. Create Incident");
        System.out.println("2. Register User");
        System.out.println("3. Check-In User");
        System.out.println("4. View Incident Summary");
        System.out.println("5. Exit");
        System.out.print("Select option: ");
    }

    /**
     * Prompts the user to enter an incident type
     * and creates a new incident in the database.
     */
    private static void createIncident() {

        System.out.print("Enter incident type: ");
        String type = scanner.nextLine();

        // DAO call to create the incident in the database
        Incident incident = IncidentDAO.createIncident(type);

        if (incident != null) {
            System.out.println("Incident created with ID: " + incident.getIncidentId());
        } else {
            System.out.println("Incident creation failed.");
        }
    }

    /**
     * Registers a new user in the system.
     * (Currently creates a User object locally. Later this could
     * be extended to save the user in the database using a UserDAO.)
     */
    private static void registerUser() {

    System.out.print("Enter User ID: ");
    int id = scanner.nextInt();
    scanner.nextLine();

    System.out.print("Enter Name: ");
    String name = scanner.nextLine();

    User user = new User(id, name);

    boolean success = UserDAO.insertUser(user);

    if (success) {
        System.out.println("User registered successfully.");
    } else {
        System.out.println("User registration failed.");
    }
    
    }

    /**
     * Allows a user to check into a specific incident
     * with a status such as SAFE, INJURED, or MISSING.
     */
    private static void checkInUser() {

        System.out.print("Enter User ID: ");
        int userId = scanner.nextInt();

        System.out.print("Enter Incident ID: ");
        int incidentId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Status (SAFE / INJURED / MISSING): ");
        String statusInput = scanner.nextLine();

        // Convert user input into the User.Status enum
        User.Status status = User.Status.valueOf(statusInput.toUpperCase());

        // Temporary user object (later we can retrieve from database)
        User user = UserDAO.getUserById(userId);

        if (user == null) {
            System.out.println("User not found. Please register the user first.");
            return;
        }

        // Call service layer to perform the check-in
        String result = checkInService.checkInUser(user, incidentId, status);

        System.out.println(result);
    }

    /**
 * Displays a summary report for an incident.
 */
    private static void viewIncidentSummary() {

    System.out.print("Enter Incident ID: ");
    int incidentId = scanner.nextInt();
    scanner.nextLine();

    checkInService.printIncidentSummary(incidentId);
    }
}