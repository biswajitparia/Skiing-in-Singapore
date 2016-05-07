package biswajit.paria.skiing;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Skiing {
	class TotalCount{
		private BigInteger count = new BigInteger("0");
		public BigInteger getCount() {
			return count;
		}
		public void setCount(BigInteger val) {
			this.count = count.add(val);
		}		
		public void setCountAsItis(BigInteger count) {
			this.count = count;
		}
	}
	public static void main(String[] args) {
		Instant startTime = Instant.now();		
		new Skiing().startSkiing();		
		Instant endTime = Instant.now();
		Duration timeElapsed = Duration.between(startTime, endTime);
		System.out.println("**** TIME TAKEN==>"+timeElapsed.getSeconds()+" secs");
	}

	private void startSkiing() {
		Map<String, Peak> masterPeakList = new HashMap<>();
		getMatrixMap("C:\\Test\\SkiMap.txt", masterPeakList);
		List<String> masterTraversedSkiRouteLogList = new ArrayList<>();
		System.out.println("**** TOTAl PEAKS==>"+masterPeakList.keySet().size());
			
		TotalCount count = new TotalCount();		
		masterPeakList.keySet().forEach(e -> {
			Peak currentPeak = masterPeakList.get(e.toString());			
			PathGuider path = new PathGuider();
			Map<String, Boolean> currentPeakTraversableMap = new HashMap<>();
			
			while (path.isPathAvailable()) {	
				path.setPathAvailable(false);
				path.setNewPath(false);
				String currentyTraversedPath = traverseSkiRoute(currentPeak, masterPeakList, currentPeakTraversableMap,path);
				//System.out.println("currentyTraversedPath==>"+currentyTraversedPath);
				checkIfLargestPathAndDrop( masterTraversedSkiRouteLogList, currentyTraversedPath);				
				count.setCount(new BigInteger("1"));
			}			
		});	
		//TRAVERSED
		System.out.println("**** TOTAL TRAVERSED PATHs==>"+count.getCount().intValue());
		TotalCount countList = new TotalCount();
		TotalCount steap = new TotalCount();
		
		masterTraversedSkiRouteLogList.forEach(e -> {
		String path = masterTraversedSkiRouteLogList.get(countList.getCount().intValue());
		System.out.println("**** LONGER TRAVERSED PEAK PATH==>" +path );
		String splits1[]=path.split("#");
		String splits2[]=splits1[1].split("-");
		int highestPeak=new Integer(splits2[0]);
		int lowestPeak=new Integer(splits2[splits2.length-1]);
		int currentSteap = highestPeak-lowestPeak;
		
		if(currentSteap>steap.getCount().intValue()){
			steap.setCountAsItis(new BigInteger(String.valueOf(currentSteap)));
		}		
		countList.setCount(new BigInteger("1"));
		});
		
		System.out.println("**** LONGEST TRAVERSED PATH WITH LENGTH==>" +masterTraversedSkiRouteLogList.get(0).split("#")[0]+", DROP==>"+steap.getCount().intValue() );
	}

	private void checkIfLargestPathAndDrop(List<String> masterTraversedSkiRouteLogList,String currentyTraversedPath) {
		String splits[]=currentyTraversedPath.split("-");		
		int length=splits.length;
		String key=String.valueOf(length)+"#"+currentyTraversedPath;
		if(masterTraversedSkiRouteLogList.isEmpty()){
			masterTraversedSkiRouteLogList.add(key);
		}
		List<String> filtered = masterTraversedSkiRouteLogList.stream()
                .filter(e -> new Integer(e.split("#")[0]).intValue()>length)
                .collect(Collectors.toList());
		
		if(filtered.isEmpty()){
			filtered = masterTraversedSkiRouteLogList.stream()
	                .filter(e -> new Integer(e.split("#")[0]).intValue()==length)
	                .collect(Collectors.toList());
			if(filtered.isEmpty()){
				masterTraversedSkiRouteLogList.clear();
				masterTraversedSkiRouteLogList.add(key);
			}else{
				masterTraversedSkiRouteLogList.add(key);
			}
		}
	}

	private String traverseSkiRoute(Peak currentPeak, Map<String, Peak> masterPeakList, Map<String, Boolean> currentPeakTraversableMap , PathGuider path) {
		currentPeak.linkNeighbour(masterPeakList);
		
		Peak northNeighbour = currentPeak.getNorthNeighbour();
		Peak eastNeighbour = currentPeak.getEastNeighbour();
		Peak southNeighbour = currentPeak.getSouthNeighbour();
		Peak westNeighbour = currentPeak.getWestNeighbour();
		Peak nextPeak = null;	
		String traversedPath="";
		
		if(!currentPeakTraversableMap.containsKey(currentPeak.getLocation().getLocation())){
			path.setNewPath(true);
		}
		
		if(path.isNewPath()){
			if (northNeighbour != null && currentPeakTraversableMap.containsKey(northNeighbour.getLocation().getLocation())){
				currentPeakTraversableMap.put(northNeighbour.getLocation().getLocation(), new Boolean(false));
			}
			if (eastNeighbour != null && currentPeakTraversableMap.containsKey(eastNeighbour.getLocation().getLocation())){
				currentPeakTraversableMap.put(eastNeighbour.getLocation().getLocation(), new Boolean(false));
			}
			if (southNeighbour != null && currentPeakTraversableMap.containsKey(southNeighbour.getLocation().getLocation())){
				currentPeakTraversableMap.put(southNeighbour.getLocation().getLocation(), new Boolean(false));
			}
			if (westNeighbour != null && currentPeakTraversableMap.containsKey(westNeighbour.getLocation().getLocation())){
				currentPeakTraversableMap.put(westNeighbour.getLocation().getLocation(), new Boolean(false));
			}			
		}
		
	
		if (northNeighbour != null && currentPeak.getHeight() > northNeighbour.getHeight() &&(!currentPeakTraversableMap.containsKey(northNeighbour.getLocation().getLocation()) ||(currentPeakTraversableMap.containsKey(northNeighbour.getLocation().getLocation())&&!currentPeakTraversableMap.get(northNeighbour.getLocation().getLocation())))) {
			nextPeak=northNeighbour;					
		}else if (eastNeighbour != null && currentPeak.getHeight() > eastNeighbour.getHeight() &&(!currentPeakTraversableMap.containsKey(eastNeighbour.getLocation().getLocation()) ||(currentPeakTraversableMap.containsKey(eastNeighbour.getLocation().getLocation())&&!currentPeakTraversableMap.get(eastNeighbour.getLocation().getLocation())))) {
			nextPeak=eastNeighbour;				
		}else if (southNeighbour != null && currentPeak.getHeight() > southNeighbour.getHeight() &&(!currentPeakTraversableMap.containsKey(southNeighbour.getLocation().getLocation()) ||(currentPeakTraversableMap.containsKey(southNeighbour.getLocation().getLocation())&&!currentPeakTraversableMap.get(southNeighbour.getLocation().getLocation())))) {
			nextPeak=southNeighbour;						
		}else if (westNeighbour != null && currentPeak.getHeight() > westNeighbour.getHeight() &&(!currentPeakTraversableMap.containsKey(westNeighbour.getLocation().getLocation()) ||(currentPeakTraversableMap.containsKey(westNeighbour.getLocation().getLocation())&&!currentPeakTraversableMap.get(westNeighbour.getLocation().getLocation())))) {
			nextPeak=westNeighbour;				
		}
		
		int totalTraversingdirection = 0;
		
		if (northNeighbour != null && currentPeak.getHeight() > northNeighbour.getHeight() &&(!currentPeakTraversableMap.containsKey(northNeighbour.getLocation().getLocation()) ||(currentPeakTraversableMap.containsKey(northNeighbour.getLocation().getLocation())&&!currentPeakTraversableMap.get(northNeighbour.getLocation().getLocation())))) {
			totalTraversingdirection++;					
		}
		if (eastNeighbour != null && currentPeak.getHeight() > eastNeighbour.getHeight() &&(!currentPeakTraversableMap.containsKey(eastNeighbour.getLocation().getLocation()) ||(currentPeakTraversableMap.containsKey(eastNeighbour.getLocation().getLocation())&&!currentPeakTraversableMap.get(eastNeighbour.getLocation().getLocation())))) {
			totalTraversingdirection++;				
		}
		if (southNeighbour != null && currentPeak.getHeight() > southNeighbour.getHeight() &&(!currentPeakTraversableMap.containsKey(southNeighbour.getLocation().getLocation()) ||(currentPeakTraversableMap.containsKey(southNeighbour.getLocation().getLocation())&&!currentPeakTraversableMap.get(southNeighbour.getLocation().getLocation())))) {
			totalTraversingdirection++;							
		}
		if (westNeighbour != null && currentPeak.getHeight() > westNeighbour.getHeight() &&(!currentPeakTraversableMap.containsKey(westNeighbour.getLocation().getLocation()) ||(currentPeakTraversableMap.containsKey(westNeighbour.getLocation().getLocation())&&!currentPeakTraversableMap.get(westNeighbour.getLocation().getLocation())))) {
			totalTraversingdirection++;				
		}
		
		if (totalTraversingdirection >1){		   
			path.setPathAvailable(true);
		}
			
		if (nextPeak!=null ){			
			currentPeakTraversableMap.put(currentPeak.getLocation().getLocation(), new Boolean(false));
			traversedPath = currentPeak.getHeight()+"-"+traverseSkiRoute(nextPeak, masterPeakList, currentPeakTraversableMap, path);
		}else{
			traversedPath += currentPeak.getHeight();
			currentPeakTraversableMap.put(currentPeak.getLocation().getLocation(), new Boolean(true));
		}
		return traversedPath;
	}

	private void getMatrixMap(String filePath, Map<String, Peak> masterPeakList) {
		AtomicInteger row = new AtomicInteger(0);
		try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
			stream.forEach(e -> {
				String splits[] = e.trim().split(" ");
				AtomicInteger column = new AtomicInteger(1);
				int r = row.getAndIncrement();
				for (String height : splits) {
					if (r != 0) {
						String key = r + "-" + column.getAndIncrement();
						Peak peak = new Peak(new Location(key), new Integer(height));
						masterPeakList.put(key, peak);
					}
				}
			});

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}


