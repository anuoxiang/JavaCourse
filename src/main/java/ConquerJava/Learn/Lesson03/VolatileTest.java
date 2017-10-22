package ConquerJava.Learn.Lesson03;

import java.util.concurrent.atomic.AtomicLong;

import org.apache.log4j.Logger;

/**
 * 第三课 Java多线程
 * 
 * Volatile测试
 * 
 * Create Date   :  Oct 22, 2017
 * 
 * @author       :  Django
 */
public class VolatileTest {
	// 日志工具
	final static Logger logger = Logger.getLogger(DaemonTest.class);
	public volatile static int count = 0;
	private static AtomicLong atomCount = new AtomicLong(0);
	public static void inc() {

		// 这里延迟1毫秒，使得结果明显
		try {
			Thread.sleep(1);
			
		} catch (InterruptedException e) {
		}

		count++;
		atomCount.getAndIncrement();
		System.out.println(count+":"+atomCount.get());
	}

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
