package com.athabasca;

import java.util.ArrayList;
import java.util.Map;
import java.util.function.Consumer;

//THIS CLASS NEEDS PROPER IMPLEMENTATION
public class Clients {
    public static ArrayList<Client> clients;

    public static void loadClients(Consumer<ArrayList<Client>> callback)
    {
        clients = new ArrayList<Client>();
        DatabaseUtil db = new DatabaseUtil();
        db.setRef("client", data -> {
            if(db.getData()!=null)
            {
                Map<String, Map<String, Object>> loadedData = (Map<String, Map<String, Object>>) db.getData();
                for(Map.Entry<String, Map<String, Object>> entry : loadedData.entrySet())
                {
                    clients.add(new Client(
                        (String) entry.getValue().get("f_name"),
                        (String) entry.getValue().get("l_name"),
                        (Long) entry.getValue().get("phone"),
                        (String) entry.getValue().get("address"),
                        (String) entry.getValue().get("date_joined")
                    ));
                }
            }
        });
    }
}   
