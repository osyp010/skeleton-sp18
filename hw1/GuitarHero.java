import edu.princeton.cs.algs4.StdAudio;
import synthesizer.ArrayRingBuffer;

/** A client that uses the synthesizer package to replicate a plucked guitar string sound */
public class GuitarHero {
    private static final double CONCERT_A = 440.0;
    private static final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    private static Double[] frequencies = new Double[37];

    public static void main(String[] args) {
        for (int i = 0; i < 37; i += 1) {
            frequencies[i] = CONCERT_A * Math.pow(2, (i - 24) / 12);
        }
        while (true) {
            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                if (index == -1) {
                    continue;
                }
                synthesizer.GuitarString stringI = new synthesizer.GuitarString(frequencies[index]);
                stringI.pluck();
                StdAudio.play(stringI.sample());
                stringI.tic();
            }
        }


    }
}

