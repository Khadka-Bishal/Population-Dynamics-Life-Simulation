import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class Landscape {

    /**
     * The underlying grid of Cells for Conway's Game
     */
    private Cell[][] landscape;

    /**
     * The original number of rows
     */
    private int row;

    /**
     * The original number of columns
     */
    private int column;

    // /**
    //  * The original probability each individual Cell is alive
    //  */
    // private double initialChance;

    /**
     * Constructs a Landscape of the specified number of rows and columns.
     * All Cells are initially dead.
     * 
     * @param rows    the number of rows in the Landscape
     * @param columns the number of columns in the Landscape
     */
    public Landscape(int rows, int columns) {
        this.row = rows;
        this.column = columns;
        landscape = new Cell[rows][columns];
        reset();
        for (int i =0; i<rows; i++){
            for (int j = 0; j<columns; j++){
                landscape[i][j] =  new Cell();
            }
        }
    }

    /**
     * Constructs a Landscape of the specified number of rows and columns.
     * Each Cell is initially alive with probability specified by chance.
     * 
     * @param rows    the number of rows in the Landscape
     * @param columns the number of columns in the Landscape
     * @param chance  the probability each individual Cell is initially alive
     */
    public Landscape(int rows, int columns, double chance) {
        this.row = rows;
        this.column = columns;
        landscape = new Cell[rows][columns];
        Random ran = new Random();
        for (int i =0; i<rows; i++){
            for (int j = 0; j<columns; j++){
                int randomChance = ran.nextInt(101);
                if (randomChance < chance){
                    landscape[i][j] = new Cell(true);
                } else{
                    landscape[i][j] =  new Cell(false);
                }
            }
        }

    }

    /**
     * Recreates the Landscape according to the specifications given in its initial construction.
     */
    public void reset() {
        landscape = new Cell[this.getRows()][this.getCols()];
        for (int i =0; i<this.getRows(); i++){
            for (int j = 0; j<this.getCols(); j++){
                landscape[i][j] =  new Cell(false);
            }
        }
    }

    /**
     * Returns the number of rows in the Landscape.
     * 
     * @return the number of rows in the Landscape
     */
    public int getRows() {
        return landscape.length;
    }

    /**
     * Returns the number of columns in the Landscape.
     * 
     * @return the number of columns in the Landscape
     */
    public int getCols() {
        return landscape[0].length;
    }

    /**
     * Returns the Cell specified the given row and column.
     * 
     * @param row the row of the desired Cell
     * @param col the column of the desired Cell
     * @return the Cell specified the given row and column
     */
    public Cell getCell(int row, int col) {
        return landscape[row][col];
    }

    /**
     * Returns a String representation of the Landscape.
     */
    public String toString() {
        String res = "    ";
        for (int c= 0; c<this.getRows(); c++){     ///assuming that it is not a dynamic array
            res += c + " ";
        }
        res +="\n";
        for (int i =0; i < this.getRows(); i++){
            res += i + " | ";
            for (int j = 0; j<this.getCols(); j++){
                res += this.landscape[i][j] + " ";
            }
            res += "|" + "\n";
        }
        return res;
    }

    /**
     * Returns an ArrayList of the neighboring Cells to the specified location.
     * 
     * @param row the row of the specified Cell
     * @param col the column of the specified Cell
     * @return an ArrayList of the neighboring Cells to the specified location
     */
    public ArrayList<Cell> getNeighbors(int row, int col) {
        ArrayList<Cell> neighbor1 = new ArrayList<Cell>();
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
     * Advances the current Landscape by one step. 
     */
    public void advance() {
        Landscape temp_landscape = new Landscape(this.getRows(), this.getCols());
        for (int i =0; i<this.row; i++){
            for (int j = 0; j<this.column; j++){
                boolean state = this.getCell(i, j).getAlive();  //gettting the alive status of the cell
                temp_landscape.getCell(i,j).setAlive(state);  //setting the alive status in the temp landscape the same as the landscape
                ArrayList<Cell> neigh = this.getNeighbors(i, j);  //getting the neighbors from the landscape
                temp_landscape.getCell(i, j).updateState(neigh);  //settin the new cell in temp landscape to dead or alive according to its neighbors
            }
        }
        this.landscape = temp_landscape.landscape;
    }

    /**
     * Draws the Cell to the given Graphics object at the specified scale.
     * An alive Cell is drawn with a black color; a dead Cell is drawn gray.
     * 
     * @param g     the Graphics object on which to draw
     * @param scale the scale of the representation of this Cell
     */
    public void draw(Graphics g, int scale) {
        for (int x = 0; x < getRows(); x++) {
            for (int y = 0; y < getCols(); y++) {
                g.setColor(getCell(x, y).getAlive() ? Color.BLACK : Color.gray);
                g.fillOval(x * scale, y * scale, scale, scale);
            }
        }
    }
}