package order.orderap.model;

import lombok.*;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class OrderPdf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String clientName;
    private String clientPhone;
    private double discount;
    private LocalDate createdAt;
    @OneToMany
            (cascade = {CascadeType.PERSIST,
                    CascadeType.REMOVE,},
                    fetch = FetchType.EAGER//
            , mappedBy = "orderPdf"
    )
    List<OrderFile> orderFiles = new ArrayList<>();

//    @OneToOne
//    private Invoice invoice;

    public OrderPdf(Integer id, String clientName, String clientPhone, double discount, LocalDate createdAt) {
        this.id = id;
        this.clientName = clientName;
        this.clientPhone = clientPhone;
        this.discount = discount;
        this.createdAt = createdAt;
    }

    public void addOrderFile(OrderFile orderFile){
        getOrderFiles().add(orderFile);
    }

}
