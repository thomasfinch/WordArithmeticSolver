import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;


public class WordArithmeticSolver
{
    private static ArrayList<String> words = new ArrayList<String>(), subWords = new ArrayList<String>();
    private static ArrayList<String> letters = new ArrayList<String>(), remainingLetters = new ArrayList<String>();
    private static String[] values = {"", "", "", "", "", "", "", "", "", ""};
    private static boolean isSolved = false;
    private static String uN, operator;
    private static ArrayList<Integer> unNums = new ArrayList<Integer>();
    private static ArrayList<Long> nums = new ArrayList<Long>();
    private static int numWords;
    
  public static void main(String[] args)
  {
      Scanner scanner = new Scanner(System.in);
      System.out.println("Word Puzzle Solver\n");
      System.out.print("Enter the number of words: ");
      numWords = Integer.parseInt(scanner.nextLine());
      for (int i = 0; i < numWords; i++)
      {
          System.out.print("Next word: ");
          String word = scanner.nextLine().toLowerCase();
          words.add(word);
        }
        operator = "";
      while (!operator.equals("-") && ! operator.equals("+") && !operator.equals("*") && !operator.equals("/"))
      {
      System.out.print("Enter the operator: ");
      operator = scanner.nextLine().toLowerCase();
    }
      System.out.print("Enter unused numbers: ");
      uN = scanner.nextLine();
      StringTokenizer unusedNums = new StringTokenizer(uN);
      while (unusedNums.hasMoreTokens()) {
         unNums.add(Integer.parseInt(unusedNums.nextToken()));
     }
     
     if (words.size() <= 2 || (words.size() >= 4 && (operator.equals("/")) || operator.equals("-")))
     {
         System.out.println("\nSorry, you must have typed something wrong.");
         scanner.nextLine();
         System.exit(0);
        }
      
      System.out.println("\nWorking...");
      
      for (String word:words)
      {
        for (int i = 0; i < word.length(); i++)
        {
            if (!contains(word.substring(i, i+1)))
                letters.add(word.substring(i, i+1));
            }    
        }

            int counter = 0;
            while(!isSolved)
            {
                if (counter % 100 == 0)
                    System.out.println("Try #"+counter);
                counter++;
                for(int i = 0; i < 10; i++)
                {
                    values[i] = "";
                    if (isUnusedNum(i))
                        values[i] = null;
                }
                
                remainingLetters = (ArrayList<String>) letters.clone();
                Random generator = new Random();
                while (remainingLetters.size() >= 1)
                {
                    int index = generator.nextInt(10);
                    if (values[index] != null && values[index].equals(""))
                        values[index] = remainingLetters.remove(0);
                }
                
                for(int i = 0; i < 10; i++)
                    if (values[i] != null && values[i].equals(""))
                        values[i] = null;

                 subWords = new ArrayList<String>();
               for (int i = 0; i < words.size(); i++)
               {
                   subWords.add(words.get(i));
                }
               
                for(int i = 0; i < subWords.size(); i++)
                {
               for (int j = 0; j < 10; j++)
               {
                   if (values[j] != null)
                   {
                        String word = subWords.get(i);
                        subWords.set(i, word.replaceAll(values[j], j+""));
                    }
                }
                }
                    nums = new ArrayList<Long>();
                    for(int i = 0; i < subWords.size(); i++)
                        nums.add(Long.parseLong(subWords.get(i)));
                        
                    long result = 0;
                    
                    if (operator.equals("+"))
                    {
                        for(int i = 0; i < nums.size()-1; i++)
                            result += nums.get(i);
                    }
                    else if (operator.equals("*"))
                    {
                        result = 1;
                        for(int i = 0; i < nums.size()-1; i++)
                            result *= nums.get(i);
                    }
                     else if (operator.equals("/"))
                     {
                        result = nums.get(0) / nums.get(1);
                    }
                      else if (operator.equals("-"))
                      {
                        result = nums.get(0) - nums.get(1);
                    }
                       
                      if (result == nums.get(nums.size()-1))
                        isSolved = true;
                       
            }
            
            System.out.println("\n\nSolved!\n");
            for (int i = 0; i < 10; i++)
                System.out.println(i+"= "+values[i]);
             System.exit(0);
    }
    
    private static boolean contains(String letter)
    {
        for (String let:letters)
            if (let.equalsIgnoreCase(letter))
                return true;
         return false;
    }
    
    private static boolean isUnusedNum(int num)
    {
        for(Integer number:unNums)
            if (number == num)
                return true;
         return false;
        }
}
