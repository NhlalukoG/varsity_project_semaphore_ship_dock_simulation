import java.util.concurrent.Semaphore;


public class Maintance {
	int ship_id;
	static Semaphore sem = new Semaphore(2,true);
	Maintance(Loading loading){
		ship_id = loading.ship_id;
		try {
			display("Ship "+ship_id+" waiting for maintenance...");
			sem.acquire();
			Worker work = new Worker(4,ship_id);
			//Thread.sleep(work.time);
			work.sleep();
		} catch (InterruptedException e) {
			
			System.out.println(e);
		}
		display("Ship "+ship_id+" maintenance complete");
		sem.release();
	}
	public void display(String s){
		System.out.println(String.format(HelperClass.format,"","",s,""));
	}
}
