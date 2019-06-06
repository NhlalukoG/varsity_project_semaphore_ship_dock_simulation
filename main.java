
public class main {

	public static void main(String args[]){

		/*
		 * The Logic of my program
		 *
		 * There can be only five ships threads on loading/offloading dock
		 * There can be only three ships on the maintanance dock
		 *
		 * On the loading/offloading dock only three forklifts available
		 * Each forklift has one of these two states
		 * States 	- Loading
		 *			- Offloading
		 *
		 * On the maintanance dock, only one member attends a ship
		 * a member has these one of four states
		 * States	- Repair
		 *			- Service
		 *			- Pass
		 *			- Rest
		 *
		 *
		 * From the above, we can deduce:
		 *		- Only three ships are being attended at offloading/onloading dock at a time
		 *		- Only one or no ship are being attended, none during resting
		*/
		System.out.println(String.format(HelperClass.format,"Ships","Loading Dock","Maintenance Dock","Jobs"));
		System.out.println(String.format(HelperClass.format,"","","",""));
		for(int i = 0; i < 8;i++){
			Thread newship = new Ship();
			newship.start();
			try {
				Thread.sleep(750);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
