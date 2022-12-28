package order.orderap.repository;

import order.orderap.model.OrderFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderFileRepository extends JpaRepository<OrderFile, Integer> {


    List<OrderFile> findByFileName(@Param("clientName") String fileName);
//    @Modifying
//    //@Query("SELECT o.orderFiles FROM OrderPdf o WHERE o.order_id = :id")
//    List<OrderFile> getOrderFilesByOrderPdfId(@Param("id") Integer id);

}
