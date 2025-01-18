package com.athabasca;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class AssignClient extends JFrame{
    AssignClient(){
        setTitle("Client Assignement");
        setLayout(new GridBagLayout());
        FormattedPanel panel = new FormattedPanel();
        GridBagUtil constraints = new GridBagUtil(0, 0);
        Dimension dimflds = new Dimension(100,20);

        JTextField idInput = new GeneralInput(Integer.MAX_VALUE,dimflds);
        JTextField assignment = new GeneralInput(Integer.MAX_VALUE,dimflds);

        JComponent[][] elements = {{new JLabel("Rep: "), idInput}, {new JLabel("Assignment: "), assignment}};
        panel.addElements(elements);
        panel.setBorder(new EmptyBorder(25, 25, 0, 25));
        add(panel, constraints);

        JButton assign = new JButton("Assign");
        constraints.nextY();
        add(assign, constraints);

        assign.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String id = idInput.getText().replaceAll("\\.", "\\\\");
                String assign = assignment.getText().replaceAll("\\.", "\\\\");
                DatabaseUtil db = new DatabaseUtil();

                if(!(Pattern.matches("^[\\w\\\\%+-]+@[\\w\\\\-]+\\\\[a-zA-Z]{2,6}$", id)&&Pattern.matches("^[\\w\\\\%+-]+@[\\w\\\\-]+\\\\[a-zA-Z]{2,6}$", assign)))
                {
                    System.out.println("Invalid input");
                    return;
                }

                db.readEmployee(id, data -> {
                    if(data != null){
                        try {
                            ArrayList<String> f = (ArrayList<String>) data;
                            f.add(assign);
                            db.writeData("employee/"+id+"/assigned", f, data2 -> {
                                System.out.println("Data written? " + data2);
                            });
                        }
                        catch(Exception b) {
                            System.out.println(b.getMessage()+"\n"+b.getStackTrace());
                        }
                    }
                });
            }
        });
        pack();
    }
    @Override
    public String toString() {
        return "Assign Client";
    }
}
