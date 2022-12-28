package order.orderap;

import order.orderap.model.OrderFile;
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

    @Autowired
	OrderFileService orderFileService;
    @Autowired
	OrderService orderService;
	@Autowired
	OrderRepository orderRepository;
//    @BeforeEach
//    public void setup(){
//		OrderPdf orderPdf = new OrderPdf(null,"Arch1", "78454545", 10.0, LocalDate.now());
//		OrderFile orderFile = new OrderFile("Rys1", "C:\test", 297.0, 100.0,
//				true, 2, true);
//		orderFile.setOrderPdf(orderPdf);
//		orderPdf.addOrderFile(orderFile);
//		orderRepository.save(orderPdf);
//    }

	@Test
	void findByIdTest() {
		//when
		OrderPdf byIdPdf = orderService.findById(1);
		OrderFile byIdFile = orderFileService.findById(1);
		//then
		Assertions.assertEquals(1, byIdPdf.getId());
		Assertions.assertEquals(1, byIdFile.getId());
	}

	@Test
	void isOrderFileAddedToOrderTest(){
		//when
		OrderPdf byId = orderService.findById(1);
		String clientName = byId.getClientName();
		//then
		Assertions.assertEquals("Arch1", clientName);
		Assertions.assertEquals(1,byId.getOrderFiles().size());
	}

	@Test
	void deleteByIdTest(){
		//when
		orderService.deleteById(1);
		//then
		Assertions.assertThrows(OrderNotFoundException.class, () -> orderService.findById(1));
	}
	@Test
	void isOrderFileBelongsToOrder(){
		//when
		Integer rys1 = orderFileService.findByFileName("Rys1").get(0).getOrderPdf().getId();
		//then
		Assertions.assertEquals(1, rys1);

	}

}
