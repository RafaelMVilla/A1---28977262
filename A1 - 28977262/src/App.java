import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        List<Aluno> listaDeAlunos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Rafael Villa\\OneDrive\\Área de Trabalho\\Engenharia De Software\\3 - SEMESTRE\\Desenvolvimento de Software\\_Ws\\Programação Java\\A1 - 28977262\\A1 - 28977262\\src\\alunos.csv"))) {
            String linha;
            boolean primeiraLinha = true; 
            while ((linha = br.readLine()) != null) {
                if (primeiraLinha) {
                    primeiraLinha = false;
                    continue; 
                }   
                String[] campos = linha.split(";");
                int matricula = Integer.parseInt(campos[0].trim());
                String nome = campos[1];
                double nota = Double.parseDouble(campos[2].replace(",", "."));

                Aluno aluno = new Aluno(matricula, nome, nota);
                listaDeAlunos.add(aluno);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    int totalAlunos = listaDeAlunos.size();
    int aprovados = 0;
    int reprovados = 0;
    double somaNotas = 0;
    double menorNota = Double.MAX_VALUE;
    double maiorNota = Double.MIN_VALUE;

    for (Aluno aluno : listaDeAlunos) {
        somaNotas += aluno.getNota();
        menorNota = Math.min(menorNota, aluno.getNota());
        maiorNota = Math.max(maiorNota, aluno.getNota());

        if (aluno.getNota() >= 6.0) {
            aprovados++;
        } else {
            reprovados++;
        }
    }

    double mediaGeral = somaNotas / totalAlunos;
    mediaGeral = Math.round(mediaGeral * 100.0) / 100.0;

    try (FileWriter fw = new FileWriter("resumo.csv")) {
        fw.write("Quantidade de alunos: " + totalAlunos + "\n");
        fw.write("Alunos aprovados: " + aprovados + "\n");
        fw.write("Alunos reprovados: " + reprovados + "\n");
        fw.write("Menor nota: " + menorNota + "\n");
        fw.write("Maior nota: " + maiorNota + "\n");
        fw.write("Média geral: " + mediaGeral + "\n");
        System.out.println("Gravação realizada com sucesso em: resumo.csv.");
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
}
