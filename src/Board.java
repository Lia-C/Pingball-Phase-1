import javax.management.RuntimeErrorException;

import physics.Geometry;
import physics.LineSegment;
import physics.Vect;
import warmup.Ball;


public class Board {
    private final Ball ball;
    private OuterWall top = new OuterWall(//TODO);
    private OuterWall bottom = new OuterWall(//TODO);
    private OuterWall left = new OuterWall(//TODO);
    private OuterWall right = new OuterWall(//TODO);
    private final Gadget[] gadgets;
    private final int WIDTH = 20;
    private final int HEIGHT = 20;
    
    /**
     * Make a Benchmark Board
     * The 3 available benchmark boards are described at:
     *  http://web.mit.edu/6.005/www/fa14/projects/pb1/pingball-phase1-spec.html#benchmark_boards
     * @param boardName
     *          the name of the benchmark board you want
     *          must be one of "default", "absorber", "flippers"
     * @return
     *          a new Board representing the benchmark board
     */     
    public Board Board(String boardName){
        switch (boardName) {
        case "default":
            //TODO
                ball=
                Gadgets[] = 
                Board default = new Board(ball, gadgets);
        case "absorber":
            //TODO
            break;
        case "flippers":
            //TODO
            break;
        default:
            throw new IllegalArgumentException();
            break;
        }
    }
    
    /**
     * Make a new board
     * @param ball
     *          Ball object representing the ball
     * @param gadgets
     *          an array of the gadgets that are on the board, 
     *          NOT including the outer walls
     */
    private Board(Ball ball, Gadget[] gadgets){
        this.ball = ball;
        this.gadgets = gadgets;
    }    
    
    public void run(){
        long deltaT = (long) (1000./20.);
        while(true){
            System.out.println(this);   
            translate(deltaT); 
            try {
                Thread.sleep((long) (1000./20.));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void translate(long deltaT) {
        double deltaX = this.ball.getVelocity().x() * deltaT/1000;
        double deltaY = this.ball.getVelocity().y() * deltaT/1000;
        double newX = this.ball.getPosition().d1 + deltaX;
        double newY = this.ball.getPosition().d2 + deltaY;
        double xOver = Math.abs(newX - WIDTH);
        double yOver = Math.abs(newY - HEIGHT);
        Geometry.DoublePair newLoc = new Geometry.DoublePair(newX, newY); 
        LineSegment collisionWall = new LineSegment(0,0,0,0);
        
        if (newX >= WIDTH && newY <= HEIGHT && newY >= 0){
            collisionWall = right.getLineSegment();
        } else if (newX <= 0 && newY <= HEIGHT && newY >= 0){
            collisionWall = left.getLineSegment();
        } else if (newY >= HEIGHT && newX <= WIDTH && newX >= 0){
            collisionWall = bottom.getLineSegment();
        } else if (newY <= 0 && newX <= WIDTH && newX >= 0){
            collisionWall = top.getLineSegment();
        } else if (newX >= WIDTH && newY >= HEIGHT){
            if (xOver > yOver){ collisionWall = right.getLineSegment(); }
            else { collisionWall = bottom.getLineSegment(); }
        } else if (newX >= WIDTH && newY <= 0){
            if (xOver > yOver){ collisionWall = right.getLineSegment(); }
            else { collisionWall = top.getLineSegment(); }
        } else if (newX <= 0 && newY >= HEIGHT){ 
            if (xOver > yOver){ collisionWall = left.getLineSegment(); }
            else { collisionWall = bottom.getLineSegment(); }
        } else if (newX <= 0 && newY <= 0){
            if (xOver > yOver){ collisionWall = left.getLineSegment(); }
            else { collisionWall = top.getLineSegment(); }
        }
        
        if (newX > 0 && newX < WIDTH && newY > 0 && newY < WIDTH){
            moveWithoutCollision(newLoc);
        } else {
            moveWithCollision(newLoc, collisionWall, deltaT);
        }
    }
    
    /**
     * Moves the ball on the board if there is no collision with a wall.
     * @param newLoc
     *      //TODO
     */
    private void moveWithoutCollision(Geometry.DoublePair newLoc){
        this.ball.setPosition(newLoc);
        double x=this.ball.getPosition().d1;
        double y=this.ball.getPosition().d2;
        if ((int)x<=0){
            x=1;
            this.ball.setVelocity(new Vect(-this.ball.getVelocity().x(),this.ball.getVelocity().y()));
        }
        if ((int)x>=WIDTH-1){
            x=WIDTH-2;
            this.ball.setVelocity(new Vect(-this.ball.getVelocity().x(),this.ball.getVelocity().y()));
        }
        if ((int)y<=0){
            y=1;
            this.ball.setVelocity(new Vect(this.ball.getVelocity().x(),-this.ball.getVelocity().y()));
        }
        if ((int)y>=HEIGHT-1){
            y=HEIGHT-2;
            this.ball.setVelocity(new Vect(this.ball.getVelocity().x(),-this.ball.getVelocity().y()));
        }
        this.ball.setPosition( new Geometry.DoublePair(x, y));
    }
  
    /**
     * Moves the ball on the board if there is a collision with a wall.
     * @param newLoc
     *      //TODO
     * @param wall
     *      //TODO
     * @param deltaT
     *      //TODO
     */
    private void moveWithCollision(Geometry.DoublePair newLoc, LineSegment wall, long deltaT){
        double n = Geometry.timeUntilWallCollision(wall, this.ball.getCircle(), this.ball.getVelocity());
        if (n != Double.POSITIVE_INFINITY){
            long timeUntilCollision = (long) n;
            this.translate(timeUntilCollision);
            this.ball.setVelocity(Geometry.reflectWall(wall, this.ball.getVelocity()));
            this.translate(deltaT - timeUntilCollision);
        }
    }
    
    /**
     * Returns a printable string to represent the current state of the board.
     */
    @Override
    public String toString(){
        String[][] 
    }
}
