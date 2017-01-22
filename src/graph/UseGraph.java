package graph;

public class UseGraph
{
	public static void main(String[] args)
	{
		Grid aGrid = new Grid(5, 5);
		System.out.println("Empty Grid");
		System.out.println(aGrid.toString());
		int[][] values = aGrid.traverseDepth();
		System.out.println("DEPTH FIRST");
		for (int i = 0; i < values.length; i++)
		{
			for (int j = 0; j < values[0].length; j++)
			{
				System.out.print(values[i][j] + "   |  ");
			}
			System.out.println();
			System.out.println("____________________________________________");
		}

		aGrid.reset();
		System.out.println("BREADTH FIRST");

		int[][] deepValues = aGrid.traverseBreadth();
		for (int i = 0; i < deepValues.length; i++)
		{
			for (int j = 0; j < deepValues[0].length; j++)
			{
				System.out.print(deepValues[i][j] + "   |  ");
			}
			System.out.println();
			System.out.println("____________________________________________");
		}
	}
}
