package uk.mario.bowserAI;

/**
 * State objects for Finite State Machines (FSM).
 */
public abstract class FSMState<T> {
    
    /** The FSM to which this state belongs. */
    private FSM<T> fsm;
    
    /**
     * Take actions (possibly including a state-transition) depending
     * on the current context of the FSM. 
     */
    protected abstract void update();
    
    /**
     * Take any actions necessary when the FSM makes a transition to this state
     * from a different state.
     */
    protected abstract void enter();
    
    /**
     * Take any actions necessary when the FSM makes a transition from this
     * state to a different state.
     */
    protected abstract void exit();
    
    /**
     * The FSM context (the object being controlled by the FSM).
     * @return the FSM context
     */
    protected final T getContext() {
        return fsm.getContext();
    }
    
    /**
     * Make a transition to a new state.
     * @param newState the new state
     */
    protected final void gotoState(FSMState<T> newState) {
        fsm.changeState(newState);
    }
    
    /**
     * Attach this state to a FSM. This method is normally called
     * only by the FSM itself.
     * @param fsm the FSM
     */
    protected final void attach(FSM<T> fsm) {
        this.fsm = fsm;
    }
    
}
