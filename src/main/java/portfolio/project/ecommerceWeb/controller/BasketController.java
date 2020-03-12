package portfolio.project.ecommerceWeb.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import portfolio.project.ecommerceWeb.model.Basket;
import portfolio.project.ecommerceWeb.model.Products;
import portfolio.project.ecommerceWeb.model.User;
import portfolio.project.ecommerceWeb.service.BasketService;
import portfolio.project.ecommerceWeb.service.ProductsService;
import portfolio.project.ecommerceWeb.service.UserService;

@Controller
public class BasketController {

	private static final Logger logger = LogManager.getLogger(HomeController.class);

	static final String USER_SESSION = "user";
	
	@Autowired
	ProductsService productsService;

	@Autowired
	UserService userService;

	@Autowired
	BasketService basketService;

	public static String getUserSession() {
		return USER_SESSION;
	}
	
	@PostMapping("addToBasket")
	public ModelAndView addToBasket(@RequestParam("productID") long productID, @RequestParam("quantity") int quantity,
			ModelAndView modelAndView, HttpSession session //, @ModelAttribute("product") Products products
			) {
		User userfromDB = (User) session.getAttribute(USER_SESSION);
		Products productsDB = productsService.findById(productID).get();
		int stock = productsDB.getQuantity();
//		List<Products> filteredProducts = productsService.filterByType(userfromDB, products.getTypeOfProduct());

		if (stock != 0) {
//			if (products == filteredProducts) {
			Basket basketDB = basketService.addProductToBasket(userfromDB, productsDB, quantity);
			modelAndView.addObject("basket", basketDB);
			modelAndView.addObject("successMessage", "The product has been added to your basket");
//			modelAndView.addObject("user", userfromDB);
			logger.info("Success basket Saved " + basketDB);
//			} else {
//				
//			}
		} else {
			modelAndView.addObject("errorMessage", "Sorry we are currently out of stock on this product");
		}
		modelAndView.setViewName("redirect:/products");
		return modelAndView;
	}

	@RequestMapping("basket")
	public ModelAndView basket(ModelAndView modelAndView, HttpSession session) {
		User user = (User) session.getAttribute(USER_SESSION);
		Basket basket = basketService.getBasketByUser(user);
		if (basket != null) {
			modelAndView.addObject("user", user);
			modelAndView.addObject("basket", basket);
			modelAndView.addObject("products", basket.getProducts());
		} else {
			modelAndView.addObject("user", user);
			modelAndView.addObject("message", "Your basket is empty");
		}
		modelAndView.setViewName("WEB-INF/basket");
		return modelAndView;
	}
	
	@PostMapping("deleteFromBasket")
	public ModelAndView deleteFromBasket(@RequestParam("productID") long productID, ModelAndView modelAndView, 
			HttpSession session) {
		User userfromDB = (User) session.getAttribute(USER_SESSION);
		Basket basket = basketService.getBasketByUser(userfromDB);
		//productsService.deleteById(productID);
		modelAndView.addObject("user", userfromDB);
		modelAndView.addObject("basket", basket);
		modelAndView.addObject("products", basket.getProducts());
		modelAndView.setViewName("redirect:/basket");
		return modelAndView;
	}
	
	@RequestMapping("checkout")
	public ModelAndView checkout(@RequestParam("basketID") long basketID, @RequestParam("quantity") int quantity, 
			ModelAndView modelAndView, HttpSession session) {
		User user = (User) session.getAttribute(USER_SESSION);
		basketService.getBaskByID(basketID).get();
//		Basket basket = basketService.checkoutProducts(user, productsDB, quantity);
//		modelAndView.addObject("basket", basket);
		modelAndView.addObject("user", user);
		modelAndView.addObject("successMessage", "Your order has been placed");
//		logger.info("Success basket checkout " + basket);
		modelAndView.setViewName("WEB-INF/checkout");
		return modelAndView;
	}
}
