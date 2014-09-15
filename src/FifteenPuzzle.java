/* Inha Joo | ijoo2
 * CS441
 * Homework 2 - 15 Puzzle
 * BFS and Iterative deepening depth-first
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

/*
 * 
 * 1 2 3 4		0 1 2 3				0 1 2
 * 5 6 7 8		4 5 6 7				3 4 5
 * 9 A B C		8 9 10 11			6 7 8
 * D E F 0		12 13 14 15
 * 
 */

public class FifteenPuzzle {

	Queue<String> q = new LinkedList<String>(); // Use of Queue implementation using LinkedList for storing all nodes
	Map<String,Integer> map = new HashMap<String, Integer>(); // HashMap is used to ignore repeated nodes
	
	static String initialState = "";
	static int whichSearch;
	String goal = "123456789ABCDEF0"; // Final state wanted
	
	public static void main(String args[]){
		
		getInitialState();		
		FifteenPuzzle game = new FifteenPuzzle(); // New instance of FifteenPuzzle
		game.add(initialState, 0); // Add the Initial state of the board
			
		/*BFS Solution*/
		if(whichSearch == 1){
			while(game.q.peek() != null){
				game.left(game.q.peek());
				game.right(game.q.peek());
				game.up(game.q.peek());
				game.down(game.q.remove());
			}		
			System.out.println("Solution doesn't exist");	
		}
		/*run IDDFS*/
	}
	
	static void getInitialState(){
		
		System.out.println("Enter initial board state. 1-15 separated by spaces. 0 is blank tile");
		Scanner scanner = new Scanner(System.in);
		String str = scanner.nextLine();
		
		ArrayList<String> list = new ArrayList<String>();
		String[] words = str.split(" ");
		list.addAll(Arrays.asList(words));
		
		int index;
		index = list.indexOf("10"); list.set(index, "A");
		index = list.indexOf("11"); list.set(index, "B");
		index = list.indexOf("12"); list.set(index, "C");
		index = list.indexOf("13"); list.set(index, "D");
		index = list.indexOf("14"); list.set(index, "E");
		index = list.indexOf("15"); list.set(index, "F");		
		
		String[] newArray = new String[list.size()];
		newArray = list.toArray(newArray);
		
		StringBuilder builder = new StringBuilder();
		for(String s : newArray)
			builder.append(s);
		
		initialState = builder.toString();		
		System.out.println("Initial board is: " + str);
		
		System.out.println("Which search? BFS or Iterative-deepening DFS?");
		System.out.println("Enter 1 for BFS. 2 for DFS");
		
		whichSearch = scanner.nextInt();
	}
	
	void add(String str, int n){
		if(!map.containsKey(str)){
			map.put(str,n);
			q.add(str);
		}
	}
	
	/*Each method takes the current state of the board as a string. Then operation to move is done if possible
	 * After that, the new string is added to the map and queue. If it is the final state wanted,
	 * the program terminates
	 */	
	void up(String str){
		int a = str.indexOf("0");
		if(a>3){
			String s = str.substring(0,a-4) + "0" + str.substring(a-3,a) + str.charAt(a-4)+str.substring(a+1);
			add(s,map.get(str)+1);
			displayString(s);
			if(s.equals(goal)){
				System.exit(0);
			}
		}
    }
    void down(String str){
        int a = str.indexOf("0");
        if(a<12){
        	String s = str.substring(0,a) + str.substring(a+4,a+5) + str.substring(a+1,a+4) + "0" + str.substring(a+5);
        	add(s,map.get(str)+1);
        	displayString(s);
        	if(s.equals(goal)) {
                System.exit(0);
            }
        }
    }
    void left(String str){
        int a = str.indexOf("0");
        if(a!=0 && a!=4 && a!=8 && a!= 12){
            String s = str.substring(0,a-1)+"0"+str.charAt(a-1)+str.substring(a+1);
            add(s,map.get(str)+1);
            displayString(s);
            if(s.equals(goal)) {
                System.exit(0);
            }
        }
    }
    void right(String str){
        int a = str.indexOf("0");
        if(a!=3 && a!=7 && a!=11 && a!= 15){
            String s = str.substring(0,a)+str.charAt(a+1)+"0"+str.substring(a+2);
            add(s,map.get(str)+1);
            displayString(s);
            if(s.equals(goal)) {
                System.exit(0);
            }
        }
    }
    
    void displayString(String s){
    	for(int i=0;i<s.length();i++){
    		if(s.charAt(i)=='A') System.out.print("10");
    		if(s.charAt(i)=='B') System.out.print("11");
    		if(s.charAt(i)=='C') System.out.print("12");
    		if(s.charAt(i)=='D') System.out.print("13");
    		if(s.charAt(i)=='E') System.out.print("14");
    		if(s.charAt(i)=='F') System.out.print("15");
    		
    		if(s.charAt(i) != 'A' && s.charAt(i) != 'B' && s.charAt(i) != 'C'
    				&& s.charAt(i) != 'D' && s.charAt(i) != 'E' && s.charAt(i) != 'F'){
    			System.out.print(s.charAt(i));
    		}
    		System.out.print(" ");		
    	}
    	
    	System.out.println();
    }
	
	
	
	
	
	
}
