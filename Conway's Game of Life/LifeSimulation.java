/*
Instructions to run through terminal: 
javac LifeSimulation.java
java LifeSimulation.java int_scale int_chance int_iterationTimes
Ex. java LifeSimulation.java 6 45 200 
*/

public class LifeSimulation {
    /**
     * Simulats Game of Life 30 times
     * @param args
     * @throws InterruptedException
     */

    public static void main(String[] args) throws InterruptedException  {
        int iterations;
        int newScale;
        int newChance;
        if (args.length > 0 && args.length<4) {
            try {
                newScale = Integer.parseInt(args[0]);
                newChance= Integer.parseInt(args[1]);
                iterations = Integer.parseInt(args[2]);
            } catch (NumberFormatException e) {
                System.err.println("The argument must be an integer");
                return;
            }
        }else{
            System.out.println("You must pass at least 1 and at most 3 arguments");
            return;
        }

        Landscape landscape = new Landscape(100, 100, newChance);
        LandscapeDisplay display = new LandscapeDisplay(landscape, newScale);
        for (int i = 0; i<iterations; i++){
            Thread.sleep(250);
            landscape.advance();
            display.repaint();
        }
    }
}
