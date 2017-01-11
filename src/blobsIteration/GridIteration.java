package blobsIteration;

import java.util.Random;
import java.util.Stack;
import java.util.Vector;

public class GridIteration<E> 
{
	// a Vector of Vectors of Cells
	private Vector<Vector<Cell<E>>> cells;
	private Stack<Cell<E>> stack;
	
	public GridIteration(int rows, int columns) 
	{
		cells = new Vector<Vector<Cell<E>>>();
		stack = new Stack<Cell<E>>();
		
		// initialize each cell of the grid
		for (int row = 0; row < rows; row++) 
		{
			// set up a Vector that will represent a new row in a two dimensional grid
			Vector<Cell<E>> tempVector = new Vector<Cell<E>>();

			for (int col = 0; col < columns; col++) 
			{
				tempVector.add(new Cell<E>(row, col)); // add a Cell to this Vector
			}
			// add this new vector to the Vector of Vectors
			cells.add(tempVector);
		}
	}

	public void setGrid(int percentage, E value) 
	{
		Random randomGenerator = new Random(System.currentTimeMillis());
		int randomNum;

		for (int row = 0; row < cells.size(); row++) 
		{
			for (int col = 0; col < cells.get(row).size(); col++) 
			{
				randomNum = randomGenerator.nextInt(100);
				if (randomNum < percentage) 
				{
					// first get() , gets access to the Vector on a particular row
					// second get() , gets access to a particular Cell in the Vector on a particular row
					cells.get(row).get(col).setData(value);
				}
			}
		}
	}

	public int countBlobs(E value) 
	{
		int count = 0;

		for (int row = 0; row < cells.size(); row++) 
		{
			for (int col = 0; col < cells.get(row).size(); col++) 
			{
				Cell<E> startCell = cells.get(row).get(col);
				if (!startCell.isVisited() && startCell.hasData()) 
				{
					count++;
					// mark the blob connected to starting cell
					markBlob(startCell);
				}
			}
		}
		return count;
	}

	public void markBlob(Cell<E> currentCell) 
	{
		do
		{
			//put this at the top so it goes through the loop the last time when you pop the last cell
			if(!stack.isEmpty())
			{
				currentCell = stack.peek();
				stack.pop();
			}
			if(!currentCell.isVisited() && currentCell.hasData())
			{
				currentCell.setVisited();
				if (currentCell.getRow() > 0) 
				{ 	
					// move up one row and mark blob starting there
					if(!cells.get(currentCell.getRow() - 1).get(currentCell.getCol()).isVisited()
							&& cells.get(currentCell.getRow() - 1).get(currentCell.getCol()).hasData())
					{
						stack.push(cells.get(currentCell.getRow() - 1).get(currentCell.getCol()));
					}
				}

				if (currentCell.getRow() < cells.size() - 1) 
				{ 	
					// move down one row
					if(!cells.get(currentCell.getRow() + 1).get(currentCell.getCol()).isVisited()
							&& cells.get(currentCell.getRow() + 1).get(currentCell.getCol()).hasData())
					{
						stack.push(cells.get(currentCell.getRow() + 1).get(currentCell.getCol()));
					}
				}
				
				// traverse left
				if (currentCell.getCol() > 0) 
				{ 	
					// move over left one column
					if(!cells.get(currentCell.getRow()).get(currentCell.getCol() - 1).isVisited()
							&& cells.get(currentCell.getRow()).get(currentCell.getCol() - 1).hasData())
					{
						stack.push(cells.get(currentCell.getRow()).get(currentCell.getCol() - 1));
					}
				}
		
				// traverse right
				if (currentCell.getCol() < cells.get(currentCell.getRow()).size() - 1) 
				{
					// move over right one column
					if(!cells.get(currentCell.getRow()).get(currentCell.getCol() + 1).isVisited()
							&& cells.get(currentCell.getRow()).get(currentCell.getCol() + 1).hasData())
					{
						stack.push(cells.get(currentCell.getRow()).get(currentCell.getCol() + 1));
					}
				}
			}
		} while(!stack.isEmpty());
	}

	public String toString() 
	{
		StringBuffer buffer = new StringBuffer();
		for (int row = 0; row < cells.size(); row++) 
		{
			buffer.append("\n");
			for (int col = 0; col < cells.get(row).size(); col++) 
			{
				buffer.append(" " + cells.get(row).get(col).toString());
			}
		}
		return buffer.toString();
	}

	static public void main(String[] args) 
	{
		Character character = new Character('X');
		GridIteration<Character> theGrid = new GridIteration<Character>(5, 5);
		theGrid.setGrid(40, 'X');
		System.out.println(theGrid.toString());
		System.out.println(theGrid.countBlobs(character));
		System.out.println(theGrid);
	}
}
