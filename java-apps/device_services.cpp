#include <windows.h>
#include <iostream>
#include <string>
#include <vector>

using namespace std;

struct ServiceInfo {
    string name;
    string displayName;
    string status;
    string startType;
};

string getServiceStatus(DWORD status) {
    switch (status) {
        case SERVICE_STOPPED: return "STOPPED";
        case SERVICE_START_PENDING: return "START_PENDING";
        case SERVICE_STOP_PENDING: return "STOP_PENDING";
        case SERVICE_RUNNING: return "RUNNING";
        case SERVICE_CONTINUE_PENDING: return "CONTINUE_PENDING";
        case SERVICE_PAUSE_PENDING: return "PAUSE_PENDING";
        case SERVICE_PAUSED: return "PAUSED";
        default: return "UNKNOWN";
    }
}

string getStartType(DWORD startType) {
    switch (startType) {
        case SERVICE_AUTO_START: return "AUTO";
        case SERVICE_BOOT_START: return "BOOT";
        case SERVICE_DEMAND_START: return "MANUAL";
        case SERVICE_DISABLED: return "DISABLED";
        case SERVICE_SYSTEM_START: return "SYSTEM";
        default: return "UNKNOWN";
    }
}

vector<ServiceInfo> getDeviceServices() {
    vector<ServiceInfo> services;
    SC_HANDLE scManager = OpenSCManager(NULL, NULL, SC_MANAGER_ENUMERATE_SERVICE);
    
    if (scManager == NULL) {
        cerr << "Failed to open Service Control Manager. Error: " << GetLastError() << endl;
        return services;
    }
    
    DWORD bytesNeeded = 0;
    DWORD servicesReturned = 0;
    DWORD resumeHandle = 0;
    
    EnumServicesStatusEx(
        scManager,
        SC_ENUM_PROCESS_INFO,
        SERVICE_WIN32,
        SERVICE_STATE_ALL,
        NULL,
        0,
        &bytesNeeded,
        &servicesReturned,
        &resumeHandle,
        NULL
    );
    
    LPBYTE buffer = new BYTE[bytesNeeded];
    ENUM_SERVICE_STATUS_PROCESS* serviceStatus = (ENUM_SERVICE_STATUS_PROCESS*)buffer;
    
    if (EnumServicesStatusEx(
        scManager,
        SC_ENUM_PROCESS_INFO,
        SERVICE_WIN32,
        SERVICE_STATE_ALL,
        buffer,
        bytesNeeded,
        &bytesNeeded,
        &servicesReturned,
        &resumeHandle,
        NULL
    )) {
        for (DWORD i = 0; i < servicesReturned; i++) {
            SC_HANDLE service = OpenService(scManager, serviceStatus[i].lpServiceName, SERVICE_QUERY_CONFIG);
            
            if (service != NULL) {
                DWORD configBytesNeeded = 0;
                QueryServiceConfig(service, NULL, 0, &configBytesNeeded);
                
                LPQUERY_SERVICE_CONFIG serviceConfig = (LPQUERY_SERVICE_CONFIG)new BYTE[configBytesNeeded];
                
                if (QueryServiceConfig(service, serviceConfig, configBytesNeeded, &configBytesNeeded)) {
                    ServiceInfo info;
                    info.name = serviceStatus[i].lpServiceName;
                    info.displayName = serviceStatus[i].lpDisplayName;
                    info.status = getServiceStatus(serviceStatus[i].ServiceStatusProcess.dwCurrentState);
                    info.startType = getStartType(serviceConfig->dwStartType);
                    
                    services.push_back(info);
                }
                
                delete[] serviceConfig;
                CloseServiceHandle(service);
            }
        }
    }
    
    delete[] buffer;
    CloseServiceHandle(scManager);
    
    return services;
}

void displayServices(const vector<ServiceInfo>& services) {
    cout << "\n========================================" << endl;
    cout << "       WINDOWS DEVICE SERVICES" << endl;
    cout << "========================================\n" << endl;
    cout << "Total Services Found: " << services.size() << "\n" << endl;
    
    cout.width(40); cout << left << "Service Name";
    cout.width(50); cout << left << "Display Name";
    cout.width(15); cout << left << "Status";
    cout.width(10); cout << left << "Start Type" << endl;
    cout << string(115, '-') << endl;
    
    for (const auto& service : services) {
        cout.width(40); cout << left << service.name;
        cout.width(50); cout << left << service.displayName;
        cout.width(15); cout << left << service.status;
        cout.width(10); cout << left << service.startType << endl;
    }
}

void searchService(const vector<ServiceInfo>& services, const string& searchTerm) {
    cout << "\nSearching for: " << searchTerm << "\n" << endl;
    bool found = false;
    
    for (const auto& service : services) {
        if (service.name.find(searchTerm) != string::npos || 
            service.displayName.find(searchTerm) != string::npos) {
            cout << "Service Name: " << service.name << endl;
            cout << "Display Name: " << service.displayName << endl;
            cout << "Status: " << service.status << endl;
            cout << "Start Type: " << service.startType << endl;
            cout << string(50, '-') << endl;
            found = true;
        }
    }
    
    if (!found) {
        cout << "No services found matching: " << searchTerm << endl;
    }
}

void filterByStatus(const vector<ServiceInfo>& services, const string& status) {
    cout << "\nServices with status: " << status << "\n" << endl;
    int count = 0;
    
    for (const auto& service : services) {
        if (service.status == status) {
            cout << service.displayName << " (" << service.name << ")" << endl;
            count++;
        }
    }
    
    cout << "\nTotal: " << count << " services" << endl;
}

int main() {
    cout << "Reading Windows Device Services..." << endl;
    
    vector<ServiceInfo> services = getDeviceServices();
    
    if (services.empty()) {
        cout << "No services found or access denied." << endl;
        return 1;
    }
    
    int choice;
    do {
        cout << "\n========================================" << endl;
        cout << "           MENU OPTIONS" << endl;
        cout << "========================================" << endl;
        cout << "1. Display All Services" << endl;
        cout << "2. Search Service" << endl;
        cout << "3. Filter by Status (RUNNING)" << endl;
        cout << "4. Filter by Status (STOPPED)" << endl;
        cout << "5. Exit" << endl;
        cout << "Enter your choice: ";
        cin >> choice;
        cin.ignore();
        
        switch (choice) {
            case 1:
                displayServices(services);
                break;
            case 2: {
                string searchTerm;
                cout << "Enter service name to search: ";
                getline(cin, searchTerm);
                searchService(services, searchTerm);
                break;
            }
            case 3:
                filterByStatus(services, "RUNNING");
                break;
            case 4:
                filterByStatus(services, "STOPPED");
                break;
            case 5:
                cout << "Exiting..." << endl;
                break;
            default:
                cout << "Invalid choice!" << endl;
        }
    } while (choice != 5);
    
    return 0;
}
