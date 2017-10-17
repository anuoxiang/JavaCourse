package ConquerJava.Learn.Lesson03;

import org.apache.log4j.Logger;

/**
 * 第三课 Java多线程 线程同步执行实验 Thread 继承类
 * 
 * @author Django
 * @date 2017年10月15日
 */
public class ThreadClass extends Thread {
	// 日志工具
	final static Logger logger = Logger.getLogger(ThreadClass.class);

	/**
	 * 相比较 Runnable 接口不同的调用方法，直接集成Thread来编写多线程可以更方便；
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Thread t1 = new ThreadClass("张三", 9);
		Thread t2 = new ThreadClass("李四", 9999);

		t1.start();
		t2.start();
	}

	/**
	 * 调用父类的构造函数方法：线程名，仅作标记，可以重名
	 * 
	 * @param name
	 *            线程名称
	 */
	public ThreadClass(String name, int step) {
		super(name);
		this.step = step;
	}

	private int step = 10;

	// 线程执行代码
	public void run() {
		for (int i = 0; i < 100; i++) {
			// 让CPU缓一缓（让步），但是只有一次有效，必须保持执行，才能让吓成执行的优先级慢下来；
			if (i >= step) {
				Thread.yield();
				logger.info(String.format("%s 让步了", this.getName()));
			}

			logger.info(String.format("%s: count to:%d", this.getName(), i));
		}
	}
}
