import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.*;

public class SJF 
{
	LinkedList<Process> pList = new LinkedList<Process>();
	LinkedList<Integer> totalTimesofP = new LinkedList<Integer>();
	boolean CPUBusy = false;

	public SJF()
	{
		
	}
	
	public SJF(LinkedList<Process> pList)
	{
		this.pList = pList;
		int size =  pList.size();
		for(int i = 0 ; i < size; i++)
		{
			totalTimesofP.add(i);
		}		

	}
	
	public int calculateSJF(int time, Process temp)
	{
		for(int i = 0; i < pList.size(); i++)
		{
			if(pList.get(i).equals(temp))
			{
				pList.get(i).setDone(true);
			}
		}
		//ArrivalTime + StartingTime + runningTime
		temp.setStartTime(time);
		temp.setFinishTime(time + temp.getRunTime());
		//System.out.println(temp.getFinishTime() - temp.getArrivalTime());
		int slot = temp.getIncomingSlot();
		totalTimesofP.remove(slot);
		totalTimesofP.add(slot, temp.getFinishTime() - temp.getArrivalTime());
		
		//totalTimesofP.add(temp.getFinishTime() - temp.getArrivalTime());

		// return temp.getFinishTime() - temp.getArrivalTime();
		// time + runningTime
		return time + temp.getRunTime();
	}
	
	public void crunchSJF(PrintWriter outputStream)
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
				temp.setRunTime(200);
				//Will need work on the conditions it's suppose to find the process that can run on the CPU
				//** Add in something that says after everything is done, do not do newTime blahbah
				for(int a = 0; a < pList.size(); a++)
				{
						if(pList.get(a).getArrivalTime() <= i && pList.get(a).isDone == false
								&& pList.get(a).runTime < temp.getRunTime())
						{
							temp = pList.get(a);
							//foundFirst = true;
							//pList.get(a).setDone(true);
						}
					
				}
				
				for(int b = 0; b < pList.size(); b++)
				{
					if(pList.get(b).isDone() == true)
					{
						allDone = true;
					}
					else
					{
						allDone = false;
						break;
					}
				}
				
				if(allDone == false && temp.isDone() != true)
				{
					if(temp.getArrivalTime() == 0 && temp.getRunTime() == 200)
					{
						
					}
					else
					{
					newTime = calculateSJF(i, temp);
					CPUBusy = true;
					
					}
					
				
				}
			}
		}
		
	}
	
}
