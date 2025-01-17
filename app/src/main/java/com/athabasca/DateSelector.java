package com.athabasca;

import java.util.Properties;
import java.awt.Container;

import javax.swing.JFrame;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class DateSelector {
    void GUI() {
        JFrame f1 = new JFrame();
        f1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f1.setSize(300, 300);
        f1.setVisible(true);

        Container conn = f1.getContentPane();
        conn.setLayout(null);

        UtilDateModel model = new UtilDateModel();
        //model.setDate(20,04,2014);
        // Need this...
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        // Don't know about the formatter, but there it is...
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        // Set bounds and add the date picker to the container
        datePicker.setBounds(50, 50, 200, 30);
        conn.add(datePicker);
    }
}