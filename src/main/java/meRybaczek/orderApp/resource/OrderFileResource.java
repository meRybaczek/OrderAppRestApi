package meRybaczek.orderApp.resource;

import meRybaczek.orderApp.dto.OrderFileFormDto;
import meRybaczek.orderApp.dto.OrderFileFullDto;
import meRybaczek.orderApp.model.OrderFile;
import meRybaczek.orderApp.service.OrderFileService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderFile")
public class OrderFileResource {

    private final OrderFileService orderFileService;

    private final ModelMapper modelMapper;

    public OrderFileResource(OrderFileService orderFileService, ModelMapper modelMapper) {
        this.orderFileService = orderFileService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{id}")
    public OrderFileFullDto get(@PathVariable Integer id) {
        return convertToFullDto(orderFileService.findById(id));
    }

    @GetMapping
    public List<OrderFileFormDto> getByOrderId(@RequestParam Integer orderId) {
        return orderFileService.getByOrderId(orderId)
                .stream()
                .map(this::convertToFormDto)
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderFileFormDto add(@RequestBody OrderFile orderFile, @RequestParam Integer orderId) {
        return convertToFormDto(orderFileService.add(orderFile, orderId));
    }

    @PutMapping
    public OrderFileFormDto update(@RequestBody OrderFile orderFile) {
        return convertToFormDto(orderFileService.update(orderFile));
    }

    @DeleteMapping
    public void delete(@RequestParam Integer id) {
        orderFileService.deleteById(id);
    }

    private OrderFileFormDto convertToFormDto(OrderFile orderFile) {
        return modelMapper.map(orderFile, OrderFileFormDto.class);
    }
    private OrderFileFullDto convertToFullDto(OrderFile orderFile) {
        return modelMapper.map(orderFile, OrderFileFullDto.class);
    }

}
