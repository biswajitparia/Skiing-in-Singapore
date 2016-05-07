package biswajit.paria.skiing;

import java.util.Map;
/*
 * Each elevation has information on it's peak location and height as well as other neighbour's peak elevation details
 */
public class Peak{		
	private Location location;
	private int height;	
	private Peak northNeighbour;
	private Peak eastNeighbour;
	private Peak southNeighbour;
	private Peak westNeighbour;	
	
	public Peak(Location currentLocation, int height){		
		this.location=currentLocation;
		this.height=height;
	}
	
	public void linkNeighbour(Map<String,Peak> peaks){
		northNeighbour = getNeighbour(Direction.NORTH,peaks);
		eastNeighbour = getNeighbour(Direction.EAST,peaks);
		southNeighbour = getNeighbour(Direction.SOUTH,peaks);
		westNeighbour = getNeighbour(Direction.WEST,peaks);
	}

	private Peak getNeighbour(Direction direction, Map<String,Peak> peaks) {
		int currentCoordinate[]=location.getCoordinate();
		int topupCoordinate[]=direction.getTopupCoordinate();
		int mapCoordinate[]=Direction.MAP.getTopupCoordinate();
		
		int updatedRow = currentCoordinate[0]+topupCoordinate[0];
		int updatedColumn = currentCoordinate[1]+topupCoordinate[1];
		
		if(updatedRow<1||updatedRow >mapCoordinate[0] || updatedColumn<1|| updatedColumn> mapCoordinate[1]){
			//System.out.println("No Peak is linked for the coordinate =>"+updatedRow+"-"+updatedColumn );
			return null;
		}else{			
			Peak peak = peaks.get(updatedRow+"-"+updatedColumn);			
			//System.out.println("Neighbour Peak is linked. Coordinate is="+updatedRow+"-"+updatedColumn +"  And Value is"+peak.getHeightValue());
			return peak;
		}
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Peak getNorthNeighbour() {
		return northNeighbour;
	}

	public void setNorthNeighbour(Peak northNeighbour) {
		this.northNeighbour = northNeighbour;
	}

	public Peak getEastNeighbour() {
		return eastNeighbour;
	}

	public void setEastNeighbour(Peak eastNeighbour) {
		this.eastNeighbour = eastNeighbour;
	}

	public Peak getSouthNeighbour() {
		return southNeighbour;
	}

	public void setSouthNeighbour(Peak southNeighbour) {
		this.southNeighbour = southNeighbour;
	}

	public Peak getWestNeighbour() {
		return westNeighbour;
	}

	public void setWestNeighbour(Peak westNeighbour) {
		this.westNeighbour = westNeighbour;
	}
}
