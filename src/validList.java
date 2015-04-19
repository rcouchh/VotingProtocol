import java.util.ArrayList;

/*
 * A list of eligible voters used by the CLA.
 */
public class validList {
	static ArrayList<Voter> staticList; //list of eligible voters
	ArrayList<Voter> list = new ArrayList<>();
	
	public validList(){
		list.add(new Voter("Ryan Couch"));
		list.add(new Voter("Eric Sinke"));
		staticList=this.list;
	}
	
	//searches through list to see if voter is eligible
	public boolean isValid(String name){
		for(int i=0; i<list.size(); i++){
			if(list.get(i).name.equalsIgnoreCase(name)){
				return true;
			}
		}
		return false;
	}
	
	//initializes validList with voter names for testing
	void initialize(){

	}
	
	
}
