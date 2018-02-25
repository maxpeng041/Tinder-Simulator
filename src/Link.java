
public class Link {
	User u1;
	User u2;
	
	public Link(User u1, User u2){
		this.u1 = u1;
		this.u2 = u2;
		
		u1.addNeighbor(u2);
		u2.addNeighbor(u1);
	}
}
