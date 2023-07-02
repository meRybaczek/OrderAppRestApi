package meRybaczek.orderApp.service;

import meRybaczek.orderApp.model.Client;
import meRybaczek.orderApp.model.OrderPdf;
import meRybaczek.orderApp.repository.ClientRepository;
import meRybaczek.orderApp.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {
    private OrderService orderService;
    private OrderRepository orderRepository;
    private ClientRepository clientRepository;

    @BeforeEach
    void setUp() {
         orderRepository = mock(OrderRepository.class);
         clientRepository = mock(ClientRepository.class);
         orderService = new OrderService(orderRepository, clientRepository);
    }

    @Test
    @DisplayName("Should retrieve orferpdf by id")
    void shouldRetrieveOrderPdfFromRepositoryById() {
        //given
        int orderPdfId = 1;
        OrderPdf orderPdf = new OrderPdf(orderPdfId, LocalDate.now());
        when(orderRepository.findById(orderPdfId)).thenReturn(Optional.of(orderPdf));
        //when
        OrderPdf byId = orderService.findById(orderPdfId);
        //then
        verify(orderRepository, times(1)).findById(orderPdfId);
        assertEquals(orderPdf, byId);
    }

    @Test
    @DisplayName("Should retrieve list of orferpdf by client id")
    void shouldRetrieveOrderPdfByClientId() {
        //given
        int clientId = 1;
        List<OrderPdf> orderPdfs = List.of(new OrderPdf(1,LocalDate.now()), new OrderPdf(2, LocalDate.now()));
        Client client = new Client(clientId,"Client1", "779-232-84-28", "email@example.pl", 10,orderPdfs);

        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));
        //when
        List<OrderPdf> all = orderService.getAll(1, null, null);
        //then
        verify(clientRepository,times(1)).findById(clientId);
        assertEquals(2,all.size());
    }

    @Test
    @DisplayName("Should retrieve list of orderPdfs by client name")
    void shouldRetrieveOrderPdfByClientName() {
        //given
        String clientName = "Client1";
        List<OrderPdf> orderPdfs = List.of(new OrderPdf(1,LocalDate.now()), new OrderPdf(2, LocalDate.now()));
        Client client = new Client(1,clientName, "779-232-84-28", "email@example.pl", 10,orderPdfs);

        when(clientRepository.findByClientName(clientName)).thenReturn(Optional.of(client));
        //when
        List<OrderPdf> all = orderService.getAll(null, clientName, null);
        //then
        verify(clientRepository,times(1)).findByClientName(clientName);
        assertEquals(2,all.size());
    }

    @Test
    @DisplayName("Should save orderPdf by client id")
    void shouldAddOrderPdfByClientId() {
        //given
        int clientId = 1;
        Client client = new Client(clientId,"Client1", "779-232-84-28", "email@example.pl", 10);
        OrderPdf orderPdf = new OrderPdf(1, LocalDate.now());
        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));
        //when
        orderService.add(orderPdf, clientId);
        //then
        verify(orderRepository,times(1)).save(orderPdf);
    }

    @Test
    @DisplayName("Should delete orderPdf by id")
    void shouldDeleteOrderPdfById() {
        //given
        int orderPdfId = 1;
        OrderPdf orderPdf = new OrderPdf(orderPdfId, LocalDate.now());
        when(orderRepository.existsById(orderPdfId)).thenReturn(true);
        //when
        orderService.delete(orderPdfId);
        //then
        verify(orderRepository,times(1)).deleteById(orderPdfId);
    }
}