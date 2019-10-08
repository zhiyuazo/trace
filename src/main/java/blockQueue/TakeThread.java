package blockQueue;

public class TakeThread extends Thread{
	private TakeThread() {
	}
	
	private static TakeThread instance = new TakeThread();
	
	public static TakeThread getInstance() {
		return instance;
	}
	
	public void run() {
		System.out.println("take Thread start!...");
		while(true) {
			String s = DataHub.getInstance().take();
			System.out.println("take.." + s);
		}
	}
}
