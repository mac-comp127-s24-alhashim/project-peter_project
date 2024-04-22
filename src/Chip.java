import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.GraphicsText;
import java.awt.Color;


public class Chip{
    private Ellipse chip;
    private double value;
    private GraphicsText valueText;

    public Chip(double x, double y, double width, double height,double value) {
        chip = new Ellipse(x, y, width, height);
        this.value = value;
        valueText = new GraphicsText(Double.toString(value));
        valueText.setCenter(x+40,y+40);

    }


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
    
    public double getValue(){
        return value;
    }
    
    public void moveChipAndText(double xLocation, double yLocation){
        chip.setCenter(xLocation,yLocation);
        valueText.setCenter(xLocation,yLocation);
    }
    
    public GraphicsObject getEllipse(){
        return chip;
    }
    
    public GraphicsObject getText(){
        return valueText;
    }


}



