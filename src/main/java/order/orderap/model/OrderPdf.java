package order.orderap.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Entity
public class OrderPdf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String clientName;
    private String clientPhone;
    private double discount;
    private LocalDate createdAt;
    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "order_id")
    List<OrderFile> orderFiles = new ArrayList<>();


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return  "OrderNo: " + id + " >>> Client: " + clientName + ", " + "PhoneNo: " + clientPhone + ", "+ "Discount: "
                + discount + ", Date: " + createdAt + "\n";

    }

}
