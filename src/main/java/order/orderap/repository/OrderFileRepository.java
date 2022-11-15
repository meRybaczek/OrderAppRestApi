package order.orderap.repository;

import order.orderap.model.OrderFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderFileRepository extends JpaRepository<OrderFile, Integer> {

    @Modifying
    @Query("SELECT o FROM OrderFile o WHERE o.order_id = :id")
    List<OrderFile> getOrderFilesByOrderId(@Param("id") Integer id);

    @Modifying
    @Query("DELETE OrderFile o WHERE o.id = :id AND o.order_id = :order_id")
    void deleteById(@Param("id") Integer id, @Param("order_id") Integer order_id);

}
