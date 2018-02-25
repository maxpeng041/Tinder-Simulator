public class HashTable {
	User[][] userArray;
	
	public HashTable(){
		userArray = new User[37][19];
	}
	public void put(double longitude, double latitude, long id, String gender, String interestedGender){
		int longitudeIndex = ((int) longitude/10)+18;
		int latitudeIndex = ((int) latitude/10)+9;
		User newUser = new User(id);
		newUser.longitude = longitude;
		newUser.latitude = latitude;
		newUser.gender = gender;
		newUser.interestedGender = interestedGender;
		if(userArray[longitudeIndex][latitudeIndex]==null){
			userArray[longitudeIndex][latitudeIndex] = newUser;
		}
		else{
			User currentUser = userArray[longitudeIndex][latitudeIndex];
			while(currentUser.next!=null){
				currentUser = currentUser.next;
			}
			currentUser.next = newUser;
		}
	}
}
