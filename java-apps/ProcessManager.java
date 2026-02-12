import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class ProcessInfo {
    String name;
    String pid;
    String memoryUsage;
    String sessionName;
    
    public ProcessInfo(String name, String pid, String sessionName, String memoryUsage) {
        this.name = name;
        this.pid = pid;
        this.sessionName = sessionName;
        this.memoryUsage = memoryUsage;
    }
}

public class ProcessManager {
    
    public static List<ProcessInfo> getRunningProcesses() {
        List<ProcessInfo> processes = new ArrayList<>();
        
        try {
            Process process = Runtime.getRuntime().exec("tasklist /FO CSV /NH");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            
            String line;
            while ((line = reader.readLine()) != null) {
                // Parse CSV format: "name","pid","session","session#","memory"
                String[] parts = line.split("\",\"");
                
                if (parts.length >= 5) {
                    String name = parts[0].replace("\"", "");
                    String pid = parts[1];
                    String sessionName = parts[2];
                    String memory = parts[4].replace("\"", "").replace(" K", " KB");
                    
                    processes.add(new ProcessInfo(name, pid, sessionName, memory));
                }
            }
            
            reader.close();
            process.waitFor();
            
        } catch (Exception e) {
            System.err.println("Error reading processes: " + e.getMessage());
        }
        
        return processes;
    }
    
    public static void displayAllProcesses(List<ProcessInfo> processes) {
        System.out.println("\n" + "=".repeat(120));
        System.out.println("                              RUNNING PROCESSES ON WINDOWS");
        System.out.println("=".repeat(120) + "\n");
        System.out.println("Total Processes: " + processes.size() + "\n");
        
        System.out.printf("%-40s %-10s %-20s %-15s%n", "Process Name", "PID", "Session Name", "Memory Usage");
        System.out.println("-".repeat(120));
        
        for (ProcessInfo proc : processes) {
            System.out.printf("%-40s %-10s %-20s %-15s%n", 
                truncate(proc.name, 40), 
                proc.pid, 
                truncate(proc.sessionName, 20),
                proc.memoryUsage);
        }
    }
    
    public static void searchProcess(List<ProcessInfo> processes, String searchTerm) {
        System.out.println("\nSearching for: " + searchTerm + "\n");
        boolean found = false;
        
        for (ProcessInfo proc : processes) {
            if (proc.name.toLowerCase().contains(searchTerm.toLowerCase())) {
                System.out.println("Process Name: " + proc.name);
                System.out.println("PID: " + proc.pid);
                System.out.println("Session: " + proc.sessionName);
                System.out.println("Memory: " + proc.memoryUsage);
                System.out.println("-".repeat(50));
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("No processes found matching: " + searchTerm);
        }
    }
    
    public static void displayTopMemoryProcesses(List<ProcessInfo> processes, int count) {
        System.out.println("\nTop " + count + " Processes by Memory Usage:\n");
        
        // Sort by memory (simple bubble sort for demonstration)
        List<ProcessInfo> sorted = new ArrayList<>(processes);
        for (int i = 0; i < sorted.size() - 1; i++) {
            for (int j = 0; j < sorted.size() - i - 1; j++) {
                long mem1 = parseMemory(sorted.get(j).memoryUsage);
                long mem2 = parseMemory(sorted.get(j + 1).memoryUsage);
                if (mem1 < mem2) {
                    ProcessInfo temp = sorted.get(j);
                    sorted.set(j, sorted.get(j + 1));
                    sorted.set(j + 1, temp);
                }
            }
        }
        
        System.out.printf("%-40s %-10s %-15s%n", "Process Name", "PID", "Memory Usage");
        System.out.println("-".repeat(70));
        
        for (int i = 0; i < Math.min(count, sorted.size()); i++) {
            ProcessInfo proc = sorted.get(i);
            System.out.printf("%-40s %-10s %-15s%n", 
                truncate(proc.name, 40), 
                proc.pid, 
                proc.memoryUsage);
        }
    }
    
    public static long parseMemory(String memStr) {
        try {
            return Long.parseLong(memStr.replace(" KB", "").replace(",", "").trim());
        } catch (Exception e) {
            return 0;
        }
    }
    
    public static void killProcess(String pid) {
        try {
            System.out.println("\nAttempting to kill process with PID: " + pid);
            Process process = Runtime.getRuntime().exec("taskkill /PID " + pid + " /F");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            
            reader.close();
            process.waitFor();
            
        } catch (Exception e) {
            System.err.println("Error killing process: " + e.getMessage());
            System.err.println("You may need to run as Administrator.");
        }
    }
    
    public static void displayProcessCount(List<ProcessInfo> processes) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("Process Statistics:");
        System.out.println("=".repeat(50));
        System.out.println("Total Processes: " + processes.size());
        
        // Count by session
        int consoleProcesses = 0;
        int serviceProcesses = 0;
        
        for (ProcessInfo proc : processes) {
            if (proc.sessionName.toLowerCase().contains("console")) {
                consoleProcesses++;
            } else if (proc.sessionName.toLowerCase().contains("services")) {
                serviceProcesses++;
            }
        }
        
        System.out.println("Console Processes: " + consoleProcesses);
        System.out.println("Service Processes: " + serviceProcesses);
        System.out.println("=".repeat(50));
    }
    
    public static String truncate(String str, int length) {
        if (str.length() > length) {
            return str.substring(0, length - 3) + "...";
        }
        return str;
    }
    
    public static void main(String[] args) {
        System.out.println("Windows Process Manager");
        System.out.println("Loading processes...\n");
        
        List<ProcessInfo> processes = getRunningProcesses();
        
        if (processes.isEmpty()) {
            System.out.println("No processes found or access denied.");
            return;
        }
        
        Scanner scanner = new Scanner(System.in);
        int choice;
        
        do {
            System.out.println("\n" + "=".repeat(50));
            System.out.println("              MENU OPTIONS");
            System.out.println("=".repeat(50));
            System.out.println("1. Display All Processes");
            System.out.println("2. Search Process");
            System.out.println("3. Top 10 Memory Consuming Processes");
            System.out.println("4. Process Statistics");
            System.out.println("5. Kill Process by PID");
            System.out.println("6. Refresh Process List");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            
            choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    displayAllProcesses(processes);
                    break;
                case 2:
                    System.out.print("Enter process name to search: ");
                    String searchTerm = scanner.nextLine();
                    searchProcess(processes, searchTerm);
                    break;
                case 3:
                    displayTopMemoryProcesses(processes, 10);
                    break;
                case 4:
                    displayProcessCount(processes);
                    break;
                case 5:
                    System.out.print("Enter PID to kill: ");
                    String pid = scanner.nextLine();
                    killProcess(pid);
                    System.out.println("\nRefreshing process list...");
                    processes = getRunningProcesses();
                    break;
                case 6:
                    System.out.println("Refreshing process list...");
                    processes = getRunningProcesses();
                    System.out.println("Process list refreshed! Total: " + processes.size());
                    break;
                case 7:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 7);
        
        scanner.close();
    }
}
