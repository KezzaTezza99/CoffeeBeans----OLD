package engine_main.physics;
import java.awt.*;

//TODO: Do I need setters?
public class AABB {

    private float x, y;                             //Position of the bottom left corner
    private float width, height;                    //Dimensions of the box

    public AABB(float x, float y, float width, float height) {
        System.out.printf("Being created with x, y: %f %f %n", x, y);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean isCollidingWith(AABB other) {
        return this.x <= other.x + other.width &&
                this.x + this.width >= other.x &&
                this.y <= other.y + other.height &&
                this.y + this.height >= other.y;
    }

    public void fillCollider(Graphics2D graphics2D) {
        graphics2D.fillRect((int) this.getX(), (int) this.getY(), (int) this.getWidth(), (int) this.getHeight());
    }

    public void drawCollider(Graphics2D graphics2D) {
        graphics2D.drawRect((int) this.getX(), (int) this.getY(), (int) this.getWidth(), (int) this.getHeight());
    }

    public void drawCollider(Graphics2D graphics2D, Color colour) {
        graphics2D.setColor(colour);
        graphics2D.drawRect((int) this.getX(), (int) this.getY(), (int) this.getWidth(), (int) this.getHeight());
    }

    //Getters and setters
    public float getX() {
        return x;
    }
    public void setX(float x) {
        this.x = x;
    }
    public float getY() {
        return y;
    }
    public void setY(float y) {
        this.y = y;
    }
    public float getWidth() {
        return width;
    }
    public void setWidth(float width) {
        this.width = width;
    }
    public float getHeight() {
        return height;
    }
    public void setHeight(float height) {
        this.height = height;
    }
    public void setCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }
}