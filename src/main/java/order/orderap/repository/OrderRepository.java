package order.orderap.repository;

// CR: nieuzywane zaleznosci
import order.orderap.model.OrderFile;
import order.orderap.model.OrderPdf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderPdf, Integer> {
    List<OrderPdf> findByCreatedAt(@Param("createdAt") LocalDate createdAt);

}
