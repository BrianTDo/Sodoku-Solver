package sudoku;

import java.util.*;


public class Grid 
{
	private int[][]						values;
	

	//
	// DON'T CHANGE THIS.
	//
	// Constructs a Grid instance from a string[] as provided by TestGridSupplier.
	// See TestGridSupplier for examples of input.
	// Dots in input strings represent 0s in values[][].
	//
	public Grid(String[] rows)
	{
		values = new int[9][9];
		for (int j=0; j<9; j++)
		{
			String row = rows[j];
			char[] charray = row.toCharArray();
			for (int i=0; i<9; i++)
			{
				char ch = charray[i];
				if (ch != '.')
					values[j][i] = ch - '0';
			}
		}
	}
	
	
	//
	// DON'T CHANGE THIS.
	//
	public String toString()
	{
		String s = "";
		for (int j=0; j<9; j++)
		{
			for (int i=0; i<9; i++)
			{
				int n = values[j][i];
				if (n == 0)
					s += '.';
				else
					s += (char)('0' + n);
			}
			s += "\n";
		}
		return s;
	}


	//
	// DON'T CHANGE THIS.
	// Copy ctor. Duplicates its source. Youâ€™ll call this 9 times in next9Grids.
	//
	Grid(Grid src)
	{
		values = new int[9][9];
		for (int j=0; j<9; j++)
			for (int i=0; i<9; i++)
				values[j][i] = src.values[j][i];
	}
	
	
	//
	// COMPLETE THIS
	//
	//
	// Finds an empty member of values[][]. Returns an array list of 9 grids that look like the current grid,
	// except the empty member contains 1, 2, 3 .... 9. Returns null if the current grid is full. Donâ€™t change
	// â€œthisâ€� grid. Build 9 new grids.
	// 
	//
	// Example: if this grid = 1........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//
	// Then the returned array list would contain:
	//
	// 11.......          12.......          13.......          14.......    and so on     19.......
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	//
	public ArrayList<Grid> next9Grids()
	{		
		int xOfNextEmptyCell = 0;
		int yOfNextEmptyCell = 0;

		// Find x,y of an empty cell.
		boolean isDone = false;
		while (!isDone)
		{
			for (int i = 0; i < values.length && !isDone; i++) {
				for (int j = 0; j < values[0].length && !isDone; j++) {
					if (values[i][j] == 0) {
						xOfNextEmptyCell = i;
						yOfNextEmptyCell = j;
						isDone = true;
					}
				}
			}
		}
		// Construct array list to contain 9 new grids.
		ArrayList<Grid> grids = new ArrayList<Grid>();

		// Create 9 new grids as described in the comments above. Add them to grids.
		for (int i = 1; i <= 9; i++)
		{
			Grid newG = new Grid(this);
			newG.values[xOfNextEmptyCell][yOfNextEmptyCell] = i;
			grids.add(newG);
		}
		return grids;
	}
	
	
	//
    // COMPLETE THIS
    //
    // Returns true if this grid is legal. A grid is legal if no row, column, or
    // 3x3 block contains a repeated 1, 2, 3, 4, 5, 6, 7, 8, or 9.
    //
    public boolean isLegal()
    {
        // Check every row. If you find an illegal row, return false.
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values[0].length; j++) {
            	int rowCheck = values[i][j];
            	for (int h = j + 1; h < values.length; h++) {
            			if (values[i][h] == rowCheck && rowCheck != 0)
            				return false;
            	}
        // Check every column. If you find an illegal column, return false.
            	int columnCheck = values[j][i];
            	for (int h = j + 1; h < values.length; h++) {
            		if (values[h][i] == columnCheck && columnCheck != 0)
            			return false;
            	}
        // Check every block. If you find an illegal block, return false.
            	int sRow = i - i % 3;
            	int sCol = j - j % 3;
            	for (int row = sRow; row < sRow + 3; row++) {			
            		for (int col = sCol; col < sCol + 3; col++) {
            			int blockCheck = values[row][col];
            			if (values[i][j] == blockCheck && blockCheck != 0 && (col != j && row != i))
            				return false;
            		}
            	}
            }
        }       
        // All rows/cols/blocks are legal.suypr
        return true;
    }


    //
    // COMPLETE THIS
    //
    // Returns true if every cell member of values[][] is a digit from 1-9.
    //
    public boolean isFull()
    {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (values[i][j] < 1 || values[i][j] > 9) {
                    return false;
                }
            }
        }
        return true;
    }


    //
    // COMPLETE THIS
    //
    // Returns true if x is a Grid and, for every (i,j), 
    // x.values[i][j] == this.values[i][j].
    //
    public boolean equals(Object x)
    {
        if (!(x instanceof Grid))
            return false;
        Grid that = (Grid)x;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (that.values[i][j] != this.values[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
    	

    public static void main(String[]args) {
        Grid sol = TestGridSupplier.getSolution1();
        Grid sol1 = TestGridSupplier.getSolution1();
        Grid sol2 = TestGridSupplier.getSolution2();
        Grid rej1 = TestGridSupplier.getReject1();
        Grid rej2 = TestGridSupplier.getReject2();
        Grid rej3 = TestGridSupplier.getReject3();
        Grid rej4 = TestGridSupplier.getReject4();
        Grid puz = TestGridSupplier.getPuzzle2();

        System.out.println(sol.equals(sol1));
        System.out.println(sol.equals(sol2));
        System.out.println(sol.isFull());
        System.out.println(rej4.isLegal());
        System.out.println(rej3.isLegal());
        System.out.println(sol.isLegal());
        
        ArrayList<Grid> grids = puz.next9Grids();
        for (Grid g : grids)
        	System.out.println(g.toString());
    }
}
