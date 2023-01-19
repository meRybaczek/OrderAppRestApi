package order.orderap.resource;

import order.orderap.model.Client;
import order.orderap.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientResource {

    @Autowired
    ClientService clientService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client add(@RequestBody Client client) {
        return clientService.addClient(client);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestParam Integer id) {
        clientService.deleteById(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Client findById(@PathVariable Integer id) {
        return clientService.findById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<Client> findAll() {
        return clientService.findAllClients();
    }

    @GetMapping("/")                // JAKI URI DAC ???
    @ResponseStatus(HttpStatus.FOUND)
    public Client findBy(@RequestParam(required = false) String clientName,
                         @RequestParam(required = false) String clientEmail,
                         @RequestParam(required = false) String nipNo) {

        return clientService.findBy(clientName, clientEmail, nipNo);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public Client updateData(@RequestParam Integer id,
                             @RequestParam(required = false) String clientName,
                             @RequestParam(required = false) String clientEmail,
                             @RequestParam(required = false) String nipNo,
                             @RequestParam(required = false) Double discount) {
        return clientService.updateDataById(id,clientName,clientEmail,nipNo,discount);
    }

}
