package com.athabasca;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

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
                String uname = flduname.getText().trim();
                char[] pword = fldPass.getPassword();

                uname.replaceAll("\\.", "\\");

                AuthService as = new AuthService();
                String password = "";
                for(char i : pword){
                    password += i;
                }
                String token = as.createUserAndToken(uname, password);
                if(token!=null)
                {
                    JOptionPane.showMessageDialog(null, "Login Successful!");
                    new Session(uname, token);
                    if(Session.getPermission() == 1)
                    {
                        new Dashboard(true);
                    }
                    else
                    {
                        new Dashboard(false);
                    }
                }
                else
                    JOptionPane.showMessageDialog(null, "Invalid Username or Password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
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
