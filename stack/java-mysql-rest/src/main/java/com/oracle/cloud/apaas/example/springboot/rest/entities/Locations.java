package com.oracle.cloud.apaas.example.springboot.rest.entities;

public final class Locations {
	
	private static String[][] locations = {  
			  {    "loc-0",    "200 Oracle Parkway",    "",    "Redwood Shores",    "CA",    "94065",    "USA",    "37.530737",    "-122.265557"  },
			  {    "loc-1",    "100 Main Street",    "",    "San Francisco",    "CA",    "94135",    "USA",    "37.791826",    "-122.394953"  },
			  {    "loc-2",    "300 Second Street",    "",    "Fremont",    "CA",    "94536",    "USA",    "37.552713",    "-121.994713"  },
			  {    "loc-3",    "200 First Street",    "",    "Livermore",    "CA",    "94566",    "USA",    "37.660406",    "-121.876038" },
			  {    "loc-4",    "100 Park Street",    "",    "Alameda",    "CA",    "94501",    "USA",    "37.763389",    "-122.243514"  },
			  {    "loc-5",    "4500 Broadway",    "",    "Oakland",    "CA",    "94611",    "USA",    "37.832892",    "-122.253260"  },
			  {    "loc-6",    "4226 Piedmont Ave",    "",    "Oakland",    "CA",    "94611",    "USA",    "37.827911",    "-122.249982"  },
			  {    "loc-7",    "5655 College Ave",    "",    "Oakland",    "CA",    "94618",    "USA",    "37.843693",    "-122.251890" },
			  {    "loc-8",    "1200 University Ave",    "",    "Berkeley",    "CA",    "94702",    "USA",    "37.869368",    "-122.289508" },
			  {    "loc-9",    "2100 Ward Street",    "",    "Berkeley",    "CA",    "94702",    "USA",    "37.859333",    "-122.266792"  },
			  {    "loc-10",    "2181 Shattuck Avenue",    "",    "Berkeley",    "CA",    "94702",    "USA",    "37.869801",    "-122.267527" },
			  {    "loc-11",    "1200 Shattuck Avenue",    "",    "Berkeley",    "CA",    "94702",    "USA",    "37.869801",    "-122.267527" },
			  {    "loc-12",    "5655 College Ave",    "",    "Oakland",    "CA",    "94618",    "USA",    "37.843693",    "-122.251890"  },
			  {    "loc-13",    "4226 Piedmont Ave",    "",    "Oakland",    "CA",    "94611",    "USA",    "37.827911",    "-122.249982" },
			  {    "loc-14",    "100 Main Street",    "",    "San Francisco",    "CA",    "94135",    "USA",    "37.791826",    "-122.394953" },
			  {    "loc-15",    "300 Second Street",    "",    "Fremont",    "CA",    "94536",    "USA",    "37.552713",    "-121.994713" }
			};
	
	
	public static final Location getLocation(String id) {
		Location location = null;
		
		for (int i = 0; i < locations.length; i++) {
			if (locations[i][0].equals(id)) {
				location = new Location(locations[i][0], locations[i][1], locations[i][2], locations[i][3], locations[i][4], 
						locations[i][5], locations[i][6], locations[i][7], locations[i][8]);
			}
		}
		
		return location;
	}
	
	public static void main (String args[]) {
		System.out.println(getLocation("loc-13").getLatitude());
	}
}
