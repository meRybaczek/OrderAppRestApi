package meRybaczek.orderApp;

import com.fasterxml.jackson.databind.ObjectMapper;
import meRybaczek.orderApp.model.OrderPdf;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Sql(scripts = {"/data.sql"})
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class OrderPdfIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldReturnOrderPdfListWhenGetApi() throws Exception {
        mockMvc.perform(get("/order"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].createdAt").value("2012-12-12"))
                .andDo(print());
    }

    @Test
    public void shouldReturnOrderPfdWhenPostApi() throws Exception {

        OrderPdf orderPdf = new OrderPdf(LocalDate.now());
        int clientId = 1;

        mockMvc.perform(post("/order?clientId={clientId}", clientId)
                        .content(objectMapper.writeValueAsString(orderPdf))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2))
                .andDo(print());
    }

    @Test
    public void shouldReturnDeletedStatusWhenDeleteOrderPdfById() throws Exception {
        //given
        int id = 1;
        //then
        mockMvc.perform(delete("/order?id={id}", id))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void shouldReturnOrderPdfWhenFindById() throws Exception {
        //given
        int id = 1;
        //then
        mockMvc.perform(get("/order/{id}", id))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andDo(print());
    }

    @Test
    public void shouldReturnOrderPdfListWhenFindByClientId() throws Exception {
        //given
        int clientId = 1;
        //then
        mockMvc.perform(get("/order?clientId={clientId}", clientId))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").exists())
                .andDo(print());
    }

    @Test
    public void shouldReturnOrderPdfListWhenFindOrderPdfByClientName() throws Exception {
        //given
        String clientName = "Archi1";
        //then
        mockMvc.perform(get("/order?clientName={clientName}", clientName))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").exists())
                .andDo(print());
    }

}
