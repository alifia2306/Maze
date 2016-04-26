package maze;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The <code>MazeCell</code> class stores information about each individual cell
 * in the maze. The boolean values <code>north</code>, <code>east</code>,
 * <code>west</code>, and <code>south</code> store which walls are up; e.g., if
 * <code>north</code> is <code>true</code>, then the north wall is up.
 *
 * Each cell also has pointer to its four <code>MazeCell</code> neighbors,
 * <code>neighborN</code>, <code>neighborE</code>, <code>neighborS</code>, and
 * <code>neighborW</code>. If any of these values are null, it means this cell
 * is on the border of the maze.
 * 
 * @author Alifia Haidery, Jincheng Cao
 * @version 1.0
 * @since 2016-04-26
 */

public class MazeCell {

	private boolean north, east, south, west;
	private boolean visited, examined;
	private MazeCell neighborN, neighborE, neighborS, neighborW;
	private Random generator;
	private MazeCell parent;
	private int rank;

	public MazeCell getParent() {
		return parent;
	}

	public void setParent(MazeCell parent) {
		this.parent = parent;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		rank = rank;
	}

	/**
	 * Sets all the walls to <code>true</code> and initializes the random number
	 * generator.
	 */
	public MazeCell() {
		north = true;
		east = true;
		south = true;
		west = true;
		generator = new Random();
		visited = false;
		examined = false;
	}

	/**
	 * Sets the visited flag to <code>true</code>.
	 */
	public void visit() {
		visited = true;
	}

	/**
	 * Returns whether or not this cell has been visited.
	 * 
	 * @return <code>true</code> if the cell has been visited.
	 */
	public boolean visited() {
		return visited;
	}

	/**
	 * Sets the examined flag to <code>true</code>.
	 */
	public void examine() {
		examined = true;
	}

	/**
	 * Returns whether or not this cell has been examined.
	 * 
	 * @return <code>true</code> if the cell has been examined.
	 */
	public boolean examined() {
		return examined;
	}

	/**
	 * Sets the pointers to the neighbors of this cell. If a pointer is null,
	 * that means this cell is along the border of the maze.
	 * 
	 * @param n
	 *            The north neighbor of this cell.
	 * @param e
	 *            The east neighbor of this cell.
	 * @param s
	 *            The south neighbor of this cell.
	 * @param w
	 *            The west neighbor of this cell.
	 */
	public void setNeighbors(MazeCell n, MazeCell e, MazeCell s, MazeCell w) {
		neighborN = n;
		neighborE = e;
		neighborS = s;
		neighborW = w;
	}

	/**
	 * Sets whether or not there are walls up to the north, east, south, and
	 * west of this cell.
	 * 
	 * @param north
	 *            <code>true</code> if there's a wall on the north side of the
	 *            cell.
	 * @param east
	 *            <code>true</code> if there's a wall on the east side of the
	 *            cell.
	 * @param south
	 *            <code>true</code> if there's a wall on the south side of the
	 *            cell.
	 * @param west
	 *            <code>true</code> if there's a wall on the west side of the
	 *            cell.
	 */
	public void setWalls(boolean north, boolean east, boolean south,
			boolean west) {
		this.north = north;
		this.east = east;
		this.south = south;
		this.west = west;
	}

	/**
	 * Returns whether or not this cell has all its walls up.
	 * 
	 * @return <code>true</code> if all walls are up.
	 */
	public boolean hasAllWalls() {
		if (!north || !east || !south || !west) {
			return false;
		}
		return true;
	}

	/**
	 * Returns whether or not this cell has its north wall up.
	 * 
	 * @return <code>true</code> if the cell's north wall is up.
	 */
	public boolean north() {
		return north;
	}

	/**
	 * Returns whether or not this cell has its south wall up.
	 * 
	 * @return <code>true</code> if the cell's south wall is up.
	 */
	public boolean south() {
		return south;
	}

	/**
	 * Returns whether or not this cell has its east wall up.
	 * 
	 * @return <code>true</code> if the cell's east wall is up.
	 */
	public boolean east() {
		return east;
	}

	/**
	 * Returns whether or not this cell has its west wall up.
	 * 
	 * @return <code>true</code> if the cell's west wall is up.
	 */
	public boolean west() {
		return west;
	}

	/**
	 * Gets the neighbors of this cell. Returns an array of those neighbors. The
	 * length of the array is the number of neighbors this cell has.
	 * 
	 * @return An array with the neighbors contained within it.
	 */
	public MazeCell[] getNeighbors() {
		List<MazeCell> neighborsList = new ArrayList<MazeCell>();
		if (neighborN != null && !hasWall(neighborN))
			neighborsList.add(neighborN);
		if (neighborS != null && !hasWall(neighborS))
			neighborsList.add(neighborS);
		if (neighborE != null && !hasWall(neighborE))
			neighborsList.add(neighborE);
		if (neighborW != null && !hasWall(neighborW))
			neighborsList.add(neighborW);
		// return (MazeCell[]) neighborsList.toArray();
		MazeCell[] cellArr = new MazeCell[neighborsList.size()];
		cellArr = neighborsList.toArray(cellArr);
		return cellArr;
	}

	/**
	 * Knocks down the wall between this cell and the neighbor cell. The
	 * neighbor cell must actually be a neighbor of this cell. This method is
	 * used in conjunction with <code>neighborWithWalls</code>.
	 * 
	 * @param neighbor
	 *            The neighboring cell; must be one of the neighbors set in
	 *            <code>setNeighbors</code>.
	 */
	public void knockDownWall(MazeCell neighbor) {
		// Remember that any wall that is knocked down
		// will require you to change values for both this and neighbor.

		if (neighbor.equals(neighborN)) {
			// neighborN = null;
			north = false;
			// neighbor.neighborS = null;
			neighbor.south = false;
		} else if (neighbor.equals(neighborS)) {
			// neighborS = null;
			south = false;
			// neighbor.neighborN = null;
			neighbor.north = false;
		} else if (neighbor.equals(neighborE)) {
			// neighborE = null;
			east = false;
			// neighbor.neighborW = null;
			neighbor.west = false;
		} else if (neighbor.equals(neighborW)) {
			// neighborW = null;
			west = false;
			// neighbor.neighborE = null;
			neighbor.east = false;
		}
	}

	/**
	 * Use this function whenever you want to randomly pick one of the
	 * neighbours
	 * 
	 * @return - random choice of one of the neighbours.
	 */
	public MazeCell getRandomNeighbor() {
		int randomNeighbor = generator.nextInt(4) + 1;
		switch (randomNeighbor) {
		case 1:
			return neighborN;
		case 2:
			return neighborS;
		case 3:
			return neighborE;
		case 4:
			return neighborW;
		}
		return null;
	}

	/**
	 * Returns a neighbor that has all its walls intact.
	 * 
	 * @return Neighbor with all its walls intact.
	 */
	public MazeCell neighborWithWalls() {
		MazeCell[] neighbors = this.getNeighbors();
		for (int i = 0; i < neighbors.length; i++) {
			if (neighbors[i].hasAllWalls()) {
				return neighbors[i];
			}
		}
		return null;
	}

	/**
	 * Check if the neighbor cell has any walls on the border of main cell to be
	 * knocked down.
	 * 
	 * @param neighbor
	 *            a neighbor cell
	 * @return a boolean value indicating if the neighbor has any walls on the
	 *         border of main cell to be knocked down.
	 * 
	 */

	public boolean hasWall(MazeCell neighbor) {
		if (neighbor.equals(neighborN)) {
			return north == true && neighbor.south == true;
		} else if (neighbor.equals(neighborS)) {
			return south == true && neighbor.north == true;
		} else if (neighbor.equals(neighborE)) {
			return east == true && neighbor.west == true;
		} else if (neighbor.equals(neighborW)) {
			return west == true && neighbor.east == true;
		}
		return false;
	}

}