package portfolio.project.ecommerceWeb.Services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import portfolio.project.ecommerceWeb.model.Basket;
import portfolio.project.ecommerceWeb.model.Products;
import portfolio.project.ecommerceWeb.model.User;
import portfolio.project.ecommerceWeb.service.BasketService;
import portfolio.project.ecommerceWeb.service.ProductsService;
import portfolio.project.ecommerceWeb.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class BasketServiceTests {

	@Autowired
	ProductsService productsService;
	
	@Autowired
	BasketService basketService;
	
	@Autowired
	UserService userService;
	
	
	public Basket createBasket() {
		User user = userService.getByUsername("Admin").get();
		List<Products> productList = new ArrayList<Products>();
		Products product1 = productsService.findById(1L).get();
		productList.add(product1);
		LocalDate date = LocalDate.now();
		int quantity = 2;
		double totalPrice = product1.getPrice() * quantity;
		Basket baskets = new Basket(productList, quantity, date, totalPrice, user);
		basketService.save(baskets);
		return baskets;
	}
	
	
	@Test
	public void ABasketCanBeFoundById() {
		Basket basket = createBasket();
		assertEquals(basket.getQuantityOrdered(), basketService.getBaskByID(1L).get().getQuantityOrdered());
	}
	
	@Test
	public void ABasketCanBeFoundByUser() {
		Basket basket = createBasket();
		User user = userService.getByUsername("Admin").get(); 
		assertEquals(basket.getUser(), basketService.getBasketByUser(user).getUser());
	}
	
	@Test
	public void CanGetTheBasketAndCheckTheSize() { //similar as the test below...no testing different things
		Basket basket = createBasket();
		assertEquals(1, basketService.retrieveAll().size());
	}
	
	@Test
	public void CanGetAListOfAvailableBasketsAndAddNewBasket_ThenTheSizeShouldIncrement() {
		int beforeAddition = basketService.retrieveAll().size();
		Basket basket = createBasket();
		List<Basket> basketsList = new ArrayList<Basket>(); 
		basketsList.add(basket); 
		int afterAddition = basketService.retrieveAll().size();
		assertEquals(afterAddition, beforeAddition + 1);
	}
	
	@Test
	public void WeCanDeleteABasketById_CreatingAListOfBasketsToCheckTheSizeReduces() {
		List<Basket> basketList = new ArrayList<Basket>(); 
		Basket basket1 = createBasket();
		basketList.add(basket1); 
		Basket basket2 = createBasket();
		basketList.add(basket2);
		int beforeDeletion = basketService.retrieveAll().size();
		basketService.deleteById(2L);
		int afterBasketDelete = basketService.retrieveAll().size(); 
		assertEquals(afterBasketDelete, beforeDeletion -1);
	}
	
	@Test
	public void WhenChangingQuantityOrdered_TheNewChangesAreSavedIntoTheBasket() {
		Basket basket = createBasket();
		basket = basketService.getBaskByID(1L).get(); //test still passes without this but, in a real case you will have to get the basket first before making any changes, that's why it is included.
		basket.setQuantityOrdered(5);
		basketService.save(basket);
		int quantityAfterChange = basketService.getBaskByID(1L).get().getQuantityOrdered();
		assertEquals(quantityAfterChange, basket.getQuantityOrdered());
	}
	
	@Test
	@Transactional //helps to remain the session, passes the duty of the session to hibernate
	public void AddingANewProductToABasket_UpdatesTheQuantityTotal_AndTheProductsListInBasket() {
		Basket basket = createBasket();
		User userByBasket = basketService.getBaskByID(1L).get().getUser();
		int productListBeforeAddingProduct = basketService.getBaskByID(1L).get().getProducts().size();
		Products product = productsService.findById(9L).get();
		int quantityAdded = 5;
		basket = basketService.addProductToBasket(userByBasket, product, quantityAdded);
		int afterAddingProduct = basketService.getBaskByID(1L).get().getProducts().size();
		assertEquals(afterAddingProduct, productListBeforeAddingProduct + 1);
		assertEquals(basket.getQuantityOrdered(), basketService.getBaskByID(1L).get().getQuantityOrdered());
	}
	
	@Test
	@Transactional
	public void IncreasingQuantityOfAProductThatAlreadyExistsInBasket() {
		Basket basket = createBasket();
		int quantityBeforeChange = basket.getQuantityOrdered();
		User userBasket = basketService.getBaskByID(1L).get().getUser();
		int productsInBasket = basketService.getBaskByID(1L).get().getProducts().size();
		Products product = productsService.findById(1L).get();
		int quantity = 4;
		basket = basketService.addProductToBasket(userBasket, product, quantity);
		int productsInBasketAfterAddition = basketService.getBaskByID(1L).get().getProducts().size();
		assertEquals(productsInBasket, productsInBasketAfterAddition);
		assertEquals(quantity + quantityBeforeChange, basket.getQuantityOrdered());
	}
	
	@Test
	public void GetBasketByTheOrderDate() {
		Basket basket = createBasket();
		assertEquals(LocalDate.now(), basketService.getBasketbyDate(basket.getOrderDate()).getOrderDate());
	}
	
	@Test
	public void AddingANewProductWhenUserDoesNotHaveABasket_CreatesANewBasket() {
		User newUser = userService.getById(2L).get();
		Products products = productsService.findById(8L).get();
		int quantity = 5;
		Basket basket = basketService.addProductToBasket(newUser, products, quantity);
		assertEquals(basket.getUser(), basketService.getBasketByUser(newUser).getUser());
	}
	
}
