import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class BoggleSolver
{
	private BoggleTrie<Integer> save;

    public BoggleSolver(String[] dictionary)
    {
    	 this.save= new BoggleTrie<Integer>();
    	 for(int i=0; i<dictionary.length; i++)
    	 {
    		
    		 save.put(dictionary[i], 0);
    		
    	 }
    	 
    }
    
    private boolean check(String a)
    {
    	return save.contains(a)&&a.length()>=3;
    }

    
    private ArrayList<String> add(BoggleBoard b, ArrayList<String> valid, boolean[][] marked, String s,int row ,int col)
    {
    	marked[row][col]=true;
    	
    	if(check(s)&&!valid.contains(s))
    	{
    		valid.add(s);

    	}
    	else if(!save.prefix(s))
    	{    
    		marked[row][col]=false;
    		return valid;
    	}

    	for(int i=row-1; i<=row+1; i++)
    	{
    		for(int j=col-1; j<=col+1; j++)
    		{
    			if(i>-1&&j>-1&&i<b.rows()&&j<b.cols()&&marked[i][j]!=true)
    			{
    				if((b.getLetter(i, j)+"").equals("Q"))
    				{
    					add(b,valid,marked,s+b.getLetter(i, j)+"U",i ,j);
    				}
    				else
    				{
    					add(b,valid,marked,s+b.getLetter(i, j),i ,j);
    				}

    			}
    		}
    	}
    	marked[row][col]=false;
    	return valid;
    }
    	
    public Iterable<String> getAllValidWords(BoggleBoard board)
    {
    	
    	ArrayList<String> valid= new ArrayList<String>();
    	
       for(int i=0; i<board.rows(); i++)
       {
    	   for(int j=0; j<board.cols(); j++)
    	   {
    		   if((board.getLetter(i, j)+"").equals("Q"))
    		   {
        		   valid=add(board,valid,new boolean[board.rows()][board.cols()],board.getLetter(i, j)+""+"U",i,j);

    		   }
    		   else
    		   {
        		   valid=add(board,valid,new boolean[board.rows()][board.cols()],board.getLetter(i, j)+"",i,j);
    		   }
    	   }
       }
       return valid;
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

        mainWithOneBoardFile(solver,"testinput/board-4x4.txt");

        mainWithOneBoardFile(solver, "testinput/board-points.0.b.txt");

        
        // Example 2: Run with ALL boards.  If you use this, you should only use
        // dictionary-common.txt as your dictionary (above), as this will
        // print passed or failed based on whether
        // the scores you returned match the filename.
        //mainWithAllBoardFiles(solver);
    }
}