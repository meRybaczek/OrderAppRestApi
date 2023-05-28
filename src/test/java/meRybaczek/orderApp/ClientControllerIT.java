package meRybaczek.orderApp;

import com.fasterxml.jackson.databind.ObjectMapper;
import meRybaczek.orderApp.model.Client;
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
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ClientControllerIT {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldReturnClientWhenGetAll() throws Exception {
        mockMvc.perform(get("/client"))
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
        Client client1 = new Client("Archi2", "666-66-666-66", "new@archi.pl", 10.0);
        //then
        mockMvc.perform(post("/client")
                        .content(objectMapper.writeValueAsString(client1))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.clientName").value("Archi2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nipNo").value("666-66-666-66"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.discount").value(10.0))
                .andDo(print());
    }

    @Test
    void shouldReturnUpdatedClientWhenUpdateAPI() throws Exception {
        //given
        Client client = new Client(1, "Archi1", "777-77-777-77", "new@archi.pl", 10.0);

        //then
        mockMvc.perform(put("/client")
                        .content(objectMapper.writeValueAsString(client))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
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
        //then
        mockMvc.perform(delete("/client?id={id}", id))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void shouldReturnClientWhenFindByIdAPI() throws Exception {
        //given
        int id = 1;
        //then
        mockMvc.perform(get("/client/{id}", id))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andDo(print());
    }

    @Test
    public void shouldReturnClientWhenFindByCriteriaName() throws Exception {
        String clientName = "Archi1";

        mockMvc.perform(get("/client?clientName={clientName}", clientName))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(1))
                .andExpect((MockMvcResultMatchers.jsonPath("$.[0].clientName").value("Archi1")))
                .andExpect((MockMvcResultMatchers.jsonPath("$.[0].nipNo").value("779-232-84-28")))
                .andExpect((MockMvcResultMatchers.jsonPath("$.[0].discount").value(5.0)))
                .andDo(print());
    }

    @Test
    public void shouldReturnClientWhenFindByCriteriaEmail() throws Exception {
        String clientEmail = "archi@archi.com";

        mockMvc.perform(get("/client?clientEmail={clientEmail}", clientEmail))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(1))
                .andExpect((MockMvcResultMatchers.jsonPath("$.[0].clientName").value("Archi1")))
                .andExpect((MockMvcResultMatchers.jsonPath("$.[0].nipNo").value("779-232-84-28")))
                .andExpect((MockMvcResultMatchers.jsonPath("$.[0].discount").value(5.0)))
                .andDo(print());
    }

    @Test
    public void shouldReturnClientWhenFindByCriteriaDiscount() throws Exception {
        Double discount = 5.0;

        mockMvc.perform(get("/client?discount={discount}", discount))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(1))
                .andExpect((MockMvcResultMatchers.jsonPath("$.[0].clientName").value("Archi1")))
                .andExpect((MockMvcResultMatchers.jsonPath("$.[0].nipNo").value("779-232-84-28")))
                .andExpect((MockMvcResultMatchers.jsonPath("$.[0].discount").value(5.0)))
                .andDo(print());
    }

    @Test
    public void shouldReturnClientDtoWhenGetClient() throws Exception {
        mockMvc.perform(get("/client"))
                .andExpect(status().isOk())
                .andExpect((MockMvcResultMatchers.jsonPath("$.[0].clientEmail").doesNotExist()))
                .andDo(print());
    }

    @Test
    public void shouldReturnClientIdNotFoundException() throws Exception {
        //given
        int id = 100;
        //then
        mockMvc.perform(get("/client/{id}", id))
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value("Could not find Client id :" + id))
                .andDo(print());
    }

    @Test
    public void shouldReturnClientDataNotFoundException() throws Exception {
        String clientName = "Archi1NotFound";

        mockMvc.perform(get("/client?clientName={clientName}", clientName))
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value("Could not find Client by data"))
                .andDo(print());
    }

}