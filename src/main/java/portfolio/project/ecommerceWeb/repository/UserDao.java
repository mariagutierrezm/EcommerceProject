package portfolio.project.ecommerceWeb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import portfolio.project.ecommerceWeb.model.User;

public interface UserDao extends JpaRepository<User, Long>{

	@Query("SELECT u FROM Users u WHERE u.username = :username")
	Optional<User> getByUsername(@Param("username") String username);
}
