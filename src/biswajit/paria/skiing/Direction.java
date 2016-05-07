package biswajit.paria.skiing;

/*
 * Topup coordinate will be added on current location for any direction changes
 */
public enum Direction {
	
  NORTH(new int[]{-1,0}), EAST(new int[]{0,1}), SOUTH(new int[]{1,0}), WEST(new int[]{0,-1}), MAP(new int[]{1000,1000});
	
  int coordinateChanges[];
 
  Direction(int[] coordinateChanges){
	 this.coordinateChanges=coordinateChanges;
 }
  
  public int[] getTopupCoordinate () { return coordinateChanges; }

}