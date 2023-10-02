package agent_ABCD; 

public class Environment {
	public static final Action MOVE_LEFT = new DynamicAction("LEFT");
	public static final Action MOVE_RIGHT = new DynamicAction("RIGHT");
	public static final Action SUCK_DIRT = new DynamicAction("SUCK");
	public static final Action MOVE_UP = new DynamicAction("UP");
	public static final Action MOVE_DOWN = new DynamicAction("DOWN");
	public static final String LOCATION_A = "A";
	public static final String LOCATION_B = "B";
	public static final String LOCATION_C = "C";
	public static final String LOCATION_D = "D";
	public static int k =0;
	public enum LocationState {
		CLEAN, DIRTY
	}

	private EnvironmentState envState;
	private boolean isDone = false;// all squares are CLEAN
	private Agent agent = null;

	public Environment(LocationState locAState, LocationState locBState) {
		envState = new EnvironmentState(locAState, locBState);
	}

	// add an agent into the enviroment
	public void addAgent(Agent agent, String location) {
		// TODO
		this.agent = agent;
		envState.setAgentLocation(location);
	}

	public EnvironmentState getCurrentState() {
		return this.envState;
	}

	// Update enviroment state when agent do an action
	public EnvironmentState executeAction(Action action) {
		// TODO
		if (action == Environment.SUCK_DIRT) {
			String agentLocation =  envState.getAgentLocation();
			envState.setLocationState(agentLocation, LocationState.CLEAN);
		} else if(action == Environment.MOVE_RIGHT && envState.getAgentLocation() == LOCATION_A) {
			envState.setAgentLocation(LOCATION_B);
		} else if(action == Environment.MOVE_LEFT && envState.getAgentLocation() == LOCATION_B) {
			envState.setAgentLocation(LOCATION_A);
		}else if(action == Environment.MOVE_LEFT && envState.getAgentLocation() == LOCATION_C) {
			envState.setAgentLocation(LOCATION_D);
		} else if(action == Environment.MOVE_RIGHT && envState.getAgentLocation() == LOCATION_D) {
			envState.setAgentLocation(LOCATION_C);
		} else if(action == Environment.MOVE_UP && envState.getAgentLocation() == LOCATION_D) {
			envState.setAgentLocation(LOCATION_A);
		} else if(action == Environment.MOVE_UP && envState.getAgentLocation() == LOCATION_C) {
			envState.setAgentLocation(LOCATION_B);
		} else if(action == Environment.MOVE_DOWN && envState.getAgentLocation() == LOCATION_B) {
			envState.setAgentLocation(LOCATION_C);
		} else if(action == Environment.MOVE_DOWN && envState.getAgentLocation() == LOCATION_A) {
			envState.setAgentLocation(LOCATION_D);
		}
		return envState;
	}

	// get percept<AgentLocation, LocationState> at the current location where agent
	// is in.
	public Percept getPerceptSeenBy() {
		// TODO
		Percept percept = new Percept(envState.getAgentLocation(), envState.getLocationState(envState.getAgentLocation()));
		return percept;
	}

	public void step() {
		envState.display();
		String agentLocation = this.envState.getAgentLocation();
		Action anAction = agent.execute(getPerceptSeenBy());
		if (anAction == Environment.SUCK_DIRT) {
			k += 500;
		} else if (anAction == NoOpAction.NO_OP) {
			k -= 100;
		} else k -= 10;
		EnvironmentState es = executeAction(anAction);

		System.out.println("Agent Loc.: " + agentLocation + "\tAction: " + anAction);
		System.out.println("Score: "+k);
		if ((es.getLocationState(LOCATION_A) == LocationState.CLEAN)
				&& (es.getLocationState(LOCATION_B) == LocationState.CLEAN)
				&& (es.getLocationState(LOCATION_C) == LocationState.CLEAN)
				&& (es.getLocationState(LOCATION_D) == LocationState.CLEAN))
			isDone = true;// if both squares are clean, then agent do not need to do any action
		es.display();
	}

	public void step(int n) {
		for (int i = 0; i < n; i++) {
			step();
			System.out.println("-------------------------");
		}
	}

	public void stepUntilDone() {
		int i = 0;

		while (!isDone) {
			System.out.println("step: " + i++);
			step();
		}
	}
}
