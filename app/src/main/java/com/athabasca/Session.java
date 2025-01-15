package com.athabasca;
import java.util.List;
import java.util.Map;

public class Session 
{
    /*
     * Permission system
     * 0 = lowest, employees
     * 1 = admins
     */
    private int PERMISSION = -1;
    private String f_name;
    private String l_name;
    private String email;
    private String token;
    private Map<Integer, String> assigned;

    public Session(String email, String token)
    {
        DatabaseUtil db = new DatabaseUtil();
        db.setRef(DatabaseUtil.THING+"employee", data -> {
            if(db.getData() != null)
            {
                Map<String, Map<String, Object>> loadedData = (Map<String, Map<String, Object>>) db.getData();
                for(Map.Entry<String, Map<String, Object>> entry : loadedData.entrySet())
                {
                    if(entry.getValue().get("email").equals(email))
                    {
                        this.f_name = (String) entry.getValue().get("f_name");
                        this.l_name = (String) entry.getValue().get("l_name");
                        this.email = (String) entry.getValue().get("email");
                        this.PERMISSION = (int) entry.getValue().get("permission");
                        this.assigned = (Map<Integer, String>) entry.getValue().get("assigned");
                        break;
                    }
                }
            }
        });
    }

    /**
     * @return String return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @return int return the PERMISSION
     */
    public int getPermissin() {
        return PERMISSION;
    }

    /**
     * @return String return the f_name
     */
    public String getF_name() {
        return f_name;
    }

    /**
     * @param f_name the f_name to set
     */
    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    /**
     * @return String return the l_name
     */
    public String getL_name() {
        return l_name;
    }

    /**
     * @param l_name the l_name to set
     */
    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    /**
     * @return String return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return Map<Integer, String> return the assigned
     */
    public Map<Integer, String> getAssigned() {
        return assigned;
    }
}
