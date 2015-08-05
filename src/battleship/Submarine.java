package battleship;

public class Submarine extends Ship{

	public Submarine() {
		// TODO Auto-generated constructor stub
		this.length = 1;
	}
	
	@Override
	public String getShipType(){
		return "submarine";
	}

}
