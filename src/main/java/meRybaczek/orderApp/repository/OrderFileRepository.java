package meRybaczek.orderApp.repository;

import meRybaczek.orderApp.model.OrderFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderFileRepository extends JpaRepository<OrderFile, Integer> {
}
