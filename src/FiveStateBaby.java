import java.util.ArrayList;

public class FiveStateBaby {
    static final String[] stateNames = {"SLEEPING" , "EATING", "CRYING", "PLAYING", "FUSSING"};
    int[][] table = new int[5][5];
    double[][] percent = new double[5][5];


    private static final int SLEEPING = 0;
    private static final int EATING = 1;
    private static final int CRYING = 2;
    private static final int PLAYING = 3;
    private static final int FUSSING = 4;
    private int currentState;

    public FiveStateBaby(int startState){
        currentState = startState;
    }


    public void train(int[] stateSequence){
        double[][] counts = new double[5][5];

        ArrayList<Integer> firstIndex = new ArrayList<>();
        ArrayList<Integer> lastIndex = new ArrayList<>();

        for (int i = 0; i < stateSequence.length - 1; i++) {
            firstIndex.add(stateSequence[i]);
            lastIndex.add(stateSequence[i + 1]);
        }
        for (int state = 0; state <= 4; state++) {
            for (int i = 0; i < firstIndex.size(); i++) {
                if(lastIndex.get(i) == state){
                    counts[state][lastIndex.get(i)] += 1;
                }
            }
        }

        double totalS = 0;
        double totalE = 0;
        double totalC = 0;
        double totalP = 0;
        double totalF = 0;
        for (int col = 0; col < percent[0].length; col++) {
            totalS = totalS + counts[0][col];
            totalE = totalE + counts[1][col];
            totalC = totalC + counts[2][col];
            totalP = totalP + counts[3][col];
            totalF = totalF + counts[4][col];
        }
        for (int col = 0; col < percent[0].length; col++) {
            percent[0][col] = counts[0][col] /totalS;
            percent[1][col] = counts[1][col] /totalE;
            percent[2][col] = counts[2][col] /totalC;
            percent[3][col] = counts[3][col] /totalP;
            percent[4][col] = counts[4][col] /totalF;
        }

//        double[][] counts = new double[5][5];
//
//        ArrayList<Integer> start = new ArrayList<>();
//        ArrayList<Integer> end = new ArrayList<>();
//
//        for (int i = 0; i < stateSequence.length - 1; i++) {
//            start.add(stateSequence[i]);
//            end.add(stateSequence[i + 1]);
//        }
//        for (int state = 0; state <= 4; state++) {
//            for (int i = 0; i < start.size(); i++) {
//                if(start.get(i) == state){
//                    counts[state][end.get(i)] += 1;
//                }
//            }
//        }
//
//        double totalS = 0;
//        double totalE = 0;
//        double totalC = 0;
//        double totalP = 0;
//        double totalF = 0;
//        for (int col = 0; col < percent[0].length; col++) {
//                totalS = totalS + counts[0][col];
//                totalE = totalE + counts[1][col];
//                totalC = totalC + counts[2][col];
//                totalP = totalP + counts[3][col];
//                totalF = totalF + counts[4][col];
//        }
//        for (int col = 0; col < percent[0].length; col++) {
//            percent[0][col] = counts[0][col] /totalS;
//            percent[1][col] = counts[1][col] /totalE;
//            percent[2][col] = counts[2][col] /totalC;
//            percent[3][col] = counts[3][col] /totalP;
//            percent[4][col] = counts[4][col] /totalF;
//        }
    }

    public int mostLikelyNextState(){
        int max = -1;
        if(percent[currentState][SLEEPING] > percent[currentState][EATING]) max = SLEEPING;
        else max = EATING;
        if(percent[currentState][max] > percent[currentState][CRYING]);
        else max = CRYING;
        if(percent[currentState][max] > percent[currentState][PLAYING]);
        else max = PLAYING;
        if(percent[currentState][max] > percent[currentState][FUSSING]);
        else max = FUSSING;
        return max;
    }

    public int getNextState(){
        double num = Math.random();
        if(num < percent[currentState][SLEEPING]) return SLEEPING;
        else if (num >= percent[currentState][SLEEPING] && num < (percent[currentState][SLEEPING] + percent[currentState][EATING])) return EATING;
        else if (num >= (percent[currentState][SLEEPING] + percent[currentState][EATING]) && num < (percent[currentState][SLEEPING] + percent[currentState][EATING] + percent[currentState][CRYING])) return CRYING;
        else if (num >= (percent[currentState][SLEEPING] + percent[currentState][EATING] + percent[currentState][CRYING]) && num < (percent[currentState][SLEEPING] + percent[currentState][EATING] + percent[currentState][CRYING] + percent[currentState][PLAYING])) return PLAYING;
        else return FUSSING;
    }

    public void transitionToNextState(){
        currentState = getNextState();
    }

    public void displayBabyStates(int n){
        for (int i = 0; i < n; i++) {
            String state = " ";
            int stateNumber = getNextState();
            if(stateNumber == 0) state = "SLEEPING";
            if(stateNumber == 1) state = "EATING";
            if(stateNumber == 2) state = "CRYING";
            if(stateNumber == 3) state = "PLAYING";
            if(stateNumber == 4) state = "FUSSING";
            System.out.println(state);


        }
    }
}

//        for (int i = 0; i < n; i++) {
//            System.out.println(stateNames[getNextState()]);
//        }
