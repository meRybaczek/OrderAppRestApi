package order.orderap.resource;

import order.orderap.model.OrderPdf;
import order.orderap.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
// CR: DTO
public class OrderResource {

    @Autowired
    // CR: private
    // CR: wstrzykiwanie przez konstruktor i bez autowired
    private OrderService orderService;

    // CR: root path bez client, tu ty jako admin/manager apki pobierasz wszystkie, dodajesz, modyfikujesz oderdery
    @PostMapping("client/{clientId}/order") //CZYLI DAĆ CLIENT_iD JAKO @REQUEST_PARAM? CZY NP W BODY PRZEKAZAĆ CLIENT ID?
    @ResponseStatus(HttpStatus.CREATED)
    public OrderPdf add(@RequestBody OrderPdf order, @PathVariable Integer clientId) {
        return orderService.addOrder(order, clientId);
    }

    @DeleteMapping("/order")
    // CR: root path w klasie
    public void delete(@RequestParam Integer id) {
        orderService.deleteById(id);
    }

    
    @GetMapping("/order")
    // CR: root path w klasie
    public List<OrderPdf> getAllOrders(@RequestParam(required = false) String clientName,
                                       @RequestParam(required = false) LocalDate createdAt) {
        return orderService.getAllOrdersBy(clientName, createdAt);

    }

    @GetMapping("order/{id}")
    // CR: root path w klasie
    public OrderPdf findById(@PathVariable Integer id) {
        return orderService.findById(id);
    }

    @GetMapping("client/{clientId}/order")
    // CR: root path w klasie
    // CR: bez client, niezalezny endpoint
    public List<OrderPdf> getAllByClientId(@PathVariable Integer clientId) {
        return orderService.getByClientId(clientId);
    }

}
