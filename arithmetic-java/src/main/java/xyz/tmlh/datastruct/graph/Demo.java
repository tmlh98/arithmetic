package xyz.tmlh.datastruct.graph;

public class Demo {
    public static void main(String[] args) {
        System.out.println("dfs");
        Maze dfs = new DFSMaze();
        dfs.printMaze();
        dfs.firstSearch();
        System.out.println("bfs");
        Maze bfs = new BFSMaze();
        bfs.firstSearch();
    }
}
