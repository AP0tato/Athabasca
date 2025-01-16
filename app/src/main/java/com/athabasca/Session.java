package com.athabasca;
import java.util.Map;

public class Session 
{
    /*
     * Permission system
     * 0 = lowest, employees
     * 1 = admins
     */
    private static int PERMISSION = -1;
    private static String f_name;
    private static String l_name;
    private static String email;
    private static String token;
    private static Map<Integer, String> assigned;
    private static DatabaseUtil db;

    public Session(String email, String token)
    {
        db = new DatabaseUtil();
        update();
    }

    @SuppressWarnings("unchecked")
    public static void update()
    {
        db.setRef("employee", data -> {
            if(data != null)
            {
                try {
                    Map<String, Map<String, Object>> loadedData = (Map<String, Map<String, Object>>) data;
                    for(Map.Entry<String, Map<String, Object>> entry : loadedData.entrySet())
                    {
                        if(entry.getValue().get("email").equals(email))
                        {
                            f_name = (String) entry.getValue().get("f_name");
                            l_name = (String) entry.getValue().get("l_name");
                            email = (String) entry.getValue().get("email");
                            PERMISSION = (int) entry.getValue().get("permission");
                            assigned = (Map<Integer, String>) entry.getValue().get("assigned");
                            break;
                        }
                    }
                } catch(ClassCastException e) {
                    System.err.println("Error casting data: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * @return String return the token
     */
    public static String getToken() {
        return token;
    }

    /**
     * @return int return the PERMISSION
     */
    public static int getPermission() {
        return PERMISSION;
    }

    /**
     * @return String return the f_name
     */
    public static String getF_name() {
        return f_name;
    }

    /**
     * @param f_name the f_name to set
     */
    public static void setF_name(String F_name) {
        f_name = F_name;
    }

    /**
     * @return String return the l_name
     */
    public static String getL_name() {
        return l_name;
    }

    /**
     * @param l_name the l_name to set
     */
    public static void setL_name(String L_name) {
        l_name = L_name;
    }

    /**
     * @return String return the email
     */
    public static String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public static void setEmail(String Email) {
        email = Email;
    }

    /**
     * @return Map<Integer, String> return the assigned
     */
    public static Map<Integer, String> getAssigned() {
        return assigned;
    }
}
