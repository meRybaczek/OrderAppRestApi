package meRybaczek.orderApp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import meRybaczek.orderApp.model.Client;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderPdfFormDto {

    private Integer id;

    private LocalDate createdAt;

    private ClientFormDto client;
}
