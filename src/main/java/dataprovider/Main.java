package dataprovider;

import dataprovider.signalgenerator.SignalGenerator;
import dataprovider.signalgenerator.SingleSinSignalGenerator;

import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        SignalGenerator signalGenerator = new SingleSinSignalGenerator();
        createDataSet("C:\\musicData\\music", 50_000, signalGenerator);
    }

    public static void createDataSet(String fileName, int dataSize, SignalGenerator signalGenerator) throws Exception {
        PrintWriter writer = new PrintWriter(fileName + ".csv", "UTF-8");
        for (int i = 0; i < dataSize; i++) {
            String signal = signalGenerator.generateRandomSignal();
            writer.println(signal);
        }
        writer.close();



        writer = new PrintWriter(fileName + "_test" + ".csv", "UTF-8");
        for (int i = 0; i < dataSize; i++) {
            String signal = signalGenerator.generateRandomSignal();
            writer.println(signal);
        }
        writer.close();
    }
}
