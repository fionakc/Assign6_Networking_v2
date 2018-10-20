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
                
                if(words.get(0).equals("ask")) {
                	out.println(askDefine(words));
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
    	String defined=psgres.defineWord(words.get(1));
    	if(defined!=null) {
    		System.out.println("found def");
    		return defined;
    	}else {
    		return ("Sorry, I could not find this definition");
    	}
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