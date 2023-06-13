package meRybaczek.orderApp.resource;

import meRybaczek.orderApp.dto.ClientFullDto;
import meRybaczek.orderApp.model.Client;
import meRybaczek.orderApp.service.ClientService;
import meRybaczek.orderApp.dto.ClientFormDto;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientResource {
    private final ClientService clientService;
    private final ModelMapper modelMapper;

    public ClientResource(ClientService clientService, ModelMapper modelMapper) {
        this.clientService = clientService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClientFullDto get(@PathVariable Integer id) {
        return convertToFullDto(clientService.findById(id));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ClientFormDto> getAll(@RequestParam(required = false) String clientName,
                                      @RequestParam(required = false) String clientEmail,
                                      @RequestParam(required = false) Double discount) {

        return clientService.findByCriteria(clientName, clientEmail, discount)
                .stream().map(this::convertToFormDto)
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientFormDto add(@Valid @RequestBody Client client) {
        return convertToFormDto(clientService.add(client));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ClientFormDto update(@RequestBody Client client) {
        return convertToFormDto(clientService.update(client));
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestParam Integer id) {
        clientService.delete(id);
    }

    private ClientFormDto convertToFormDto(Client client) {
        return modelMapper.map(client, ClientFormDto.class);
    }

    private ClientFullDto convertToFullDto(Client client) {
        return modelMapper.map(client, ClientFullDto.class);
    }


}
