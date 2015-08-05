package battleship;

public class Destroyer extends Ship{

	public Destroyer() {
		// TODO Auto-generated constructor stub
		this.length = 2;
	}
	
	@Override
	public String getShipType(){
		return "destroyer";
	}

}
