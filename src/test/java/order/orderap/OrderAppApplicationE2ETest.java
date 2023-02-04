package order.orderap;

import order.orderap.model.OrderPdf;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = {"/data.sql"})
public class OrderAppApplicationE2ETest {
    @Value(value = "${local.server.port}")
    private int port;
    @Autowired
    RestTemplate restTemplate;

    @Test
    public void shouldReturn200ok() throws Exception { 
        // CR: tu zamiast wywolywac jakis endpoint mozna wstrzyknac zaleznosc do serwisu i assertNotNull sprawdzić czy nie jest null
        // nie trzeba wywolywac endpointu
        // jesli zaleznosc bedzie rozna od null, to znaczy ze applikacja dobrze sie uruchomila 
        ResponseEntity<OrderPdf[]> response = restTemplate.getForEntity("http://localhost:" + port + "/order", OrderPdf[].class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);

    }
}
