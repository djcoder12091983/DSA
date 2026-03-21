import java.io.*;
import java.util.*;
 
public class ColoringTree {
	
	// recursive result detail
	class RecursiveResult {
		int tentativeCrowdCount = 0;
		//boolean crowded = false;
		boolean terminal = false;
		int totalCrowdedCount = 0;
		
		RecursiveResult() {
			// empty
		}
	}
	
	// city node in tree like area
	class City {
		int city;
		boolean terminal;
		
		public City(int city) {
			this.city = city;
		}
		
		@Override
		public boolean equals(Object obj) {
			if(obj == null) {
				return false;
			}
			if(obj == this) {
				return true;
			}
			if(obj instanceof City) {
				return ((City)obj).city == city;
			}
			return false;
		}
		
		@Override
		public int hashCode() {
			return this.city;
		}
	}
	
	Map<Integer, Set<City>> tree = new HashMap<>();
	Set<Integer> visited = new HashSet<>();
	Map<Integer, City> tmap = new HashMap<>();
	
	// assigns terminal flag
	void terminal(int city) {
		tmap.get(city).terminal = true;
	}
 
	// add connectedness
	void addCity(int city1, int city2) {
		
		City c1 = tmap.get(city1);
		if(c1 == null) {
			c1 = new City(city1);
			tmap.put(city1, c1);
		}
		City c2 = tmap.get(city2);
		if(c2 == null) {
			c2 = new City(city2);
			tmap.put(city2, c2);
		}
		
		// two way linking
		
		// city1 -> city2 link
		Set<City> cities = tree.get(city1);
		if(cities == null) {
			cities = new HashSet<>(2);
			tree.put(city1, cities);
		}
		cities.add(c2);
		
		// city2 -> city1 link
		cities = tree.get(city2);
		if(cities == null) {
			cities = new HashSet<>(2);
			tree.put(city2, cities);
		}
		cities.add(c1);
	}
	
	int findCrowd() {
		RecursiveResult res = findCrowd1(tmap.get(1)); // starts with city 1
		return res.totalCrowdedCount;
	}
	
	// find crowd after simulations
	RecursiveResult findCrowd1(City city) {
		
		visited.add(city.city); // marked as visited
		
		Set<City> cities = tree.get(city.city);
		if(cities.size() == 1) {
			// degree 1, leaf
			RecursiveResult result = new RecursiveResult();
			if(city.terminal) {
				// bus terminal
				result.totalCrowdedCount = 1;
				result.terminal = true;
			}
			return result;
		}
		
		int tc = 0; // terminal count
		Collection<RecursiveResult> sresult = new ArrayList<>(cities.size()); // sub result
		for(City child : cities) {
			
			if(visited.contains(child.city)) {
				// skip visited
				continue;
			}
			
			RecursiveResult r = findCrowd1(child); // recursive call
			if(r.terminal) {
				// terminal exists
				tc++;
			}
			sresult.add(r);
		}
		RecursiveResult result = new RecursiveResult();
		result.terminal = city.terminal || tc > 0;
		boolean f = (tc >= 1 && city.terminal) || tc > 1;
		result.totalCrowdedCount = (city.terminal || tc > 1) ? 1 : 0; // current node
		// merge result
		for(RecursiveResult sr : sresult) {
			if(f) {
				// mark tentative crowd as crowded
				result.totalCrowdedCount += sr.tentativeCrowdCount + sr.totalCrowdedCount;
			} else {
				result.tentativeCrowdCount += sr.tentativeCrowdCount;
				result.totalCrowdedCount += sr.totalCrowdedCount;
			}
		}
		if(tc == 1 && !city.terminal) {
			result.tentativeCrowdCount++;
		}
		
		return result;
	}
	
	public static void main(String[] args) throws Exception {
		
		ColoringTree ct = new ColoringTree();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(reader.readLine());
		int n = Integer.parseInt(tokens.nextToken());
		int k = Integer.parseInt(tokens.nextToken());
		
		for(int i = 1 ;i < n; i ++) {
			String line = reader.readLine();
			tokens = new StringTokenizer(line);
			// add connectedness
			ct.addCity(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()));
		}
		tokens = new StringTokenizer(reader.readLine());
		// assigns terminal flag
		for(int i = 0 ;i < k; i ++) {
			ct.terminal(Integer.parseInt(tokens.nextToken()));
		}
		
		// result
		System.out.println(ct.findCrowd());
	}
}