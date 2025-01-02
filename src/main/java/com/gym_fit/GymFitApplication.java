package com.gym_fit;

import com.gym_fit.model.Client;
import com.gym_fit.service.IClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class GymFitApplication implements CommandLineRunner {
    private static final Logger logger =
            LoggerFactory.getLogger(GymFitApplication.class);
    String nl = System.lineSeparator();
    @Autowired
    private IClientService IClientService;

    public static void main(String[] args) {
        logger.info("Starting the application");
        SpringApplication.run(GymFitApplication.class, args);
        logger.info("finalized");
    }


    @Override
    public void run(String... args) throws Exception {
        runApp();
    }

    private void runApp() {
        var leave = false;
        var console = new Scanner(System.in);
        while (!leave) {
            var option = printMenu(console);
            leave = executeOptions(console, option);
            logger.info("");
        }
    }

    private boolean executeOptions(Scanner console, int option) {
        var leave = false;
        switch (option) {
            case 1 -> {
                logger.info("{}--- List client ---{}", nl, nl);
                List<Client> clients = IClientService.listClients();
                clients.forEach(client ->
                        logger.info("{}{}", client.toString(), nl));
            }
            case 2 -> {
                logger.info("---- Search client by id ----{}", nl);
                logger.info("Please enter an id: ");
                int idClient = Integer.parseInt(console.nextLine());
                Client client = IClientService.findClientById(idClient);
                if (client != null) {
                    logger.info("Client found {}", client);
                } else
                    logger.info("Client not found {}", client);
            }
            case 3 -> {
                logger.info("---- Add new client ----{}", nl);
                logger.info("Please enter a name: ");
                var nameClient = String.valueOf(console.nextLine());
                logger.info("Please enter a last name: ");
                var lastNameClient = String.valueOf(console.nextLine());
                logger.info("Please enter a membership number: ");
                var membershipNumber = Integer.parseInt(console.nextLine());

                Client client = new Client(nameClient, lastNameClient, membershipNumber);
                IClientService.saveClient(client);
                logger.info("client saved " + client);
            }
            case 4 -> {
                logger.info("--- Modified client ---{}", nl);
                logger.info("Please enter the id client: ");
                var idClientModified = Integer.parseInt(console.nextLine());
                Client client = IClientService.findClientById(idClientModified);
                if (client != null) {
                    logger.info("Enter new name: ");
                    var nameClient = console.nextLine();
                    logger.info("Enter new las name: ");
                    var lastName = console.nextLine();
                    logger.info("Enter new membership number: ");
                    var membership = Integer.parseInt(console.nextLine());

                    client.setName(nameClient);
                    client.setLASTNAME(lastName);
                    client.setMembership(membership);
                    IClientService.saveClient(client);
                    logger.info("Modified client {}", nl);
                } else
                    logger.info("Client not found " + client);

            }
            case 5 -> {
                logger.info("---- Delete client ---- {}", nl);
                logger.info("Please enter a client id to delete it: ");
                var idCLientDelete = Integer.parseInt(console.nextLine());
                var client = IClientService.findClientById(idCLientDelete);
                if (client != null) {
                    IClientService.deleteClient(client);
                    logger.info("Deleted client {}", client);
                } else
                    logger.info("Client not found " + client);
            }
            case 6 -> {
                logger.info("leaving...{}{}", nl, nl);
                leave = true;
            }
            default -> logger.info("Unrecognized option {}{}", option, nl);
        }

        return leave;

    }

    private int printMenu(Scanner console) {
        logger.info("""
                \n*** GymFit application ***
                1. List Clients
                2. Search client
                3. Add client
                4. Modify client
                5. Delete client
                6. Leave application
                chose an option:\s""");
        return Integer.parseInt(console.nextLine());
    }
}
