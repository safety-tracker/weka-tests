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

        NaiveBayes algorithm = new NaiveBayes();
        algorithm.buildClassifier(instances);
    }
}
