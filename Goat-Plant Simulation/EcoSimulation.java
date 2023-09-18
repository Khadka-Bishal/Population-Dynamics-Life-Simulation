/*
Note: After running the program, maximize the window to see the control buttons.
*/

public class EcoSimulation {

    public static void main(String[] args) throws InterruptedException  {
        EcoLandscape landscape = new EcoLandscape(100, 100, 60);
        LandscapeDisplay display = new LandscapeDisplay(landscape, 7);
    }
}
