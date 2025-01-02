package com.gym_fit.service;

import com.gym_fit.model.Client;

import java.util.List;

public interface IClientService {
    public List<Client> listClients();

    public Client findClientById(Integer clientId);

    public void saveClient(Client client);

    public void deleteClient(Client client);

}
