package meRybaczek.orderApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.pl.NIP;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @SequenceGenerator(name = "seq", sequenceName = "client_seq")
    private Integer id;
    @NotNull
    private String clientName;
    @NIP
    @NotNull
    private String nipNo;
    @Email
    private String clientEmail;
    @Positive
    @Max(100)
    private double discount;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
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
