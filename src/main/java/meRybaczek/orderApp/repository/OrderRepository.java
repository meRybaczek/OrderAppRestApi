package meRybaczek.orderApp.repository;

import meRybaczek.orderApp.model.OrderPdf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderPdf, Integer> {
    List<OrderPdf> findByCreatedAt(@Param("createdAt") LocalDate createdAt);

}
