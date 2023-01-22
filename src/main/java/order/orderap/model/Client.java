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

    private double discount;                // bez cascade.remove nie usuwa clienta --> error 500
                                            // ale usuwa za to kaskadowo, orphan to samo

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")     // i tak pobiera calosc mimo LAZY ??
    private List<OrderPdf> orderPdfList = new ArrayList<>();

}
