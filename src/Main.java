import controller.Controller;
import model.Registry;
import view.EnglishView;

public class Main {

    public static void main(String[] args) {
        EnglishView view = new EnglishView();
        Registry registry = new Registry();
        Controller start = new Controller(view, registry);
        start.run();
    }
}
