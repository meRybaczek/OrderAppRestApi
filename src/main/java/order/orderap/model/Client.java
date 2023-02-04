package order.orderap.model;
// CR: czemu pakieta nie ma orderapp?

import com.fasterxml.jackson.annotation.JsonIgnore;
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
// CR: record
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @SequenceGenerator(name = "seq", sequenceName = "client_seq")
    private Integer id;

    private String clientName;

    private String nipNo;

    private String clientEmail;

    private double discount;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")     // i tak pobiera calosc mimo LAZY ??
    private List<OrderPdf> orderPdfList = new ArrayList<>();

    public Client(String clientName, String nipNo, String clientEmail, double discount) {
        this.clientName = clientName;
        this.nipNo = nipNo;
        this.clientEmail = clientEmail;
        this.discount = discount;
    }

    public Client(Integer id, String clientName, String nipNo, String clientEmail, double discount) {
        this.id = id;
        this.clientName = clientName;
        this.nipNo = nipNo;
        this.clientEmail = clientEmail;
        this.discount = discount;
    }
}
