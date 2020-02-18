package portfolio.project.ecommerceWeb.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import portfolio.project.ecommerceWeb.model.Basket;
import portfolio.project.ecommerceWeb.model.User;

public interface BasketDao extends JpaRepository<Basket, Long>{
	
	@Query("SELECT b FROM Basket b WHERE b.orderDate = :orderDateParam")
	Basket getOrderByOrderDate(@Param("orderDateParam") LocalDate orderDate);
	
	@Query("SELECT b FROM Basket b WHERE b.user = :userParam")
	Basket getBasketByUsername(@Param("userParam") User user);

}
