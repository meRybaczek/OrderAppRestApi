package meRybaczek.orderApp.dto;

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

    private OrderPdfFormDto orderPdf;
}
