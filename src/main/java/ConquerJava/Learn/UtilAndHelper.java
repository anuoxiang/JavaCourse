package ConquerJava.Learn;

/**
 * 工具类
 *  
 * @author       :  Django
 */
public class UtilAndHelper {
	/**
	 * 延迟
	 * @param millisec  毫秒
	 * Create date	: Oct 17, 2017 
	 * 
	 * @author		: Django
	 */
	public static void WaitFor(Long millisec)  {
		try {
			Thread.sleep(millisec);
		} catch (InterruptedException e) {

		}
	}
	
	/**
	 * 延迟
	 * 
	 * @param sec  秒数
	 * Create date	: Oct 17, 2017 
	 * 
	 * @author		: Django
	 */
	public static void WaitFor(int sec) {
		WaitFor(new Long(sec)*1000);
	}
}
