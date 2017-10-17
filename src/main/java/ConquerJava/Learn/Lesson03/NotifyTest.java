package ConquerJava.Learn.Lesson03;

import org.apache.log4j.Logger;

import ConquerJava.Learn.UtilAndHelper;

/**
 * 第三课 Java多线程
 * 
 * 线程状态变化，交互实验
 * 
 * @Create Date : Oct 17, 2017
 * 
 * @author : Django
 */
public class NotifyTest {

	// 日志工具
	final static Logger logger = Logger.getLogger(NotifyTest.class);

	public static void main(String[] args) {
		NotifyTest.ThreadB b = new NotifyTest().new ThreadB();

		logger.info(String.format("线程已经被创建，尚未启动【%s】", b.getState()));

		// wait() 和 notify() 成对使用
		// wait 起足阻塞当前线程作用
		// notify 释放被wait阻塞对线程
		synchronized (b) {
			try {
				b.start();
				logger.info(String.format("等待线程完成计算，进入阻塞状态……【%s】", b.getState()));
				b.wait();
				logger.info(String.format("线程完成计算【%s】", b.getState()));
			} catch (InterruptedException e) {
				e.printStackTrace();
				logger.warn(String.format("异常！%s", e.getMessage()));
			}
			logger.info(String.format("b对象计算的总和是：%d，线程状态是：【%s】", b.total, b.getState()));
		}

	}

	public class ThreadB extends Thread {
		int total;

		public void run() {
			logger.info(String.format("线程内部：进入运行……【%s】", this.getState()));
			synchronized (this) {
				for (int i = 0; i < 101; i++) {
					total += i;
					UtilAndHelper.WaitFor(10L);
				}
				logger.info(String.format("线程内部：实际逻辑完成，3秒后唤醒……【%s】", this.getState()));
				UtilAndHelper.WaitFor(3);
				// 唤醒被阻塞对线程
				notify();
			}
		}
	}
}
