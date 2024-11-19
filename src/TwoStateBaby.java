import java.util.Arrays;

public class TwoStateBaby {
    static final String[] stateNames = {"awake" , "sleeipng"};
    static final int AWAKE = 0;
    static final int SLEEPING = 1;
    double[][] counts = new double[2][2];


    private int currentState;
    double[][] transitions = { { 0.3, 0.7},
                                {0.4, 0.6}  };
    public TwoStateBaby(int startState){
        currentState = startState;
    }

    public double[][] train(int[] stateSequence){
        double aToS = 0, sToA = 0, sToS = 0, aToA = 0;
        double aToSProb = 0, sToAProb = 0, sToSProb = 0, aToAProb = 0;

        // to make the 2d array of probabilities, loop over statesequence and find transitions. then use that to adjust probability.
        for (int i = 0; i < stateSequence.length - 1; i++) {
            if(stateSequence[i] == AWAKE && stateSequence[i+1] == SLEEPING) aToS++;
            if(stateSequence[i] == SLEEPING && stateSequence[i+1] == AWAKE) sToA++;
            if(stateSequence[i] == SLEEPING && stateSequence[i+1] == SLEEPING) sToS++;
            if(stateSequence[i] == AWAKE && stateSequence[i+1] == AWAKE) aToA++;
        }


        aToSProb = aToS / (aToS + aToA);
        aToAProb = aToA / (aToS + aToA);
        sToAProb = sToA / (sToA + sToS);
        sToSProb = sToS / (sToA + sToS);
        counts[0][0] = aToAProb;
        counts[0][1] = aToSProb;
        counts[1][0] = sToAProb;
        counts[1][1] = sToSProb;

        return counts;
    }

    public int mostLikelyNextState(){
        if(transitions[currentState][AWAKE] > transitions[currentState][SLEEPING]){
            return AWAKE;
        }else{
            return SLEEPING;
        }
    }

    public int getNextState(){
        double p = Math.random();

        if(p <= transitions[currentState][AWAKE]){
            return AWAKE;
        }else{
            return SLEEPING;
        }
    }

    public void transitionToNextState(){
        currentState = getNextState();
    }

    public void displayBabyStates(int n){
        for (int i = 0; i < n; i++) {
            String state = " ";
            int stateNum = getNextState();
            if(stateNum == 0) state = "AWAKE";
            if(stateNum == 1) state = "SLEEPING";
            System.out.println(state);
        }
    }

    @Override
    public String toString() {
        return "TwoStateBaby{" +
                "counts=" + Arrays.toString(counts) +
                '}';
    }
}
