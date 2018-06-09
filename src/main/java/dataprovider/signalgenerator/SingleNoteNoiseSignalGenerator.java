package dataprovider.signalgenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SingleNoteNoiseSignalGenerator extends SingleNoteSignalGenerator {

    double noiseLevel;

    public SingleNoteNoiseSignalGenerator(int samplingFrequency, double frameDuration, double noiseLevel) {
        this.samplingFrequency = samplingFrequency;
        this.frameDuration = frameDuration;
        this.noiseLevel = noiseLevel;
    }

    public SingleNoteNoiseSignalGenerator() {
        this(44100, 0.02, 0.1);
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
        int harmonicCount = ThreadLocalRandom.current().nextInt(4, 9);
        List<Double> amplitudes = new ArrayList<>(harmonicCount);
        List<Double> initialPhases = new ArrayList<>(harmonicCount);
        for (int i = 0; i < harmonicCount; i++) {
            amplitudes.add(ThreadLocalRandom.current().nextDouble());
            initialPhases.add(ThreadLocalRandom.current().nextDouble() * 2 * Math.PI);
        }
        double signalAmplitude = 0;
        for (int i = 0; i < harmonicCount; i++) {
            signalAmplitude += Math.pow(amplitudes.get(i), 2);
        }
        double max = 0;
        for (int i = 0; i < signalSize; i++) {
            double currentTime = frameDuration * i / signalSize;
            double signalValue = 0;
            for (int j = 0; j < harmonicCount; j++) {
                signalValue += amplitudes.get(j) * Math.sin(2 * Math.PI * currentTime * toneFrequency * (j + 1) + initialPhases.get(j));
            }
            double noiseValue = ThreadLocalRandom.current().nextDouble() * noiseLevel * signalAmplitude;
            signalValue += noiseValue;
            if (signalValue > max) {
                max = signalValue;
            }
            signal.add(signalValue);
        }
        for (int i = 0; i < signal.size(); i++) {
            signal.set(i, signal.get(i) / max);
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < signal.size() - 1; i++) {
            result = result.append(signal.get(i)).append(",");
        }
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

    public double getNoiseLevel() {
        return noiseLevel;
    }

    public void setNoiseLevel(double noiseLevel) {
        this.noiseLevel = noiseLevel;
    }
}
