package ConquerJava.Learn.Lesson03;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import ConquerJava.Learn.UtilAndHelper;

/**
 * 第三课 Java多线程
 * 
 * 线程池
 * 
 * @author Django
 * @date 2017年10月22日
 */
public class ThreadPoolTest {
	// 日志工具
	final static Logger logger = Logger.getLogger(ThreadPoolTest.class);

	public static void main(String[] args) {

		//单线程执行
		//ExecutorService pool = Executors.newSingleThreadExecutor();
		//根据启动线程改变池大小
		//ExecutorService pool = Executors.newCachedThreadPool();
		//固定尺寸线程池
		//ExecutorService pool = Executors.newFixedThreadPool(2);
		//定时延迟线程池
		//ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);
		
		BlockingQueue<Runnable> bqueue = new ArrayBlockingQueue<Runnable>(20); 
        //创建一个单线程执行程序，它可安排在给定延迟后运行命令或者定期地执行。 
		//内核池有2个线程，最大3线程，等待2毫秒A
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2,3,51,TimeUnit.MILLISECONDS,bqueue); 
        /*
         * 这个部分尚未理解透，需要再深入了解； 
         */
        
		List<Thread> queue = new ArrayList<Thread>();
		for (int i = 0; i < 100; i++) {
			queue.add(new ThreadPoolTest().new SubThread());
			//通过线程池控制线程的执行
			pool.execute(queue.get(i));
			//pool.schedule(queue.get(i), 45, TimeUnit.MILLISECONDS);
		}
		pool.shutdown();
		logger.info("执行完成");
	}

	class SubThread extends Thread {
		@Override
		public void run() {

			logger.info(String.format("%s 执行中", Thread.currentThread().getName()));
			UtilAndHelper.WaitFor(50L);
		}
	}

}
