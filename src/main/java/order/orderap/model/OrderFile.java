package order.orderap.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;
@Entity
public class OrderFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer order_id;
    private String fileName;
    private String fileDir;
    private double drawingSizeWidth;
    private double drawingSizeHight;
    private boolean isDrawingColor;
    private int drawingCopyQty;
    private boolean isFold;

    public OrderFile() {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileDir() {
        return fileDir;
    }

    public void setFileDir(String fileDir) {
        this.fileDir = fileDir;
    }

    public double getDrawingSizeWidth() {
        return drawingSizeWidth;
    }

    public void setDrawingSizeWidth(double drawingSizeWidth) {
        this.drawingSizeWidth = drawingSizeWidth;
    }

    public double getDrawingSizeHight() {

        return drawingSizeHight;
    }

    public void setDrawingSizeHight(double drawingSizeHight) {
        this.drawingSizeHight = drawingSizeHight;
    }

    public boolean isDrawingColor() {
        return isDrawingColor;
    }

    public void setDrawingColor(boolean drawingColor) {
        isDrawingColor = drawingColor;
    }

    public int getDrawingCopyQty() {
        return drawingCopyQty;
    }

    public void setDrawingCopyQty(int drawingCopyQty) {
        this.drawingCopyQty = drawingCopyQty;
    }

    public boolean isFold() {
        return isFold;
    }

    public void setFold(boolean fold) {
        isFold = fold;
    }

    @Override
    public String toString() {
        return
                ">>File name: " + fileName +
                "\nFile directory: " + fileDir +
                "\nDrawing width: " + drawingSizeWidth + "[mm]" +
                "\nDrawing hight: " + drawingSizeHight + "[mm]" +
                "\nDrawing color: " + isDrawingColor +
                "\nCopies Qty: " + drawingCopyQty +
                "\nDrawing to fold: " + isFold;
    }

}
