package net.victorbetoni.wekatest.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVDataSet {

    public static class Loader {
        private File file;

        public Loader(File file) {
            this.file = file;
        }

        public CSVDataSet load() {
            Map<String, String[]> lines = new HashMap<>();
            try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                boolean first = true;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                while ((line = reader.readLine()) != null) {
                    if(first) {
                        first = false;
                        continue;
                    }
                    lines.put(line.split(";")[0], (String[]) Arrays.stream(line.split(";")).skip(1).toArray());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new CSVDataSet(lines);
        }
    }

    private Map<String, String[]> lines;
    private List<CSVAttribute> attributes = new ArrayList<>();

    public CSVDataSet(Map<String, String[]> lines) {
        this.lines = lines;
    }

    public Map<String, String[]> getLines() {
        return lines;
    }

    public List<CSVAttribute> getAttributes() {
        return attributes;
    }
}