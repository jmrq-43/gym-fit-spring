package com.gym_fit.gui;

import com.gym_fit.service.ClientService;
import com.gym_fit.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class GymFitForm extends JFrame {
    IClientService clientService;
    private JPanel mainPanel;
    private JTable clientTable;

    @Autowired
    public GymFitForm(ClientService clientService) {
        this.clientService = clientService;
        startForm();
    }

    private void startForm() {

        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900,700);
        setLocationRelativeTo(null);
    }
}
