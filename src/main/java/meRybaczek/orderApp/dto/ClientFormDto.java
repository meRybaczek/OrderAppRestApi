package meRybaczek.orderApp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ClientFormDto {

    private Integer id;

    private String clientName;

    private String nipNo;

    private double discount;
}
