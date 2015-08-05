package battleship;

import java.util.*;

public class Ocean {
	private Ship[][] ships;
	private int shotsFired;
	private int hitCount;
	private int shipsSunk;
	Random random = new Random();
	private boolean[][] shadow;
	private Ship battleship;
	private Ship cruiser1, cruiser2;
	private Ship destroyer1, destroyer2, destroyer3;
	private Ship submarine1, submarine2, submarine3, submarine4;
	private ArrayList<Ship> allShips;
	//private boolean[][] shotLocations;


	public Ocean() {
		// TODO Auto-generated constructor stub
		battleship = new Battleship();
		cruiser1 = new Cruiser();
		cruiser2 = new Cruiser();
		destroyer1 = new Destroyer();
		destroyer2 = new Destroyer();
		destroyer3 = new Destroyer();
		submarine1 = new Submarine();
		submarine2 = new Submarine();
		submarine3 = new Submarine();
		submarine4 = new Submarine();

		allShips = new ArrayList<Ship>();
		allShips.add(battleship);
		allShips.add(cruiser1);
		allShips.add(cruiser2);
		allShips.add(destroyer1);
		allShips.add(destroyer2);
		allShips.add(destroyer3);
		allShips.add(submarine1);
		allShips.add(submarine2);
		allShips.add(submarine3);
		allShips.add(submarine4);


		ships = new Ship[10][10];
		shadow = new boolean[10][10];
		//shotLocations = new boolean[10][10];

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				this.ships[i][j] = new EmptySea();
				this.ships[i][j].setBowRow(i);
				this.ships[i][j].setBowColumn(j);
				this.ships[i][j].setHorizontal(true);
				this.shadow[i][j] = false;
				//this.shotLocations[i][j] = false;
			}
		}
		this.shotsFired = 0;
		this.hitCount = 0;
		this.shipsSunk = 0;

	}

	public void placeAllShipsRandomly() {
		int row;
		int column;
		int trueOrFalse;
		for (Ship ship: allShips){
			row = (int) (Math.random() * 10);
			column = (int) (Math.random() * 10);
			trueOrFalse = (int) (Math.random() * 2);
			boolean horizontal = false;
			if (trueOrFalse == 1) {
				horizontal = true;
			}
			else {
				horizontal = false;
			}

			while (!ship.okToPlaceShipAt(row, column, horizontal, this)) {
				row = (int) (Math.random() * 10);
				column = (int) (Math.random() * 10);
				trueOrFalse = (int) (Math.random() * 2);
				if (trueOrFalse == 1) {
					horizontal = true;
				}
				else {
					horizontal = false;
				}
			}
			ship.placeShipAt(row, column, horizontal, this);

		}
	}

	public boolean isOccupied(int row, int column) {
		if (this.ships [row][column].getShipType().equals("empty")) {
			return false;
		}
		return true;
	}

	public boolean shootAt(int row, int column) {
		int hit = 0;
		int sunkNum = 0;
		if (isOccupied(row, column) && !ships[row][column].isSunk()) {
			this.hitCount += 1;
			hit = 1;
		}
		this.shotsFired += 1;
		//this.shotLocations[row][column] = true;
		this.ships[row][column].shootAt(row, column);
		for (Ship ship: this.allShips) {
			if (ship.isSunk()){
				sunkNum += 1;
			}
		}
		this.shipsSunk = sunkNum;
		if (hit == 1) {
			return true;
		}
		return false;
	}

	public int getShotsFired() {
		return this.shotsFired;
	}

	public int getHitCount() {
		return this.hitCount;
	}

	public int getShipsSunk() {
		return this.shipsSunk;
	}

	public boolean isGameOver() {
		if (this.shipsSunk == 10) {
			return true;
		}
		return false;
	}

	public Ship[][] getShipArray() {
		return this.ships;
	}

	public void print() {
		String s = " ";
		int i;
		int j;
		for (i = -1; i < 10; i++) {
			for (j = -1; j < 10; j++) {
				if (i == -1){				
					if (j > -1){
						s += "  " + j;
					}
				}
				else if (j == -1) {
					s += i + "  ";
				}
				else if (!this.isHit(i, j)) {
					s += "." + "  ";
				}
				else {
					s += ships[i][j].toString() + "  ";
				}
			}
			s += "\n";
		}
		System.out.println(s);
	}


	////////////////////////////////////////////////additional helper functions//////////////////////////
	public boolean[][] getShadow() {
		return this.shadow;
	}

	/**
	 * when put in one ship, shadow all its adjacent sea. Then the okToPrint function can make judgment and forbid ships to place on the shadow.  
	 */
	public void setShadow() {
		for (int i = 0; i < 10 ; i++){
			for (int j = 0; j < 10; j++) {
				if (this.isOccupied(i,j)) {
					for (int k = -1; k < 2; k++) {
						for (int l = -1; l <2; l++ ) {
							if ((i+k>=0) && (i+k<=9) && (j+l>=0) && (j+l <=9)) {
								shadow[i+k][j+l] = true;
							}
						}
					}
				}
			}
		}
	}

	/**
	 * setter for ship class to place ship in the ocean
	 * @param row
	 * @param column
	 * @param ship
	 */
	public void placeShip(int row, int column, Ship ship) {
		this.ships[row][column] = ship;
		//update the shadow(places which don't allow ship to be placed) 
		this.setShadow();
	}

	/**
	 * all ships list getter for testing
	 * @return
	 */
	public ArrayList<Ship> getAllShips() {
		return this.allShips;
	}

	public void printTest() {
		String s = " ";
		int i;
		int j;
		for (i = -1; i < 10; i++) {
			for (j = -1; j < 10; j++) {
				if (i == -1){				
					if (j > -1){
						s += "  " + j;
					}
				}
				else if (j == -1) {
					s += i + "  ";
				}
				else if (!isOccupied(i,j)) {
					s += "." + "  ";
				}
				else {
					s += ships[i][j].toString() + "  ";
				}
			}
			s += "\n";
		}
		System.out.println(s);
	}
	
	public boolean isHit(int row, int column) {
		Ship ship = this.ships[row][column];
		int bowRow = ship.getBowRow();
		int bowColumn = ship.getBowColumn();
		//System.out.println(row + " " + column + " " + ship + " " + bowRow + " " + bowColumn + ship.isHorizontal());
		
		if (ship.getShipType().equals("empty")) {
			return (ship.getHitArray()[0]);
		}
		else if (ship.isHorizontal()) {
			if (ship.getHitArray()[column - bowColumn]) {
				return true;
			}
			return false;
		}
		else {
			if (ship.getHitArray()[row - bowRow]) {
				return true;
			}
			return false;
		}
	}

}

