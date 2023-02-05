package order.orderap.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public record ClientDto (Integer id,String clientName,String nipNo,double discount) {
}
