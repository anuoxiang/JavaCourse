package ConquerJava.Learn.Lesson03;

import org.apache.log4j.Logger;

/**
 * 第三课 Java多线程
 *
 * 生产者和消费者实验
 * 
 * Create Date : Oct 22, 2017
 * 
 * @author : Django
 */
public class ConsumerAndProducerTest {
	// 日志工具
	final static Logger logger = Logger.getLogger(DaemonTest.class);

	public static void main(String[] args) {
		
	}

	/**
	 * 仓库类
	 * 
	 * @author : Django
	 */
	class Warehouse {
		/**
		 * 最大库存
		 */
		public static final int MAX_SIZE = 100;
		/**
		 * 当前库存
		 */
		public int CurrentNumber;

		/**
		 * 设置当前库存
		 * 
		 * @param num
		 *            库存数
		 */
		public Warehouse(int num) {
			this.CurrentNumber = num;
		}

		/**
		 * 生产过程
		 * 
		 * @param Income
		 *            数量
		 */
		public synchronized void product(int Income) {
			while (this.CurrentNumber + Income > MAX_SIZE) {
				logger.warn(String.format("当前库存：%d，即将生产：%d，超过承载库存%d，暂停执行", this.CurrentNumber, Income, MAX_SIZE));
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			this.CurrentNumber += Income;
			logger.info(String.format("生产了：%d，当前库存：%d", Income, this.CurrentNumber));
			// 唤醒别的线程，
			notifyAll();
		}

		/**
		 * 消费过程
		 * 
		 * @param Output
		 *            数量
		 * 
		 * @author : Django
		 */
		public synchronized void consume(int Output) {
			while (this.CurrentNumber - Output < 0) {
				logger.warn(String.format("当前库存：%d，即将消费：%d，库存不足，暂停执行", this.CurrentNumber, Output));
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				this.CurrentNumber -= Output;
				logger.info(String.format("消费了：%d，当前库存：%d", Output, this.CurrentNumber));
				// 唤醒别的线程，
				notifyAll();
			}
		}
	}

	/**
	 * 消费者类
	 * 
	 * @author : Django
	 */
	class Consumer extends Thread {
		private int consumerNumber;
		private Warehouse warehouse;

		public Consumer(Warehouse warehouse, int consumerNumber) {
			this.warehouse = warehouse;
			this.consumerNumber = consumerNumber;
		}

		public void run() {
			this.warehouse.consume(this.consumerNumber);
		}
	}

	/**
	 * 生产者类
	 * 
	 * @author : Django
	 */
	class Procuder extends Thread {
		private int procudeNumber;
		private Warehouse warehouse;

		public Procuder(Warehouse warehouse, int procudeNumber) {
			this.warehouse = warehouse;
			this.procudeNumber = procudeNumber;
		}

		public void run() {
			this.warehouse.product(this.procudeNumber);
		}
	}
}
