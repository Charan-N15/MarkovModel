public class Main {
    private static double[][] ans;

    public static void main(String[] args) {
        FiveStateBaby baby = new FiveStateBaby(0);
        int[] observationsFive = {4, 0, 2, 0, 2, 0, 2, 3, 4, 0, 4, 0, 0 , 0, 1, 1, 1, 3, 3, 3, 1, 2, 3, 1, 3, 3, 3 ,4 ,4 ,1 ,1 ,1, 0, 2, 2, 3, 0 , 0 , 2, 0 , 2, 0, 4, 4 , 1, 4, 4, 3, 4,2, 3,1 ,3,2, 3, 3,4, 1, 3, 4, 2,4};
        baby.train(observationsFive);
        baby.displayBabyStates(100);
    }
}
