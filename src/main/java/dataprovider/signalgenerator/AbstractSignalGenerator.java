package dataprovider.signalgenerator;

public abstract class AbstractSignalGenerator implements SignalGenerator {
    int samplingFrequency;

    double frameDuration;

    final int signalSize;

    public AbstractSignalGenerator(int samplingFrequency, double frameDuration) {
        this.samplingFrequency = samplingFrequency;
        this.frameDuration = frameDuration;
        signalSize = (int)Math.floor(samplingFrequency * frameDuration);
    }

    public AbstractSignalGenerator() {
        this.samplingFrequency = 44100;
        this.frameDuration = 0.02;
        signalSize = (int)Math.floor(samplingFrequency * frameDuration);
    }

    @Override
    public abstract String generateRandomSignal();

    public int getSamplingFrequency() {
        return samplingFrequency;
    }

    public void setSamplingFrequency(int samplingFrequency) {
        this.samplingFrequency = samplingFrequency;
    }

    public double getFrameDuration() {
        return frameDuration;
    }

    public void setFrameDuration(double frameDuration) {
        this.frameDuration = frameDuration;
    }

    public int getSignalSize() {
        return signalSize;
    }
}
