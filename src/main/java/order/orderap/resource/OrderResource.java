package order.orderap.resource;

import order.orderap.model.OrderPdf;
import order.orderap.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;


@RestController
public class OrderResource {

    @Autowired
    OrderService orderService;
    @PostMapping("client/{clientId}/order")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderPdf add(@RequestBody OrderPdf order, @PathVariable Integer clientId) {
        return orderService.addOrder(order, clientId);
    }

    @DeleteMapping("/order")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestParam Integer id) {
        orderService.deleteById(id);
    }

    @GetMapping("/order")
    @ResponseStatus(HttpStatus.FOUND)
    public List<OrderPdf> getAllOrders(@RequestParam(required = false) String clientName,
                                       @RequestParam(required = false) LocalDate createdAt) {
        return orderService.getAllOrdersBy(clientName,createdAt);

        }
    @GetMapping("order/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public OrderPdf findById(@PathVariable Integer id) {
        return orderService.findById(id);
    }

    @GetMapping("client/{clientId}/order")
    @ResponseStatus(HttpStatus.FOUND)
    public List<OrderPdf> getAllByClientId(Integer clientId) {
        return orderService.getByClientId(clientId);
    }



}
