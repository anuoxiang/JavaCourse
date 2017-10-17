package ConquerJava.Learn.Lesson03;

import java.lang.Thread.State;
import org.apache.log4j.Logger;
import ConquerJava.Learn.UtilAndHelper;

/**
 * 第三课 Java多线程
 * 
 * 守护进程实验
 * 一组随着主线程结束而自动结束的线程
 * 
 * Create Date   :  Oct 17, 2017
 * 
 * @author       :  Django
 */
public class DaemonTest  {
	// 日志工具
	final static Logger logger = Logger.getLogger(DaemonTest.class);

	public static void main(String[] args) {
		Thread thread = new DaemonTest().new NormalThread();
		Thread daemonThread = new DaemonTest().new DaemonThread();
		//需要手动设定为守护类型线程
		daemonThread.setDaemon(true);
		daemonThread.start();
		thread.start();
		while(thread.getState() != State.TERMINATED) {
			UtilAndHelper.WaitFor(500L);
		}
		logger.info("程序运行结束！");
	}
	
	/**
	 * 普通
	 * 
	 * Create Date   :  Oct 17, 2017
	 * 
	 * @author       :  Django
	 */
	public class NormalThread extends Thread{
		
		public void run() {
			for(int i=0;i<10;i++) {
				logger.info(String.format("[%s] 运行中", Thread.currentThread()));
				UtilAndHelper.WaitFor(500L);
			}
		}
	}

	/**
	 * 守护进程类，永不结束
	 * 
	 * 
	 *  Create Date   :  Oct 17, 2017
	 * 
	 * @author       :  Django
	 */
	public class DaemonThread extends Thread{
		public void run() {
			while(true) {
				UtilAndHelper.WaitFor(1);
				logger.info(String.format("[%s]守护进程运行中……", Thread.currentThread()));
			}
		}
	}

}
