import java.io.*;
import java.util.*;

public class WordsTweeted
{
   
   public static void main (String [] args)
   {
       
        WordsTweeted object = new WordsTweeted();
        
        String filePath = "/tweet_input/tweets.txt";
        
        //String filePath = args[0];
        
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
       
       //Get the systems root path e.g in windows c:\\users\\***
       String home  = System.getProperty("user.home");
       
       try 
       {
            BufferedReader in = new BufferedReader(new FileReader(new File(home + filePath)));
            
            while((line = in.readLine()) != null)
            {
                content.add(line);
            } in.close();processContent(content);
            
       } 
       catch(IOException e)
       {
          e.printStackTrace();
       }
    }
   
   
   /**
    * processContent() does the word count in each tweet
    */
   public void processContent(ArrayList <String> list)
   {
       //For the words and count
       HashMap<String, String> wordCount = new HashMap<String, String>();
       
       for(int i=0;i<list.size();i++)
       {
           //Split the sentence
           String tweet[] =  list.get(i).split(" ");
           
           for(int j=0;j<tweet.length;j++)
           {
               if(wordCount.containsKey(tweet[j]))
               {
                   int count = Integer.parseInt(wordCount.get(tweet[j]));
                   count++;
                   wordCount.remove(tweet[j]);
                   wordCount.put(tweet[j],String.valueOf(count));
               }
               else
               {
                   int count = 1;
                   wordCount.put(tweet[j],String.valueOf(count));
               }
               
               
           }
       }
       
       //write to file
       writeToFile(wordCount);
   }
   
   /**
    * Write to file
    */
   public void writeToFile(HashMap <String, String> content)
   {
       
       Map<String, String> treeMap = new TreeMap<String, String>(content);
       String home  = System.getProperty("user.home");
       String fileToWrite = home+"/tweet_output/ft1.txt";
       
       //TreeSet used to help in arranging the words alphabetically, Iterator used so we can cycle through the set
       Iterator it = treeMap.entrySet().iterator();
       
       try 
       {
           BufferedWriter ou = new BufferedWriter(new FileWriter(new File(fileToWrite)));
           
           while (it.hasNext())
           {
               Map.Entry pair = (Map.Entry)it.next();
               System.out.println(pair.getKey() + " = " + pair.getValue());
               
               ou.write(pair.getKey() + "    " + pair.getValue()+"\n");
           }
           
           ou.close();
       }
       catch(IOException e) 
       {
           e.printStackTrace();
       }
       
       it.remove();
       
   }
   
   
   
   
}