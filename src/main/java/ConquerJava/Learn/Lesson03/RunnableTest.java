package ConquerJava.Learn.Lesson03;

import org.apache.log4j.Logger;

import ConquerJava.Learn.UtilAndHelper;

/**
 * 第三课 Java多线程 
 * 
 * Runnable 接口扩展类测试
 * 
 * 线程冲突、资源同步锁实验代码
 * 
 * @author Django
 * @date 2017年10月15日
 */
public class RunnableTest implements Runnable {
	// 日志工具
	final static Logger logger = Logger.getLogger(RunnableTest.class);

	public static void main(String[] args) throws InterruptedException /* thread.join必须要用的中断异常 */
	{
		logger.info("RunnableClass Started.");

		RunnableTest running1 = new RunnableTest("Jack");
		//RunnableTest running2 = new RunnableTest("Rose");
		Thread t1 = new Thread(running1);
		//Thread t2 = new Thread(running2);
		Thread t11 = new Thread(running1);

		// 同时启动，观察两线程的交错输出（抢占System.out资源）；
		t1.start();
		// join 方法在这里时：t1执行完成后 t2才会被执行
		//t1.join();
		//t2.start();
		// join 方法在这里时：t1和t2 就会交替执行
		// t1.join();
		// t1 与 t11 同时对一个对象进行多线程操作
		// 如果没有资源锁，则会发生资源冲突，输出结果中会有漏执行
		t11.start();
	}

	private String name;
	private int x = 100;

	public RunnableTest(String name) {
		this.name = name;
	}

	// 以Thread方法启动（ start方法）时，调用此过程
	public String getName() {
		return name;
	}

	//同步锁也可以作为一个方法修饰符放在方法声明中
	//如果是静态方法，则放在static关键词后
	public /*synchronized*/ int doSomething(int y) {
		// 不加锁
		// 当多个线程同时执行 如果没有资源锁，则会发生资源冲突，输出结果中会有漏执行
		// return x -= y;
		
		// 加锁
		// 无论多少个线程同时执行，均不会出现资源冲突；
		// 注意！return x 必须在资源锁内，否则输出还是会出现问题
		//（虽然资源是不会再有因抢占造成的异常）；
		synchronized (this) {
			x-=y;
			return x;
		}
	}

	public void setName(String name) {
		this.name = name;
	}

	public void run() {
		for (int i = 0; i < 10; i++) {
			// 要用sleep必须使用现将捕获
			UtilAndHelper.WaitFor(10L);
			//logger.info(String.format("%s: count to:%d", this.name, i));
			logger.info(String.format("%s: done :%d", this.name, this.doSomething(10)));
		}
	}
}
