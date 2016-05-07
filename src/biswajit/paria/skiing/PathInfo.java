package biswajit.paria.skiing;

import java.math.BigInteger;

public class PathInfo implements Comparable<PathInfo> {
	private String pathRoute;	
	private BigInteger pathRouteInt;	
	private int highestPeak;
	private int lowestPeak;
	private int length;
	private int steap;
	
	public PathInfo(String pathRoute){
		this.pathRoute=pathRoute;
		calculateOtheParameter(pathRoute);
	}
	private void calculateOtheParameter(String pathRoute) {
		String splits[]=pathRoute.split("-");
		this.length=splits.length;
		this.highestPeak=new Integer(splits[0]);
		this.lowestPeak=new Integer(splits[splits.length-1]);
		this.steap=highestPeak-lowestPeak;
		this.pathRouteInt = new BigInteger(pathRoute.replaceAll("-", ""));
	}
	
	@Override
	public int compareTo(PathInfo path) {
			return  new BigInteger(path.getPathRoute().replaceAll("-", "")).compareTo(this.pathRouteInt);
	}
	public String getPathRoute() {
		return pathRoute;
	}
	public void setPathRoute(String pathRoute) {
		this.pathRoute = pathRoute;
	}
	public BigInteger getPathRouteInt() {
		return pathRouteInt;
	}
	public void setPathRouteInt(BigInteger pathRouteInt) {
		this.pathRouteInt = pathRouteInt;
	}
	public int getHighestPeak() {
		return highestPeak;
	}
	public void setHighestPeak(int highestPeak) {
		this.highestPeak = highestPeak;
	}
	public int getLowestPeak() {
		return lowestPeak;
	}
	public void setLowestPeak(int lowestPeak) {
		this.lowestPeak = lowestPeak;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getSteap() {
		return steap;
	}
	public void setSteap(int steap) {
		this.steap = steap;
	}	
}
