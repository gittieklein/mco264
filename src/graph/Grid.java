package graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Grid
{
	private int[][] grid;

	public Grid(int x, int y)
	{
		grid = new int[x][y];
		for (int i = 0; i < grid.length; i++)
		{
			for (int j = 0; j < grid[0].length; j++)
			{
				grid[i][j] = 0;
			}
		}
	}

	public int[][] traverseDepth()
	{
		Stack<Vertex> stack = new Stack<Vertex>();
		// create a parallel array to know if the place was visited
		boolean[][] visited = new boolean[grid.length][grid[0].length];
		// set it up to false because you didn't visit anything yet
		for (int i = 0; i < visited.length; i++)
		{
			for (int j = 0; j < visited[0].length; j++)
			{
				visited[i][j] = false;
			}
		}
		// keep track of the number place it is that you are visiting so you can
		// fill the array
		int num = 0;
		// start with top left as the first vertex
		Vertex vertex = new Vertex(0, 0);
		stack.push(vertex);

		do
		{
			vertex = stack.pop();

			if (!visited[vertex.getX()][vertex.getY()]) // if vertex is not
														// visited
			{
				grid[vertex.getX()][vertex.getY()] = ++num;
				visited[vertex.getX()][vertex.getY()] = true;
				if (vertex.getX() > 0)
				{
					if (vertex.getY() < grid[0].length - 1)
					{
						stack.push(new Vertex((vertex.getX() - 1), (vertex.getY() + 1)));
					}

					if (vertex.getY() > 0)
					{
						stack.push(new Vertex((vertex.getX() - 1), (vertex.getY() - 1)));
					}

					stack.push(new Vertex(vertex.getX() - 1, vertex.getY()));
				}

				if (vertex.getY() > 0)
				{
					stack.push(new Vertex(vertex.getX(), vertex.getY() - 1));
				}

				if (vertex.getX() < grid.length - 1)
				{
					stack.push(new Vertex(vertex.getX() + 1, vertex.getY()));

					if (vertex.getY() > 0)
					{
						stack.push(new Vertex(vertex.getX() + 1, vertex.getY() - 1));
					}
				}

				if (vertex.getY() < grid[0].length - 1)
				{
					stack.push(new Vertex(vertex.getX(), vertex.getY() + 1));

					if (vertex.getX() < grid.length - 1)
					{
						stack.push(new Vertex(vertex.getX() + 1, vertex.getY() + 1));
					}
				}
			}
		} while (!stack.isEmpty());

		// create a copy of the grid to return
		int[][] grid2 = new int[grid.length][grid[0].length];
		for (int i = 0; i < grid.length; i++)
		{
			for (int j = 0; j < grid[0].length; j++)
			{
				grid2[i][j] = grid[i][j];
			}
		}
		reset();
		return grid2;
	}

	public int[][] traverseBreadth()
	{
		Queue<Vertex> queue = new LinkedList<Vertex>();

		// create a parallel array to know if the place was visited
		boolean[][] visited = new boolean[grid.length][grid[0].length];
		// set it up to false because you didn't visit anything yet
		for (int i = 0; i < visited.length; i++)
		{
			for (int j = 0; j < visited[0].length; j++)
			{
				visited[i][j] = false;
			}
		}
		// keep track of the number place it is that you are visiting so you can
		// fill the array
		int num = 0;
		// start with top left as the first vertex
		Vertex vertex = new Vertex(0, 0);
		queue.add(vertex);

		do
		{
			vertex = queue.poll();

			if (!visited[vertex.getX()][vertex.getY()]) // if vertex is not
														// visited
			{
				grid[vertex.getX()][vertex.getY()] = ++num;
				visited[vertex.getX()][vertex.getY()] = true;
				if (vertex.getX() > 0)
				{
					if (vertex.getY() < grid[0].length - 1)
					{

						queue.add(new Vertex((vertex.getX() - 1), (vertex.getY() + 1)));
					}

					if (vertex.getY() > 0)
					{
						queue.add(new Vertex((vertex.getX() - 1), (vertex.getY() - 1)));
					}

					queue.add(new Vertex(vertex.getX() - 1, vertex.getY()));
				}

				if (vertex.getY() > 0)
				{
					queue.add(new Vertex(vertex.getX(), vertex.getY() - 1));
				}

				if (vertex.getX() < grid.length - 1)
				{
					queue.add(new Vertex(vertex.getX() + 1, vertex.getY()));

					if (vertex.getY() > 0)
					{
						queue.add(new Vertex(vertex.getX() + 1, vertex.getY() - 1));
					}
				}

				if (vertex.getY() < grid[0].length - 1)
				{
					queue.add(new Vertex(vertex.getX(), vertex.getY() + 1));

					if (vertex.getX() < grid.length - 1)
					{
						queue.add(new Vertex(vertex.getX() + 1, vertex.getY() + 1));
					}
				}
			}
		} while (!queue.isEmpty());

		// create a copy of the grid to return
		int[][] grid2 = new int[grid.length][grid[0].length];
		for (int i = 0; i < grid.length; i++)
		{
			for (int j = 0; j < grid[0].length; j++)
			{
				grid2[i][j] = grid[i][j];
			}
		}
		reset();
		return grid2;
	}

	public void reset()
	{
		for (int i = 0; i < grid.length; i++)
		{
			for (int j = 0; j < grid[0].length; j++)
			{
				grid[i][j] = 0;
			}
		}
	}
}
