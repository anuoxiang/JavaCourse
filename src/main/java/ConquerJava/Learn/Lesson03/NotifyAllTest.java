package ConquerJava.Learn.Lesson03;

import org.apache.log4j.Logger;

/**
 * 第三课 Java多线程
 *
 * NotifyAll实验
 * 
 * 
 * Create Date : Oct 17, 2017
 * 
 * @author : Django
 */
public class NotifyAllTest extends Thread {
	// 日志工具
	final static Logger logger = Logger.getLogger(NotifyAllTest.class);
	// 自线程执行对象
	Calculator c;
	
	public NotifyAllTest(Calculator c) {
		this.c = c;
	}

	public void run() {
		synchronized (c) {
			try {
				logger.info(String.format("[%s] 等待一个子线程执行结果，[%s]", Thread.currentThread(), this.getState()));
				c.wait();
			} catch (InterruptedException e) {
			}
			logger.info(String.format("[%s]发现，被监控的子线程执行完成", Thread.currentThread()));
		}
	}

	public static void main(String[] args) {
		Calculator c = new Calculator();
		
		//启动三个父线程，并把自线程执行对象扔进线程监控执行
		new NotifyAllTest(c).start();
		new NotifyAllTest(c).start();
		new NotifyAllTest(c).start();
		logger.info("多个监控线程（父）已经启动");
		
		c.start();
		logger.info("单个子线程启动");
	}
}