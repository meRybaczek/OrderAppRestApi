package order.orderap.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq")
    @SequenceGenerator(name="seq",sequenceName="order_pdf_seq")
    private Integer id;
    private String clientName;
    private String clientPhone;
    private double discount;
    @OneToMany
            (cascade = {CascadeType.PERSIST,
                    CascadeType.REMOVE,},
                    fetch = FetchType.EAGER//
                    , mappedBy = "client"
            )
    private OrderPdf orderPdf;




}
