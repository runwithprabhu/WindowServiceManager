# Windows Services & Process Manager

Complete portable solution for monitoring and managing Windows services and processes with web-based UIs.

## Quick Start

### Automated Launch (Recommended)
```cmd
LAUNCH.bat
```
This will:
1. Check Java installation
2. Compile and start REST API server
3. Open web UIs in Chrome automatically

### Installation Check
```cmd
INSTALL.bat
```
Verifies all requirements are met before running.

## Project Structure

```
KIRO_PJT/
├── LAUNCH.bat              # Main launcher script
├── INSTALL.bat             # Installation checker
├── backend/                # REST API Server
│   ├── ServicesRestAPI.java
│   └── START_SERVER.bat
├── web-ui/                 # Web Interfaces
│   ├── device-services-ui.html
│   ├── process-manager-ui.html
│   └── angular-login-v2.html
├── calculators/            # Calculator Applications
│   ├── calculator-modern.html
│   └── scientific-calculator.html
├── java-apps/              # Standalone Java Apps
│   ├── DeviceServices.java
│   ├── ProcessManager.java
│   └── device_services.cpp
└── docs/                   # Documentation
    ├── README.md
    └── DEPLOYMENT.md
```

## Features

### Services Manager
- ✅ View all Windows services
- ✅ Filter by status (Running/Stopped)
- ✅ Search by name
- ✅ Real-time statistics
- ✅ Responsive design

### Process Manager
- ✅ View all running processes
- ✅ Multi-select processes
- ✅ Kill selected processes
- ✅ Memory usage tracking
- ✅ Color-coded memory indicators
- ✅ Sortable columns

### Tizen TV App
- ✅ TV-optimized 1080p interface
- ✅ Remote control navigation
- ✅ Service details modal
- ✅ Real-time clock
- ✅ Filter by status

## Requirements

- Java JDK 8 or higher
- Modern web browser (Chrome, Firefox, Edge)
- Windows OS (for service/process data)

## Troubleshooting

**Problem**: "Failed to load services/processes"
**Solution**: Make sure the REST API server is running on port 8080

**Problem**: Port 8080 already in use
**Solution**: Edit `ServicesRestAPI.java` and change the port number

**Problem**: CORS errors
**Solution**: The API already includes CORS headers. Make sure you're accessing via http://localhost

## Notes

- The REST API must be running for the UIs to display real data
- Some operations may require Administrator privileges
- The Tizen TV app can be deployed to Samsung Smart TVs using Tizen Studio
