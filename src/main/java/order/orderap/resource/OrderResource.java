package order.orderap.resource;

import order.orderap.model.OrderPdf;
import order.orderap.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;


@RestController
public class OrderResource {

    @Autowired
    private OrderService orderService;

    @PostMapping("/order")
    public OrderPdf add(@RequestBody OrderPdf order) {
        return orderService.addOrder(order);
    }

    @DeleteMapping("/order")
    public void delete(@RequestParam Integer id) {
        orderService.deleteById(id);
    }

    @GetMapping("/order")
    public List<OrderPdf> getAllOrders(@RequestParam(required = false) String clientName,
                                       @RequestParam(required = false) String clientPhone,
                                       @RequestParam(required = false) LocalDate createdAt,
                                       @RequestParam(required = false) Double clientDiscount) {
        return orderService.getAllOrders(clientName,clientPhone,createdAt,clientDiscount);

        }


    @GetMapping("/order/{id}")
    public OrderPdf findById(@PathVariable Integer id) {
        return orderService.findById(id);

    }

        @PatchMapping("/order")
        public void updateDiscountById (@RequestParam Double discount, @RequestParam Integer id){
            orderService.updateDiscountById(discount, id);
        }
}
