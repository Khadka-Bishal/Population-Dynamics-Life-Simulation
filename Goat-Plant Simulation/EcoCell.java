import java.util.ArrayList;
import java.util.Random;

public class EcoCell{
    /**
     * The status of the Cell.
     */

    private String type; //type of the cell : Goat / Plant
    private boolean edible; //if it is a plant that the goat feeds on
    private int height;  //height of the plant
    private boolean alive; //alive status of the goat or plant
    private Random ran = new Random();  //random object

    /**
     * Constructs either a plant or a goat with corresponding attributes
     */
    public EcoCell() {
        ArrayList<String> plantType = new ArrayList<>();
        plantType.add("G");
        plantType.add("P");
        this.type = plantType.get(ran.nextInt(2)); 
        if (this.type == "G") {
            this.alive = true;
        } else if (type == "P"){
            this.alive = true;
            this.height = ran.nextInt(1,10);
            ArrayList<Boolean> plant_edible = new ArrayList<>();
            plant_edible.add(true);
            plant_edible.add(false);
            this.edible = plant_edible.get(ran.nextInt(2));  //assigning random edibility
        }
    }

    /**
     * Constructs either a plant or a goat with corresponding attributes
     */

    public EcoCell(String typeOfSpecies) {
        if (typeOfSpecies == "G") {
            this.type = typeOfSpecies;
            this.alive = true;
        } else if (typeOfSpecies == "P"){
            this.type = typeOfSpecies;
            this.alive = true;
            this.height = ran.nextInt(1,10);
            ArrayList<Boolean> plant_edible = new ArrayList<>();
            plant_edible.add(true);
            plant_edible.add(false);
            this.edible = plant_edible.get(ran.nextInt(2));  //assigning random edibility
        }
    }
    /**
     * Returns the type of species
     * 
     * @return the type of species
     */


    public String getType(){
        return this.type;
    }

    /**
     * Returns whether the cell is currently alive.
     * 
     * @return whether the cell is currently alive
     */
    public boolean getAlive() {
        return this.alive;
    }

    /**
     * Returns the height of the plant
     * 
     * @return the height of the plant
     */

    public int getHeight(){
        return this.height;
    }

    /**
     * Returns whether the plant is edible to goat
     * 
     * @return whether the plant is edible to goat
     */

    public boolean getEdible(){
        return this.edible;
    }

    /**
     * sets the alive status
     */

    public void setAlive(boolean status) {
        this.alive = status;
    }

    /**
     * sets the height
     */
    
    public void setHeight(int newHeight){
        this.height = newHeight;
    }

    /**
     * sets the edibility of the plant
     */
    public void setEdible(boolean isEdible){
        this.edible = isEdible;
    }

    /**
     * Updates the state of the Cell.
     * For goat: rules of the Conway's game of life is implemented.
     * For plant: rules of the Conway's game of life is implemented with some more conditions
     * If the plant's height is less or equal to 5 ft and it is edible to goat and if it is surround by more than 3 goats, the plant dies
     * the plant also gets alive if there are only 2 neighbors around as I assumed it is easier for plants to reproduce
     * 
     * @param neighbors An ArrayList of Cells
     */
    public void updateState(ArrayList<EcoCell> neighbors) {  
        int numAliveGoats = 0;
        int numAlivePlants = 0;
        for (EcoCell neighbor :  neighbors){
            if (neighbor.getType() == "G" && neighbor.getAlive()){
                numAliveGoats++;
            } else if (neighbor.getType() == "P" && neighbor.getAlive()){
                numAlivePlants++;
            }
        }

        if (this.getType()=="G"){
            if (this.getAlive()){
                if (numAliveGoats < 2 || numAliveGoats > 3){ //underpopulation || overpopulation 
                    this.setAlive(false);
                }
            }else{
                if (numAliveGoats == 3){
                    this.setAlive(true);
                } 
            }
        } else if (this.getType() == "P"){
            if(this.getAlive()){
                if (numAlivePlants < 2 || numAlivePlants > 3){
                    this.setAlive(false);
                }else if (this.getHeight() <=5 && this.getEdible() == true){
                    if (numAliveGoats > 3){
                        this.setAlive(false);
                    }
                } 
            }else {
                if (numAlivePlants == 2 | numAlivePlants == 3){
                    this.setAlive(true);
                }
            }
        }
    }
 

    /**
     * Returns a String representation of this EcoCell.
     * 
     * @return TypeofSpecies Height Edible_status AliveStatus for Plant and TypeofSpecies AliveStatus for Goat
     */
    public String toString() {
        String res = "";
        if (this.getType() == "P"){
            res += "P" + this.getHeight();
            if (this.getEdible()){
                res += "E";
            } else{
                res += "NE";
            }
            if (this.getAlive()){
                res += 1;
            } else{
                res += 0;
            }    
            
        } else{
            res += "G";
            if (this.getAlive()){
                res += 1;
            } else{
                res += 0;
            }   
        }
        return res;
    }

    public static void main(String[] args) {
        EcoCell test = new EcoCell();
        System.out.println(test);
        EcoCell test1 = new EcoCell();
        System.out.println(test1);
    }
}