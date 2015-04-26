import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public class Field {
	
	//square 0, ul=upperLeft
	private int [][] ul;
	//square 1, ul=upperRight
	private int [][] ur;
	//square 2, ul=bottomLeft;
	private int [][] bl;
	//square 3, ul=bottomRight
	private int [][] br;
	
	private Random rnd;
	//array for keeping number of available positions, for deciding next array/square to use
	private int[] order;
	
	public Field() {
		super();
		this.ul= new int [2][2];
		//fill square with -2
		init(this.ul);
		this.ur= new int [2][2];
		init(this.ur);
		this.bl= new int [2][2];
		init(this.bl);
		this.br= new int [2][2];
		init(this.br);
		
		this.order = new int[4];
		this.rnd = new Random();
		createField();
	}

	
	//build playing field
	public void createField(){
		//numbers to be written on the field
		int [] numbers = new int[]{1,2,3,4};
		//current square/array that should be used
		int currentSq = 0;

		//!!!!!!!!!! increase j<1 to break it. with working code should be 4 !!!!!!!!!!
		for(int j=0;j<1;j++){
			for(int i= 0;i<4;i++){
				createSquare(currentSq, getArr(currentSq), numbers[j]);
				//get the number of the next square to be used
				currentSq = getNext();
			}
			currentSq=0;
			//replace all the -1 with -2, to get ready for filling field with next number
			fillSquare(this.ul,-2);
			fillSquare(this.bl,-2);
			fillSquare(this.ur,-2);
			fillSquare(this.br,-2);
		}	
		
	}
	//calls functions that populates a square
	private void createSquare(int currSq, int [][]arr, int nr){
		int [] pos = new int[2];
		
		//find the pos to populate
		pos=findPos(pos,arr);
		//populate the square and mark positions as unavailable
		fill(currSq, pos, nr);
		//check how many available positions are left
		check(0,this.ul);
		check(1,this.ur);
		check(2,this.bl);
		check(3,this.br);
		
	}
	//returns the array that represents the square that is entered
	private int[][] getArr(int sq){
		int currArr[][] = null;
		if(sq == 0)
			currArr = this.ul;
		else if(sq == 1)
			currArr =this.ur;
		else if(sq == 2)
			currArr =this.bl;
		else
			currArr =this.br;
		
		return currArr;
	}
	
	//returns which square that should be populated next
	private int getNext(){
		int next=0;
		//if square 0 has less positions available
		if(order[0]<order[1])
			next=0;
		for(int i = 0;i<3;i++){

			//if square has less positions available
			if(order[i+1]<order[i]){
				next = i+1;
			}
		}
		
		return next;
	}
	//finds a position in a square that is available
	private int[] findPos(int[]pos, int [][]arr){
		
		boolean go = false;
		while(!go){
			//randomize a position
			pos = randomize(new int[]{0,1});
			//if pos is available continue
			if( arr[ pos[0] ][ pos[1] ] == -2 )
				go=true;
		}
		return pos;
	}
	
	//marks a position as unavailable
	private void markOne(int[][] arr,int pos[]){
		if(arr[pos[0]][pos[1]] < 0)
			arr[pos[0]][pos[1]] = -1;
	}
	
	//populates a pos and makrs positions where we cant put a number
	private void fill(int square, int[] pos, int nr){
		//if == 0 it is upper left corner
		if(square==0){
			//mark all positions in a square as unavailable
			fillSquare(this.ul,-1);	
			//populate the pos with number
			this.ul[ pos[0] ][ pos[1] ] = nr;
			
			// marks all the positions on the same row as unavailable
			markOne(this.ur, new int[]{pos[0],0});
			markOne(this.ur, new int[]{pos[0],1});
			
			//marks all the positions in the same column as unavailable
			markOne(this.bl, new int[]{0,pos[1]});
			markOne(this.bl, new int[]{1,pos[1]});
			
		}
		else if(square==1){
			//mark positions as unavailable
			fillSquare(this.ur,-1);	
			//populate the pos
			this.ur[ pos[0] ][ pos[1] ] = nr;
			
			markOne(this.ul, new int[]{pos[0],0});
			this.ul[pos[0]][0] = -1;
			markOne(this.ul, new int[]{pos[0],1});
			this.ul[pos[0]][1] = -1;
			
			markOne(this.br, new int[]{0,pos[1]});
			markOne(this.ur, new int[]{1,pos[1]});
		}
		else if(square==2){
			//mark positions as unavailable
			fillSquare(this.bl,-1);	
			//populate the pos
			this.bl[ pos[0] ][ pos[1] ] = nr;
			
			markOne(this.ul, new int[]{0,pos[1]});
			markOne(this.ul, new int[]{1,pos[1]});
			
			markOne(this.br, new int[]{pos[0],0});
			markOne(this.br, new int[]{pos[0],1});
		}
		else if(square==3){
			//mark positions as unavailable
			fillSquare(this.br,-1);	
			//populate the pos
			this.br[ pos[0] ][ pos[1] ] = nr;
			
			markOne(this.ur, new int[]{0,pos[1]});
			markOne(this.ur, new int[]{1,pos[1]});
			
			this.bl[ pos[0] ][ 0 ] = -1;
			this.bl[ pos[0] ][ 1 ] = -1;
		}
	}
	
	//fills one arr with nr, either -1 or -2. where it doesnt containt i number > 0
	private void fillSquare(int[][] sq, int nr){
		 for (int i = 0; i < 2; i++)
		 {
			 for (int j = 0; j < 2; j++)
			 {
				 if(sq[i][j] < 0)
					 sq[i][j] = nr;
			 }
		 }
	}
	
	//counts how many empty pos an arr has
	private void check(int sq, int [][]arr){
		int nr = 0;
		//count full pos in corner
		for(int i=0;i<2;i++){
			for(int j=0;j<2;j++){
				if(arr[i][j] == -2)
					nr+=1;
			}
		}
		// if it only contains -2 it should be overlooked, so it is set to 4
		if(nr==0)
			nr=4;
		this.order[sq] = nr;
	}
	
	//randomize order in an array
	private int[] randomize(int[] numbers){
		
		 for (int i = numbers.length - 1; i > 0; i--)
		    {
		      int index = rnd.nextInt(i + 1);
		      int a = numbers[index];
		      numbers[index] = numbers[i];
		      numbers[i] = a;
		    }
		 return numbers;
	}

	//just random a number in range
	private int rand(int max,int min){
		return rnd.nextInt((max-min)+1)+min;
	}
	//initiates the field with -2
	private void init(int[][] arr){
		 for (int i = 0; i < 2; i++)
		    {
			 for (int j = 0; j < 2; j++)
			    {
				 	arr[i][j] = -2;
			    }
		    }
	}
	
	//creates a 2D array to print in main
	public int [][] getField(){
		int[][] field = new int [4][4];

		field[0][0] = this.ul[0][0];
		field[0][1] = this.ul[0][1];
		field[0][2] = this.ur[0][0];
		field[0][3] = this.ur[0][1];
		field[1][0] = this.ul[1][0];
		field[1][1] = this.ul[1][1];
		field[1][2] = this.ur[1][0];
		field[1][3] = this.ur[1][1];
		field[2][0] = this.bl[0][0];
		field[2][1] = this.bl[0][1];
		field[2][2] = this.br[0][0];
		field[2][3] = this.br[0][1];
		field[3][0] = this.bl[1][0];
		field[3][1] = this.bl[1][1];
		field[3][2] = this.br[1][0];
		field[3][3] = this.br[1][1];
	
		
		return field;
	}
}
