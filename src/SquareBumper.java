import physics.*;

public class SquareBumper implements Gadget{
    private final LineSegment top, bottom, left, right;
    private final int xCor, yCor;
    
    private final double COEFFICIENT_OF_REFLECTION = 1.0;
    
    /**
     * An immutable class representing a square bumper.
     * 
     * Abstraction function: represents a square bumper with a specific location on the board
     * Rep invariant: xCor and yCor are in [1,20]
     * 
     * Size and shape: a square shape with edge length 1L
     * Orientation: not applicable (symmetric to 90 degree rotations)
     * Coefficient of reflection: 1.0
     * Trigger: generated whenever the ball hits it
     * Action: none, just adjusts the ball's velocity
     * 
     */
    
    /**
     * Make new square bumper
     * @param xCor
     *          x-coordinate of the desired upper-left of the bumper's bounding box
     *          has to be in the range [1,20] (needs to be in the playing area)
     * @param yCor
     *          y-coordinate of the desired upper-left of the bumper's bounding box
     *          has to be in the range [1,20] (needs to be in the playing area)
     */
    public SquareBumper(int xCor, int yCor){
        this.xCor = xCor;
        this.yCor = yCor;
        this.top = new LineSegment(xCor, yCor, xCor+1, yCor);
        this.bottom = new LineSegment(xCor, yCor+1, xCor+1, yCor+1);
        this.left = new LineSegment(xCor, yCor, xCor, yCor+1);
        this.right = new LineSegment(xCor+1, yCor, xCor+1, yCor+1);
        checkRep();
    }
    
    public boolean isOccupying(int x, int y){
        return x == xCor && y == yCor;
    }
    
    public boolean isEmpty(){
        return false;
    }
    
    /**
     * Mutates the ball's velocity when the ball hits the bumper.
     * 
     * @param ball
     *          the ball which hit the bumper
     */
    public void Action(Ball ball){
       //TODO 
    }
    
//    /**
//     * Get the x-coordinate of the upper-left corner of the square bumper's bounding box
//     * @return xCor of upper-left corner of the square bumper's bounding box
//     */
//    public int getXCor(){
//        return xCor;
//    }
//    
//    /**
//     * Get the y-coordinate of the upper-left corner of the square bumper's bounding box
//     * @return yCor of upper-left corner of the square bumper's bounding box
//     */
//    public int getYCor(){
//        return yCor;
//    }
//    
//    /**
//     * Get the LineSegment representing the top of the bumper
//     * @return the LineSegment representing the top of the bumper
//     */
//    public LineSegment getTop(){
//        return top;
//    }
//    
//    /**
//     * Get the LineSegment representing the bottom of the bumper
//     * @return the LineSegment representing the bottom of the bumper
//     */
//    public LineSegment getBottom(){
//        return bottom;
//    }
//    
//    /**
//     * Get the LineSegment representing the left side of the bumper
//     * @return the LineSegment representing the left side of the bumper
//     */
//    public LineSegment getLeft(){
//        return left;
//    }
//    
//    /**
//     * Get the LineSegment representing the right side of the bumper
//     * @return the LineSegment representing the right side of the bumper
//     */
//    public LineSegment getRight(){
//        return right;
//    }
    
    private void checkRep(){
        assert xCor >= 1 && xCor <= 20 && yCor >= 1 && yCor <= 20;
    }
    
    @Override
    public String toString(){
        return "#";
    }
    
}
