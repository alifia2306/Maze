package maze;

public class DisjointSet {
    
    //TODO - write all the methods of this class
    
    /**
     * make a set out of the cells in the maze
     * @param maze
     */
    public void makeSet(MazeCell[][] maze) {
        if(maze == null || maze.length == 0 || maze[0].length == 0) {
        	return;
        }
        for(int i = 0; i < maze.length; i++) {
        	for (int j = 0; j < maze[0].length; j++) {
        		maze[i][j].setParent(maze[i][j]);
        		maze[i][j].setRank(0);
        	}
        }
    }

    /**
     * Create the union of the sets that contain cell1 and cell2.
     * If the two cells are in the same set, nothing changes,
     * else create the new set as a union. 
     * Please see the union-find algorithm.
     * @param cell1
     * @param cell2
     */
    public void union(MazeCell cell1, MazeCell cell2){
        if(cell1 == null||cell2 == null) {
        	return;
        }
        MazeCell p1 = find(cell1);
        MazeCell p2 = find(cell2);
        if(p1 == p2) return;
        if(p1.getRank() > p2.getRank()){
        	p2.setParent(p1);
        } else {
        	p1.setParent(p2);
        	if(p1.getRank() == p2.getRank()){
        		p2.setRank(p2.getRank() + 1);
        	}
        }
    }

    /**
     * Find the set that the cell is a part of.
     * While finding the set, do the path compression as well.
     * @param cell
     * @return
     */
    public MazeCell find(MazeCell cell){
    	if(cell == null) return null;
        if(!cell.getParent().equals(cell)) {
        	cell.setParent(find(cell.getParent()));
        }
        return cell.getParent();
    }

}
