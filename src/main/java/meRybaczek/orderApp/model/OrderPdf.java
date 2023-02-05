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
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
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