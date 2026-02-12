import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class ServiceInfo {
    String name;
    String displayName;
    String status;
    String startType;
    
    public ServiceInfo(String name, String displayName, String status, String startType) {
        this.name = name;
        this.displayName = displayName;
        this.status = status;
        this.startType = startType;
    }
}

public class DeviceServices {
    
    public static List<ServiceInfo> getWindowsServices() {
        List<ServiceInfo> services = new ArrayList<>();
        
        try {
            Process process = Runtime.getRuntime().exec("sc query type= service state= all");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            
            String line;
            String serviceName = "";
            String displayName = "";
            String status = "";
            
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                
                if (line.startsWith("SERVICE_NAME:")) {
                    serviceName = line.substring(13).trim();
                } else if (line.startsWith("DISPLAY_NAME:")) {
                    displayName = line.substring(13).trim();
                } else if (line.contains("STATE")) {
                    String[] parts = line.split(":");
                    if (parts.length > 1) {
                        status = parts[1].trim().split("\\s+")[0];
                    }
                    
                    if (!serviceName.isEmpty()) {
                        services.add(new ServiceInfo(serviceName, displayName, status, "N/A"));
                        serviceName = "";
                        displayName = "";
                        status = "";
                    }
                }
            }
            
            reader.close();
            process.waitFor();
            
        } catch (Exception e) {
            System.err.println("Error reading services: " + e.getMessage());
        }
        
        return services;
    }
    
    public static void displayAllServices(List<ServiceInfo> services) {
        System.out.println("\n========================================");
        System.out.println("       WINDOWS DEVICE SERVICES");
        System.out.println("========================================\n");
        System.out.println("Total Services Found: " + services.size() + "\n");
        
        System.out.printf("%-40s %-50s %-15s%n", "Service Name", "Display Name", "Status");
        System.out.println("-".repeat(105));
        
        for (ServiceInfo service : services) {
            System.out.printf("%-40s %-50s %-15s%n", 
                truncate(service.name, 40), 
                truncate(service.displayName, 50), 
                service.status);
        }
    }
    
    public static void searchService(List<ServiceInfo> services, String searchTerm) {
        System.out.println("\nSearching for: " + searchTerm + "\n");
        boolean found = false;
        
        for (ServiceInfo service : services) {
            if (service.name.toLowerCase().contains(searchTerm.toLowerCase()) || 
                service.displayName.toLowerCase().contains(searchTerm.toLowerCase())) {
                System.out.println("Service Name: " + service.name);
                System.out.println("Display Name: " + service.displayName);
                System.out.println("Status: " + service.status);
                System.out.println("-".repeat(50));
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("No services found matching: " + searchTerm);
        }
    }
    
    public static void filterByStatus(List<ServiceInfo> services, String status) {
        System.out.println("\nServices with status: " + status + "\n");
        int count = 0;
        
        for (ServiceInfo service : services) {
            if (service.status.equalsIgnoreCase(status)) {
                System.out.println(service.displayName + " (" + service.name + ")");
                count++;
            }
        }
        
        System.out.println("\nTotal: " + count + " services");
    }
    
    public static String truncate(String str, int length) {
        if (str.length() > length) {
            return str.substring(0, length - 3) + "...";
        }
        return str;
    }
    
    public static void main(String[] args) {
        System.out.println("Reading Windows Device Services...\n");
        
        List<ServiceInfo> services = getWindowsServices();
        
        if (services.isEmpty()) {
            System.out.println("No services found or access denied.");
            System.out.println("Try running as Administrator.");
            return;
        }
        
        Scanner scanner = new Scanner(System.in);
        int choice;
        
        do {
            System.out.println("\n========================================");
            System.out.println("           MENU OPTIONS");
            System.out.println("========================================");
            System.out.println("1. Display All Services");
            System.out.println("2. Search Service");
            System.out.println("3. Filter by Status (RUNNING)");
            System.out.println("4. Filter by Status (STOPPED)");
            System.out.println("5. Refresh Services List");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            
            choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    displayAllServices(services);
                    break;
                case 2:
                    System.out.print("Enter service name to search: ");
                    String searchTerm = scanner.nextLine();
                    searchService(services, searchTerm);
                    break;
                case 3:
                    filterByStatus(services, "RUNNING");
                    break;
                case 4:
                    filterByStatus(services, "STOPPED");
                    break;
                case 5:
                    System.out.println("Refreshing services list...");
                    services = getWindowsServices();
                    System.out.println("Services refreshed!");
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 6);
        
        scanner.close();
    }
}
