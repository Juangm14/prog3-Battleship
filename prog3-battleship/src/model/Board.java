package model;

public class Board {

	public char HIT_SYMBOL;
	public char WATER_SYMBOL;
	public char NOTSEEN_SYMBOL;
	public int MAX_BOARD_SIZE;
	public int MIN_BOARD_SIZE;
	
	private int size;
	private int numCrafts;
	private int destroyedCrafts;
	
	public Board(int size) {
		
	}
	
	public int getSize() {
		
	}
	
	public boolean checkCoordinate(Coordinate c) {
		
	}
	
	public boolean add(Ship ship, Coordinate position) {
		
	}
	
	public Ship getShip(Coordinate c) {
		
	}
	
	public boolean isSeen(Coordinate c) {
		
	}
	
	public CellStatus hit(Coordinate c) {
		
	}
	
	public boolean areAllCraftsDestoryed() {
		
	}
	
	public set<Coordinate> getNeighborhood(Ship ship, Coordinate position){
		
	}
	
	public set<Coordinate> getNeighborhood(Ship ship) {
		
	}
	
	public String show(boolean unveil) {
		
	}
	
	public String toString(){
		
	}
}
