import java.util.Random;
import java.util.concurrent.Semaphore;
public class ForkLift{
	int time = 6500;
	int state; // 0 = Loading, 1 = offloading
	static Semaphore sem = new Semaphore(3,true);
	static int lift = 0;
	int ship_id;
	int fork_num;
	public ForkLift(int r,int ship_id) {
		Random rand = new Random();
		this.ship_id = ship_id;
		state = rand.nextInt(r);
		lift++;
		fork_num = lift;
	}
	public void start(){
		try {
			sem.acquire();
			display("ForkLift : "+fork_num+" Working on Ship "+ship_id);
			sleep();
			lift--;
			sem.release();
		} catch (InterruptedException e) {
			display("ForkLift : "+fork_num+" Lock interrupted");
		}
	}
	public void sleep(){
		switch(state){
		case 0:
			// Loading
			display("ForkLift : "+fork_num+" loading....");
			time = 10000;
			break;
		case 1:
			// OffLoading
			display("ForkLift : "+fork_num+" Offloading....");
			time = 5000;
			break;
		default:
			display("ForkLift : "+fork_num+" Operation unknown");
			time = 5000;
		}
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			display("ForkLift : "+fork_num+" Operation disturbed");
		}
		display("ForkLift : "+fork_num+" done....");
	}
	public void display(String s){
		System.out.println(String.format(HelperClass.format, "","","",s));
	}
}
