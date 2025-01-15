package com.athabasca;

import java.util.ArrayList;
import java.util.Map;
import java.util.function.Consumer;

public class Clients {
    public static ArrayList<Client> clients = new ArrayList<>();

    public static void loadClients(Consumer<ArrayList<Client>> callback) {
        clients = new ArrayList<>();
        DatabaseUtil db = new DatabaseUtil();
        db.setRef("client", data -> {
            if (data != null) {
                System.out.println("Data received in callback: " + data);
                try {
                    Map<String, Map<String, Object>> loadedData = (Map<String, Map<String, Object>>) data;
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
                callback.accept(clients); // Invoke the callback with the loaded clients
            } else {
                System.out.println("No data found at path: client");
                callback.accept(clients); // Invoke the callback with the empty list
            }
        });
    }
}