package com.athabasca;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
        GridBagUtlity gbc = new GridBagUtlity(0, 0);
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
