package order.orderap.resource;

import order.orderap.model.Client;
import order.orderap.model.ClientDto;
import order.orderap.service.ClientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientResource {

    @Autowired
    ClientService clientService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client add(@RequestBody Client client) {
        return clientService.add(client);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestParam Integer id) {
        clientService.deleteById(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClientDto findById(@PathVariable Integer id) {
        return convertToDto(clientService.findById(id));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ClientDto> findAll() {
        return clientService.findAllClients()
                .stream().map(this::convertToDto)
                .toList();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client updateData(@RequestBody Client client) {
        return clientService.update(client);
    }

    @GetMapping("/criteria")
    @ResponseStatus(HttpStatus.OK)
    public List<ClientDto> findByCriteria(@RequestParam(required = false) String clientName,
                                          @RequestParam(required = false) String clientEmail,
                                          @RequestParam(required = false) Double discount) {

        return clientService.findByCriteria(clientName, clientEmail, discount)
                .stream().map(this::convertToDto)
                .toList();
    }

    private ClientDto convertToDto(Client client) {
        return modelMapper.map(client, ClientDto.class);
    }


}
