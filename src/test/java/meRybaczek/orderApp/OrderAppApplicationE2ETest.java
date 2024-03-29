package meRybaczek.orderApp;

import meRybaczek.orderApp.model.OrderPdf;
import meRybaczek.orderApp.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderAppApplicationE2ETest {

    @Value(value = "${local.server.port}")
    private int port;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    OrderService orderService;

    @Test
    public void shouldReturn200okWhenEndToEndTest() throws Exception {
        //given
        URI apiUri = new URI("http://localhost:" + port + "/order");
        //when
        ResponseEntity<OrderPdf[]> response = restTemplate.getForEntity(apiUri, OrderPdf[].class);
        //then
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void shouldReturnIfApllicationHasStarted() {
        assertNotNull(orderService);
    }
}
