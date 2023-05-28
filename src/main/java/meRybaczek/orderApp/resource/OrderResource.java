package meRybaczek.orderApp.resource;

import meRybaczek.orderApp.dto.OrderPdfFormDto;
import meRybaczek.orderApp.dto.OrderPdfFullDto;
import meRybaczek.orderApp.service.OrderService;
import meRybaczek.orderApp.model.OrderPdf;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderResource {

    private final OrderService orderService;

    private final ModelMapper modelMapper;

    public OrderResource(OrderService orderService, ModelMapper modelMapper) {
        this.orderService = orderService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrderPdfFullDto get(@PathVariable Integer id) {
        return convertToFullDto(orderService.findById(id));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderPdfFormDto> getAll(@RequestParam(required = false) Integer clientId,
                                       @RequestParam(required = false) String clientName,
                                       @RequestParam(required = false) LocalDate createdAt) {
        return orderService.getAll(clientId, clientName, createdAt)
                .stream()
                .map(this::convertToFormDto)
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderPdfFormDto add(@RequestBody OrderPdf order, @RequestParam Integer clientId) {
        return convertToFormDto(orderService.add(order, clientId));
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestParam Integer id) {
        orderService.delete(id);
    }
    private OrderPdfFormDto convertToFormDto(OrderPdf orderPdf) {
        return modelMapper.map(orderPdf, OrderPdfFormDto.class);
    }
    private OrderPdfFullDto convertToFullDto(OrderPdf orderPdf) {
        return modelMapper.map(orderPdf, OrderPdfFullDto.class);
    }

}
