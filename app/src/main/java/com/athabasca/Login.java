package com.athabasca;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

import java.awt.Dimension;
import java.awt.GridBagLayout;

public class Login extends JFrame
{
    Login(){
        setLayout(new GridBagLayout());
        GridBagUtlity gbc = new GridBagUtlity(0, 0);
        FormattedPanel pnl = new FormattedPanel();
        Dimension dimflds = new Dimension(100,20);

        String[] roles = {"Admin", "Rep"};

        JComboBox bxRoles = new JComboBox<String>(roles);

        GeneralInput flduname = new GeneralInput(20, dimflds);

        JPasswordField fldPass = new JPasswordField();
        fldPass.setSize(dimflds);

        JButton btnLogin = new JButton("Login");

        JComponent[][] elements = {{new JLabel("Role: "),bxRoles},{new JLabel("Username"),flduname},{new JLabel("Password"),fldPass}};

        pnl.addElements(elements);

        add(new JLabel("Login"),gbc);
        gbc.nextY();
        add(pnl,gbc);
        pack();
        setVisible(true);
    }
}
