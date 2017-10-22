package ConquerJava.Learn.Lesson03;

import java.util.concurrent.atomic.AtomicLong;

import org.apache.log4j.Logger;

import ConquerJava.Learn.UtilAndHelper;

/**
 * 第三课 Java多线程
 * 
 * Volatile测试
 * 
 * Create Date : Oct 22, 2017
 * 
 * @author : Django
 */
public class VolatileTest {
	// 日志工具
	final static Logger logger = Logger.getLogger(VolatileTest.class);
	/**
	 * 对比测试用，多线程共享变量
	 */
	public volatile static int count = 0;
	/**
	 * 原子长整形
	 */
	private static AtomicLong atomCount = new AtomicLong(0);

	public static void inc() {
		UtilAndHelper.WaitFor(1L);
		//解读：由于可能2个线程同时从count变量中取值，并且+1，
		//所以，明明执行两次，可能数值并没有变化，
		//甚至有可能取值之后的线程进入等待状态，后发的线程先执行，最后的结果反而是减少了；
		count++;
		//原子级，从取值到增加，到保存；不可分割；		
		atomCount.getAndIncrement();
		logger.info(String.format("%d:%d", count,atomCount.get()));
	}

	public static void main(String[] args) {		
		for (int i = 0; i < 1000; i++) {
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {					
					VolatileTest.inc();
				}
			});
			t.start();
		}
		UtilAndHelper.WaitFor(3);
		//最终结果 atomCount >= count
		logger.info(String.format("运行结果:count=%d",  VolatileTest.count));
		logger.info(String.format("运行结果:atomCount=%d",  VolatileTest.atomCount.get()));
		
	}

}
