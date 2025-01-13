package com.athabasca;
public class Admin extends Employee
{
    /*
     * Permission system
     * 0 = lowest, employees
     * 1 = admins
     */
    public static final int PERMISSION = 1;

    public Admin(String f_name, String l_name)
    {
        super(f_name, l_name);
    }
}
