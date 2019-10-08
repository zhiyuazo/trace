package blockQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataHub {
	
	private static final Logger logger = LoggerFactory.getLogger(DataHub.class);
	
	private BlockingQueue<String> hub = new LinkedBlockingQueue<String>();
	
	private static DataHub instance  = new DataHub(); 
			
	private DataHub() {
		//----
	}
	
	public  static DataHub getInstance(){
		return instance ;
	}
	
	public void put(String t) {
		try {
			hub.put(t);
		} catch (InterruptedException e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	public String take() {
		try {
			String res = hub.take();
			return res;
		} catch (InterruptedException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}
}
