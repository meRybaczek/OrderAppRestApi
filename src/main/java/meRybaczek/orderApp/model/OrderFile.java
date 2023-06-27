package meRybaczek.orderApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class OrderFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
//    @SequenceGenerator(name = "seq", sequenceName = "order_file_seq")
    private Integer id;
    @NotNull
    private String fileName;
    @NotNull
    private String fileDir;
    @Positive
    private double drawingSizeWidth;
    @Positive
    private double drawingSizeHight;

    private boolean isDrawingColor;

    private int drawingCopyQty;

    private boolean isFold;

    @JsonIgnore
    @ManyToOne
    private OrderPdf orderPdf;

    public OrderFile(String fileName, String fileDir, double drawingSizeWidth, double drawingSizeHight,
                     boolean isDrawingColor, int drawingCopyQty, boolean isFold) {
        this.fileName = fileName;
        this.fileDir = fileDir;
        this.drawingSizeWidth = drawingSizeWidth;
        this.drawingSizeHight = drawingSizeHight;
        this.isDrawingColor = isDrawingColor;
        this.drawingCopyQty = drawingCopyQty;
        this.isFold = isFold;
    }

    public OrderFile(Integer id, String fileName, String fileDir, double drawingSizeWidth, double drawingSizeHight,
                     boolean isDrawingColor, int drawingCopyQty, boolean isFold) {
        this.id = id;
        this.fileName = fileName;
        this.fileDir = fileDir;
        this.drawingSizeWidth = drawingSizeWidth;
        this.drawingSizeHight = drawingSizeHight;
        this.isDrawingColor = isDrawingColor;
        this.drawingCopyQty = drawingCopyQty;
        this.isFold = isFold;
    }

}
