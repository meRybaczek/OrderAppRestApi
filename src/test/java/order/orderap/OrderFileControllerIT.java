package order.orderap;

import com.fasterxml.jackson.databind.ObjectMapper;
import order.orderap.model.OrderFile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Sql(scripts = {"/data.sql"})
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class OrderFileControllerIT {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldReturnOrderFileWhenFindById() throws Exception {
        //given
        int id = 1;
        //then
        mockMvc.perform(get("/orderFile/{id}", id))
                .andExpect(status().isFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andDo(print());
    }

    @Test
    public void shouldReturnOrderFileByOrderIdWhenGetAllByOrderPdfId() throws Exception {
        //given
        int orderId = 1;
        //then
        mockMvc.perform(get("/order/{orderId}/orderFile", orderId))
                .andExpect(status().isFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].fileName").value("Rys1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].fileDir").value("C:\\test"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].drawingSizeWidth").value(594.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].drawingSizeHight").value(297.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].drawingCopyQty").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].drawingColor").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].fold").value(true))
                .andDo(print());
    }

    @Test
    public void shouldReturnOrderFileWhenAddOrderFileToOrderPdfByOrderPdfId() throws Exception {
        //given
        OrderFile orderFile = new OrderFile("Rys2", "C://o2", 594, 1200,
                true, 2, true);
        int orderId = 1;
        //then
        mockMvc.perform(post("/order/{orderId}/orderFile", orderId)
                        .content(objectMapper.writeValueAsString(orderFile))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.fileName").value("Rys2"))
                .andDo(print());

        mockMvc.perform(get("/order/{orderId}/orderFile", orderId))
                .andExpect(status().isFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
                .andDo(print());
    }

    @Test
    public void shouldReturnOrderFileWhenUpdate() throws Exception {
        //given
        OrderFile orderFile = new OrderFile(1,"Rys2", "C://o2", 594, 1200,
                true, 2, true);
        //then
        mockMvc.perform(put("/orderFile")
                        .content(objectMapper.writeValueAsString(orderFile))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.fileName").value("Rys2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andDo(print());

    }

    @Test
    public void shouldReturnDeletedStatusWhenDeleteOrderFileById() throws Exception {
        //given
        int id = 1;
        //then
        mockMvc.perform(delete("/orderFile?id={id}", id))
                .andExpect(status().isOk())
                .andDo(print());
    }









}
