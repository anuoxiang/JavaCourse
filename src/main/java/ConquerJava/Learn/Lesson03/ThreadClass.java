package ConquerJava.Learn.Lesson03;

import org.apache.log4j.Logger;

/**
 * 第三课 Java多线程
 * Thread 继承类
 * @author 		Django
 * @date		2017年10月15日
 */
public class ThreadClass extends Thread {
	//日志工具
	final static Logger logger = Logger.getLogger(ThreadClass.class);
	/**
	 * 相比较 Runnable 接口不同的调用方法，直接集成Thread来编写多线程可以更方便；
	 * @param args
	 */
	public static void main(String[] args) {
		Thread t1 = new ThreadClass("张三");
		Thread t2 = new ThreadClass("李四");
		
		t1.start();
		t2.start();
	}

	/**
	 * 调用父类的构造函数方法：线程名，仅作标记，可以重名
	 * @param name	线程名称
	 */
	public ThreadClass(String name) {
		super(name);
	}
	
	//线程执行代码
	public void run() {
		for (int i=0;i<100;i++) {
			//每“疯狂”执行10次，让CPU缓一缓，观察输出；
			if (i % 10 == 0)
				Thread.yield();
			logger.info(String.format("%s: count to:%d",this.getName(),i));
		}
	}
}
