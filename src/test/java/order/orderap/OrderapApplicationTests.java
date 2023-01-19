package order.orderap;

import order.orderap.model.OrderPdf;
import order.orderap.repository.OrderRepository;
import order.orderap.service.OrderFileService;
import order.orderap.service.OrderNotFoundException;
import order.orderap.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;


@ExtendWith(SpringExtension.class)
@Sql(scripts={"/data.sql"})
@Transactional
@SpringBootTest
class OrderapApplicationTests {

//    @Autowired
//	OrderFileService orderFileService;
//    @Autowired
//	OrderService orderService;
//	@Autowired
//	OrderRepository orderRepository;
//
//	@Test
//	void findByIdTest() {
//		//when
//		OrderPdf byIdPdf = orderService.findById(1);
//		//then
//		Assertions.assertEquals(1, byIdPdf.getId());
//		Assertions.assertEquals(1,byIdPdf.getOrderFiles().size());
//		Assertions.assertEquals("Arch1", byIdPdf.getClientName());
//		Assertions.assertEquals("Rys1", byIdPdf.getOrderFiles().get(0).getFileName());
//		//reszte kilka asercji
//	}
//
//	@Test
//	void deleteByIdTest(){
//		//when
//		orderService.deleteById(1);
//		//then
//		Assertions.assertThrows(OrderNotFoundException.class, () -> orderService.findById(1));
//	}
//

}
