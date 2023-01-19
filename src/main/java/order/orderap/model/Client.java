package order.orderap.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq")
    @SequenceGenerator(name="seq",sequenceName="client_seq")
    private Integer id;
    private String clientName;
    private String nipNo;
    private String clientEmail;
    private double discount;
    @OneToMany
            (cascade = {CascadeType.PERSIST,
                    CascadeType.REMOVE,},
                    fetch = FetchType.EAGER//
                    , mappedBy = "client"
            )
    private List<OrderPdf> orderPdf = new ArrayList<>();




}
