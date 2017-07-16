package P03_GraphicEditor;

import com.sun.org.apache.regexp.internal.RE;

public class GraphicEditor {
    public void drawShape(Shape shape) {
        System.out.println(shape.draw());
    }

    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle();
        Circle circle = new Circle();
        GraphicEditor editor = new GraphicEditor();
        editor.drawShape(circle);
        editor.drawShape(rectangle);
    }
}
