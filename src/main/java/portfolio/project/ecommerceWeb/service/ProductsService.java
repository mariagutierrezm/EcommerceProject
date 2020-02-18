package portfolio.project.ecommerceWeb.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import portfolio.project.ecommerceWeb.model.Products;
import portfolio.project.ecommerceWeb.model.TypeOfProduct;
import portfolio.project.ecommerceWeb.model.User;
import portfolio.project.ecommerceWeb.repository.ProductsDao;

@Service
public class ProductsService {

	@Autowired
	ProductsDao productsDao;
	

	public List<Products> retrieveAll() {
		return productsDao.findAll();
	}

	public Optional<Products> findById(Long productID) {
		return productsDao.findById(productID);
	}
	
	public List<Products> filterByType(User user, TypeOfProduct typeOfProduct){
		List<Products> filteredTypes = productsDao.filterByTypeOfProduct(typeOfProduct);
		return filteredTypes;
	}

	@Transactional
	public Products save(Products product) {
		return productsDao.save(product);
	}

	@Transactional
	public void deleteById(Long id) {
		productsDao.deleteById(id);
	}
	
}
