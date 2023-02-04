package order.orderap.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ClientDto { 
    // CR: record
    // CR: Client.ListDTO i Client.FullDTO
    // CR: dto do osobnego pakietu .dto
    private Integer id;

    private String clientName;

    private String nipNo;

    private double discount;
}
