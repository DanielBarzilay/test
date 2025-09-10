import java.util.LinkedHashSet;
import java.util.Set;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    // Check on CS for correct input type

    /**
     *
     * @param num
     * @return list of all prime numbers under num including num
     */
    public static Set<Integer> primeSet(int num) {
        boolean flag = false;
        Set<Integer> primeNumbers = new LinkedHashSet<Integer>();

        for (int i = num; i > 1; i--) {
            for (int j = i - 1; j > 1; j--) {
                if (i % j == 0) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                primeNumbers.add(i);
            } else {
                flag = false;
            }
        }
        return primeNumbers;
    }

    public static void main(String[] args) {
        int num = 100;
        System.out.println(primeSet(num));
    }
}