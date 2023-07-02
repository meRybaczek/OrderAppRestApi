package meRybaczek.orderApp.service;

import meRybaczek.orderApp.model.Client;
import meRybaczek.orderApp.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientServiceTest {
    private ClientService clientService;
    private ClientRepository clientRepository;

    @BeforeEach
    private void setUp() {
        clientRepository = mock(ClientRepository.class);
        clientService = new ClientService(clientRepository);
    }

    @Test
    @DisplayName("Should retrieve client from repository")
    public void shouldSaveClientToRepository() {
        //given
        Client client = new Client("Client1", "779-232-84-28", "email@example.pl", 10);
        //when
        clientService.add(client);
        //then
        verify(clientRepository, times(1)).save(client);
    }

    @Test
    @DisplayName("Should retrieve client by id")
    public void shouldFindClientByIdFromRepository() {
        //given
        Optional<Client> client = Optional.of(new Client(1,"Client1", "779-232-84-28", "email@example.pl", 10));
        when(clientRepository.findById(1)).thenReturn(client);
        //when
        Client byId = clientService.findById(1);
        //then
        verify(clientRepository,times(1)).findById(1);
        assertEquals(1, byId.getId());

    }
    @Test
    @DisplayName("Should retrieve client by criteria")
    public void shouldFindClientByCriteriaFromRepository() {
        //given
        Optional<Client> client = Optional.of(new Client(1,"Client1", "779-232-84-28", "email@example.pl", 10));
        List<Client> list = List.of(client.get());
        when(clientRepository.findAll(any(Specification.class))).thenReturn(list);
        //when
        List<Client> clients = clientService.findByCriteria("Client1", null, null);
        //then
        verify(clientRepository,times(1)).findAll(any(Specification.class));
        assertEquals(clients.get(0),client.get());
    }

    @Test
    @DisplayName("Should delete client from repository by id")
    public void shouldDeleteClientFromRepositoryById() {
        //given
        int clientId = 1;
        Client client = new Client(clientId,"Client1", "779-232-84-28", "email@example.pl", 10);
        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));
        //when
        clientService.delete(clientId);
        //then
        verify(clientRepository, times(1)).deleteById(clientId);
    }
    @Test
    @DisplayName("Should update client")
    public void shouldUpdateClient() {
        //given
        Client client = new Client("Client1", "779-232-84-28", "email@example.pl", 5);
        //when
        clientService.update(client);
        //then
        verify(clientRepository, times(1)).save(client);
    }



}