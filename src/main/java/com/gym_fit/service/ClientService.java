package com.gym_fit.service;

import com.gym_fit.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gym_fit.repository.ClientRepository;

import java.util.List;

@Service
public class ClientService implements IClientService {

    @Autowired
    private ClientRepository clienteRepository;

    @Override
    public List<Client> listClients() {
        List<Client> clients = clienteRepository.findAll();
        return clients;
    }

    @Override
    public Client findClientById(Integer idClient) {
        return null;
    }

    @Override
    public void saveClient(Client client) {

    }

    @Override
    public void deleteClient(Client client) {

    }
}
