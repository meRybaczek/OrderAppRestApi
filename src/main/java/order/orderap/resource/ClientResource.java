package order.orderap.resource;

import order.orderap.model.Client;
import order.orderap.dto.ClientDto;
import order.orderap.service.ClientService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientResource {

    //@Autowired        // DLACZEGO NIE AUTOWIRED ??
    // CR: wstrzykiwanie przez konstruktor i bez autowired
    private ClientService clientService;
    //@Autowired
    // CR: wstrzykiwanie przez konstruktor i bez autowired    
    private ModelMapper modelMapper;

    public ClientResource(ClientService clientService, ModelMapper modelMapper) {
        this.clientService = clientService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client add(@RequestBody Client client) {
        return clientService.add(client);
    }

    @DeleteMapping
    public void delete(@RequestParam Integer id) {
        clientService.deleteById(id);
    }

    @GetMapping("/{id}")
    public ClientDto findById(@PathVariable Integer id) {
        return convertToDto(clientService.findById(id));
    }

    @GetMapping
    public List<ClientDto> findAll() {
        return clientService.findAllClients()
                .stream().map(this::convertToDto)
                .toList();
    }

    @PutMapping
    public Client updateData(@RequestBody Client client) {
        return clientService.update(client);
    }

    @GetMapping("/criteria")
    // CR: ta metoda na początek
    // CR: bez criteria, powinnien byc standardowy GET ktorym mozna albo pobrac wszystkie albo wyfiltrowac po wybranych parametrach
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
