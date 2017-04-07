import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.LinkedList;


public class MLF
{
	LinkedList<Process> pList = new LinkedList<Process>();
	LinkedList<Integer> totalTimesofP = new LinkedList<Integer>();
	boolean CPUBusy = false;
	boolean justDone = false;
	
	LinkedList<Process> levelOne = new LinkedList<Process>();
	LinkedList<Process> levelTwo = new LinkedList<Process>();
	LinkedList<Process> levelThree = new LinkedList<Process>();
	LinkedList<Process> levelFour = new LinkedList<Process>();
	LinkedList<Process> levelFive = new LinkedList<Process>();

	public MLF()
	{
		
	}
	
	public MLF(LinkedList<Process> pList)
	{
		this.pList = pList;
		levelOne = pList;
		int size =  pList.size();
		for(int i = 0 ; i < size; i++)
		{
			totalTimesofP.add(i);
		}		

	}
	
	public int calculateMLF(int time, Process temp)
	{
		if(temp.getRunTime() == 1)
		{
			for(int i = 0; i < levelOne.size(); i++)
			{
				if(levelOne.get(i).equals(temp))
				{
					levelOne.get(i).setDone(true);
				}
			}
			for(int i = 0; i < levelTwo.size(); i++)
			{
				if(levelTwo.get(i).equals(temp))
				{
					levelTwo.get(i).setDone(true);
				}
			}
			for(int i = 0; i < levelThree.size(); i++)
			{
				if(levelThree.get(i).equals(temp))
				{
					levelThree.get(i).setDone(true);
				}
			}
			for(int i = 0; i < levelFour.size(); i++)
			{
				if(levelFour.get(i).equals(temp))
				{
					levelFour.get(i).setDone(true);
				}
			}
			for(int i = 0; i < levelFive.size(); i++)
			{
				if(levelFive.get(i).equals(temp))
				{
					levelFive.get(i).setDone(true);
				}
			}
			for(int i = 0; i < pList.size(); i++)
			{
				if(pList.get(i).equals(temp))
				{
					pList.get(i).setDone(true);
				}
			}
			
			temp.setStartTime(time);
			temp.setFinishTime(time + temp.getRunTime());
			int slot = temp.getIncomingSlot();
			totalTimesofP.remove(slot);
			totalTimesofP.add(slot, temp.getFinishTime() - temp.getArrivalTime());
			//totalTimesofP.add(temp.getFinishTime() - temp.getArrivalTime());
			justDone = true;
			return time + temp.getRunTime();
		}
		else
		{
			int value = -1;
			justDone = false;
			
			if(temp.isLevel1())
			{
				//temp.setLevel1(false);
				//temp.setLevel2(true);
				//temp.setLevelCount(2);
				
				levelOne.remove(temp);
				//temp.setTimeleftonLevel(2);
				value = temp.getRunTime();
				temp.setRunTime(value - 1);
				temp.setTimeleftonLevel(2);
				temp.setLevel1(false);
				temp.setLevel2(true);
				levelTwo.add(temp);
				return time +1;
			}
			if(temp.isLevel2())
			{
				int place = -1;
				for(int i = 0; i < levelTwo.size(); i++)
				{
					if(levelTwo.get(i) == temp)
					{
						place = i;
					}
				}
				
				int timeleft = temp.getTimeleftonLevel();
				
				if(timeleft == 1)
				{
					levelTwo.remove(place);
					value = temp.getRunTime();
					temp.setRunTime(value - 1);
					
					temp.setTimeleftonLevel(4);
					temp.setLevelCount(3);
					temp.setLevel2(false);
					temp.setLevel3(true);
					levelThree.add(temp);
					return time +1;

				}
				else
				{
					temp.setTimeleftonLevel(timeleft - 1);
					value = temp.getRunTime();
					temp.setRunTime(value - 1);
					return time +1;

				}
			}
			if(temp.isLevel3())
			{				
				int place = -1;
				for(int i = 0; i < levelThree.size(); i++)
				{
					if(levelThree.get(i) == temp)
					{
						place = i;
					}
				}
				
				int timeleft = temp.getTimeleftonLevel();

				if(timeleft == 1)
				{
					levelThree.remove(place);
					value = temp.getRunTime();
					temp.setRunTime(value - 1);
					
					temp.setTimeleftonLevel(8);
					temp.setLevelCount(4);
					temp.setLevel3(false);
					temp.setLevel4(true);
					levelFour.add(temp);
					return time +1;

				}
				else
				{
					value = temp.getRunTime();
					temp.setRunTime(value - 1);
					temp.setTimeleftonLevel(timeleft - 1);
					return time +1;

				}	
			}
			if(temp.isLevel4())
			{
				int timeleft = temp.getTimeleftonLevel();
				int place = -1;
				
				for(int i = 0; i < levelFour.size(); i++)
				{
					if(levelFour.get(i) == temp)
					{
						place = i;
					}
				}
				
				if(timeleft == 1)
				{	
					levelFour.remove(place);
					value = temp.getRunTime();
					temp.setRunTime(value - 1);
					
					temp.setTimeleftonLevel(16);
					temp.setLevelCount(5);
					temp.setLevel4(false);
					temp.setLevel5(true);
					levelFive.add(temp);
					return time +1;

				}
				else
				{
					value = temp.getRunTime();
					temp.setRunTime(value - 1);
					temp.setTimeleftonLevel(timeleft - 1);
					return time +1;

				}
			}
			if(temp.isLevel5())
			{
				int timeleft = 0;
				value = temp.getRunTime();
				temp.setRunTime(value - 1);
				timeleft = temp.getTimeleftonLevel();
				temp.setTimeleftonLevel(timeleft - 1);
				return time+1;
			}
			return time + 1;
		}
	}
	
	public void crunchMLF(PrintWriter outputStream)
	{
		float total=0;
		int size = totalTimesofP.size();
		
		DecimalFormat df = new DecimalFormat("#.##");
		for(int i = 0; i < totalTimesofP.size(); i++)
		{
			total = total + totalTimesofP.get(i);
		}
		
		outputStream.print("" + df.format(total/size) + " ");
		for(int i = 0; i < totalTimesofP.size(); i++)
		{
			outputStream.print(totalTimesofP.get(i) + " ");
		}
		
		outputStream.println(" ");
	}
	
	public void timer()
	{

		Process temp = new Process(0, 200, 0);
		temp.setLevelCount(5);
		boolean foundFirst = false;
		boolean allDone = false;
		int time = 2500;
		int newTime = -1;
		
		for(int i = 0; i < time; i++)
		{
			if(newTime == i)
			{
				CPUBusy = false;
			}
			if(CPUBusy == false)
			{
				if(justDone == true)
				{
					temp.setRunTime(200);
					justDone = false;
				}
				//temp.setRunTime(100);
				
				foundFirst = false;
				
				for(int a = 0; a < levelOne.size(); a++)
				{
						if(levelOne.get(a).getArrivalTime() <= i && levelOne.get(a).isDone == false)
						{
							temp = levelOne.get(a);
							foundFirst = true;
							break;
						}
						
				}
				
				if(temp.getArrivalTime() == 0 && temp.getRunTime() == 200 || foundFirst == false)
				{
					for(int a = 0; a < levelTwo.size(); a++)
					{
							if(levelTwo.get(a).getArrivalTime() <= i && levelTwo.get(a).isDone == false)
							{
								temp = levelTwo.get(a);
								foundFirst = true;
								break;
							}
							
					}
				}
				
				if(temp.getArrivalTime() == 0 && temp.getRunTime() == 200 || foundFirst == false)
				{
					for(int a = 0; a < levelThree.size(); a++)
					{
							if(levelThree.get(a).getArrivalTime() <= i && levelThree.get(a).isDone == false)
							{
								temp = levelThree.get(a);
								foundFirst = true;
								break;
							}
							
					}
				}
				
				if(temp.getArrivalTime() == 0 && temp.getRunTime() == 200 || foundFirst == false)
				{
					for(int a = 0; a < levelFour.size(); a++)
					{
							if(levelFour.get(a).getArrivalTime() <= i && levelFour.get(a).isDone == false)
							{
								temp = levelFour.get(a);
								foundFirst = true;
								break;
							}
							
					}
				}
				
				if(temp.getArrivalTime() == 0 && temp.getRunTime() == 200 || foundFirst == false)
				{
					for(int a = 0; a < levelFive.size(); a++)
					{
							if(levelFive.get(a).getArrivalTime() <= i && levelFive.get(a).isDone == false)
							{
								temp = levelFive.get(a);
								foundFirst = true;
								break;
							}
							
					}
				}
				
				
				for(int b = 0; b < levelOne.size(); b++)
				{
					if(levelOne.get(b).isDone() == true)
					{
						allDone = true;
					}
					else
					{
						allDone = false;
						break;
					}
				}
				if(allDone == true)
				{
					for(int b = 0; b < levelTwo.size(); b++)
					{
						if(levelTwo.get(b).isDone() == true)
						{
							allDone = true;
						}
						else
						{
							allDone = false;
							break;
						}
					}
				}
				if(allDone == true)
				{
					for(int b = 0; b < levelThree.size(); b++)
					{
						if(levelThree.get(b).isDone() == true)
						{
							allDone = true;
						}
						else
						{
							allDone = false;
							break;
						}
					}
				}
				if(allDone == true)
				{
					for(int b = 0; b < levelFour.size(); b++)
					{
						if(levelFour.get(b).isDone() == true)
						{
							allDone = true;
						}
						else
						{
							allDone = false;
							break;
						}
					}
				}
				if(allDone == true)
				{
					for(int b = 0; b < levelFive.size(); b++)
					{
						if(levelFive.get(b).isDone() == true)
						{
							allDone = true;
						}
						else
						{
							allDone = false;
							break;
						}
					}
				}
				
				if(allDone == false && temp.isDone() != true)
				{
					if(temp.getArrivalTime() == 0 && temp.getRunTime() == 200)
					{
						
					}
					else
					{
					newTime = calculateMLF(i, temp);
					CPUBusy = true;
					
					}
					
				
				}
			}
		}
		
	}
	
}
