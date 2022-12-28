package order.orderap.repository;

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
    List<OrderPdf> findByClientName(@Param("clientName") String clientName);
    List<OrderPdf> findByClientPhone(@Param("clientPhone") String clientPhone);
    List<OrderPdf> findByDiscount(@Param("discount") Double discount);
    List<OrderPdf> findByCreatedAt(@Param("createdAt") LocalDate createdAt);

    @Modifying
    @Query("UPDATE OrderPdf o SET o.discount = :discount WHERE o.id = :id")
    void updateDiscount(@Param("discount") double discount, @Param("id") Integer id);





}
