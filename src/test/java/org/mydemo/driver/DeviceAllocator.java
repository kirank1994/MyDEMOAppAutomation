package org.mydemo.driver;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class DeviceAllocator {

    private static List<String> connectedUdidList = new ArrayList<>();
    private static AtomicInteger deviceCounter = new AtomicInteger(0);

    static {
        loadConnectedDevices();
    }

    private static void loadConnectedDevices() {
        try {
        	Process process = Runtime.getRuntime().exec("/Users/kondurikiran/Library/Android/sdk/platform-tools/adb devices");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.endsWith("device") && !line.startsWith("List")) {
                    String udid = line.split("\\s+")[0];
                    connectedUdidList.add(udid);
                }
            }

            if (connectedUdidList.isEmpty()) {
                System.err.println("❌ No connected Android devices found!");
            } else {
                System.out.println("✅ Connected devices: " + connectedUdidList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static synchronized DeviceConfig getNextDevice() {
        if (connectedUdidList.isEmpty()) {
            throw new RuntimeException("❌ No connected devices found. Start emulator or connect device.");
        }

        int index = deviceCounter.getAndIncrement() % connectedUdidList.size();
        String udid = connectedUdidList.get(index);

        int basePort = 4723;
        int baseSystemPort = 8200;

        System.out.println("✅ Allocating device: " + udid);
        return new DeviceConfig(udid, basePort + index * 2, baseSystemPort + index);
    }
}
