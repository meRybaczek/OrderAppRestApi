package order.orderap.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @SequenceGenerator(name = "seq", sequenceName = "order_pdf_seq")
    private Integer id;

    private LocalDate createdAt;

    @JsonIgnore
    @ManyToOne
    private Client client;

    @OneToMany
            (cascade = {CascadeType.PERSIST, CascadeType.REMOVE,}
                    , fetch = FetchType.LAZY, mappedBy = "orderPdf")
    List<OrderFile> orderFiles = new ArrayList<>();

    public OrderPdf(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}
