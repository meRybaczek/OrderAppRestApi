package order.orderap.repository;

import order.orderap.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client,Integer> {


    Optional<Client> findByClientName(@Param("clientName") String clientName);
    Optional<Client> findByClientEmail(@Param("clientEmail") String clientEmail);
    Optional<Client> findByNipNo(@Param("nipNo") String nipNo);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Client o SET o.discount = :discount WHERE o.id = :id")
    void updateDiscount(@Param("discount") double discount, @Param("id") Integer id);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Client o SET o.clientName = :clientName WHERE o.id = :id")
    void updateClientName(@Param("clientName") String clientName, @Param("id") Integer id);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Client o SET o.clientEmail = :clientEmail WHERE o.id = :id")
    void updateClientEmail(@Param("clientEmail") String clientEmail, @Param("id") Integer id);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Client o SET o.nipNo = :nipNo WHERE o.id = :id")
    void updateClientNipNo(@Param("nipNo") String nipNo, @Param("id") Integer id);


}
