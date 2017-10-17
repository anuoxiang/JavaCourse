package ConquerJava.Learn.Lesson03;

import org.apache.log4j.Logger;

import ConquerJava.Learn.UtilAndHelper;

public class Calculator extends Thread {
	// 日志工具
	final static Logger logger = Logger.getLogger(Calculator.class);
	
	int result;

	public void run() {
		// 注意，notifyAll() 也必须加入在同步锁内
		UtilAndHelper.WaitFor(100L);
		synchronized (this) {
			for (int i = 0; i < 101; i++) {
				result += i;
				UtilAndHelper.WaitFor(10L);
			}
			// 唤醒所有被阻塞的线程
			logger.info("执行完成！");
			notifyAll();
		}
	}
}
