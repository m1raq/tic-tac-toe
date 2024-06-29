import java.util.concurrent.ThreadLocalRandom;

public class Bot {

    public static Integer cellGenerate(){
        return ThreadLocalRandom.current().nextInt(1,8 + 1);
    }

}
