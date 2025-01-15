package com.athabasca;

import java.util.ArrayList;
import java.util.Map;
import java.util.function.Consumer;

//THIS CLASS NEEDS PROPER IMPLEMENTATION
public class Clients {
    public static ArrayList<Client> clients;

    public static void loadClients()
    {
        clients = new ArrayList<Client>();
        DatabaseUtil db = new DatabaseUtil();
        db.setRef("client");
        if (db.getData() != null) {
            try {
                Map<String, Map<String, Object>> loadedData = (Map<String, Map<String, Object>>) db.getData();
                for (Map.Entry<String, Map<String, Object>> entry : loadedData.entrySet()) {
                    System.out.println("Processing entry: " + entry);
                    clients.add(new Client(
                        (String) entry.getValue().get("f_name"),
                        (String) entry.getValue().get("l_name"),
                        (Long) entry.getValue().get("p_number"),
                        (String) entry.getValue().get("address"),
                        (String) entry.getValue().get("date_joined")
                    ));
                }
                System.out.println("Clients loaded: " + clients);
            } catch (ClassCastException e) {
                System.err.println("Error casting data: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("No data found at path: client");
        }
        System.out.println(1);
    }
}   
