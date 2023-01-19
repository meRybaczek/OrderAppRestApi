package order.orderap;

import com.fasterxml.jackson.databind.ObjectMapper;
import order.orderap.model.OrderFile;
import order.orderap.model.OrderPdf;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@Sql(scripts = {"/data.sql"})
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class WebLayerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//    @Autowired
//    private ObjectMapper objectMapper;
//
//
//    @Test
//    public void shouldReturnOrderPdfWhenGetAllAPI() throws Exception {
//        mockMvc.perform(get("/order"))
//                .andExpect(status().isFound())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(1))
//                .andExpect((MockMvcResultMatchers.jsonPath("$.[0].clientName").value("Arch1")))
//                .andExpect((MockMvcResultMatchers.jsonPath("$.[0].clientPhone").value("78454545")))
//                .andExpect((MockMvcResultMatchers.jsonPath("$.[0].discount").value(5.0)))
//                .andDo(print());
//    }
//    @Test
//    public void shouldReturnOrderPdfWhenAddAPI() throws Exception {
//        //given
//        OrderPdf orderPdf = new OrderPdf( "Client1", "7788", 10, LocalDate.now());
//        //then
//        mockMvc.perform(post("/order")
//                        .content(objectMapper.writeValueAsString(orderPdf))
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.clientName").value("Client1"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.clientPhone").value("7788"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.discount").value(10.0))
//                .andDo(print());
//    }
//
//    @Test
//    void shouldReturnUpdatedOrderPdfWhenUpdateDiscountAPI() throws Exception {
//        //given
//        int id = 1;
//        double discount = 20.0;
//        //then
//        mockMvc.perform(patch("/order?discount=" + discount + "&id=" + id))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.discount").value(discount))
//                .andDo(print());
//    }
//
//    @Test
//    public void shouldReturnDeletedStatusWhenDeleteOrderPdfByIdAPI() throws Exception {
//        //given
//        int id = 1;
//        //then
//        mockMvc.perform(delete("/order?id=" + id + ""))
//                .andExpect(status().isOk())
//                .andDo(print());
//    }
//    @Test
//    public void shouldReturnOrderPdfWhenFindByIdAPI() throws Exception {
//        //given
//        int id = 1;
//        //then
//        mockMvc.perform(get("/order/{id}", id))
//                .andExpect(status().isFound())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
//                .andDo(print());
//    }
//
//    // OrderFile tests below //osobna klasa testowa dla tego
//
//    @Test
//    public void shouldReturnOrderFileWhenFindByIdAPI() throws Exception {
//        //given
//        int id = 1;
//        //then
//        mockMvc.perform(get("/orderFile/{id}", id))
//                .andExpect(status().isFound())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
//                .andDo(print());
//    }
//    @Test
//    public void shouldReturnOrderFileByOrderIdWhenGetAllByOrderPdfIdAPI() throws Exception {
//        //given
//        int orderId = 1;
//        //then
//        mockMvc.perform(get("/order/{orderId}/orderFile", orderId))
//                .andExpect(status().isFound())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(1))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].fileName").value("Rys1"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].fileDir").value("C:\\test"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].drawingSizeWidth").value(594.0))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].drawingSizeHight").value(297.0))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].drawingCopyQty").value(3))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].drawingColor").value(true))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].fold").value(true))
//                .andDo(print());
//    }
//
//    @Test
//    public void shouldReturnOrderFileWhenAddOrderFileToOrderPdfByOrderPdfIdAPI() throws Exception {
//        //given
//        OrderFile orderFile = new OrderFile("Rys2", "C://o2", 594, 1200, true, 2, true);
//        int orderId = 1;
//        //then
//        mockMvc.perform(post("/order/{orderId}/orderFile", orderId)
//                        .content(objectMapper.writeValueAsString(orderFile))
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.fileName").value("Rys2"))
//                .andDo(print());
//
//        mockMvc.perform(get("/order/{orderId}/orderFile", orderId))      //zwraca tylko jeden obiekt OrderFile, a powinien 2
//                .andExpect(status().isFound())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
//                .andDo(print());
//    }
//    @Test
//    public void shouldReturnDeletedStatusWhenDeleteOrderFileByIdAPI() throws Exception {
//        //given
//        int id = 1;
//        //then
//        mockMvc.perform(delete("/orderFile?id={id}", id))
//                .andExpect(status().isOk())
//                .andDo(print());
//    }

}