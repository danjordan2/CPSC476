package com.problemset1.readers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader implements IReader {

    private StringBuilder builder = null;
    private Scanner scanner = null;
    private String componentName = null;

    public FileReader(String fileName) {
        try {
            scanner = new Scanner(new File(fileName));
        } catch (FileNotFoundException ex) {
            System.out.println("FileNotFoundException occured : " + ex.getMessage());
        }
        builder = new StringBuilder();
    }

    public String read() {
        while (scanner.hasNext()) {
            builder.append(scanner.next());
            builder.append(",");
        }
        return builder.toString();
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }
}
