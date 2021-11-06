/**
* class XRef - Cross Reference Map
*
* I understand the meaning of academic dishonesty, in particular plagiarism, copyright infringement
* and collusion. I am aware of the consequences if found to be involved in these misconducts. I hereby
* declare that the work submitted for the "ITP4510 Data Structures & Algorithms" is authentic record
* of my own work.
*
* @Name : Tsang Tsz Fung
* @StdID: 200539567
* @Class: IT114105/1A
* @2021-import java.io.*;
*/
import java.io.*;
import java.util.*;
import java.lang.*;


public class ReadFile {
	
	private static final String DELIMITER = "\"(?:\\\\\"|[^\"])*?\"|[\\s.,;:+*/|!=><@?#%&(){}\\-\\^\\[\\]\\&&]+";
	public static void main(String [] args)
	{
    	BufferedReader source;
		String line;
		int lineCount = 0;
		int longest = 0;
		char f = 'A';
		wordfiller key = new wordfiller();
		LinkedList s = new LinkedList(new ReferenceEntryComparator());
		//the word need to ban
		String[] keywords = new String[]{ "abstract", "assert", "boolean",
                "break", "byte", "case", "catch", "char", "class", "const",
                "continue", "default", "do", "double", "else", "extends", "false",
                "final", "finally", "float", "for", "goto", "if", "implements",
                "import", "instanceof", "int", "interface", "long", "native",
                "new", "null", "package", "private", "protected", "public",
                "return", "short", "static", "strictfp", "super", "switch",
                "synchronized", "this", "throw", "throws", "transient", "true",
                "try", "void", "volatile", "while" };
				
		try
		{
			System.out.println("SOURCE FILE:"+args[0]);		
			source = new BufferedReader(new FileReader(args[0])); //Read the file 
			line = source.readLine();
			
			while ( line != null) //Loop Each Line
			{
				lineCount++;
				System.out.println(String.format("%04d | %s", lineCount, line)); //print the readed line (not the outcome)
				String[] tokens = line.split(DELIMITER); //split each word to the tokens
				
				for (String x : tokens) { //divde the sentence into words
					if(!x.isBlank()){
						int result = key.binarySearch(keywords, x); 
					    if (result < 0 ){//check is it keywords if(<0)Not if(0-45)Yes
							f = x.charAt(0); // get first character is string
							if(Character.isLetter(f) == true || f == '_' || f == '$'){ //check the first character is it $,_
								ListNode node = null;
								if((node = s.findItem(new ReferenceEntry(x))) == null) {
									longest = Math.max(longest, x.length());
									ReferenceEntry entry = new ReferenceEntry(x);
									entry.getOccurences().addToTail(lineCount);
									s.insertInOrder(entry);//add the filled item in the order
								} else {
									ReferenceEntry entry = (ReferenceEntry)node.data;
									LinkedList rows = entry.getOccurences();
									rows.addToTail(lineCount);
									
								}
							}
						}
					}
				}
				
				
					
				line = source.readLine();
			}
			source.close();
			System.out.println("CROSS REFERENCE:");//outcome
			while(!s.isEmpty()) {  
				ReferenceEntry entry = (ReferenceEntry)s.removeFromHead();//print the list using removefromhead
				System.out.print(String.format("%-" + longest + "s", entry.getIdentifier()) + "\t : [ "); 
				int k; 
				while(true) {
					try {
						k = (int)entry.getOccurences().removeFromHead();
					} catch (EmptyListException e){
						break;
					}
					System.out.print(k);
					System.out.print(" ");
				}
				System.out.println("]");
			}
			
			
		}
		catch ( IOException iox ) {
	        System.out.println("Problem encountered in reading file!" );
	    }
	}
}