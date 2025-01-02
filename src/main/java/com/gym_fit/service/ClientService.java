package com.gym_fit.service;

import com.gym_fit.model.Client;
import com.gym_fit.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService implements IClientService {

    @Autowired
    private ClientRepository clienteRepository;

    @Override
    public List<Client> listClients() {
        return clienteRepository.findAll();
    }

    @Override
    public Client findClientById(Integer idClient) {
        return clienteRepository.findById(idClient).orElse(null);
    }

    @Override
    public void saveClient(Client client) {
        clienteRepository.save(client);
    }

    @Override
    public void deleteClient(Client client) {
        clienteRepository.delete(client);
    }
}
