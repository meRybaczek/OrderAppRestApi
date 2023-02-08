package meRybaczek.orderApp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import meRybaczek.orderApp.model.OrderPdf;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ClientFullDto {

    private Integer id;

    private String clientName;

    private String nipNo;

    private String clientEmail;

    private double discount;

    // CR: tu powinno byc OrderPdfFullDto
    private List<OrderPdf> orderPdfList;
}