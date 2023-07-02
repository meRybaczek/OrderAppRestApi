package meRybaczek.orderApp.service;

import meRybaczek.orderApp.model.OrderFile;
import meRybaczek.orderApp.model.OrderPdf;
import meRybaczek.orderApp.repository.OrderFileRepository;
import meRybaczek.orderApp.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderFileServiceTest {
    OrderFileRepository orderFileRepository;
    OrderRepository orderRepository;
    OrderFileService orderFileService;

    @BeforeEach
    void setUp() {
        orderFileRepository = mock(OrderFileRepository.class);
        orderRepository = mock(OrderRepository.class);
        orderFileService = new OrderFileService(orderFileRepository, orderRepository);
    }

    @Test
    @DisplayName("Should retrieve orderFile by id from repository")
    void shouldFindOrderFileById() {
        //given
        int orderFileId = 1;
        OrderFile orderFile = new OrderFile(orderFileId, "pdf1", "c:/pdfDir", 297,
                420, true, 10, true);
        when(orderFileRepository.findById(orderFileId)).thenReturn(Optional.of(orderFile));
        //when
        OrderFile byId = orderFileService.findById(orderFileId);
        //then
        verify(orderFileRepository,times(1)).findById(orderFileId);
        assertEquals(orderFileId,byId.getId());
    }

    @Test
    @DisplayName("Should retrieve orderFile by orderPdf id")
    void shouldRetrieveOrderFileByOrderPdfId() {
        //given
        OrderFile orderFile = new OrderFile(1, "pdf1", "c:/pdfDir", 297,
                420, true, 10, true);
        int orderPdfId = 1;
        OrderPdf orderPdf = new OrderPdf(orderPdfId, LocalDate.now(), List.of(orderFile));

        when(orderRepository.findById(orderPdfId)).thenReturn(Optional.of(orderPdf));
        //when
        List<OrderFile> byOrderId = orderFileService.getByOrderId(orderPdfId);
        //then
        verify(orderRepository, times(1)).findById(orderPdfId);
        assertEquals(orderFile,byOrderId.get(0));
    }

    @Test
    @DisplayName("Should add orderFile to repository using orderPdf id")
    void shouldSaveOrderFileToRepositoryByOrderPdfId() {
        //given
        int orderPdfId = 1;
        OrderFile orderFile = new OrderFile(1, "pdf1", "c:/pdfDir", 297,
                420, true, 10, true);
        OrderPdf orderPdf = new OrderPdf(orderPdfId, LocalDate.now(), List.of(orderFile));
        when(orderRepository.findById(orderPdfId)).thenReturn(Optional.of(orderPdf));
        //when
        orderFileService.add(orderFile,orderPdfId);
        //then
        verify(orderFileRepository,times(1)).save(orderFile);
    }

    @Test
    @DisplayName("Should update orderFile")
    void shouldUpdateOrderFile() {
        //given
        OrderFile orderFile = new OrderFile(1, "pdf1", "c:/pdfDir", 297,
                420, true, 10, true);
        //when
        orderFileService.update(orderFile);
        //then
        verify(orderFileRepository, times(1)).save(orderFile);
    }

    @Test
    @DisplayName("Should delete orderFile by orderFile id")
    void shouldDeleteOrderFileById() {
        //given
        int orderFileId = 1;
        OrderFile orderFile = new OrderFile(orderFileId, "pdf1", "c:/pdfDir", 297,
                420, true, 10, true);
        when(orderFileRepository.existsById(orderFileId)).thenReturn(true);
        //when
        orderFileService.deleteById(orderFileId);
        //then
        verify(orderFileRepository, times(1)).deleteById(orderFileId);
    }
}