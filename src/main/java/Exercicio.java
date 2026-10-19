import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Exercicio {
    public static void main(String[] args) {
        String[] posicoes = {"Goleiro", "Defensor", "Meio-C ampista", " Atacante" };
        String[] times = {"Flamengo", "São Paulo", "Palmeir as", " Santos", " Corinthians", " Grêmio", " Internacional", " F luminense", " Atlético Mineiro", " Cruzeiro" };
        String[] nomes = {"Neymar", "Gabriel Barbosa", "Alisson", "Ma rquinhos", " Casemiro",


                "Roberto Firmino", "Thiago Silva", "Richarl ison", " Lucas Paquetá", " Éder Militão", "Vinícius Júnior", "Fabinho", "Douglas Lui z", " Everton Ribeiro", " Ederson", "Gabriel Jesus", "Danilo", "Rodrygo", "Fre d", " Reinier" };
        List<Jogador> jogadores = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Random r = new Random();
            String posicao = posicoes[r.nextInt(posicoes.length)];
            String time = times[r.nextInt(times.length)];
            String nome = nomes[r.nextInt(nomes.length)];
            int idade = r.nextInt(18, 40);
            int gols = r.nextInt(0, 10);
            jogadores.add(new Jogador(nome, posicao, idade, time, gols));
        }



    }


}