import java.util.Random;
import java.util.concurrent.Semaphore;
public class Worker {
	int time = 6500;
	int state; // 0 = Loading, 1 = offloading
	boolean resting;
	static Semaphore sem = new Semaphore(1,true);
	int ship_id;
	public Worker(int r,int ship_id) {
		this.ship_id = ship_id;
		Random rand = new Random();
		state = rand.nextInt(r);
		// Only r activities
		// Load/Offload OR repair,rest,etc
		
		try {
			sem.acquire();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void sleep(){
		switch(state){
		case 0:
			// Repair
			display("Worker: Repairing Ship "+ship_id);
			time = 10000;
			break;
		case 1:
			// Service
			display("Worker: Servicing Ship "+ship_id);
			time = 5000;
			break;
		case 2:
			// Pass
			display("Worker: Pass work on Ship"+ship_id);
			time = 0;
			break;
		case 3:
			// Rest
			// Special case
			if(resting){
				resting = false;
				display("Worker done resting");
				while(state == 3){
					Random rand = new Random();
				state = rand.nextInt(4);
				sleep();
				}
			}else{
				resting = true;
				display("Worker resting...");
				time = 5000;
			}
			break;
		default:
			time = 5000;
		}
		try {
			Thread.sleep(time);
			if(resting){
				sleep();
				return;
			}
		} catch (InterruptedException e) {
			display("Worker interrupted");
		}
		display("Woker done on Ship"+ship_id);
		sem.release();
	}
	void display(String s){
		System.out.println(String.format(HelperClass.format,"","","",s));
	}
}
