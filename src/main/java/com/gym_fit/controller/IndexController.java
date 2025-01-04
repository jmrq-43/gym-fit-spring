package com.gym_fit.controller;

import com.gym_fit.model.Client;
import com.gym_fit.service.IClientService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@ViewScoped
public class IndexController {

    private static final Logger logger =
            LoggerFactory.getLogger(IndexController.class);
    @Autowired
    IClientService clientService;
    private List<Client> clients;

    @PostConstruct
    public void init() {
        loadData();
    }

    private void loadData() {
        this.clients = (this.clientService.listClients());
        this.clients.forEach(client -> logger.info(client.toString()));
    }


}
