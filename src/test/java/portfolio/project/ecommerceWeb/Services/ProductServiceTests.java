package portfolio.project.ecommerceWeb.Services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import portfolio.project.ecommerceWeb.model.Products;
import portfolio.project.ecommerceWeb.model.TypeOfProduct;
import portfolio.project.ecommerceWeb.model.User;
import portfolio.project.ecommerceWeb.service.BasketService;
import portfolio.project.ecommerceWeb.service.ProductsService;
import portfolio.project.ecommerceWeb.service.UserService;


@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ProductServiceTests {

	@Autowired
	ProductsService productsService;
	
	@Autowired
	BasketService basketService;
	
	@Autowired
	UserService userService;
	
	@Test
	public void AProductCanBeFoundById() {
		Products product = productsService.findById(1L).get();
		assertEquals("Organic Leaves", product.getTitleProduct());
	}
	
	@Test
	public void AllProductsCanBeRetrievedAtTheSameTime() {
		List<Products> allproducts = productsService.retrieveAll();
		assertEquals(20, allproducts.size());
	}
	
	@Test
	public void ThatChangesToAProductCanBeMade() {
		Products product = productsService.findById(2L).get();
		product.setTitleProduct("colores");
		productsService.save(product);
		assertEquals("colores", product.getTitleProduct());
	}
	
	@Test
	public void ProductsCanBeFilteredByType() {
		User user = userService.getById(1L).get();
		List<Products> products = productsService.filterByType(user, TypeOfProduct.FRUIT);
		assertEquals(6, products.size());
		
		List<Products> productsleaves = productsService.filterByType(user, TypeOfProduct.LEAVES);
		assertEquals(7, productsleaves.size());
	}
	
	@Test
	public void CanDeleteProductsById_ProveSizeOfListOfProductsChange() {
		List<Products> productsList = productsService.retrieveAll();
		int listBeforeDeletion = productsList.size();
		productsService.deleteById(2L);
		productsService.deleteById(5L);
		List<Products> productsListNew = productsService.retrieveAll();
		int listAfterDeletion = productsListNew.size();
		assertEquals(listAfterDeletion, listBeforeDeletion - 2);
	}
}
