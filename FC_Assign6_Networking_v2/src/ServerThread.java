import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerThread extends Thread {

	//private static PostgresJDBC psgres;
	
	Socket socket;
    public ServerThread(Socket s)
    {
        this.socket = s;
    }
    
    public void run() 
    {
        try {
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                System.out.println("A client request received at "+socket);
                
                BufferedReader input =
                        new BufferedReader(new InputStreamReader(socket.getInputStream()));
                
                //System.out.println("testing string");
                String res=input.readLine();
                List<String> words=tokenize(res,':');

                
                if(words.get(0).equals("auth")) {
                	out.println(checkLogin(words));
                }
                else if(words.get(0).equals("ask")) {
                	out.println(askDefine(words));
                	
                } else if(words.get(0).equals("add")) {
                	out.println(addDefine(words));
                }
                
            } 
        catch(IOException e)
            {
                System.out.println("Error: ");
                e.printStackTrace();
            }
    }
	
    private static String checkLogin(List<String> words) {
    	PostgresJDBC psgres = new PostgresJDBC();   //should be able to move out so not create new object each time?
        
    	//psgres=new PostgresJDBC();
    	if(psgres.logIn(words.get(1), words.get(2))) {
            return("y");       	
        }
        else { 
        	return("n");
        
        } 
    } //end logincheck
    
    private static String askDefine(List<String> words) {
    	PostgresJDBC psgres = new PostgresJDBC();
    	words.set(1,removeCapitals(words.get(1)));
    	String defined=psgres.defineWord(words.get(1));
    	if(defined!=null) {
    		System.out.println("found def");
    		return defined;
    	}else {
    		return ("Sorry, I could not find this definition");
    	}
    }
    
    private static String addDefine(List<String> words) {
    	PostgresJDBC psgres = new PostgresJDBC();   //should be able to move out so not create new object each time?
    	words.set(1,removeCapitals(words.get(1)));
    	//psgres=new PostgresJDBC();
    	if(psgres.addDefine(words.get(1), words.get(2))) {
    		System.out.println("added word and def");
            return ("y");        	
        }
        else { 
        	System.out.println("could not add");
        	return ("n");
        
        } 
    } //end logincheck
    
    //changes a word to lower case
    private static String removeCapitals(String word) {
    	String lowerCase=word.toLowerCase();
    	return lowerCase;
    	
    }
 
  //break string into tokens, based on separator, returns list of tokens
  	private  static List<String> tokenize( String phrase, char separator ){

  		List<String> terms = new ArrayList<String>();
  		int pos = -1;

  		do{
  			pos = findNextTerm( phrase, separator );
  			if( pos != -1 ){

  				String term = phrase.substring( 0, pos );

  				terms.add( term );
  				

  				phrase = phrase.substring( pos + 1, phrase.length() );
  			}

  			else if( ! phrase.equals( "" ) ) {
  				terms.add( phrase );
  			}

  		} while( pos != -1 );


  		return terms;
  	}
  		
  	//takes a string, finds the end of the next term based on the seperator, returns end of term position	
  	private static int findNextTerm( String phrase, char separator ){

  		for( int i = 0; phrase != null && i < phrase.length(); ++i ) {
  			if( phrase.charAt( i ) == separator ) {
  				return i;
  			}
  		}

  		return -1;
  	}
    
}