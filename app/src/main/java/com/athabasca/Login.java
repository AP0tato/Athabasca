package com.athabasca;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import java.util.HashMap;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame
{
    Login(){
        setLayout(new GridBagLayout());
        GridBagUtil gbc = new GridBagUtil(0, 0);
        FormattedPanel pnl = new FormattedPanel();
        Dimension dimflds = new Dimension(100,20);

        String[] roles = {"Admin", "Rep"};

        JComboBox<String> bxRoles = new JComboBox<String>(roles);

        GeneralInput flduname = new GeneralInput(20, dimflds);

        JPasswordField fldPass = new JPasswordField();
        fldPass.setPreferredSize(dimflds);

        JButton btnLogin = new JButton("Login");

        JComponent[][] elements = {{new JLabel("Role: "),bxRoles},{new JLabel("Username: "),flduname},{new JLabel("Password: "),fldPass}};

        pnl.addElements(elements);

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                HashMap<String,Object> login_info = new HashMap<String,Object>();
                String role = bxRoles.getSelectedItem().toString();
                String uname = flduname.getText().trim();
                char[] pword = fldPass.getPassword();
                login_info.put("ROLE",role);
                login_info.put("UNAME", uname);
                login_info.put("PWORD", pword);

                //process the login data
                boolean loginSuccessful = authenticateUser(login_info);

                if(loginSuccessful){
                    //handle successful login
                    JOptionPane.showMessageDialog(null, "Login Successful!");
                    //redirect to the appropriate dashboard based on role
                    if(role.equals("Admin")){}
                        //Open Admin Dashboard
                    }
                    else if(role.equals("Rep")){
                        //Open Rep dashboard
                    }
                    else {
                        // Handle login failure
                        JOptionPane.showMessageDialog(null, "Invalid Username or Password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                    }
                }

                private boolean authenticateUser(HashMap<String, Object> login_info) {
                    String uname = (String) login_info.get("UNAME");
                    char[] pword = (char[]) login_info.get("PWORD");
            
                    // Replace this with actual authentication logic
                    // For example, check against a database or an authentication service
                    if ("admin".equals(uname) && "password".equals(new String(pword))) {
                        return true;
                    }
                    return false;
            }
            
        });

        add(new JLabel("Login"),gbc);
        gbc.nextY();
        add(pnl,gbc);
        gbc.nextY();
        add(btnLogin,gbc);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
