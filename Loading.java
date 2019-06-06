import java.util.concurrent.Semaphore;
public class Loading {
	int ship_id;
	static Semaphore sem = new Semaphore(3,true);
	static int ondock;
	Loading(Ship ship){
		ship_id = ship.ship_id;
		try {
			display("Ship "+ship_id+" requesting entrance to loading dock...");
			sem.acquire();
			ondock++;
			display("Ship "+ship_id+" Entering Load/Offload dock");
			Ship.display(Loading.ondock+" ships on dock");
			ForkLift work = new ForkLift(2,ship_id);
			if(work.state == 0){
				display("Ship "+ship_id+" is empty, coming to load");
				display("Ship "+ship_id+" Loading..............");
				work.start();
				work.sleep();
			}else{
				display("Ship "+ship_id+" has cargo, coming to offload");
				display("Ship "+ship_id+" OffLoading..............");
				work.start();
				work.sleep();
			}
		} catch (InterruptedException e) {
			System.out.println(e);
		}
		sem.release();
		ondock--;
		display("Ship "+ship_id+" Done On Load Dock");
		Ship.display(Loading.ondock+" ships on dock");
		// Once done Loading, go to maintenance
		Maintance maint = new Maintance(this);
	}
	public void display(String s){
		System.out.println(String.format(HelperClass.format,"",s,"",""));
	}
	
}
