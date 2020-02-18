package portfolio.project.ecommerceWeb.Services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import portfolio.project.ecommerceWeb.model.User;
import portfolio.project.ecommerceWeb.service.BasketService;
import portfolio.project.ecommerceWeb.service.ProductsService;
import portfolio.project.ecommerceWeb.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserServiceTests {

	@Autowired
	ProductsService productsService;
	
	@Autowired
	BasketService basketService;
	
	@Autowired
	UserService userService;
	
	@Test
	public void test_CanGetUserById() {
		assertEquals("Juli", userService.getById(2L).get().getFirstname());
	}
	
	@Test
	public void test_CanGetUserByUsername() {
		assertEquals("jeje", userService.getByUsername("Juli").get().getLastname());
	}
	
	@Test
	public void test_CanRegisterNewUsers() {
		int beforeAddition = userService.findAll().size();
		User userNew = new User("Joy", "FireInMyBones", "Elena", "Lina");
		userService.addUser(userNew);
		int afterAddition = userService.findAll().size();
		assertEquals(afterAddition, beforeAddition + 1);
	}
}
