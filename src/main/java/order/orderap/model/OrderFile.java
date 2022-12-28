package order.orderap.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity

public class OrderFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fileName;
    private String fileDir;
    private double drawingSizeWidth;
    private double drawingSizeHight;
    private boolean isDrawingColor;
    private int drawingCopyQty;
    private boolean isFold;

    @JsonIgnore
    @ManyToOne
    private OrderPdf orderPdf;

    public OrderFile(String fileName, String fileDir, double drawingSizeWidth, double drawingSizeHight, boolean isDrawingColor, int drawingCopyQty, boolean isFold) {
        this.fileName = fileName;
        this.fileDir = fileDir;
        this.drawingSizeWidth = drawingSizeWidth;
        this.drawingSizeHight = drawingSizeHight;
        this.isDrawingColor = isDrawingColor;
        this.drawingCopyQty = drawingCopyQty;
        this.isFold = isFold;
    }
}
