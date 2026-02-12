import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.*;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

class ServiceData {
    String name;
    String displayName;
    String status;
    String startType;
    
    public ServiceData(String name, String displayName, String status, String startType) {
        this.name = name;
        this.displayName = displayName;
        this.status = status;
        this.startType = startType;
    }
    
    public String toJSON() {
        return String.format(
            "{\"name\":\"%s\",\"displayName\":\"%s\",\"status\":\"%s\",\"startType\":\"%s\"}",
            escapeJSON(name), escapeJSON(displayName), status, startType
        );
    }
    
    private String escapeJSON(String str) {
        return str.replace("\\", "\\\\")
                  .replace("\"", "\\\"")
                  .replace("\n", "\\n")
                  .replace("\r", "\\r");
    }
}

public class ServicesRestAPI {
    
    private static List<ServiceData> getWindowsServices() {
        List<ServiceData> services = new ArrayList<>();
        
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
                        String[] statusParts = parts[1].trim().split("\\s+");
                        if (statusParts.length > 1) {
                            status = statusParts[1];
                        }
                    }
                    
                    if (!serviceName.isEmpty()) {
                        services.add(new ServiceData(serviceName, displayName, status, "AUTO"));
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
    
    private static List<ServiceData> getRunningProcesses() {
        List<ServiceData> processes = new ArrayList<>();
        
        try {
            Process process = Runtime.getRuntime().exec("tasklist /FO CSV /NH");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\",\"");
                
                if (parts.length >= 5) {
                    String name = parts[0].replace("\"", "");
                    String pid = parts[1];
                    String sessionName = parts[2];
                    String memory = parts[4].replace("\"", "").replace(" K", " KB");
                    
                    processes.add(new ServiceData(name, pid, sessionName, memory));
                }
            }
            
            reader.close();
            process.waitFor();
            
        } catch (Exception e) {
            System.err.println("Error reading processes: " + e.getMessage());
        }
        
        return processes;
    }
    
    static class ServicesHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // Enable CORS
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, OPTIONS");
            exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");
            exchange.getResponseHeaders().add("Content-Type", "application/json");
            
            if ("OPTIONS".equals(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(200, -1);
                return;
            }
            
            List<ServiceData> services = getWindowsServices();
            
            StringBuilder json = new StringBuilder("[");
            for (int i = 0; i < services.size(); i++) {
                json.append(services.get(i).toJSON());
                if (i < services.size() - 1) {
                    json.append(",");
                }
            }
            json.append("]");
            
            String response = json.toString();
            exchange.sendResponseHeaders(200, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
    
    static class ProcessesHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // Enable CORS
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, OPTIONS");
            exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");
            exchange.getResponseHeaders().add("Content-Type", "application/json");
            
            if ("OPTIONS".equals(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(200, -1);
                return;
            }
            
            List<ServiceData> processes = getRunningProcesses();
            
            StringBuilder json = new StringBuilder("[");
            for (int i = 0; i < processes.size(); i++) {
                ServiceData p = processes.get(i);
                json.append(String.format(
                    "{\"name\":\"%s\",\"pid\":\"%s\",\"sessionName\":\"%s\",\"memoryUsage\":\"%s\"}",
                    p.name, p.displayName, p.status, p.startType
                ));
                if (i < processes.size() - 1) {
                    json.append(",");
                }
            }
            json.append("]");
            
            String response = json.toString();
            exchange.sendResponseHeaders(200, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
    
    static class ServiceControlHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // Enable CORS
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "POST, OPTIONS");
            exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");
            exchange.getResponseHeaders().add("Content-Type", "application/json");
            
            if ("OPTIONS".equals(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(200, -1);
                return;
            }
            
            if (!"POST".equals(exchange.getRequestMethod())) {
                String response = "{\"success\":false,\"message\":\"Method not allowed\"}";
                exchange.sendResponseHeaders(405, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
                return;
            }
            
            // Read request body
            InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            StringBuilder body = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                body.append(line);
            }
            
            String requestBody = body.toString();
            String serviceName = extractValue(requestBody, "serviceName");
            String action = extractValue(requestBody, "action");
            
            String result = controlService(serviceName, action);
            
            exchange.sendResponseHeaders(200, result.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(result.getBytes());
            os.close();
        }
        
        private String extractValue(String json, String key) {
            String searchKey = "\"" + key + "\":\"";
            int startIndex = json.indexOf(searchKey);
            if (startIndex == -1) return "";
            startIndex += searchKey.length();
            int endIndex = json.indexOf("\"", startIndex);
            if (endIndex == -1) return "";
            return json.substring(startIndex, endIndex);
        }
        
        private String controlService(String serviceName, String action) {
            try {
                String command = "";
                if ("start".equals(action)) {
                    command = "sc start " + serviceName;
                } else if ("stop".equals(action)) {
                    command = "sc stop " + serviceName;
                } else {
                    return "{\"success\":false,\"message\":\"Invalid action\"}";
                }
                
                Process process = Runtime.getRuntime().exec(command);
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                
                StringBuilder output = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line).append(" ");
                }
                while ((line = errorReader.readLine()) != null) {
                    output.append(line).append(" ");
                }
                
                int exitCode = process.waitFor();
                
                if (exitCode == 0 || output.toString().contains("SUCCESS")) {
                    return String.format("{\"success\":true,\"message\":\"Service %s %sed successfully\"}",
                        serviceName, action);
                } else {
                    return String.format("{\"success\":false,\"message\":\"Failed to %s service: %s\"}",
                        action, output.toString().replace("\"", "'"));
                }
                
            } catch (Exception e) {
                return String.format("{\"success\":false,\"message\":\"Error: %s. You may need Administrator privileges.\"}",
                    e.getMessage().replace("\"", "'"));
            }
        }
    }
    
    static class ProcessControlHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // Enable CORS
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "POST, OPTIONS");
            exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");
            exchange.getResponseHeaders().add("Content-Type", "application/json");
            
            if ("OPTIONS".equals(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(200, -1);
                return;
            }
            
            if (!"POST".equals(exchange.getRequestMethod())) {
                String response = "{\"success\":false,\"message\":\"Method not allowed\"}";
                exchange.sendResponseHeaders(405, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
                return;
            }
            
            // Read request body
            InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            StringBuilder body = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                body.append(line);
            }
            
            String requestBody = body.toString();
            String pid = extractValue(requestBody, "pid");
            String processName = extractValue(requestBody, "processName");
            
            String result = killProcess(pid, processName);
            
            exchange.sendResponseHeaders(200, result.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(result.getBytes());
            os.close();
        }
        
        private String extractValue(String json, String key) {
            String searchKey = "\"" + key + "\":\"";
            int startIndex = json.indexOf(searchKey);
            if (startIndex == -1) return "";
            startIndex += searchKey.length();
            int endIndex = json.indexOf("\"", startIndex);
            if (endIndex == -1) return "";
            return json.substring(startIndex, endIndex);
        }
        
        private String killProcess(String pid, String processName) {
            try {
                String command = "taskkill /F /PID " + pid;
                
                Process process = Runtime.getRuntime().exec(command);
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                
                StringBuilder output = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line).append(" ");
                }
                while ((line = errorReader.readLine()) != null) {
                    output.append(line).append(" ");
                }
                
                int exitCode = process.waitFor();
                
                if (exitCode == 0 || output.toString().contains("SUCCESS")) {
                    return String.format("{\"success\":true,\"message\":\"Process %s (PID: %s) terminated successfully\"}",
                        processName, pid);
                } else {
                    return String.format("{\"success\":false,\"message\":\"Failed to kill process: %s\"}",
                        output.toString().replace("\"", "'"));
                }
                
            } catch (Exception e) {
                return String.format("{\"success\":false,\"message\":\"Error: %s. You may need Administrator privileges.\"}",
                    e.getMessage().replace("\"", "'"));
            }
        }
    }
    
    public static void main(String[] args) throws Exception {
        int port = 8080;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        
        server.createContext("/api/services", new ServicesHandler());
        server.createContext("/api/processes", new ProcessesHandler());
        server.createContext("/api/service/control", new ServiceControlHandler());
        server.createContext("/api/process/kill", new ProcessControlHandler());
        
        server.setExecutor(null);
        server.start();
        
        System.out.println("=".repeat(60));
        System.out.println("REST API Server Started Successfully!");
        System.out.println("=".repeat(60));
        System.out.println("Server running on: http://localhost:" + port);
        System.out.println("\nAvailable Endpoints:");
        System.out.println("  GET  http://localhost:" + port + "/api/services");
        System.out.println("  GET  http://localhost:" + port + "/api/processes");
        System.out.println("  POST http://localhost:" + port + "/api/service/control");
        System.out.println("  POST http://localhost:" + port + "/api/process/kill");
        System.out.println("\nNote: Start/Stop services and Kill processes requires Administrator privileges");
        System.out.println("Press Ctrl+C to stop the server");
        System.out.println("=".repeat(60));
    }
}
