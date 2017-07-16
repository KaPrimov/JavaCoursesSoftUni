package shapesDrawing;

public class Rectangle implements Drawable {

    private int height;
    private int width;

    public Rectangle(int height, int width) {
        this.height = height;
        this.width = width;
    }

    @Override
    public void draw() {
        for (int i = 0; i < height; i++) {
            System.out.print("*");
            for (int k = 0; k < width - 1; k++) {
                System.out.print(" ");
                if(i == 0 || i == (height - 1)) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.print(" ");
            System.out.print("*");
            System.out.print("\n");
        }
    }
}
