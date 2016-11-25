package interfatce.test;

public class Product {

	Userable userable;
	
	public Product(Userable userable) {
		this.userable = userable;
	}
	
	public void use() {
		userable.use();
	}
}
