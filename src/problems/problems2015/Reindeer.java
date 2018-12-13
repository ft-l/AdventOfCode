package problems.problems2015;
public class Reindeer {
	public String name;
	public int speed, endurance, restTime, distance, travelTime;
	public Reindeer(String name, int speed, int endurance, int restTime) {
		this.name = name;
		this.speed = speed;
		this.endurance = endurance;
		this.restTime = restTime;
	}
	public int distanceTravelled(int travelTime) {
		int distance = 0;
		int tempEndurance = endurance;
		int tempRestTime = restTime;
		boolean travelling = true;
		for(int i = travelTime; i > 0; i--) {
			if(travelling) {
				distance += speed;
				tempEndurance -= 1;
				if(tempEndurance == 0) {
					travelling = false;
					tempEndurance = endurance;
				}
			} else {
				tempRestTime -= 1;
				if(tempRestTime == 0) {
					travelling = true;
					tempRestTime = restTime;
				}
			}
		}
		return distance;
	}
}