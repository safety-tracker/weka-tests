package net.victorbetoni.wekatest.csv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CSVDataSetDAO {

    public static class Loader {
        private File file;

        public Loader(File file) {
            this.file = file;
        }

        public CSVDataSetDAO load() {
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
            return new CSVDataSetDAO(lines);
        }
    }

    private Map<String, String[]> lines;

    public CSVDataSetDAO(Map<String, String[]> lines) {
        this.lines = lines;
    }

    public Map<String, String[]> getLines() {
        return lines;
    }
}
