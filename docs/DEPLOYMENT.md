# Portable Deployment Guide

## Overview
This is a portable Windows Services & Process Manager that can be deployed on any Windows system without installation.

## System Requirements

### Minimum Requirements
- **OS**: Windows 7 or higher (Windows 10/11 recommended)
- **Java**: JDK 8 or higher
- **Browser**: Chrome, Firefox, or Edge
- **RAM**: 512 MB minimum
- **Disk Space**: 50 MB

### Administrator Privileges
- Required for: Starting/stopping services and killing processes
- Not required for: Viewing services and processes

## Quick Start

### Option 1: Automated Launch (Recommended)
1. Double-click `LAUNCH.bat`
2. Wait for the server to start
3. Web UIs will open automatically in Chrome

### Option 2: Manual Launch
1. Open Command Prompt in the project folder
2. Run: `cd backend`
3. Run: `javac ServicesRestAPI.java`
4. Run: `java ServicesRestAPI`
5. Open `web-ui/device-services-ui.html` in browser
6. Open `web-ui/process-manager-ui.html` in browser

## Deployment Steps

### 1. Prepare the Package
Copy the entire project folder to a USB drive or network location:
```
KIRO_PJT/
├── LAUNCH.bat              (Main launcher)
├── INSTALL.bat             (Installation checker)
├── backend/                (REST API server)
├── web-ui/                 (Web interfaces)
├── calculators/            (Calculator apps)
├── java-apps/              (Standalone Java apps)
└── docs/                   (Documentation)
```

### 2. Deploy to Target System
1. Copy the entire folder to the target system
2. Run `INSTALL.bat` to check requirements
3. Install Java if needed
4. Run `LAUNCH.bat` to start

### 3. First-Time Setup on New System
```cmd
# Check installation
INSTALL.bat

# If Java is missing, install it:
# Download from: https://www.oracle.com/java/technologies/downloads/

# Launch application
LAUNCH.bat
```

## Portable USB Deployment

### Create Portable Package
1. Copy project folder to USB drive
2. Include Java JRE (optional):
   - Download Java JRE portable
   - Extract to `portable-java/` folder
   - Update LAUNCH.bat to use portable Java

### Modified LAUNCH.bat for Portable Java
```batch
@echo off
set JAVA_HOME=%~dp0portable-java
set PATH=%JAVA_HOME%\bin;%PATH%
cd backend
javac ServicesRestAPI.java
start java ServicesRestAPI
cd ..
start chrome web-ui/device-services-ui.html
```

## Network Deployment

### Deploy on Network Share
1. Copy project to network location: `\\server\share\KIRO_PJT`
2. Users can run directly from network
3. Each user gets their own server instance (different ports)

### Multi-User Setup
Edit `ServicesRestAPI.java` to use different ports:
```java
int port = 8080 + userId; // e.g., 8081, 8082, etc.
```

## Configuration

### Change Server Port
Edit `backend/ServicesRestAPI.java`:
```java
int port = 8080; // Change to desired port
```

### Change Browser
Edit `LAUNCH.bat`:
```batch
REM Replace chrome with firefox or msedge
start firefox "file:///%CURRENT_DIR%/web-ui/device-services-ui.html"
```

## Troubleshooting

### Java Not Found
**Problem**: "Java is not installed or not in PATH"
**Solution**:
1. Install Java JDK from Oracle
2. Add to PATH: `C:\Program Files\Java\jdk-XX\bin`
3. Restart Command Prompt

### Port Already in Use
**Problem**: "Address already in use: bind"
**Solution**:
1. Kill existing Java process: `taskkill /F /IM java.exe`
2. Or change port in ServicesRestAPI.java

### Permission Denied
**Problem**: "Access denied" when starting/stopping services
**Solution**:
1. Right-click `LAUNCH.bat`
2. Select "Run as administrator"

### Browser Not Opening
**Problem**: Web UIs don't open automatically
**Solution**:
1. Manually open browser
2. Navigate to: `file:///C:/path/to/KIRO_PJT/web-ui/device-services-ui.html`

## Security Considerations

### Running as Administrator
- Only required for service/process control
- Viewing data works without admin rights
- Consider security implications on shared systems

### Network Security
- Server binds to localhost only (127.0.0.1)
- Not accessible from network by default
- CORS enabled for local file access

### Data Privacy
- No data is stored or transmitted externally
- All operations are local to the machine
- No telemetry or analytics

## Performance Tips

### Optimize for Low-End Systems
1. Reduce refresh frequency in UI
2. Limit displayed processes/services
3. Close unused browser tabs

### Optimize for High-End Systems
1. Enable auto-refresh
2. Display all data
3. Run multiple instances

## Backup and Updates

### Backup Configuration
- No configuration files to backup
- All settings are in source code

### Update Procedure
1. Stop running server
2. Replace files with new version
3. Restart server

## Uninstallation

### Complete Removal
1. Stop server: `taskkill /F /IM java.exe`
2. Delete project folder
3. No registry entries or system files to clean

### Partial Removal
- Keep `docs/` for reference
- Delete `backend/` and `web-ui/` only

## Support

### Common Issues
- Check `INSTALL.bat` output
- Review server console for errors
- Check browser console (F12)

### Log Files
- Server output in console window
- Browser console (F12) for UI errors

## License
This is a demonstration project. Use at your own risk.

## Version
Version: 1.0.0
Last Updated: February 2026
