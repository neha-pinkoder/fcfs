import java.io.DataInputStream;
class schedulefcfs
{   
	int i;
	String str[];
	int A[]=new int[5];
	int B[]=new int[5];
	int T[]=new int[5];
	int W[]=new int[5];
	int A1[]=new int[5];
	int j,k;
	void getdata()
	{
		DataInputStream in=new DataInputStream(System.in);
		str=new String[5];
		try
		{
			for(i=0;i<5;i++)
			{
				System.out.print("Enter "+(i+1)+" Process name: ");
				str[i]=in.readLine();
				System.out.print("Enter "+(i+1)+" Process Arrival time: ");
				A[i]=Integer.parseInt(in.readLine());
				System.out.print("Enter "+(i+1)+" Process Burst time:  ");
				B[i]=Integer.parseInt(in.readLine());	
				System.out.println();
				System.out.println();
			}
		}
		catch(Exception e)
		{}
	}
	void Tmedur()
	{
		int prevb=0;
		for(i=0;i<5;i++)
		{	System.out.println("Process\t\t"+"Duration\t"+"   Status");
			System.out.print(str[i]+"\t");
			if(A[i]<prevb)
			{
				System.out.print("\t"+prevb+" - "+(prevb+B[i])+"\t\t\t");
				System.out.println("Exit");
				T[i]=((prevb+B[i])-A[i]);	
				W[i]=T[i]-B[i];
				prevb=prevb+B[i];
			}
			else
			{
				System.out.print("\t"+A[i]+" - "+(A[i]+B[i])+"\t\t\t");
				System.out.println("Exit");
				T[i]=(A[i]+B[i])-A[i];
				W[i]=T[i]-B[i];
				prevb=(A[i]+B[i]);
			}
			
		}
	}
	void arrange()
	{
		for(i=0;i<5;i++)
		{
			int j=0;
			{
				for(j=0;j<5;j++)
				{if(j!=0)
					{
						if(A[j]<A[j-1])
						{	int x=A[j];
							A[j]=A[j-1];
							A[j-1]=x;
							String ch=str[j];
							str[j]=str[j-1];
							str[j-1]=ch;
							int y=B[j];
							B[j]=B[j-1];
							B[j-1]=y;
						}
					}
				}
			}
		}
	}
	void display()
	{	System.out.println();
		System.out.println("_______________________________________________");
		System.out.println("Process\t\t"+"Turn Around Time\t"+"Waiting Time");
		for(i=0;i<5;i++)
		{
			System.out.println(str[i]+   "\t\t"+T[i]+   "\t\t\t"+W[i]);
		}
	}
}						
class fcfs
{
	public static void main(String args[])
	{
		schedulefcfs ob=new schedulefcfs();
		ob.getdata();
		ob.arrange();
		ob.Tmedur();
		ob.display();
	}
}