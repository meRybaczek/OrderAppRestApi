package meRybaczek.orderApp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import meRybaczek.orderApp.model.Client;
import meRybaczek.orderApp.model.OrderFile;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderPdfFullDto {

    private Integer id;

    private LocalDate createdAt;

    private Client client;

    private List<OrderFileFullDto> orderFiles;
}
