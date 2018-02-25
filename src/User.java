import java.util.ArrayList;

public class User {
	long id;
	String firstName;
	String lastName;
	String email;
	String gender;
	String interestedGender;
	double longitude;
	double latitude;
	double radius;
	ArrayList<User> neighbors;
	
	public User next;
	
	public User (long id){
		this.id = id;
		this.neighbors = new ArrayList<User>();
	}
	
	public void addNeighbor (User id){
		if(!this.neighbors.contains(id)){
			neighbors.add(id);
		}
	}
}
