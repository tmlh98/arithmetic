package xyz.tmlh.airthmetic.example.map;

import java.util.Scanner;

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
public class Maze {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入迷宫: ");
		char[][] data = new char[21][];
		for (int i = 0; i < 21; i++) {
			data[i] = sc.next().toCharArray();
		}
		 
		bfs( 0 , 0 , data ,0  );
		
		sc.close();
	}
	/**
	 *  深度优先遍历实现
	 *  
	 * @param @param y 横坐标
	 * @param @param x 列坐标
	 * @param @param data 
	 * @param @param bs    步数 
	 */
	private static void bfs(int y, int x, char[][] data ,int bs ) {
		if(y == data.length -1 && x == data[0].length -1){
			System.out.println("当前路线步数为:" +bs);
			return;
		}
		char temp = data[y][x];
		data[y][x] = '*';
		try{
			if(y > 0 &&temp == '.'){
				bfs(y - 1, x, data, bs+1);
			}
			if(y < data.length -1 &&temp == '.'){
				bfs(y + 1, x, data, bs +1);
			}
			if(x > 0 &&temp == '.'){
				bfs(y , x -1, data, bs +1);
			}
			if(x < data[0].length -1 &&temp == '.'){
				bfs(y , x +1, data, bs +1);
			}
		}finally{
		    //回溯
			data[y][x] = temp;
		}
		
	}
	
	
	
}
