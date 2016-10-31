import java.util.NoSuchElementException;

public class ItemNotFound extends NoSuchElementException{
	  public String message;

	    public ItemNotFound(String message){
	        this.message = message;
	    }
	    
	    @Override
	    public String getMessage(){
	    	return message;
	    }
	       
}
