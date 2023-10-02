package agent_AB;

import agent_AB.Environment.LocationState;

public class AgentProgram {

	public Action execute(Percept p) {// location, status
		//TODO
		if (p.getLocationState() == LocationState.DIRTY) {
			return Environment.SUCK_DIRT;
		}else if (p.getAgentLocation() == Environment.LOCATION_A) {
			return Environment.MOVE_RIGHT;
		} else if (p.getAgentLocation() == Environment.LOCATION_B) {
			return Environment.MOVE_LEFT;
		}
		return NoOpAction.NO_OP;
	}
}