package com.olebokolo.distance.reading;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommandLineHelper {

    Map<String, List<String>> getCommandLineParams(String[] args) {
        Map<String, List<String>> params = new HashMap<>();
        List<String> options = null;
        for (final String a : args) {
            if (a.startsWith("-")) {
                if (a.length() < 2) {
                    System.err.println("Error at argument " + a);
                    return null;
                }
                options = new ArrayList<>();
                params.put(a.substring(1), options);
            } else if (options != null) {
                options.add(a);
            } else {
                System.err.println("Illegal parameter usage");
                return null;
            }
        }
        return params;
    }
}
