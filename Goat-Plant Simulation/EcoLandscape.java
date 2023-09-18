import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;


public class EcoLandscape {

    /**
     * The underlying grid of EcoCells
     */
    private EcoCell[][] landscape;

    /**
     * The original number of rows
     */
    private int row;

    /**
     * The original number of columns
     */
    private int column;

    /**
     * Constructs a Landscape of the specified number of rows and columns.
     * Each Cell is initially alive with probability specified by chance.
     * 
     * @param rows    the number of rows in the EcoLandscape
     * @param columns the number of columns in the EcoLandscape
     * @param chance  the probability each species is a goat
     */
    public EcoLandscape(int rows, int columns, double chance) {
        this.row = rows;
        this.column = columns;
        landscape = new EcoCell[rows][columns];
        Random ran = new Random();
        for (int i =0; i<rows; i++){
            for (int j = 0; j<columns; j++){
                int randomChance = ran.nextInt(101);
                if (randomChance < chance){
                    landscape[i][j] = new EcoCell("G");
                } else{
                    landscape[i][j] =  new EcoCell("P");
                }
            }
        }

    }

    /**
     * Returns the number of rows in the EcoLandscape.
     * 
     * @return the number of rows in the EcoLandscape
     */
    public int getRows() {
        return landscape.length;
    }

    /**
     * Returns the number of columns in the EcoLandscape.
     * @return the number of columns in the EcoLandscape
     */
    public int getCols() {
        return landscape[0].length;
    }

    /**
     * Returns the Cell specified the given row and column.
     * 
     * @param row the row of the desired EcoCell
     * @param col the column of the desired EcoCell
     * @return the Cell specified the given row and column
     */
    public EcoCell getCell(int row, int col) {
        return landscape[row][col];
    }

    /**
     * Returns a String representation of the EcoLandscape.
     */
    public String toString() {
        String res = "";
        res +="\n";
        for (int i =0; i < this.getRows(); i++){
            res += i + " | ";
            for (int j = 0; j<this.getCols(); j++){
                if (j < this.getCols() - 1){
                res += this.landscape[i][j] + ", ";
                } else{
                    res += this.landscape[i][j];
                }
            }
            res += "\n";
        }
        return res;
    }

    /**
     * Returns an ArrayList of the neighboring Cells to the specified location.
     * 
     * @param row the row of the specified EcoCell
     * @param col the column of the specified EcoCell
     * @return an ArrayList of the neighboring EcoCells to the specified location
     */
    public ArrayList<EcoCell> getNeighbors(int row, int col) {
        ArrayList<EcoCell> neighbor1 = new ArrayList<>();
        for (int i = row-1; i<row+2; i++){
            for (int j = col - 1; j<col+2; j++){
                if (i > -1 && j>-1 && (i!=row || j!=col) && (i<this.getRows()) && (j < this.getCols())){
                    neighbor1.add(this.landscape[i][j]);
                }
            }
        }
        return neighbor1;
    }

    /**
     * Advances the current EcoLandscape by one step. 
     */
    public void advance() {
        EcoCell[][] temp_landscape = new EcoCell[this.getRows()][this.getCols()];
        for (int i =0; i<this.getRows(); i++){
            for (int j = 0; j<this.getCols(); j++){
                EcoCell cell = getCell(i, j);
                cell.updateState(getNeighbors(i, j));
                temp_landscape[i][j] = cell;  //settin the new cell in temp landscape to dead or alive according to its neighbors
            }
        }
        this.landscape = temp_landscape;
    }
    
    /**
     * Draws the Cell to the given Graphics object at the specified scale.
     * An alive Cell is drawn with a black color; a dead Cell is drawn gray.
     * 
     * @param g     the Graphics object on which to draw
     * @param scale the scale of the representation of this EcoCell
     */
    public void draw(Graphics g, int scale) {
        for (int x = 0; x < getRows(); x++) {
            for (int y = 0; y < getCols(); y++) {
                if (this.getCell(x, y).getType() == "G" && this.getCell(x, y).getAlive()){   
                    g.setColor(Color.RED);     //Draw red for an alive goat
                    g.fillOval(x * scale, y * scale, scale, scale);
                } else if (this.getCell(x, y).getType() == "P" && this.getCell(x, y).getAlive()){
                    g.setColor(Color.BLUE);   //draw Blue for an alive plant
                    g.fillOval(x * scale, y * scale, scale, scale);
                    }
                }
            }
        }
    /**
     * returns an array with the total number of alive species for each type
     * @return array that hold total number of alive goat and alive plant
     */
    public int[] totalAliveSpecies(){
        int[] res = {0, 0};
        for (int i =0; i<this.row; i++){
            for (int j = 0; j<this.column; j++){
                if (this.getCell(i, j).getType() == "G" && this.getCell(i, j).getAlive()){
                    res[0]++;
                } else if (this.getCell(i, j).getType() == "P" && this.getCell(i, j).getAlive()){
                    res[1]++;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        EcoLandscape ls = new EcoLandscape(5, 5, 50);
        System.out.println(ls);
        System.out.println(ls.totalAliveSpecies()[0]);
        ls.advance();
        System.out.println(ls);
        System.out.println(ls.totalAliveSpecies()[1]);
        System.out.println(ls.getNeighbors(4, 4));;
    }
}

