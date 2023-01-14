package order.orderap.resource;

import order.orderap.model.OrderPdf;
import order.orderap.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/order")
public class OrderResource {

    @Autowired
    private OrderService orderService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderPdf add(@RequestBody OrderPdf order) {
        return orderService.addOrder(order);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestParam Integer id) {
        orderService.deleteById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<OrderPdf> getAllOrders(@RequestParam(required = false) String clientName,
                                       @RequestParam(required = false) String clientPhone,
                                       @RequestParam(required = false) LocalDate createdAt,
                                       @RequestParam(required = false) Double clientDiscount) {
        return orderService.getAllOrders(clientName,clientPhone,createdAt,clientDiscount);

        }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public OrderPdf findById(@PathVariable Integer id) {
        return orderService.findById(id);

    }
    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public OrderPdf updateDiscountById (@RequestParam Double discount, @RequestParam Integer id){
        return orderService.updateDiscountById(discount, id);
    }
}
