package battleship;

public class Cruiser extends Ship{

	public Cruiser() {
		// TODO Auto-generated constructor stub
		super();
		this.length = 3;
	}
	
	@Override
	public String getShipType(){
		return "cruiser";
	}
	

}
