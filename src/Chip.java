import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.GraphicsText;
import java.awt.Color;

/**
 * Represents a casino chip with a specific value.
 */
public class Chip{
    private Ellipse chip;
    private double value;
    private GraphicsText valueText;

    /**
     * Constructs a chip object with the given parameters.
     *
     * @param x the x-coordinate of the chip's center
     * @param y the y-coordinate of the chip's center
     * @param width the width of the chip
     * @param height the height of the chip
     * @param value the monetary value of the chip
     */
    public Chip(double x, double y, double width, double height,double value) {
        chip = new Ellipse(x, y, width, height);
        this.value = value;
        valueText = new GraphicsText(Double.toString(value));
        valueText.setCenter(x+40,y+40);

    }

    /**
     * Sets the color of the chip based on its value.
     */
    public void setColorBasedOnValue(){
        if(value==1){
            chip.setFillColor(Color.WHITE);
        }
        if(value==5){
            chip.setFillColor(Color.RED);
        }
        if(value==10){
            chip.setFillColor(Color.GREEN);
        }
        if(value==25){
            chip.setFillColor(Color.BLUE);
        }
    }

    /**
     * Retrieves the monetary value of the chip.
     */
    public double getValue(){
        return value;
    }
    
    /**
     * Moves the chip and its value text to the specified location.
     */
    public void moveChipAndText(double xLocation, double yLocation){
        chip.setCenter(xLocation,yLocation);
        valueText.setCenter(xLocation,yLocation);
    }
    
    /**
     * Retrieves the graphical representation of the chip.
     */
    public GraphicsObject getEllipse(){
        return chip;
    }
    
     /**
     * Retrieves the graphical representation of the value displayed on the chip.
     */
    public GraphicsObject getText(){
        return valueText;
    }


}



