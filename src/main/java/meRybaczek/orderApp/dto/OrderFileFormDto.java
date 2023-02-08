package meRybaczek.orderApp.dto;

import meRybaczek.orderApp.model.OrderPdf;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderFileFormDto {

    private Integer id;

    private String fileName;

    private String fileDir;

    // CR: odrderpdfdto
    private OrderPdf orderPdf;
}
