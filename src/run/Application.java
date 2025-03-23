
package run;

import display.MenuSystem;
import utils.InitializeSuperAdmin;
import utils.AnimationOpeningAndEnding;

/**
 *
 * @author Vo Anh Ben - CE190709
 */
public class Application {
    public static void main(String[] args) throws InterruptedException {
        // AnimationOpeningAndEnding.MovieOpening();
        new InitializeSuperAdmin().init();
        new MenuSystem().getMenuNotLogin();

    }
}
