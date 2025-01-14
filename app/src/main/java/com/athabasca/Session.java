package com.athabasca;
import java.util.List;

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
    private String employee_id;
    private List<String> assigned;

    public Session(String email)
    {
        
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
     * @return String return the employee_id
     */
    public String getEmployee_id() {
        return employee_id;
    }

    /**
     * @param employee_id the employee_id to set
     */
    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    /**
     * @return List<String> return the assigned
     */
    public List<String> getAssigned() {
        return assigned;
    }

    /**
     * @param assigned the assigned to set
     */
    public void setAssigned(List<String> assigned) {
        this.assigned = assigned;
    }

}
