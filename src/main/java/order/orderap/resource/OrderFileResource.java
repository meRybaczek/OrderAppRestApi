package order.orderap.resource;

import order.orderap.model.OrderFile;
import order.orderap.service.OrderFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderFileResource {

    @Autowired
    private OrderFileService orderFileService;

    @PostMapping("/order/{order_id}/orderFile")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderFile add(@RequestBody OrderFile orderFile, @PathVariable Integer order_id) {
        return orderFileService.add(orderFile, order_id);
    }

    @PutMapping("/orderFile")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderFile update(@RequestBody OrderFile orderFile) {
        return orderFileService.update(orderFile);
    }

    @GetMapping("/order/{order_id}/orderFile")
    @ResponseStatus(HttpStatus.FOUND)
    public List<OrderFile> getByOrderId(@PathVariable Integer order_id) {
        return orderFileService.getByOrderId(order_id);
    }

    @GetMapping("/orderFile/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public OrderFile get(@PathVariable Integer id) {
        return orderFileService.findById(id);
    }

    @DeleteMapping("/orderFile")
    public void deleteById(@RequestParam Integer id) {
        orderFileService.deleteById(id);
    }

}
