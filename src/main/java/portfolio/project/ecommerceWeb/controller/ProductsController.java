package portfolio.project.ecommerceWeb.controller;

import java.util.EnumSet;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import portfolio.project.ecommerceWeb.model.Products;
import portfolio.project.ecommerceWeb.model.TypeOfProduct;
import portfolio.project.ecommerceWeb.model.User;
import portfolio.project.ecommerceWeb.service.ProductsService;
import portfolio.project.ecommerceWeb.service.UserService;

@Controller
public class ProductsController {


	static final String USER_SESSION = "user";

	@Autowired
	ProductsService productsService;

	@Autowired
	UserService userService;

	public static String getUserSession() {
		return USER_SESSION;
	}

	@GetMapping("products")
	public ModelAndView products(@ModelAttribute("product") Products product, @ModelAttribute("user") User user,
			HttpSession session, ModelAndView modelAndView) {
		User userfromDB = (User) session.getAttribute(USER_SESSION);
		modelAndView.addObject("typeOfProduct", EnumSet.allOf(TypeOfProduct.class));
		modelAndView.addObject("products", productsService.retrieveAll());
		modelAndView.addObject(userService.getById(userfromDB.getId()).get());
		modelAndView.setViewName("WEB-INF/products");
		return modelAndView;

	}

	@PostMapping("filterProducts")
	public ModelAndView filterProducts(@ModelAttribute("product") Products products, ModelAndView modelAndView,
			@ModelAttribute("user") User user, HttpSession session) {
		User userfromDB = (User) session.getAttribute(USER_SESSION);
		List<Products> filteredProducts = productsService.filterByType(userfromDB, products.getTypeOfProduct());
		modelAndView.addObject("products", filteredProducts);
		modelAndView.addObject(userService.getById(userfromDB.getId()).get());
		modelAndView.setViewName("WEB-INF/products");
		return modelAndView;
	}

	


}
