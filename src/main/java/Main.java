import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static Logger logger = LoggerFactory.getLogger(Main.class);

     static void main(String[] args) {
        logger.info("Hello and welcome!");
        logger.debug("Hello and welcome!");
        logger.error("Hello and welcome!");
        logger.warn("Hello and welcome!");
    }
}