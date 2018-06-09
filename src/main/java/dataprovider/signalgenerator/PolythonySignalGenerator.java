package dataprovider.signalgenerator;

public class PolythonySignalGenerator extends TimeDependentSignalGenerator {
    public PolythonySignalGenerator(int samplingFrequency, double frameDuration, double noiseLevel) {
        this.samplingFrequency = samplingFrequency;
        this.frameDuration = frameDuration;
        this.noiseLevel = noiseLevel;
    }

    public PolythonySignalGenerator() {
        this(44100, 0.02, 0.1);
    }

    /**
     * k from -53 to 43
     * k from -265 to 215
     * @return
     */
    @Override
    public String generateRandomSignal() {
        return "";
    }
}
