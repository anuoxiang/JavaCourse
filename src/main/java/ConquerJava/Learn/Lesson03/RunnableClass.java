package ConquerJava.Learn.Lesson03;

import org.apache.log4j.Logger;

/**
 * 第三课 Java多线程
 * Runnable 扩展类
 * @author 		Django
 * @date		2017年10月15日
 */
public class RunnableClass implements Runnable {
	//日志工具
	final static Logger logger = Logger.getLogger(RunnableClass.class);
	
	public static void main(String[] args) {		
		logger.info("RunnableClass Started.");
		
		RunnableClass running1 = new RunnableClass("Jack");
		RunnableClass running2 = new RunnableClass("Rose");
		Thread t1 = new Thread(running1);
		Thread t2 = new Thread(running2);
		
		//同时启动，观察两线程的交错输出（抢占System.out资源）；
		t1.start();
		t2.start();		
	}
	
	private String name;
	
	public RunnableClass(String name) {
		this.name = name;
	}
	
	//以Thread方法启动（ start方法）时，调用此过程
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void run() {
		for (int i=0;i<10000;i++) {
			logger.info(String.format("%s: count to:%d",this.name,i));
		}
	}
}
