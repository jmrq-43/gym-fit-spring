package com.gym_fit.gui;

import com.gym_fit.model.Client;
import com.gym_fit.service.ClientService;
import com.gym_fit.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Component
public class GymFitForm extends JFrame {
    IClientService clientService;
    private JPanel mainPanel;
    private JTable clientTable;
    private JTextField nameText;
    private JTextField lastNameText;
    private JTextField membershipText;
    private JButton saveButton;
    private JButton deleteButton;
    private JButton clearButton;
    private DefaultTableModel tableModel;
    private Integer idClient;

    @Autowired
    public GymFitForm(ClientService clientService) {
        this.clientService = clientService;
        startForm();
        saveButton.addActionListener(e -> saveClient());
        clientTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                loadClientSelected();
            }
        });
    }

    private void startForm() {
        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        this.tableModel = new DefaultTableModel(0, 4);
        String[] headers = {"id", "name", "last name", "membership"};
        this.tableModel.setColumnIdentifiers(headers);
        this.clientTable = new JTable(tableModel);
        listClients();
    }

    private void listClients() {
        this.tableModel.setRowCount(0);
        var client = this.clientService.listClients();
        client.forEach(client1 -> {
            Object[] clientLine = {
                    client1.getId(),
                    client1.getName(),
                    client1.getLASTNAME(),
                    client1.getMembership()
            };
            this.tableModel.addRow(clientLine);
        });
    }

    private void saveClient() {
        if (nameText.getText().isEmpty()) {
            showMessage("Enter a name");
            nameText.requestFocusInWindow();
            return;
        }
        if (membershipText.getText().isEmpty()) {
            showMessage("Enter a memebership");
            nameText.requestFocusInWindow();
            return;
        }

        var name = nameText.getText();
        var lastName = lastNameText.getText();
        var membership = Integer.parseInt(membershipText.getText());

        var client = new Client(this.idClient, name, lastName, membership);
        this.clientService.saveClient(client);
        if (this.idClient == null) {
            showMessage("added new client");
        } else
            showMessage("updated client");
        cleanform();
        listClients();
    }

    private void loadClientSelected() {
        var row = clientTable.getSelectedRow();
        if (row != -1) {
            var id = clientTable.getModel().getValueAt(row, 0).toString();
            this.idClient = Integer.parseInt(id);
            var name = clientTable.getModel().getValueAt(row, 1).toString();
            this.nameText.setText(name);
            var lastName = clientTable.getModel().getValueAt(row, 2).toString();
            this.lastNameText.setText(lastName);
            var membership = clientTable.getModel().getValueAt(row, 3).toString();
            this.membershipText.setText(membership);
        }
    }

    private void cleanform() {
        nameText.setText("");
        lastNameText.setText("");
        membershipText.setText("");
    }

    private void showMessage(String enterAName) {
        JOptionPane.showMessageDialog(this, enterAName);
    }
}
