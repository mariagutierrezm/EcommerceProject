package portfolio.project.ecommerceWeb.model;

public enum TypeOfProduct {
	
	LEAVES("Leaves"), VEGETABLES("Vegetables"), FRUIT("Fruit");
	
	private String name;
	
	private TypeOfProduct(String type) {
		name = type;
	}
	
	public String getName() {
		return name;
	}

}
