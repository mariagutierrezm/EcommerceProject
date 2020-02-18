package portfolio.project.ecommerceWeb.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import portfolio.project.ecommerceWeb.model.Basket;
import portfolio.project.ecommerceWeb.model.Products;
import portfolio.project.ecommerceWeb.model.User;
import portfolio.project.ecommerceWeb.repository.BasketDao;
import portfolio.project.ecommerceWeb.repository.ProductsDao;

@Service
public class BasketService {

	@Autowired
	BasketDao basketDao;
	
	@Autowired
	ProductsDao productsDao;
	
//	public Basket checkoutProducts(User user, Basket basket, int quantity) {
//		Basket basketFinal = basketDao.getBasketByUsername(user);
//		Basket checkoutBasket = basketDao.findById(basket.getBasketID()).get();
//		if(product.getQuantity() != 0) {
////			int newQuantity = product.getQuantity();
//			product.setQuantity(product.getQuantity() - quantity);
//			productsDao.save(product);
//			return basketFinal;
//		}
//		return basketFinal;
//	}
	
	public Basket addProductToBasket(User user, Products product, int quantity) {
		LocalDate date = LocalDate.now();
		Basket basketFromDB = basketDao.getBasketByUsername(user);
		product.setQuantityBasket(quantity);
		if(basketFromDB == null) {
			Basket baskets = new Basket();
			List<Products> productList = new ArrayList<Products>();
			productList.add(product);
			baskets.setProduct(productList);
			baskets.setOrderDate(date);
			baskets.setUser(user);
			baskets.setQuantityOrdered(quantity);
			baskets.setTotalPrice(product.getPrice() * quantity);
			basketDao.save(baskets);
			return baskets;
		} else {
			if(basketFromDB.getProducts().contains(product)){
			basketFromDB.setQuantityOrdered(basketFromDB.getQuantityOrdered() + quantity);
			basketFromDB.setTotalPrice(basketFromDB.getTotalPrice() + (product.getPrice() * quantity));
			basketDao.save(basketFromDB);
			return basketFromDB;
			} else {
				List<Products> updateProductList = basketFromDB.getProducts();
				updateProductList.add(product);
				basketFromDB.setProduct(updateProductList);
				basketFromDB.setQuantityOrdered(basketFromDB.getQuantityOrdered() + quantity);
				basketFromDB.setTotalPrice(basketFromDB.getTotalPrice() + (product.getPrice() * quantity));
				basketDao.save(basketFromDB);
				return basketFromDB;
			}
		}
	}
	
	@Transactional
	public Basket save (Basket basket) {
		return basketDao.save(basket);
	}
	
	public List<Basket> retrieveAll(){
		return basketDao.findAll();
	}
	
	@Transactional
	public void deleteById(Long basketID) {
		basketDao.deleteById(basketID);
	}
	
	public Basket getBasketbyDate(LocalDate orderDate) {
		return basketDao.getOrderByOrderDate(orderDate);
	}
	
	public Basket getBasketByUser(User user) {
		return basketDao.getBasketByUsername(user);
	}
	
	public Optional<Basket> getBaskByID(Long basketID) {
		return basketDao.findById(basketID);
	}
	
	
	
	

}
