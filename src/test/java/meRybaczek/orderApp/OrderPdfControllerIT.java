package meRybaczek.orderApp;

import com.fasterxml.jackson.databind.ObjectMapper;
import meRybaczek.orderApp.model.OrderPdf;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class OrderPdfControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldReturnOrderPdfListWhenGetApi() throws Exception {
        //given
        //V2__insert_test_data.sql
        //when
        ResultActions perform = mockMvc.perform(get("/order"));
        //then
        perform
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].createdAt").value(LocalDate.now().toString()))
                .andDo(print());
    }

    @Test
    public void shouldReturnOrderPfdWhenPostApi() throws Exception {
        //given
        OrderPdf orderPdf = new OrderPdf(1,LocalDate.now());
        int clientId = 1;
        //when
        ResultActions perform = mockMvc.perform(post("/order?clientId={clientId}", clientId)
                .content(objectMapper.writeValueAsString(orderPdf))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        //then
        perform
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andDo(print());
    }

    @Test
    public void shouldReturnDeletedStatusWhenDeleteOrderPdfById() throws Exception {
        //given
        int id = 1;
        //when
        ResultActions perform = mockMvc.perform(delete("/order?id={id}", id));
        //then
        perform
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void shouldReturnOrderPdfWhenFindById() throws Exception {
        //given
        int id = 1;
        //when
        ResultActions perform = mockMvc.perform(get("/order/{id}", id));
        //then
        perform
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andDo(print());
    }

    @Test
    public void shouldReturnOrderPdfListWhenFindByClientId() throws Exception {
        //given
        int clientId = 1;
        //when
        ResultActions perform = mockMvc.perform(get("/order?clientId={clientId}", clientId));
        //then
        perform
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").exists())
                .andDo(print());
    }

    @Test
    public void shouldReturnOrderPdfListWhenFindOrderPdfByClientName() throws Exception {
        //given
        String clientName = "Archi1";
        //when
        ResultActions perform = mockMvc.perform(get("/order?clientName={clientName}", clientName));
        //then
        perform
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").exists())
                .andDo(print());
    }

    @Test
    public void shouldReturnOrderPdfIdNotFound() throws Exception {
        //given
        int id = 100;
        //when
        ResultActions perform = mockMvc.perform(get("/order/{id}", id));
        //then
        perform
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value("Could not find order id :" + id))
                .andDo(print());
    }

}
