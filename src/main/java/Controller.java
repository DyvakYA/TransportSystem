import model.Model;

/**
 * Created by User on 17.03.2016.
 */
public class Controller {
    // The Constants
    public static final double PI = 3.14;

    // The Regex
    // «nick@mail.com»
    public static final String REGEX_MAIL = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
    // «http://www.my-site.com»
    public static final String REGEX_URL = "^((https?|ftp)\\:\\/\\/)?([a-z0-9]{1})((\\.[a-z0-9-])|([a-z0-9-]))*\\.([a-z]{2,6})(\\/?)$";
    // «+38(044)555-55-55»
    public static final String REGEX_PHONE = "^\\+\\d{2}\\(\\d{3}\\)\\d{3}-\\d{2}-\\d{2}$";

    // Constructor
    Model model;
    View view;

    public Controller() {
        model = new Model();
        view = new View();
    }

    // The Work method
    public void processUser(){
        model.userProcces();

        //Scanner sc = new Scanner(System.in);

        //model.setValue(inputIntValueWithScanner(sc));
        //model.addIntOurValue(4);

        //view.printMessageAndInt(view.OUR_INT, model.getValue());
    }

    // The Utility methods
//    public int inputIntValueWithScanner(Scanner sc) {
//        view.printMessage(view.INPUT_INT_DATA);
//        while( ! sc.hasNextInt()) {
//            view.printMessage(view.WRONG_INPUT_INT_DATA + view.INPUT_INT_DATA);
//            sc.next();
//        }
//        return sc.nextInt();
//    }
}
