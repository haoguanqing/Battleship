package battleship;

public class Battleship extends Ship{

	public Battleship() {
		// TODO Auto-generated constructor stub
		super();
		this.length = 4;
	}
	
	@Override
	public String getShipType(){
		return "battleship";
	}
	

}
