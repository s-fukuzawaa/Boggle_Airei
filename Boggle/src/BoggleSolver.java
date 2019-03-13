import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class BoggleSolver
{
	private TrieST<Integer> save;
    public BoggleSolver(String[] dictionary)
    {
    	 this.save= new TrieST<Integer>();
    	 for(int i=0; i<dictionary.length; i++)
    	 {
    		 int score=0;
    		 int length=dictionary[i].length();
    		 if(length<3)
    		 {
    			 score=0;
    		 }
    		 if(length>2&&length<5)
    		 {
    			score=1;
    		 }
    		 else if(length==5)
    		 {
    			 score=2;
    		 }
    		 else if(length==6)
    		 {
    			 score=3;
    		 }
    		 else if(length==7)
    		 {
    			 score=5;
    		 }
    		 else if(length>7)
    		 {
    			 score=11;
    		 }
    		 
    		 save.put(dictionary[i], score);
    	 }
    	 
    }
    
    private boolean valid(String a)
    {
    	return save.contains(a);
    }

    private ArrayList<String> add(BoggleBoard b,ArrayList<String> result, String s,boolean[][] marked,int row ,int col)
    {
    	if(!valid(s)||result.contains(s))
    	{
    		return result;
    	}
    	if(valid(s)&&!result.contains(s))
    	{
    		result.add(s);
    	}
    	
    	marked[row][col]=true;
    	if(row==0)
    	{
    		if(col==0)
    		{
    			if(!marked[row][col+1])
    			{
    				String a=b.getLetter(row, col+1)+"";
    				add( b, result,  s+a, marked, row , col+1);
    			}
    			if(!marked[row+1][col+1])
    			{
    				String two=b.getLetter(row+1, col+1)+"";
    				add( b, result,  s+two, marked, row+1 , col+1);
    			}
    			if(!marked[row+1][col])
    			{
    				String thr=b.getLetter(row+1, col)+"";
    				add( b, result,  s+thr,marked, row+1 , col);

    			}
    			
    		}
    		else if(col==b.cols()-1)
    		{
    			if(!marked[row][col-1])
    			{
    				String a=b.getLetter(row, col-1)+"";
    				add(b, result, s+a, marked, row, col-1);
    			}
    			if(!marked[row+1][col])
    			{
    				String two=b.getLetter(row+1, col)+"";
    				add(b, result, s+two, marked, row+1, col);
    			}
    			if(!marked[row+1][col-1])
    			{
    				String thr=b.getLetter(row+1, col-1)+"";
    				add( b, result,  s+thr,marked, row+1 , col-1);
    			}
    		}
    		
    	}
    	else if(row==b.rows()-1)
    	{
    		if(col==0)
    		{
    			if(!marked[row][col+1])
    			{
    				String a=b.getLetter(row, col+1)+"";
    				add( b, result,  s+a, marked, row , col+1);
    			}
    			if(!marked[row-1][col+1])
    			{
    				String two=b.getLetter(row-1, col+1)+"";
    				add( b, result,  s+two, marked, row-1 , col+1);
    			}
    			if(!marked[row-1][col])
    			{
    				String thr=b.getLetter(row-1, col)+"";
    				add( b, result,  s+thr,marked, row-1 , col);

    			}
    			
    		}
    		else if(col==b.cols()-1)
    		{
    			if(!marked[row][col-1])
    			{
    				String a=b.getLetter(row, col-1)+"";
    				add(b, result, s+a, marked, row, col-1);
    			}
    			if(!marked[row-1][col])
    			{
    				String two=b.getLetter(row-1, col)+"";
    				add(b, result, s+two, marked, row-1, col);
    			}
    			if(!marked[row-1][col-1])
    			{
    				String thr=b.getLetter(row-1, col-1)+"";
    				add( b, result,  s+thr,marked, row-1 , col-1);
    			}
    		}
    		
    	}
    	else if(col==0)
    	{
    		if(!marked[row][col+1])
			{
				String a=b.getLetter(row, col+1)+"";
				add( b, result,  s+a, marked, row , col+1);
			}
			if(!marked[row+1][col+1])
			{
				String two=b.getLetter(row+1, col+1)+"";
				add( b, result,  s+two, marked, row+1 , col+1);
			}
			if(!marked[row+1][col])
			{
				String thr=b.getLetter(row+1, col)+"";
				add( b, result,  s+thr,marked, row+1 , col);

			}
			if(!marked[row+1][col+1])
			{
				String f=b.getLetter(row+1, col+1)+"";
				add( b, result,  s+f,marked, row+1 , col+1);
			}
			if(!marked[row+1][col])
			{
				String fi=b.getLetter(row+1, col)+"";
				add( b, result,  s+fi,marked, row+1 , col);
			}
    	}
    	else if(col==b.cols()-1)
    	{
			if(!marked[row+1][col])
			{
				String two=b.getLetter(row+1, col)+"";
				add(b, result, s+two, marked, row+1, col);
			}
			if(!marked[row+1][col-1])
			{
				String thr=b.getLetter(row+1, col-1)+"";
				add( b, result,  s+thr,marked, row+1 , col-1);
			}
			if(!marked[row][col-1])
			{
				String a=b.getLetter(row, col-1)+"";
				add(b, result, s+a, marked, row, col-1);
			}
			if(!marked[row-1][col])
			{
				String two=b.getLetter(row-1, col)+"";
				add(b, result, s+two, marked, row-1, col);
			}
			if(!marked[row-1][col-1])
			{
				String thr=b.getLetter(row-1, col-1)+"";
				add( b, result,  s+thr,marked, row-1 , col-1);
			}
    	}
    	else
		{
			if(!marked[row][col+1])
			{
				String a=b.getLetter(row, col+1)+"";
				add(b, result, s+a, marked,row ,col+1);
			}
			if(!marked[row+1][col+1])
			{
				String two=b.getLetter(row+1, col+1)+"";
				add( b, result, s+two, marked,row+1 , col+1);
			}
			if(!marked[row+1][col])
			{
				String thr=b.getLetter(row+1, col)+"";
				add( b, result, s+thr, marked, row+1 , col);

			}
			if(!marked[row+1][col-1])
			{
				String f=b.getLetter(row+1, col-1)+"";
				add( b, result, s+f,marked, row+1 , col-1);

			}
		}
    	
    	
    	return result;
    	
    }
    	
    public Iterable<String> getAllValidWords(BoggleBoard board)
    {
       ArrayList<String> result= new ArrayList<String>();
       
       for(int i=0; i<board.rows(); i++)
       {
    	   for(int j=0; j<board.cols(); j++)
    	   {
    		   boolean[][] a=new boolean[board.rows()][board.cols()];
    		   
    		   for(int k=0; k<board.rows(); k++)
    		   {
    			   for(int m=0; m<board.cols(); m++)
    			   {
    				   a[k][m]=false;
    			   }
    		   }
    		   result=add(board,result,board.getLetter(i, j)+"",new boolean[board.rows()][board.cols()], i ,j);
    	   }
       }
       return result;
    }

    public int scoreOf(String word)
    {
    	if(!save.contains(word))
    	{
    		return 0;
    	}
		 int length=word.length();
		 if(length<3)
		 {
			 return 0;
		 }
		 if(length>2&&length<5)
		 {
			return 1;
		 }
		 else if(length==5)
		 {
			 return 2;
		 }
		 else if(length==6)
		 {
			 return 3;
		 }
		 else if(length==7)
		 {
			 return 5;
		 }
		 return 11;
		 
    }
    
    // ------------------------
    // FREE TEST CODE
    // ------------------------

    private static void mainWithOneBoardFile(BoggleSolver solver, String boardFile)
    {
        BoggleBoard board = new BoggleBoard(boardFile);
        int score = 0;
        for (String word : solver.getAllValidWords(board))
        {
            StdOut.print(word + " ");
            score += solver.scoreOf(word);
        }
        StdOut.println("\nScore = " + score);
    }
    
    private static void mainWithAllBoardFiles(BoggleSolver solver)
    {
        
        Path dir = FileSystems.getDefault().getPath("testinput"); 
        
        DirectoryStream<Path> ds;
        
        try
        {
            ds = Files.newDirectoryStream(dir);
        }
        catch(Exception e)
        {
            // This is awful error handling.  Never do this in your job.
            return;
        }
        
        for (Path file : ds)
        {
            String filenameString = file.getFileName().toString().toLowerCase();
            if (!filenameString.startsWith("board-points."))
                continue;
            
            String expectedPointsStr = filenameString.split("\\.")[1];
            int expectedPoints = Integer.parseInt(expectedPointsStr);
            
            String filepathString = file.toString();
            StdOut.print(filepathString + ":");
            BoggleBoard board = new BoggleBoard(filepathString);
            int score = 0;
            ArrayList<String> words = new ArrayList<String>();
            for (String word : solver.getAllValidWords(board))
            {
                words.add(word);
                score += solver.scoreOf(word);
            }
            String successMsg = (expectedPoints == score) ? ".  passed" : ".  ***** FAILED *****\nWords found:\n" + words + "\n\n";
            StdOut.println(": Score = " + score + successMsg);
        }
    }
    
    public static void main(String[] args)
    {
        In in = new In("testinput/dictionary-common.txt");
        String[] dictionary = in.readAllStrings();
        BoggleSolver solver = new BoggleSolver(dictionary);
        
        // You can use exactly one of these (and comment out the other).
        //
        // Use the first one to test against a single board.
        //
        // Use the second one to test against all the boards at once. 
        
        // Example 1: Run with a single board
        mainWithOneBoardFile(solver, "testinput/board-4x4.txt");
        
        // Example 2: Run with ALL boards.  If you use this, you should only use
        // dictionary-common.txt as your dictionary (above), as this will
        // print passed or failed based on whether
        // the scores you returned match the filename.
        // mainWithAllBoardFiles(solver);
    }
}
