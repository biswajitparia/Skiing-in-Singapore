package biswajit.paria.skiing;

public class PathGuider {
	private boolean pathAvailable = true;	
	private boolean newPath = false;
	private boolean hightestKey=true;
	
	public boolean isPathAvailable() {
		return pathAvailable;
	}
	public void setPathAvailable(boolean pathAvailable) {
		this.pathAvailable = pathAvailable;
	}
	public boolean isNewPath() {
		return newPath;
	}
	public void setNewPath(boolean newPath) {
		this.newPath = newPath;
	}
	public boolean isHightestKey() {
		return hightestKey;
	}
	public void setHightestKey(boolean hightestKey) {
		this.hightestKey = hightestKey;
	}

	
}