package xyz.tmlh.airthmetic.graph;

import java.util.LinkedList;
import java.util.Queue;

/* 21 行 32列
 *  ...11111111111111111111111111111
	11.111111........1111111111.1111
	11.111111..111.11111111.....1111
	11.11111111111.1111111111.111111
	11.111111.................111111
	11.111111.11111111111.11111.1111
	11.111111.11111111111.11111..111
	11..........111111111.11111.1111
	11111.111111111111111.11....1111
	11111.111111111111111.11.11.1111
	11111.111111111111111.11.11.1111
	111...111111111111111.11.11.1111
	111.11111111111111111....11.1111
	111.11111111111111111111111.1111
	111.1111.111111111111111......11
	111.1111.......111111111.1111.11
	111.1111.11111.111111111.1111.11
	111......11111.111111111.1111111
	11111111111111.111111111.111...1
	11111111111111...............1.1
	111111111111111111111111111111..
	如上图的迷宫，入口，出口分别：左上角，右下角
	"1"是墙壁，"."是通路
	求最短需要走多少步？
 */
public class MazeIssue {
    
	public static void main(String[] args) {
	    System.out.println("dfs:");
		dfs( 0 , 0 , getData() , 0 );
		System.out.println("bfs:");
		bfs(0 , 0 , getData());
	}
    
    private static void bfs(int x, int y, char[][] data) {
        Queue<Person> q = new LinkedList<Person>();
        Person person = new Person(x , y , 0);
        q.offer(person);
        data[x][y] = '1';
        while(!q.isEmpty()) {
            Person p = q.poll();
            x = p.x;
            y = p.y;
            
            if(y == data[0].length -1 && x == data.length -1) {
                System.out.println("最短路径为:" + p.bs);
                return;
            }
            if(x > 0 &&data[x -1][y ]  == '.'){
                q.offer(new Person(x - 1, y, p.bs + 1));
                data[x -1][y ] = '1';
            }
            if(x < data.length -1 &&data[x + 1][y]  == '.'){
                q.offer(new Person(x + 1, y , p.bs + 1));
                data[x + 1][y] = '1';
            }
            if(y > 0 && data[x][y - 1] == '.'){
                q.offer(new Person(x, y -1, p.bs + 1));
                data[x][y -1] = '1';
            }
            if(y < data[0].length -1 &&data[x][y + 1]  == '.'){
                q.offer(new Person(x, y + 1, p.bs + 1));
                data[x][y + 1] = '1';
            }
        }
    }
    
    static class Person{
        int x;
        int y;
        int bs;
        public Person() {}
        public Person(int x, int y, int bs) {
            this.x = x;
            this.y = y;
            this.bs = bs;
        }
    }    
    

    /**
	 *  深度优先 + 回溯
	 *  
	 * @param @param y 横坐标
	 * @param @param x 列坐标
	 * @param @param data 
	 * @param @param bs    步数 
	 */
	private static void dfs(int y, int x, char[][] data ,int bs ) {
		if(y == data.length -1 && x == data[0].length -1){
			System.out.println("当前路线步数为:" +bs);
			return;
		}
		char temp = data[y][x];
		data[y][x] = '1';
		try{
			if(y > 0 &&temp == '.'){
				dfs(y - 1, x, data, bs+1);
			}
			if(y < data.length -1 &&temp == '.'){
				dfs(y + 1, x, data, bs +1);
			}
			if(x > 0 &&temp == '.'){
				dfs(y , x -1, data, bs +1);
			}
			if(x < data[0].length -1 &&temp == '.'){
				dfs(y , x +1, data, bs +1);
			}
		}finally{
		    //回溯
			data[y][x] = temp;
		}
		
	}
	
	private static char[][] getData() {
	    String mazeStr= "...11111111111111111111111111111" + 
            	        "11.111111........1111111111.1111" + 
            	        "11.111111..111.11111111.....1111" + 
            	        "11.11111111111.1111111111.111111" + 
            	        "11.111111.................111111" + 
            	        "11.111111.11111111111.11111.1111" + 
            	        "11.111111.11111111111.11111..111" + 
            	        "11..........111111111.11111.1111" + 
            	        "11111.111111111111111.11....1111" + 
            	        "11111.111111111111111.11.11.1111" + 
            	        "11111.111111111111111.11.11.1111" + 
            	        "111...111111111111111.11.11.1111" + 
            	        "111.11111111111111111....11.1111" + 
            	        "111.11111111111111111111111.1111" + 
            	        "111.1111.111111111111111......11" + 
            	        "111.1111.......111111111.1111.11" + 
            	        "111.1111.11111.111111111.1111.11" + 
            	        "111......11111.111111111.1111111" + 
            	        "11111111111111.111111111.111...1" + 
            	        "11111111111111...............1.1" + 
            	        "111111111111111111111111111111.." ;
	    
	    int xIndex = 21;
	    int yIndex = 32;
	    char[][] data = new char[xIndex][yIndex];
	    for (int i = 0; i < xIndex; i++) {
            for (int j = 0; j < yIndex; j++) {
                data[i][j] = mazeStr.charAt(i * yIndex + j);
            }
        }
        return data;
    }
}
