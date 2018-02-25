import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Scanner;

public class MyTinder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashTable hashTable = new HashTable();
		Scanner sc = new Scanner(System.in);
		
		JSONParser parser = new JSONParser();	
		try {
			Object obj = parser.parse(new FileReader("users.json"));
			//System.out.println("File exists.");
			JSONArray jsonArray = (JSONArray) obj;
            
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                //System.out.println(jsonObject);
                
                double longitude;
                double latitude;
                long id = (long) jsonObject.get("id");
                String gender = ((String) jsonObject.get("gender")).toUpperCase().substring(0, 1);
                String interestedGender = ((String) jsonObject.get("interesed")).toUpperCase().substring(0, 1);
                
                //find longitude from json file
                //if longitude is not double, convert string or long to double
                try{
                	longitude = (double) jsonObject.get("longitude");
                }
                catch(ClassCastException e){
                	try{
                		String longitudeString = (String) jsonObject.get("longitude");
                    	longitude = Double.parseDouble(longitudeString);
                	}
                	catch(ClassCastException e1){
                		long longitudeLong = (long) jsonObject.get("longitude");
                		longitude = (double) longitudeLong;
                	}
                }
                
                try{
                	latitude = (double) jsonObject.get("latitude");
                }
                catch(ClassCastException e){
                	try{
                		String latitudeString = (String) jsonObject.get("latitude");
                		latitude = Double.parseDouble(latitudeString);
                	}
                	catch(ClassCastException e1){
                		long latitudeLong = (long) jsonObject.get("latitude");
                		latitude = (double) latitudeLong;
                	}
                }
                
                hashTable.put(longitude, latitude, id, gender, interestedGender);
            }
            
            
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("What is the gender of the new user? M/F");
		String gender = sc.nextLine();
		System.out.println("What is the new user's interested gender? M/F");
		String interestedGender = sc.nextLine();
		double newLongitude=999;
		double newLatitude=999;
		while(newLongitude>180 || newLongitude<-180){
			System.out.println("Please enter longitude for the new user: ");
			newLongitude = sc.nextDouble();
			if(newLongitude<=180 && newLongitude>=-180){
				break;
			}
			else{
				System.out.println("Error. Longitude should be between -180 and 180.");
			}
		}
		while(newLatitude>90 || newLatitude<-90){
			System.out.println("Please enter latitude for the new user: ");
			newLatitude = sc.nextDouble();
			if(newLatitude<=90 && newLatitude>=-90){
				break;
			}
			else{
				System.out.println("Error. Latitude should be between -90 and 90.");
			}
		}
		System.out.println("Please enter the search radius for the new user: ");
		double radius = sc.nextDouble();
		
		int longitudeIndex = ((int) newLongitude/10)+18;
		int latitudeIndex = ((int) newLatitude/10)+9;
		int curLongitudeIndex = longitudeIndex;
		int curLatitudeIndex = latitudeIndex;
		double testLatitude=newLatitude;
		double testLongitude=newLongitude;
		int longitudeIndexToMax=0;
		int longitudeIndexToMin=0;
		int latitudeIndexToMax=0;
		int latitudeIndexToMin=0;
		double distance=0;
		
		System.out.println();
		System.out.println("The following distances show the buckets in horizontal and vertical directions that are reached by the search radius.");
		System.out.println("Distances must be smaller than radius and the buckets do not go out of bounds (-180<=longitude<=180 & -90<=latitude<=90)");
		System.out.println();
		
		while(distance<radius){
			testLongitude = (curLongitudeIndex-18)*10;
			if(testLongitude>180){
				break;
			}
			double lat1 = Math.toRadians(newLatitude);
			double lat2 = Math.toRadians(testLatitude);
			double latDif = Math.toRadians(testLatitude - newLatitude);
			double lonDif = Math.toRadians(testLongitude - newLongitude);
			double a = Math.sin(latDif/2)*Math.sin(latDif/2)+Math.cos(lat1)*Math.cos(lat2)*Math.sin(lonDif/2)*Math.sin(lonDif/2);
			double c = 2*Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
			distance = 6371*c/1.609344;
			if(distance!=0 && distance<radius){
				System.out.println("horizontal distance between two longitudes: " + distance + ". longitude1: "+newLongitude+" longitude2: "+testLongitude);
			}
			if(distance<radius || curLongitudeIndex==longitudeIndex){
				curLongitudeIndex++;
			}
		}
		longitudeIndexToMax = curLongitudeIndex - longitudeIndex - 1;
		System.out.println();
		
		distance=0;
		curLongitudeIndex = longitudeIndex;
		while(distance<radius){
			testLongitude = (curLongitudeIndex-18)*10;
			if(testLongitude<-180){
				break;
			}
			double lat1 = Math.toRadians(newLatitude);
			double lat2 = Math.toRadians(testLatitude);
			double latDif = Math.toRadians(testLatitude - newLatitude);
			double lonDif = Math.toRadians(testLongitude - newLongitude);
			double a = Math.sin(latDif/2)*Math.sin(latDif/2)+Math.cos(lat1)*Math.cos(lat2)*Math.sin(lonDif/2)*Math.sin(lonDif/2);
			double c = 2*Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
			distance = 6371*c/1.609344;
			if(distance!=0 && distance<radius){
				System.out.println("horizontal distance between two longitudes: " + distance + ". longitude1: "+newLongitude+" longitude2: "+testLongitude);
			}
			if(distance<radius || curLongitudeIndex==longitudeIndex){
				curLongitudeIndex--;
			}
		}
		longitudeIndexToMin = longitudeIndex- curLongitudeIndex - 1;
		System.out.println();
		
		
		distance=0;
		testLongitude=newLongitude;
		while(distance<radius){
			testLatitude = (curLatitudeIndex-9)*10;
			if(testLatitude>90){
				break;
			}
			double lat1 = Math.toRadians(newLatitude);
			double lat2 = Math.toRadians(testLatitude);
			double latDif = Math.toRadians(testLatitude - newLatitude);
			double lonDif = Math.toRadians(testLongitude - newLongitude);
			double a = Math.sin(latDif/2)*Math.sin(latDif/2)+Math.cos(lat1)*Math.cos(lat2)*Math.sin(lonDif/2)*Math.sin(lonDif/2);
			double c = 2*Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
			distance = 6371*c/1.609344;
			if(distance!=0 && distance<radius){
				System.out.println("vertical distance between two latitudes: " + distance + ". latitude1: "+newLatitude+" latitude2: "+testLatitude);
			}
			if(distance<radius || curLatitudeIndex==latitudeIndex){
				curLatitudeIndex++;
			}
		}
		latitudeIndexToMax = curLatitudeIndex - latitudeIndex - 1;
		System.out.println();
		
		distance=0;
		curLatitudeIndex = latitudeIndex;
		while(distance<radius){
			testLatitude = (curLatitudeIndex-9)*10;
			if(testLatitude<-90){
				break;
			}
			double lat1 = Math.toRadians(newLatitude);
			double lat2 = Math.toRadians(testLatitude);
			double latDif = Math.toRadians(testLatitude - newLatitude);
			double lonDif = Math.toRadians(testLongitude - newLongitude);
			double a = Math.sin(latDif/2)*Math.sin(latDif/2)+Math.cos(lat1)*Math.cos(lat2)*Math.sin(lonDif/2)*Math.sin(lonDif/2);
			double c = 2*Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
			distance = 6371*c/1.609344;
			if(distance!=0 && distance<radius){
				System.out.println("vertical distance between two latitudes: " + distance + ". latitude1: "+newLatitude+" latitude2: "+testLatitude);
			}
			if(distance<radius || curLatitudeIndex==latitudeIndex){
				curLatitudeIndex--;
			}
		}
		latitudeIndexToMin = latitudeIndex - curLatitudeIndex - 1;
		
		
		System.out.println();
		System.out.println("Number of additional buckets going up in longitude direction: "+longitudeIndexToMax);
		System.out.println("Number of additional buckets going down in longitude direction: "+longitudeIndexToMin);
		System.out.println("Number of additional buckets going up in latitude direction: "+latitudeIndexToMax);
		System.out.println("Number of additional buckets going down in latitude direction: "+latitudeIndexToMin);
		System.out.println();
		
		double initTime = System.nanoTime();
		System.out.println();
		for(int i=longitudeIndex-longitudeIndexToMin; i<=longitudeIndex+longitudeIndexToMax; i++){
			for(int j=latitudeIndex-latitudeIndexToMin; j<=latitudeIndex+latitudeIndexToMax; j++){
				if(hashTable.userArray[i][j]!=null){
					User currentUser = hashTable.userArray[i][j];
					while(currentUser!=null){
						double lat1 = Math.toRadians(newLatitude);
						double lat2 = Math.toRadians(currentUser.latitude);
						double latDif = Math.toRadians(currentUser.latitude - newLatitude);
						double lonDif = Math.toRadians(currentUser.longitude - newLongitude);
						double a = Math.sin(latDif/2)*Math.sin(latDif/2)+Math.cos(lat1)*Math.cos(lat2)*Math.sin(lonDif/2)*Math.sin(lonDif/2);
						double c = 2*Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
						distance = 6371*c/1.609344;
						if(distance<=radius && (interestedGender.toUpperCase().charAt(0) == currentUser.gender.charAt(0))){
							System.out.println("User"+currentUser.id+", longitude: "+currentUser.longitude +", latitude: "
									+currentUser.latitude+", gender: "+currentUser.gender);
						}
						currentUser = currentUser.next;
					}
				}
			}
		}
		double finalTime = System.nanoTime();
		double time = finalTime - initTime;
		System.out.println();
		System.out.println("The search time is: "+time);
	}
}
