package bomb;

import common.IntList;

public class BombMain {
    public static void main(String[] args) {
        int phase = 2;
        if (args.length > 0) {
            phase = Integer.parseInt(args[0]);
        }
        // TODO: Find the correct passwords to each phase using debugging techniques
        Bomb b = new Bomb();
        if (phase >= 0) {
<<<<<<< HEAD
            b.phase0("39291226");
        }
        if (phase >= 1) {
            b.phase1(IntList.of(0,9,3,0,8)); // Figure this out too
            //09308
        }
        if (phase >= 2) {
            b.phase2("-81201430 ".repeat(1338));
=======
            b.phase0("Figure this out. I wonder where the phases are defined...");
        }
        if (phase >= 1) {
            b.phase1(null); // Figure this out too
        }
        if (phase >= 2) {
            b.phase2("Figure this out. I wonder where the phases are defined...");
>>>>>>> 94c29cab06d6554e885bc8afef6b3a0e29be776f
        }
    }
}
