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
            Map<String, Integer> attributesIndex = new HashMap<>();
            try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                boolean first = true;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                while ((line = reader.readLine()) != null) {
                    if(first) {
                        String[] attributes = (String[]) Arrays.stream(line.split(";")).map(String::trim).map(String::toLowerCase).toArray();
                        for(int i = 0; i<attributes.length; i++) {
                            attributesIndex.put(attributes[i], i);
                        }
                        first = false;
                        continue;
                    }
                    lines.put(line.split(";")[0], (String[]) Arrays.stream(line.split(";")).skip(1).toArray());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new CSVDataSet(lines, attributesIndex);
        }
    }

    private Map<String, String[]> lines;
    private Map<String, Integer> attributesIndex;
    private List<CSVAttribute> attributes = new ArrayList<>();

    public CSVDataSet(Map<String, String[]> lines, Map<String, Integer> attributesIndex) {
        this.lines = lines;
        this.attributesIndex = attributesIndex;
    }

    public void normalize() {
        Map<String, String[]> buffer = new HashMap<>();
        attributes.forEach(attribute -> {
            int attributeIndex = attributesIndex.get(attribute.getIdentifier());
            lines.forEach((bo, args) -> {
                List<String> currentArgs = new ArrayList<>();
                for (int i = 0; i < args.length; i++) {
                    if(i == attributeIndex) {
                        continue;
                    }
                    currentArgs.add(args[i]);
                }
                buffer.put(bo, (String[]) currentArgs.toArray());
            });
        });
        lines = buffer;
    }

    public Map<String, Integer> getAttributesIndex() {
        return attributesIndex;
    }

    public Map<String, String[]> getLines() {
        return lines;
    }

    public List<CSVAttribute> getAttributes() {
        return attributes;
    }
}
