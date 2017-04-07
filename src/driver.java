import java.io.File
;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;


public class driver 
{
	LinkedList<Process> pList1 = new LinkedList<Process>();

	LinkedList<Process> pList2 = new LinkedList<Process>();

	LinkedList<Process> pList3 = new LinkedList<Process>();
	LinkedList<Process> pList4 = new LinkedList<Process>();

	FIFO first;
	SJF second;
	SRT third;
	MLF fourth;
	
	private Scanner x;
	PrintWriter outputStream;
	String outputFileName = "/Volumes/cs143b/87597916.txt";
	
	public void openOutputFile()
	{
		try
		{
			outputStream = new PrintWriter(outputFileName);
		}
		catch(FileNotFoundException e)
		{
			System.out.println("OutputFile cannot be opened.");
		}

	}
	public void openFile()
	{
		try
		{
			x = new Scanner(new File("/Volumes/cs143b/input.txt"));
		}
		catch(Exception e)
		{
			System.out.println("Cannot find file");
		}
	}
	
	public void readFile()
	{
		int count = 0;
		while(x.hasNext())
		{
			String a = x.next();
			String b = x.next();
			
			int y = Integer.parseInt(a);
			int z = Integer.parseInt(b);
			
			createP(y, z, count);
			count++;
		}
		
		FIFO();
		SJF();
		SRT();
		MLF();
		
		outputStream.close();
		x.close();
		
	}
	
	public void createP(int incomingTime, int runTime, int incomingSlot)
	{
		Process temp1 = new Process(incomingTime, runTime, incomingSlot);
		Process temp2 = new Process(incomingTime, runTime, incomingSlot);
		Process temp3 = new Process(incomingTime, runTime, incomingSlot);
		Process temp4 = new Process(incomingTime, runTime, incomingSlot);

		pList1.add(temp1);
		pList2.add(temp2);
		pList3.add(temp3);
		pList4.add(temp4);
	}
	
	public void FIFO()
	{
		first = new FIFO(pList1);
		first.timer();
		first.crunchFIFO(outputStream);
		
	}
	
	public void SJF()
	{
		second = new SJF(pList2);
		second.timer();
		second.crunchSJF(outputStream);
	}
	
	public void SRT()
	{
		third = new SRT(pList3);
		third.timer();
		third.crunchSRT(outputStream);
	}
	
	public void MLF()
	{
		fourth = new MLF(pList4);
		fourth.timer();
		fourth.crunchMLF(outputStream);
	}
	
	public static void main(String [] args)
	{
		driver obj = new driver();
		/*
		obj.createP(1, 1);
		obj.createP(2,4);
		obj.createP(2,4);
		obj.createP(3, 1);
		obj.createP(4,2);
		obj.MLF();
		*/
		/*
		 * (1,1) (2,4) (2,4) (3,1) (4,2)
		 */
		obj.openOutputFile();
		obj.openFile();
		obj.readFile();
		/* Gucci for MLF test case 2
		obj.createP(4, 1);
		obj.createP(15, 16);
		obj.createP(14, 2);
		obj.createP(3, 5);
		obj.createP(4, 2);
		*/
		
		/*
		obj.createP(0, 4);
		obj.createP(2, 2);
		obj.createP(4, 3);
		obj.createP(4, 1);
		obj.createP(5, 2);
		obj.createP(18, 1);
		obj.createP(10, 1);
		*/
		
		//Good for MLF test case #1 from test case defaults.
		
		//obj.createP(0, 4);
		//obj.createP(0, 2);
		//obj.createP(3, 1);
		//obj.MLF();
		
		// 0 4 
		//2 2
		//4 3
		//4 1
		//5 2
		//18 1
		// 10 1
		
		//4 1 
		//15 16
		//14 2
		//3 5 
		//4 2
		
	}
}
