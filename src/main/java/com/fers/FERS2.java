/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.fers;
import com.fers.model.Incident;
import com.fers.model.User;
import com.fers.service.CheckInService;
import com.fers.service.IncidentService;
import com.fers.service.UserService;
import com.fers.util.DatabaseInitializer;
import com.fers.util.DatabaseVerifier;
import java.sql.SQLException;
import java.util.Scanner;
/**
 *
 * @author ndukw
 */
public class FERS2 {

    public static void main(String[] args) throws SQLException {
        DatabaseInitializer.initialize();
        System.out.println("FERS backend ready.");
        DatabaseVerifier.verifyTables();
        System.out.println("FERS backend ready. ");
        //DatabaseSetup.initialize();
        //DatabaseUtil.getConnection();
        System.out.println("Databse initialized successfully.");
        UserService userService = new UserService();
        IncidentService incidentService = new IncidentService();
        CheckInService checkInService = new CheckInService();
        
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        
        while(running){
            System.out.println("\n=== FERS SYSTEM ===");
            System.out.println("1. Register User: ");
            System.out.println("2. Create incident: ");
            System.out.println("3. Check in user:");
            System.out.println("4. View users: ");
            System.out.println("5. Close incident: ");
            System.out.println("6. Exit");
            System.out.println("Choose option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume new line
            
            switch(choice){
                case 1:
                    System.out.print("Enter user ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter user name: ");
                    String name = scanner.nextLine();
                    userService.addUser(new User(id, name));
                    System.out.println("User Registered.");
                    break;
                case 2: 
                    System.out.print("Enter incident type: ");
                    String type = scanner.nextLine();
                    Incident incident = incidentService.createIncident(type);
                    System.out.println("Incident created: " + incident);
                    break;
                case 3:
                    if (!incidentService.hasActiveIncident()) {
                        System.out.println("No active incident.");
                        break;
                    }

                    System.out.print("Enter user ID: ");
                    int userId = scanner.nextInt();
                    scanner.nextLine();

                    User user = userService.getUserById(userId);
                    if (user == null) {
                        System.out.println("User not found.");
                        break;
                    }

                    System.out.println("1 = SAFE, 2 = NEED_HELP");
                    int statusChoice = scanner.nextInt();

                    User.Status status = (statusChoice == 1)
                            ? User.Status.SAFE
                            : User.Status.INJURED;

                    String result = checkInService.checkInUser(
                            user,
                            incidentService.getActiveIncident().getIncidentId(),
                            status
                    );

                    System.out.println(result);
                    break;
                case 4:
                    for(User u : userService.getAllUsers()){
                        System.out.println(u);
                    }
                    break;
                case 5:
                    incidentService.closeIncident();
                    System.out.println("Incident closed. ");
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. ");
            }
        }
        scanner.close();
    }
}
