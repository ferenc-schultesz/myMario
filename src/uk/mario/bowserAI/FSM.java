package uk.mario.bowserAI;

/**
 * Finite State Machines (FSM).
 */
public class FSM<T> {
    
    /** The current state. */
    private FSMState<T> state;
    
    /** The object being controlled by this FSM. */
    private final T context;
    
    /**
     * Initialise a new FSM. Note that client code must invoke
     * start() on the FSM before any other methods.
     * @param context the object which will be controlled by this FSM
     */
    public FSM(T context) {
        this.context = context;
        state = null;
    }
    
    /**
     * Initialise a new FSM.
     * @param context the object which will be controlled by this FSM
     * @param state the initial state
     */
    public FSM(T context, FSMState<T> state) {
        this.context = context;
        start(state);
    }
    
    /**
     * Put the FSM into its initial state.
     * @param state the initial state
     */
    public void start(FSMState<T> state) {
        this.state = state;
        state.attach(this);
        state.enter();
    }
    
    /**
     * Invalidate the FSM by leaving the current state without entering
     * a new one.
     */
    public void stop() {
        state.exit();
        state = null;
    }
    
    /**
     * Call the update() method of the current state.
     */
    public void update() {
        state.update();
    }
    
    /**
     * Make a transition to a new state.
     * @param newState the new state
     */
    public void changeState(FSMState<T> newState) {
        state.exit();
        state = newState;
        state.attach(this);
        state.enter();
    }
    
    /**
     * The object being controlled by this FSM.
     * @return the object being controlled by this FSM
     */
    public T getContext() {
        return context;
    }
    
}
