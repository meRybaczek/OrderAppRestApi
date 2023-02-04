package order.orderap.resource;

import order.orderap.model.OrderFile;
import order.orderap.service.OrderFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// CR: DTO
public class OrderFileResource {

    @Autowired
    // CR: wstrzykiwanie przez konstruktor i bez autowired    
    private OrderFileService orderFileService;

    // CR: /order/{order_id}/orderFile nad klasa
    @PostMapping("/order/{order_id}/orderFile")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderFile add(@RequestBody OrderFile orderFile, @PathVariable Integer order_id) {
        return orderFileService.add(orderFile, order_id);
    }

    @PutMapping("/orderFile")
    // CR: jw
    // CR: CREATED w upadate?
    @ResponseStatus(HttpStatus.CREATED)
    public OrderFile update(@RequestBody OrderFile orderFile) {
        return orderFileService.update(orderFile);
    }

    @GetMapping("/order/{order_id}/orderFile")
    // CR: jw
    // CR: metody GET na poczatku
    @ResponseStatus(HttpStatus.FOUND)
    public List<OrderFile> getByOrderId(@PathVariable Integer order_id) {
        return orderFileService.getByOrderId(order_id);
    }

    @GetMapping("/orderFile/{id}")
    // CR: dlaczego tu jest inny root path niz w metodach wyzej?
    @ResponseStatus(HttpStatus.FOUND)
    public OrderFile get(@PathVariable Integer id) {
        return orderFileService.findById(id);
    }

    @DeleteMapping("/orderFile")
    // CR: jw, root path inny
    // CR: po co deletById, samo delete, wyzej masz samo get, nie robi sie przy standardowych metodach GET/POST/PUT/DELETE metoda z ById
    public void deleteById(@RequestParam Integer id) {
        orderFileService.deleteById(id);
    }

}
