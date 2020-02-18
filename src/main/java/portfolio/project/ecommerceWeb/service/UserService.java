package portfolio.project.ecommerceWeb.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import portfolio.project.ecommerceWeb.model.User;
import portfolio.project.ecommerceWeb.repository.UserDao;

@Service
public class UserService {

	@Autowired
	UserDao userDao;
	
	public Optional<User> getById(long id) {
		return userDao.findById(id);
	}
	
	public Optional<User> getByUsername(String username){
		return userDao.getByUsername(username);
	}
	
	@Transactional
	public User addUser(User user) {
		return userDao.save(user);
		
	}

	public List<User> findAll() {
		return userDao.findAll();
	}
}
