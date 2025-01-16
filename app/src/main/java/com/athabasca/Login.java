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


        GeneralInput flduname = new GeneralInput(20, dimflds);

        JPasswordField fldPass = new JPasswordField();
        fldPass.setPreferredSize(dimflds);

        JButton btnLogin = new JButton("Login");

        JComponent[][] elements = {{new JLabel("Role: ")},{new JLabel("Username: "),flduname},{new JLabel("Password: "),fldPass}};

        pnl.addElements(elements);

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                HashMap<String,Object> login_info = new HashMap<String,Object>();
                String uname = flduname.getText().trim();
                char[] pword = fldPass.getPassword();
                login_info.put("UNAME", uname);
                login_info.put("PWORD", pword);

                AuthService as = new AuthService();
                String password = "";
                for(char i : pword){
                    password += i;
                }
                String token = as.createUserAndToken(uname, password);
                if(token!=null)
                {
                    Session s = new Session(uname, token);
                    
                }

                //process the login data
                // boolean loginSuccessful = authenticateUser(login_info);

                if(loginSuccessful){
                    //handle successful login
                    JOptionPane.showMessageDialog(null, "Login Successful!");
                    if(role.equals("Admin")){}
                            new Dashboard(true);
                    }
                    else if(role.equals("Rep")){
                        new Dashboard(false);
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
                        new Dashboard(true);
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
