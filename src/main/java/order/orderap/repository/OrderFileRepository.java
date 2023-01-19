package order.orderap.repository;

import order.orderap.model.OrderFile;
import order.orderap.model.OrderPdf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderFileRepository extends JpaRepository<OrderFile, Integer> {
    List<OrderFile> findByOrderPdf(OrderPdf orderPdf);

}
