package com.athabasca;
import java.util.List;

public class Session 
{
    /*
     * Permission system
     * 0 = lowest, employees
     * 1 = admins
     */
    private static int PERMISSION = -1;
    private String f_name;
    private String l_name;
    private String email;
    private String employee_id;
    private List<String> assigned;

    public Session(String email)
    {
        
    }
}
