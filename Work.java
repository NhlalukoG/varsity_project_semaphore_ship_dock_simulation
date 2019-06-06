import java.util.Random;
import java.util.concurrent.Semaphore;
public class Work {
	int time = 6500;
	int state; // 0 = Loading, 1 = offloading
	static Semaphore sem;
	static boolean load,maint;
	public Work(int r) {
		Random rand = new Random();
		state = rand.nextInt(r);
		
		// Only r activities
		// Load/Offload OR repair,rest,etc
		
		if(sem == null){
			sem = new Semaphore(r,true);
		}
		
		try {
			sem.acquire();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sem.release();
		
	}
}
