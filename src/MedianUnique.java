import java.io.*;
import java.util.*;

public class MedianUnique
{
    public static void main(String [] args)
    {
        MedianUnique object = new MedianUnique();
        //String filePath = args[0];
        
        String filePath = "/tweet_input/tweets.txt";
        
        object.processFile(filePath);
    }
    
    
    /**
    * processFile(String filePath) gets the content of the files and stores it in an ArrayList
    * 
    */
    public void processFile(String filePath)
    {
       ArrayList <String> content = new ArrayList<String>();
       String line;
       String home  = System.getProperty("user.home");
       
       try 
       {
            BufferedReader in = new BufferedReader(new FileReader(new File(home + filePath)));
            
            while((line = in.readLine()) != null)
            {
                content.add(line);
               /* if(content.size() == 10 || (line == null))
                {
                   processContent(content);
                   content.clear();
                }*/
            } in.close();processContent(content);
            
       } 
       catch(IOException e)
       {
          e.printStackTrace();
       }
    }
    
    
    /**
    * processContent() does the unique word count in each tweet and calculates the Median
    * puts the median in an ArrayList
    */
    public void processContent(ArrayList <String> list)
    {
        ArrayList <Integer> values = new ArrayList<Integer>();
        ArrayList <Double> medianValues = new ArrayList<Double>();
        
        for(int i=0;i<list.size();i++)
        {
            String tweet[] = list.get(i).split(" ");
            //removes duplicate from the tweets
            HashSet <String> uniqueCharacters = new HashSet<String>(Arrays.asList(tweet));
            
            values.add(new Integer(uniqueCharacters.size()));
            Collections.sort(values);
            
            int valuesSize = values.size();
            
            if(valuesSize == 1)
            {
                int median = values.get(0).intValue();
                medianValues.add(new Double(median));
                //writeToFile(values, median);
                System.out.println(median);
            }
            else if((valuesSize)%2 == 0)
            {
                
                double median = ( values.get(  valuesSize/2 ).doubleValue() + values.get( (valuesSize/2)-1 ).doubleValue() )/2;
                medianValues.add(median);
                //writeToFile(values, median);
                System.out.println(new Double(median));
            }
            else if((valuesSize)%2 != 0)
            {
                int median = values.get( (int)valuesSize/2 ).intValue();
                medianValues.add(new Double(median));
                //writeToFile(values, median);
                System.out.println(median);
            }
        }writeToFile(medianValues);
    }
    
    
    /**
    * Write to file
    */
    public void writeToFile(ArrayList <Double> list)
   {
       
       String home  = System.getProperty("user.home");
       String fileToWrite = home+"/tweet_output/ft2.txt";
       
       try 
       {
             BufferedWriter ou = new BufferedWriter(new FileWriter(new File(fileToWrite)));
             
             for(int i=0;i<list.size();i++)
             {
                ou.write( list.get(i).toString() + "\n" );
             }
             ou.close();
       } 
       catch(IOException e) 
       {
            e.printStackTrace(); 
       } 
   }
}