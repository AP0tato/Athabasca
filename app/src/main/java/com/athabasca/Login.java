package com.athabasca;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Login extends JFrame
{
    Login(){
        setTitle("Login");
        setLayout(new GridBagLayout());
        GridBagUtil gbc = new GridBagUtil(0, 0);
        FormattedPanel pnl = new FormattedPanel();
        Dimension dimflds = new Dimension(400,20);


        GeneralInput flduname = new GeneralInput(200, dimflds);

        JPasswordField fldPass = new JPasswordField();
        fldPass.setPreferredSize(dimflds);

        JButton btnLogin = new JButton("Login");

        JComponent[][] elements = {{new JLabel("Username: "),flduname},{new JLabel("Password: "),fldPass}};

        pnl.addElements(elements);

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String uname = flduname.getText().trim();
                char[] pword = fldPass.getPassword();

                AuthService as = new AuthService();
                String password = "";
                for(char i : pword){
                    password += i;
                }
                String token = as.loginUserAndToken(uname, password);
                if(token!=null)
                {
                    JOptionPane.showMessageDialog(null, "Login Successful!");
                    new Session(uname, token);
                    Session.update(Login.this::thing);
                    Session.update(result -> {
                        System.out.println("Permission: " + Session.getPermission());
                        if (Session.getPermission() == 1) {
                            new Dashboard(true);
                        } else {
                            new Dashboard(false);
                        }
                        setVisible(false);
                    });
                }
                else
                    JOptionPane.showMessageDialog(null, "Invalid Username or Password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        });

        pnl.setBorder(new EmptyBorder(0, 25, 0, 25));

        add(new JLabel("Login") {{
            setBorder(new EmptyBorder(25, 0, 0, 0));
        }},gbc);
        gbc.nextY();
        add(pnl,gbc);
        gbc.nextY();
        add(btnLogin,gbc);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void thing(ArrayList<String> callback){
        System.out.println("Callback: " + callback);
    }
}
