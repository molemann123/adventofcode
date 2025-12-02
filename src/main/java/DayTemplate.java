import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// https://adventofcode.com/2019/day/1
public class DayTemplate {
    static Logger logger = LoggerFactory.getLogger(DayTemplate.class);
     static void main(String[] args) {
        logger.info("Hello and welcome!");
        logger.debug("Hello and welcome!");
        logger.error("Hello and welcome!");
        logger.warn("Hello and welcome!");
    }
}
