package meRybaczek.orderApp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import meRybaczek.orderApp.model.OrderPdf;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderFileFullDto {

    private Integer id;

    private String fileName;

    private String fileDir;

    private double drawingSizeWidth;

    private double drawingSizeHight;

    private boolean isDrawingColor;

    private int drawingCopyQty;

    private boolean isFold;

    @JsonIgnore
    private OrderPdfFullDto orderPdf;
}
