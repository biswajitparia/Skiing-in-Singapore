package biswajit.paria.skiing;

/*
 * Location coordinate are separated by hyphen i.e "34-56"
 */
public class Location{	
	private String location;
	
	public Location(String location){
		this.location=location;		
	}
	
	public int[] getCoordinate(){		
		int coordinate[] = new int[2];		
		String locations[] =location.split("-"); 
		coordinate[0] =new Integer(locations[0]).intValue();
		coordinate[1] =new Integer(locations[1]).intValue();		
		return coordinate;
	}

	public String getLocation() {
		return location;
	}

}
