package meRybaczek.orderApp;

import com.fasterxml.jackson.databind.ObjectMapper;
import meRybaczek.orderApp.model.Client;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class ClientControllerIT {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldReturnClientWhenGetAll() throws Exception {
        //given
        //V2__insert_test_data.sql
        //when
        ResultActions perform = mockMvc.perform(get("/client"));
        //then
        perform
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(1))
                .andExpect((MockMvcResultMatchers.jsonPath("$.[0].clientName").value("Archi1")))
                .andExpect((MockMvcResultMatchers.jsonPath("$.[0].nipNo").value("779-232-84-28")))
                .andExpect((MockMvcResultMatchers.jsonPath("$.[0].discount").value(5.0)))
                .andDo(print());
    }

    @Test
    public void shouldReturnClientWhenAdd() throws Exception {
        //given
        Client client1 = new Client(1,"Archi2", "7792328428", "new@archi.pl", 10.0);
        //when
        ResultActions perform = mockMvc.perform(post("/client")
                .content(objectMapper.writeValueAsString(client1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        //then
        perform
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.clientName").value("Archi2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nipNo").value("7792328428"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.discount").value(10.0))
                .andDo(print());
    }

    @Test
    void shouldReturnUpdatedClientWhenUpdateAPI() throws Exception {
        //given
        Client client = new Client(1, "Archi1", "777-77-777-77", "new@archi.pl", 10.0);
        //when
        ResultActions perform = mockMvc.perform(put("/client")
                .content(objectMapper.writeValueAsString(client))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        //then
        perform
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.clientName").value("Archi1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nipNo").value("777-77-777-77"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.discount").value(10.0))
                .andDo(print());
    }

    @Test
    public void shouldReturnDeletedStatusWhenDeleteClientByIdAPI() throws Exception {
        //given
        int id = 1;
        //when
        ResultActions perform = mockMvc.perform(delete("/client?id={id}", id));
        //then
        perform
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void shouldReturnClientWhenFindByIdAPI() throws Exception {
        //given
        int id = 1;
        //when
        ResultActions perform = mockMvc.perform(get("/client/{id}", id));
        //then
        perform
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andDo(print());
    }

    @Test
    public void shouldReturnClientWhenFindByCriteriaName() throws Exception {
        //given
        String clientName = "Archi1";
        //when
        ResultActions perform = mockMvc.perform(get("/client?clientName={clientName}", clientName));
        //then
        perform
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(1))
                .andExpect((MockMvcResultMatchers.jsonPath("$.[0].clientName").value("Archi1")))
                .andExpect((MockMvcResultMatchers.jsonPath("$.[0].nipNo").value("779-232-84-28")))
                .andExpect((MockMvcResultMatchers.jsonPath("$.[0].discount").value(5.0)))
                .andDo(print());
    }

    @Test
    public void shouldReturnClientWhenFindByCriteriaEmail() throws Exception {
        //given
        String clientEmail = "archi@archi.com";
        //when
        ResultActions perform = mockMvc.perform(get("/client?clientEmail={clientEmail}", clientEmail));
        //then
        perform
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(1))
                .andExpect((MockMvcResultMatchers.jsonPath("$.[0].clientName").value("Archi1")))
                .andExpect((MockMvcResultMatchers.jsonPath("$.[0].nipNo").value("779-232-84-28")))
                .andExpect((MockMvcResultMatchers.jsonPath("$.[0].discount").value(5.0)))
                .andDo(print());
    }

    @Test
    public void shouldReturnClientWhenFindByCriteriaDiscount() throws Exception {
        //given
        Double discount = 5.0;
        //when
        ResultActions perform = mockMvc.perform(get("/client?discount={discount}", discount));
        //then
        perform
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(1))
                .andExpect((MockMvcResultMatchers.jsonPath("$.[0].clientName").value("Archi1")))
                .andExpect((MockMvcResultMatchers.jsonPath("$.[0].nipNo").value("779-232-84-28")))
                .andExpect((MockMvcResultMatchers.jsonPath("$.[0].discount").value(5.0)))
                .andDo(print());
    }

    @Test
    public void shouldReturnClientDtoWhenGetClient() throws Exception {
        //given
        //V2__insert_test_data.sql
        //when
        ResultActions perform = mockMvc.perform(get("/client"));
        //then
        perform
                .andExpect(status().isOk())
                .andExpect((MockMvcResultMatchers.jsonPath("$.[0].clientEmail").doesNotExist()))
                .andDo(print());
    }

    @Test
    public void shouldReturnClientIdNotFoundException() throws Exception {
        //given
        int id = 100;
        //when
        ResultActions perform = mockMvc.perform(get("/client/{id}", id));
        //then
        perform
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value("Could not find Client id :" + id))
                .andDo(print());
    }

    @Test
    public void shouldReturnClientDataNotFoundException() throws Exception {
        //given
        String clientName = "Archi1NotFound";
        //when
        ResultActions perform = mockMvc.perform(get("/client?clientName={clientName}", clientName));
        //then
        perform
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value("Could not find Client by data"))
                .andDo(print());
    }

}