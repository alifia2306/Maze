package maze;

/**
 * The <code>DisjointSet</code> class specifies makeSet, union and find methods
 * for solving maze.
 *
 * @author Alifia Haidery, Jincheng Cao
 * @version 1.0
 * @since 2016-04-26
 */

public class DisjointSet {

	/**
	 * make a set out of the cells in the maze, set parent and rank for each
	 * cell.
	 * 
	 * @param maze
	 */
	public void makeSet(MazeCell[][] maze) {
		// To check if the maze if invalid or empty.
		if (maze == null || maze.length == 0 || maze[0].length == 0) {
			return;
		}
		// Initialize the maze.
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				// Set each cell's parent to be themselves.
				maze[i][j].setParent(maze[i][j]);
				// Set each cell's rank to be zero.
				maze[i][j].setRank(0);
			}
		}
	}

	/**
	 * Create the union of the sets that contain cell1 and cell2. If the two
	 * cells are in the same set, nothing changes, else create the new set as a
	 * union. Please see the union-find algorithm.
	 * 
	 * @param cell1
	 * @param cell2
	 */
	public void union(MazeCell cell1, MazeCell cell2) {
		//Cover corner cases.
		if (cell1 == null || cell2 == null) {
			return;
		}
		//Find root nodes for both cells.
		MazeCell p1 = find(cell1);
		MazeCell p2 = find(cell2);
		//If they are under the same root, do nothing.
		if (p1 == p2)
			return;
		//If cell's rank is larger, set it's root to be parent of another cell's root.
		if (p1.getRank() > p2.getRank()) {
			p2.setParent(p1);
		} else {
			p1.setParent(p2);
			//If they have the same rank, update rank for the root.
			if (p1.getRank() == p2.getRank()) {
				p2.setRank(p2.getRank() + 1);
			}
		}
	}

	/**
	 * Find the set that the cell is a part of. While finding the set, do the
	 * path compression as well.
	 * 
	 * @param cell
	 * @return root of the cell
	 */
	public MazeCell find(MazeCell cell) {
		if (cell == null)
			return null;
		// If the cell's parent is not itself, go through the parent node
		// Until we find the root of this cell.
		if (!cell.getParent().equals(cell)) {
			cell.setParent(find(cell.getParent()));
		}
		// Return the root of the cell.
		return cell.getParent();
	}

}