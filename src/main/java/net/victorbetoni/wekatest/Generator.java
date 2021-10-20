package net.victorbetoni.wekatest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Random;

public class Generator
{
    public static void main(String[] args)
    {
        File target = new File("src/main/resources/data.arff");
        File original = new File("src/main/resources/original.csv");
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(target)); BufferedReader reader = new BufferedReader(new FileReader(original)))
        {
            writer.write("@relation acidentes\n\n");
            writer.write("@attribute data_hora_boletim NUMERIC\n");
            writer.write("@attribute tipo_Veiculo {AUTOMOVEL, BICICLETA, BONDE, CAMINHAO, CAMINHAO-TRATOR, CAMINHONETE, CAMIONETA, CARRO-DE-MAO, CARROCA, CICLOMOTOR, KOMBI, MICROONIBUS, MOTOCICLETA, MOTONETA, ONIBUS, PATINETE, REBOQUE-E-SEMI-REBOQUE, TRATOR-DE-RODAS, TRATOR-MISTO, TRICICLO}\n\n");
            writer.write("@attribute gravidade STRING");
            writer.write("@data\n");
            String line;
            boolean first = true;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            while ((line = reader.readLine()) != null) {
                if(first) {
                    first = false;
                    continue;
                }
                String[] vars = line.split(";");
                LocalDateTime time = LocalDateTime.parse(vars[1], formatter);
                if(vars[6].contains("INFORMADO")) {
                    continue;
                }
                writer.write(time.getHour() + ", " + vars[6].trim().replace(" ", "-") /*+ "," + vars[vars.length - 1].trim().replace(" ", "-").replace("�", "A")*/ + "\n");
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
