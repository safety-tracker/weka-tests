package net.victorbetoni.wekatest;

import weka.classifiers.bayes.NaiveBayes;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class Main {
    public static void main(String[] args) throws Exception {
        DataSource data = new DataSource(Main.class.getResourceAsStream("/data.arff"));
        Instances instances = data.getDataSet();
        instances.setClassIndex(3);

        NaiveBayes algorithm = new NaiveBayes();
        algorithm.buildClassifier(instances);

        Instance entry = new DenseInstance(instances.numAttributes());
        entry.setDataset(instances);
        entry.setValue(0, "carro");
        entry.setValue(1, "chuvoso");
        entry.setValue(2, 1848);

        double[] chance = algorithm.distributionForInstance(entry);
        System.out.printf("Y: %f\n", chance[0]);
        System.out.printf("N: %f\n", chance[1]);
    }
}
