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
        return clientService.add(client);
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

//    @GetMapping("/")                // JAKI URI DAC ???
//    @ResponseStatus(HttpStatus.FOUND)
//    public Client findBy(@RequestParam(required = false) String clientName,
//                         @RequestParam(required = false) String clientEmail,
//                         @RequestParam(required = false) String nipNo) {
//
//        return clientService.findBy(clientName, clientEmail, nipNo);
//    }

    @PutMapping                         // nie ma roznicy czy patch czy put ???
    @ResponseStatus(HttpStatus.OK)
    public Client updateData(@RequestBody Client client) {
        return clientService.update(client);
    }
    @GetMapping("/byClientName")
    @ResponseStatus(HttpStatus.FOUND)
    public List<Client> findByClientName(@RequestParam(required = false) String clientName,
                                         @RequestParam(required = false) String clientEmail,
                                         @RequestParam(required = false) String nipNo,
                                         @RequestParam(required = false) Double discount) {
        return clientService.findByClientCriteria(clientName,clientEmail,nipNo,discount);
    }

    @GetMapping("/criteria")
    @ResponseStatus(HttpStatus.FOUND)
    public List<Client> findByCriteria(@RequestParam(required = false) String clientName,
                                       @RequestParam(required = false) String clientEmail,
                                       @RequestParam(required = false) Double discount) {

        return clientService.findByCriteria(clientName,clientEmail,discount);
    }

}
