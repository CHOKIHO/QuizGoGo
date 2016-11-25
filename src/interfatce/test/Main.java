package interfatce.test;

public class Main {
	
	public static void main(String[] args) {
		
		//아무것도 안쓴다.
		Darimi darimi2 = new Darimi();
		darimi2.use();
		
		Chair chair2 = new Chair();
		chair2.use();
		
		
		//인터페이스
		System.out.println("[인터페이스]");
		Userable darimi = new Darimi();
		darimi.use();
		
		Userable chair = new Chair();
		chair.use();
		
		Userable computer = new Computer();
		computer.use();

		//위임
		System.out.println("[위임]");
		Product product;
		
		product = new Product(new Darimi());
		product.use();
		
		product = new Product(new Chair());
		product.use();
		
		product = new Product(new Computer());
		product.use();
		
		
	}

	
}
