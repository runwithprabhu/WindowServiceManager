# Windows Services & Process Manager

A portable web-based application for monitoring and managing Windows services and processes with real-time control capabilities.

![License](https://img.shields.io/badge/license-MIT-blue.svg)
![Java](https://img.shields.io/badge/Java-8%2B-orange.svg)
![Platform](https://img.shields.io/badge/platform-Windows-lightgrey.svg)

## Features

### 🔧 Service Management
- View all Windows services in real-time
- Start/Stop services with one click
- Filter by status (Running/Stopped/All)
- Search services by name
- Real-time statistics dashboard

### 💻 Process Management
- Monitor all running processes
- Kill individual or multiple processes
- Memory usage tracking with color coding
- Multi-select with checkboxes
- Sortable columns (Name, PID, Memory, Session)

### 🎨 Modern Web UI
- Responsive design
- Dark theme with vibrant colors
- Real-time updates
- AngularJS-powered interface
- No installation required

### 📱 Additional Apps
- Scientific Calculator
- Modern Calculator
- Login Page Templates
- Tizen TV App (for Samsung Smart TVs)

## Quick Start

### Prerequisites
- Windows 7 or higher
- Java JDK 8 or higher
- Modern web browser (Chrome recommended)

### Installation

1. **Clone the repository**
```bash
git clone https://github.com/YOUR_USERNAME/windows-services-manager.git
cd windows-services-manager
```

2. **Check requirements**
```cmd
INSTALL.bat
```

3. **Launch application**
```cmd
LAUNCH.bat
```

That's it! The REST API server will start and web UIs will open automatically in Chrome.

## Manual Setup

If you prefer manual setup:

```cmd
# Start REST API Server
cd backend
javac ServicesRestAPI.java
java ServicesRestAPI

# Open in browser
# Navigate to web-ui/device-services-ui.html
# Navigate to web-ui/process-manager-ui.html
```

## Project Structure

```
windows-services-manager/
├── LAUNCH.bat              # One-click launcher
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

## API Endpoints

The REST API server runs on `http://localhost:8080` with the following endpoints:

- `GET /api/services` - Get all Windows services
- `GET /api/processes` - Get all running processes
- `POST /api/service/control` - Start/Stop a service
- `POST /api/process/kill` - Kill a process

## Screenshots

### Device Services Manager
- Real-time service monitoring
- Start/Stop controls
- Search and filter capabilities

### Process Manager
- Live process list
- Memory usage tracking
- Multi-select kill operations

## Deployment

### Portable USB Deployment
1. Copy entire project folder to USB drive
2. Run `LAUNCH.bat` on any Windows system
3. No installation required

### Network Deployment
1. Copy to network share
2. Users can run directly from network location
3. Each user gets their own server instance

See [DEPLOYMENT.md](docs/DEPLOYMENT.md) for detailed deployment instructions.

## Security Notes

- **Administrator Privileges**: Required for starting/stopping services and killing processes
- **Local Only**: Server binds to localhost (127.0.0.1) by default
- **No External Access**: Not accessible from network
- **No Data Collection**: All operations are local, no telemetry

## Troubleshooting

### Java Not Found
Install Java JDK from [Oracle](https://www.oracle.com/java/technologies/downloads/) and add to PATH.

### Port Already in Use
Kill existing Java process:
```cmd
taskkill /F /IM java.exe
```

### Permission Denied
Run `LAUNCH.bat` as Administrator (right-click → Run as administrator).

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- Built with Java and AngularJS
- Uses Windows Service Control Manager API
- Inspired by Windows Task Manager

## Support

For issues, questions, or suggestions, please open an issue on GitHub.

## Version

**Current Version**: 1.0.0  
**Last Updated**: February 2026

---

Made with ❤️ for Windows system administrators and developers
