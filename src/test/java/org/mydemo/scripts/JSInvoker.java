package org.mydemo.scripts;

import java.io.*;

public class JSInvoker {
    public static void main(String[] args) throws IOException {
        // Command: node MathUtil.js 10 20
        ProcessBuilder pb = new ProcessBuilder("/opt/homebrew/bin/node", "MathUtil.js", "10", "20");

        // Set working directory if needed
        pb.directory(new File("scripts"));
        pb.redirectErrorStream(true);
        

        // Start the process
        Process process = pb.start();

        // Read the output from JS
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()));

        String line;
        System.out.print("Output from JavaScript: ");
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }
}