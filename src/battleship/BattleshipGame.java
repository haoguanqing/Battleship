package battleship;

import java.util.ArrayList;
import java.util.Scanner;

public class BattleshipGame {
	private Ocean ocean;
	private boolean[][] availableSpot;
	private Scanner sc;

	public BattleshipGame() {
		// define a new ocean and a new 2D array to store available coordinates
		ocean = new Ocean();
		availableSpot = new boolean[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++){
				availableSpot[i][j] = true;
			}
		}
	}

	/**
	 * prints the game menu and info
	 * @param select
	 */
	public void print(int select){
		String info;
		switch (select) {
		case 1:  info = "Welcome to the World of Battleship created by Minquan & Guanqing!";
		break;
		case 2:  info = "Enter coordinates to fire: ";
		break;
		case 3:  info = "Shots fired: "+ocean.getShotsFired()+", Ships sunk: "+ocean.getShipsSunk();
		break;
		case 4:  info = "Congratulations! You win!";
		break;
		case 99: info = "Invalid input. Please re-enter:";
		break;
		case 100: info = "--------------------------------------------";
		break;
		case 101: info = "\n============================================";
		break;
		default: info = "Error selection";
		break;
		}
		System.out.println(info);
	}

	/**
	 * check if the input is valid
	 * @param input
	 * @return boolean
	 */
	public boolean checkValidInput(String input){
		ArrayList<String> numList = new ArrayList<String>();
		for (int i=0;i<10;i++){
			numList.add(""+i);
		}
		String[] coordinates = input.split(" ");
		//returns false if there are not 2 strings
		if (coordinates.length!=2){
			return false;
		}
		//returns false if any of the strings is not a single digit number
		for (String str: coordinates){
			if (numList.contains(str)==false){
				return false;
			}
		}
		//returns false if the coordinates have already been shot at
		int row = Integer.parseInt(coordinates[0]);
		int column = Integer.parseInt(coordinates[1]);
		if (this.availableSpot[row][column]==false){
			return false;
		}
		
		return true;
	}
	
	/**
	 * get the coordinates to shoot at from the String input
	 * @param input
	 * @return int[] coordinates
	 */
	public int[] getCoordinates(String input){
		int[] coordinates = new int[2];
		String[] strList = input.split(" ");
		int row = Integer.parseInt(strList[0]);
		int column = Integer.parseInt(strList[1]);
		coordinates[0] = row;
		coordinates[1] = column;
		return coordinates;
	}

	/**
	 * play the battleship game
	 */
	public void play(){
		print(101);
		print(1);
		ocean.placeAllShipsRandomly();
		boolean isGameOver = ocean.isGameOver();
		sc = new Scanner(System.in);
		
		//print the ocean and start the game
		ocean.print();
		print(3);
		while (!isGameOver){
			print(2);
			String input = sc.nextLine();
			
			//check if input is valid
			while (!checkValidInput(input)){
				print(99);
				input = sc.nextLine();
			}
			
			//get coordinates and fire
			int[] coordinates = getCoordinates(input);
			int row = coordinates[0];
			int column = coordinates[1];
			ocean.shootAt(row, column);
			availableSpot[row][column] = false;
			isGameOver = ocean.isGameOver();
			ocean.print();
			print(3);
			print(100);
		}
		//print info saying you win
		print(4);
	}

	public static void main(String[] args) {
		
		BattleshipGame battleshipGame = new BattleshipGame();
		battleshipGame.play();
		System.out.println("Continue? y/n");
		Scanner sc = new Scanner(System.in);
		String isPlay = sc.next();
		while (isPlay.equals("y")){
			battleshipGame = new BattleshipGame();
			battleshipGame.play();
			System.out.println("Continue? y/n");
			isPlay = sc.next();
		}
		sc.close();
	}

}
