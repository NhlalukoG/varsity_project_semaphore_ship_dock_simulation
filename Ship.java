
public class Ship extends Thread {
	static int ship_num;
	public int ship_id;
	// This will be a queue for when the bay is full
	
	Ship(){
		// This run when a new ship comes
		ship_id = ++ship_num;
	}
	
	@Override
	public void run(){
		// This records a new ship
		display("New ship: " + ship_id);
		
		Loading load = new Loading(this);
		//Maintance maint = new Maintance(this);
	}
	
	public static void display(String s){
		System.out.println(String.format(HelperClass.format,s,"","",""));
	}
}
