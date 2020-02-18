package portfolio.project.ecommerceWeb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import portfolio.project.ecommerceWeb.model.Products;
import portfolio.project.ecommerceWeb.model.TypeOfProduct;

public interface ProductsDao extends JpaRepository<Products, Long>{

	@Query("SELECT p FROM Products p WHERE p.typeOfProduct = :typeOfProduct")
	List<Products> filterByTypeOfProduct(@Param("typeOfProduct") TypeOfProduct typeOfProduct);
	
}
