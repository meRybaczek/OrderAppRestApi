package meRybaczek.orderApp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ExceptionTest {
    @Autowired
    private MockMvc mockMvc;

    // CR: te motody powinny byc w klasach testow dla client, order, orderfile
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

    @Test
    public void shouldReturnOrderPdfIdNotFound() throws Exception {
        //given
        int id = 100;
        //then
        mockMvc.perform(get("/order/{id}", id))
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value("Could not find order id :" + id))
                .andDo(print());

    }

    @Test
    public void shouldReturnOrderFileIdNotFound() throws Exception {
        //given
        int id = 100;
        //then
        mockMvc.perform(get("/orderFile/{id}", id))
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value("Could not find orderFile id: " + id))
                .andDo(print());
    }

}
