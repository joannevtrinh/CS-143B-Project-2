import java.util.*;

public class Process 
{
	int finishTime;
	int startTime;
	int arrivalTime;
	int totalTime;
	int runTime; //Time it needs to run on the CPU
	boolean isDone = false;
	boolean level1 = true;	// 1
	boolean level2 = false;	// 2
	boolean level3 = false;	// 4
	boolean level4 = false;	// 8
	boolean level5 = false;	// 16
	int levelCount = 1;
	int timeleftonLevel = 1;
	int incomingSlot;
	
	
	
	public int getIncomingSlot() {
		return incomingSlot;
	}

	public void setIncomingSlot(int incomingSlot) {
		this.incomingSlot = incomingSlot;
	}

	public int getTimeleftonLevel() {
		return timeleftonLevel;
	}

	public void setTimeleftonLevel(int timeleftonLevel) {
		this.timeleftonLevel = timeleftonLevel;
	}

	public boolean isLevel1() 
	{
		return level1;
	}

	public void setLevel1(boolean level1) 
	{
		this.level1 = level1;
	}

	public boolean isLevel2() {
		return level2;
	}

	public void setLevel2(boolean level2) {
		this.level2 = level2;
	}

	public boolean isLevel3() {
		return level3;
	}

	public void setLevel3(boolean level3) {
		this.level3 = level3;
	}

	public boolean isLevel4() {
		return level4;
	}

	public void setLevel4(boolean level4) {
		this.level4 = level4;
	}

	public boolean isLevel5() {
		return level5;
	}

	public void setLevel5(boolean level5) {
		this.level5 = level5;
	}

	public int getLevelCount() {
		return levelCount;
	}

	public void setLevelCount(int levelCount) {
		this.levelCount = levelCount;
	}

	public Process()
	{
		
	}
	
	public Process(int arrivalTime, int runTime, int incomingSlot)
	{
		this.arrivalTime = arrivalTime;
		this.runTime = runTime;
		this.incomingSlot = incomingSlot;
	}
	
	
	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}

	public int getRunTime() {
		return runTime;
	}

	public void setRunTime(int runTime) {
		this.runTime = runTime;
	}

	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}

	public void setTotalTime()
	{
		totalTime = finishTime - arrivalTime;
	}
	
	public int getTotalTime()
	{
		return totalTime;
	}
	
	public int getFinishTime()
	{
		return finishTime;
	}
	public void setFinishTime(int finishTime)
	{
		this.finishTime = finishTime;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	
}
