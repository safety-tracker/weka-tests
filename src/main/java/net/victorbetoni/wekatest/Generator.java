package net.victorbetoni.wekatest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Random;

public class Generator {
    public static void main(String[] args) {
        String[] tipo = new String[]{"carro", "motocicleta", "caminhao"};
        String[] climas = new String[]{"chuvoso", "tempestade", "limpo", "nevoa"};
        String[] rodovias = new String[]{"1253", "6235", "4625", "2452", "3424", "2425", "3643"};
        String[] acidente = new String[]{"Y","N"};
        File file = new File("src/main/resources/data.arff");
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("@relation acidentes\n\n");
            writer.write("@attribute tipo_veiculo {carro, motocicleta, caminhao}\n");
            writer.write("@attribute cond_metereologicas {chuvoso, tempestade, limpo, nevoa}\n");
            writer.write("@attribute rodovia\n");
            writer.write("@attribute ocorre_acidente {Y,N}\n\n");
            writer.write("@data\n");
            for(int i = 0; i<1000; i++) {
                String line = String.format("%s, %s, %s, %s\n",
                        tipo[new Random().nextInt(3)],
                        climas[new Random().nextInt(4)],
                        rodovias[new Random().nextInt(rodovias.length)],
                        acidente[new Random().nextInt(2)]);
                writer.write(line);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
