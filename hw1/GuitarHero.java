import edu.princeton.cs.algs4.StdAudio;
import synthesizer.ArrayRingBuffer;
import synthesizer.GuitarString;

/** A client that uses the synthesizer package to replicate a plucked guitar string sound */
public class GuitarHero {
    private static final double CONCERT_A = 440.0;
    private static final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    private static Double[] frequencies = new Double[37];

    public static void main(String[] args) {
        for (int i = 0; i < 37; i += 1) {
            frequencies[i] = CONCERT_A * Math.pow(2, (i - 24) / 12);
        }
        ArrayRingBuffer<GuitarString> strings = new ArrayRingBuffer<>(128);
        while (true) {
            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                if (index == -1) {
                    continue;
                }
                GuitarString stringI = new GuitarString(frequencies[index]);
                strings.enqueue(stringI);
            }
            /* compute the superposition of samples */
            double sample = 0.0;
            for (GuitarString s : strings) {
                sample += s.sample();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for (GuitarString s : strings) {
                s.tic();
            }
        }


    }
}

