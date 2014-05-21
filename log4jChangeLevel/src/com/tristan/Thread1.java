package com.tristan;

public class Thread1 implements Runnable{
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Thread1.class); 

	@Override
	public void run() {
		while (true){
			logger.error("error" );
			logger.warn("warn" );
			logger.info("info" );
			logger.debug("debug" );
			
			logger.fatal("---------------------");
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	

}
