/*
 * A list of eligible voters used by the CLA.
 */
public class validList {
	static Voter[] staticList; //list of eligible voters
	Voter[] list;
	
	public validList(){
		this.list=staticList;
	}
	
	
	
	//searches through list to see if voter is eligible
	public boolean isValid(String name){
		for(int i=0; i<list.length; i++){
			if(list[i].name.equalsIgnoreCase(name)){
				return true;
			}
		}
		return false;
	}
	
	
	//initializes validList with voter names for testing
	void initialize(){
		
	}
	
	
}
