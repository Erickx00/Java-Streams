import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Exercicio {
    public static void main(String[] args) {
        String[] posicoes = {"Goleiro", "Defensor", "Meio-Campista", " Atacante" };
        String[] times = {"Flamengo", "São Paulo", "Palmeiras", " Santos", " Corinthians", " Grêmio", " Internacional", " Fluminense", " Atlético Mineiro", " Cruzeiro" };
        String[] nomes = {"Neymar", "Gabriel Barbosa", "Alisson", "Marquinhos", " Casemiro", "Roberto Firmino", "Thiago Silva", "Richarl ison", " Lucas Paquetá", " Éder Militão", "Vinícius Júnior", "Fabinho", "Douglas Luiz", " Everton Ribeiro", " Ederson", "Gabriel Jesus", "Danilo", "Rodrygo", "Fred", " Reinier" };

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

        System.out.println("Filtrar Todos os jogadores");
        // filtrarTodosJogadores
        filtrarTodosJogadores(jogadores);
        System.out.println();

        System.out.println("Filtrar jogadores por time");
        // filtrar por time
        filtraPorTime(jogadores, "Flamengo");
        System.out.println();


        System.out.println("Filtrar jogadores por time e gols acima de 1");
        // imprimir jogadores de um determinado time que tenham mais que um
        //determinado número de gols marcados
        filtrarPorTimeAndDeterminadoGols(jogadores, "Palmeiras");
        System.out.println();



    }



    private static void filtrarTodosJogadores(List<Jogador> jogadores) {
        jogadores.forEach(System.out::println);
    }


    private static void filtraPorTime(List<Jogador> jogadores, String flamengo) {
        jogadores.stream()
                .filter(j -> j.getTime().equals(flamengo))
                .forEach(j -> System.out.println("Jogador: "+ j.getNome() + " || "+ "Time: " + j.getTime()));

    }

    private static void filtrarPorTimeAndDeterminadoGols(List<Jogador> jogadores, String palmeiras) {
        jogadores.stream()
                .filter(j -> j.getTime().equals(palmeiras))
                .filter(j -> j.getGolsMarcados() > 1)
                .forEach(j -> System.out.println("Jogador: "+ j.getNome() + " || "+ "Gols: " + j.getGolsMarcados() + " || "+ "Time: " + j.getTime()));




    }






}