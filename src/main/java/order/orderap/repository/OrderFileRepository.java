package order.orderap.repository;

import order.orderap.model.OrderFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderFileRepository extends JpaRepository<OrderFile, Integer> {

}
