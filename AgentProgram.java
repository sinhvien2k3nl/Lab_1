package agent_ABCD;


import java.util.Random;

import agent_AB.Environment.LocationState;

public class AgentProgram {

	public Action execute(Percept p) {// location, status
		//TODO
		if (p.getLocationState() == agent_ABCD.Environment.LocationState.DIRTY) {
			return Environment.SUCK_DIRT;
		}else if (p.getAgentLocation() == Environment.LOCATION_A) {
			return randomMove(Environment.LOCATION_A);
		} else if (p.getAgentLocation() == Environment.LOCATION_B) {
			return randomMove(Environment.LOCATION_B);
		}else if (p.getAgentLocation() == Environment.LOCATION_C) {
			return randomMove(Environment.LOCATION_C);
		} else if (p.getAgentLocation() == Environment.LOCATION_D) {
			return randomMove(Environment.LOCATION_D);
		}
		return NoOpAction.NO_OP;
	}
	static Action randomMove(String location){
		Random generator = new Random();
		int value = generator.nextInt(2);
		if (location == Environment.LOCATION_A ) {
			if (value == 1)
				return Environment.MOVE_RIGHT;
		} else if (value == 0) {
			return Environment.MOVE_DOWN;
		} else if (location == Environment.LOCATION_B) {
			if (value == 1)
				return Environment.MOVE_LEFT;
		} else if (value == 0) {
			return Environment.MOVE_DOWN;
		} else if (location == Environment.LOCATION_C) {
			if (value == 1)
				return Environment.MOVE_LEFT;
		} else if (value == 0) {
			return Environment.MOVE_UP;
		} else if (location == Environment.LOCATION_D) {
			if (value == 1)
				return Environment.MOVE_RIGHT;
		} else if (value == 0) {
			return Environment.MOVE_UP;
		}
		return NoOpAction.NO_OP;
	}
}