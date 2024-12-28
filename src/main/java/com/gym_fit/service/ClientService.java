package com.gym_fit.service;

import com.gym_fit.model.Client;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService implements IClientService {
    @Override
    public List<Client> listClients() {
        return List.of();
    }

    @Override
    public Client findClientById() {
        return null;
    }

    @Override
    public void saveClient(Client client) {

    }

    @Override
    public void deleteClient(Client client) {

    }
}
