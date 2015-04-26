
public class Main {

	/**
	 *
	 * The idea is to random a position in one of the square the board/field consists of put 1 there.
	 * Then mark that square and all the positions on the same row and column as unavailable.
	 * Next find the square with the least number of available positions and random a position in that square to put a 1 in.
	 * Continue like this untill all the squares have a 1.
	 * Repeat with 2.
	 * 
	 * e.x.
	 * step 1:
	 * 	-1	1	-1	-1
	 * 	-1	-1	-2	-2
	 * 	-2	-1	-2	-2
	 * 	-2	-1	-2	-2
	 * 
	 * step 2:
	 * 	-1	1	-1	-1
	 * 	-1	-1	1	-1
	 * 	-2	-1	-1	-2
	 * 	-2	-1	-1	-2
	 * 
	 * step 3:
	 * 	-1	1	-1	-1
	 * 	-1	-1	1	-1
	 * 	-1	-1	-1	1
	 * 	-2	-1	-1	-1
	 * 
	 * step 4:
	 * 	-2	1	-2	-2
	 * 	-2	-2	1	-2
	 * 	-2	-2	-2	1
	 * 	1	-2	-2	-2 
	 * 
	 * step 5:
	 * 	-1	1	-2	-2
	 * 	2	-1	1	-1
	 * 	-1	-2	-2	1
	 * 	1	-2	-2	-2  
	 * 
	 * step 6:
	 * 	-1	1	-2	-2
	 * 	2	-1	1	-1
	 * 	-1	2	-1	1
	 * 	1	-1	-2	-2  
	 * 
	 * step 7:
	 * 	-1	1	2	-1
	 * 	2	-1	1	-1
	 * 	-1	2	-1	1
	 * 	1	-1	-1	-2  
	 * 
	 * step 8:
	 * 	-2	1	2	-2
	 * 	2	-2	1	-2
	 * 	-2	2	-2	1
	 * 	1	-2	-2	2
	 * 
	 * step....:
	 * repeat with 3 and 4
	 * 
	 * I havnt finished, i managed to fill the field with the number 1.
	 * later when i will fill it with the number 2 something goes wrong. it overwrites the 1´s somewhere but havent found where.
	 * 
	 *If you run the code now it will print it correct with number 1. 
	 *To break it, in class Field in function createField() increase a ForLoop(), i have marked it, supposed to be 4 if it were fully functioning.
	 * 
	 * 
	 * 
	 */
	
	
	public static void main(String[] args) 
	{
		Field field = new Field();
		
		
		print(field.getField(),4);
	}
	
	
	
	
	public static void print(int[][]arr, int size)
	{
		
		 for (int i = 0; i < 4; i++)
		    {
			 for (int j = 0; j < 4; j++)
			    {
				 	System.out.print(arr[i][j] + "\t");
			    }
			 System.out.println();
		    }	
				
				
		
	} 

}
