
public class Hello {
	public static void main(String[] args) throws InterruptedException {
		keepRunning();
	}

	private static void keepRunning() throws InterruptedException {
		while (true) {
			Thread.sleep(1000);
			System.out.println("hello---");
		}
		
	}
}
