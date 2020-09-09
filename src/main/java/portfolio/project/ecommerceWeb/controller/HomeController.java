package portfolio.project.ecommerceWeb.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import portfolio.project.ecommerceWeb.model.User;
import portfolio.project.ecommerceWeb.service.UserService;

@Controller
public class HomeController {

	private static final Logger logger = LogManager.getLogger(HomeController.class);

	static final String USER_SESSION = "user";

	@Autowired
	UserService userService;

	public static String getUserSession() {
		return USER_SESSION;
	}
	
	@GetMapping("/")
	public String homeShop() {
		return "WEB-INF/homeShop";
	}

//	@GetMapping("/")
//	private ModelAndView start(ModelAndView modelAndView) {
//		return new ModelAndView("home");
//	}

	@GetMapping("login")
	private ModelAndView login(ModelAndView modelAndView) {
		modelAndView.addObject("user", new User());
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@PostMapping("LoginSubmit")
	public ModelAndView loginSubmit(@ModelAttribute("user") User user, ModelAndView modelAndView, HttpSession session) {
		System.err.println(user);

		Optional<User> userFromDatabase = userService.getByUsername(user.getUsername());

		if (userFromDatabase.isPresent()) {
			session.setAttribute(USER_SESSION, userFromDatabase.get());
			logger.info("username", userFromDatabase.get().getUsername() + "logged");
			modelAndView.setViewName("redirect:/userShop");
			return modelAndView;
		} else { 
			modelAndView.addObject("errorMessage", "Incorrect username or password");
			modelAndView.setViewName("login");
			return modelAndView;
		}
	}
	
	@GetMapping("userShop")
	public String userShop() {
		return "WEB-INF/userShop";
	}
	
	@GetMapping("register")
	public ModelAndView registerNewUser() {
		ModelAndView modelAndView = new ModelAndView("register", "user", new User());
		return modelAndView;
	}

	@PostMapping("registerSubmit")
	public ModelAndView registerSubmit(@Valid @ModelAttribute("user") User user, BindingResult bindingResult,
			ModelAndView modelAndView, @ModelAttribute("confirmPassword") String confirmPassword) {
		
		if (bindingResult.hasErrors()) {
			logger.info("Register form had errors: ", user);
			return new ModelAndView("register", "user", user);
		}
		
		if(user.getPassword().equals(confirmPassword)) {
			Optional<User> userDB = userService.getByUsername(user.getUsername());
			if(userDB.isPresent()) {
				modelAndView.addObject("errorMessage", "This username already exists please write a different one");
				modelAndView.setViewName("register");
				return modelAndView;
			} 
		} else {
			modelAndView.addObject("errorMessage", "Your passwords do not match, please type in them again");
			modelAndView.setViewName("register");
			return modelAndView;
		}
		userService.addUser(user);
		logger.info("Success new user saved to database " + user);
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	@GetMapping("logout")
	public ModelAndView logout(HttpSession session) {
		session.removeAttribute(USER_SESSION);
		return new ModelAndView("login", "user", new User());
	}

}
