import java.util.ArrayList;

public class Tinder {
	ArrayList<User> users;
	ArrayList<Link> links;
	
	public Tinder(){
		this.users = new ArrayList<User>();
		this.links = new ArrayList<Link>();
	}
	
	public void addUser(User u1){
		if(users.contains(u1)){
			System.out.println("User already exists");
		}
		else{
			users.add(u1);
		}
	}
	public void setID(int i, long id){
		users.get(i).id = id;
	}
	public User getUser(long id){
		for(int i = 0; i<users.size(); i++){
			if(users.get(i).id == id){
				return users.get(i);
			}
		}
		System.out.println("No such user");
		return null;
	}
	public void addLink(User u1, User u2){
		if(u1.equals(u2)){
			System.out.println("Cannot link the same user");
			return;
		}
		Link newLink = new Link(u1, u2);
		if(links.contains(newLink)){
			System.out.println("Link already exists");
		}
		else{
			links.add(newLink);
		}
	}
}
