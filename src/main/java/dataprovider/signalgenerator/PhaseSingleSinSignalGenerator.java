package dataprovider.signalgenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class PhaseSingleSinSignalGenerator extends SingleSinSignalGenerator {
    public PhaseSingleSinSignalGenerator(int samplingFrequency, double frameDuration) {
        this.samplingFrequency = samplingFrequency;
        this.frameDuration = frameDuration;
    }

    public PhaseSingleSinSignalGenerator() {
        this(44100, 0.02);
    }

    /**
     * k from -53 to 43
     * @return
     */
    @Override
    public String generateRandomSignal() {
        int k = ThreadLocalRandom.current().nextInt(-53, 44);
        List<Double> signal = new ArrayList<>(signalSize);
        double k_bias = ThreadLocalRandom.current().nextDouble() - 0.5;
        double k_new  = k + k_bias;
        double toneFrequency = 440 * Math.pow(2, (k_new / (12)));
        double initialPhase = ThreadLocalRandom.current().nextDouble() * 2 * Math.PI;
        for (int i = 0; i < signalSize; i++) {
            double currentTime = frameDuration * i / signalSize;
            double signalValue = Math.sin(2 * Math.PI * currentTime * toneFrequency + initialPhase);
            signal.add(signalValue);
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < signal.size() - 1; i++) {
            result = result.append(signal.get(i)).append(",");
        }
        result = result.append(signal.get(signal.size() - 1));
        result = result.append(signal.get(signal.size() - 1));
        for (int i = -53; i < k; i++) {
            result = result.append(",").append(0);
        }
        result = result.append(",").append(1);
        for (int i = k; i < 44; i++) {
            result = result.append(",").append(0);
        }
        return result.toString();
    }
}
