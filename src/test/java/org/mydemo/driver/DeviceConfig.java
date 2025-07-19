package org.mydemo.driver;

public class DeviceConfig {
    public String udid;
    public int port;
    public int systemPort;

    public DeviceConfig(String udid, int port, int systemPort) {
        this.udid = udid;
        this.port = port;
        this.systemPort = systemPort;
    }
}
