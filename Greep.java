import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

/**
 * A Greep is an alien creature that likes to collect tomatoes.
 * 
 * @author (Jiwon Jeon)
 * @version 0.1
 * Note: this solution has improved an incomplete source code 
 * open to a discussion board at Greenfoot website.
 */
public class Greep extends Creature
{
    // Remember: you cannot extend the Greep's memory. So:
    // no additional fields (other than final fields) allowed in this class!
    
    /**
     * Default constructor for testing purposes.
     */
    public Greep()
    {
        this(null);
    }

    
    /**
     * Create a Greep with its home space ship.
     */
    public Greep(Ship ship)
    {
        super(ship);
    }
    

    /**
     * Do what a greep's gotta do.
     */
    public void act()
    {
        super.act();   // do not delete! leave as first statement in act().
        if (carryingTomato()) {
            if (atShip()) {
                dropTomato();
                setFlag(1, false);
                move();
            }
            else {
                if(randomChance(20)){
                    turnHome();
                    atBoundary();
                }
                atBoundary();
                checkFood();
                atBoundary();
                move();
            }
        }
        else {
            atBoundary();
            checkFood();
            atBoundary();
            moveForTomato();
        }
    }
    
    /**
     * Turn if at the boundary of water or world edge..
     */
    private void atBoundary(){
        if(atWater()){
            turn(Greenfoot.getRandomNumber(30));
            moveForTomato();
        }
        else{
            moveForTomato();
        }
        
        if(atWorldEdge()){
            turn(Greenfoot.getRandomNumber(30));
            moveForTomato();
        }
        
    }
    
    /**
     * Control movement to pick up tomatoes.
     */
    public void moveForTomato(){
        Greep others = (Greep) getOneIntersectingObject(Greep.class);
        if(!getFlag(1)){
            move();
        }
        if(getFlag(1)){
            move(0);        
        }
    }
    
    /**
     * Is there any food here where we are? If so, try to load some!
     */
    public void checkFood()
    {
        // check whether there's a tomato pile here
        TomatoPile tomatoes = (TomatoPile) getOneIntersectingObject(TomatoPile.class);
        Greep others = (Greep) getOneIntersectingObject(Greep.class);
        if (tomatoes != null) {
            setFlag(1,true);
            loadTomato();
            // Note: this attempts to load a tomato onto *another* Greep. It won't
            // do anything if we are alone here.
        }
    }


    /**
     * This method specifies the name of the author (for display on the result board).
     */
    public static String getAuthorName()
    {
        return "Jiwon Jeon";  // write your name here!
    }


    /**
     * This method specifies the image we want displayed at any time. (No need 
     * to change this for the competition.)
     */
    public String getCurrentImage()
    {
        if (carryingTomato())
            return "greep-with-food.png";
        else
            return "greep.png";
    }
}