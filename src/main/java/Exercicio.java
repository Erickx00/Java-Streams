import java.util.*;
import java.util.stream.Collectors;

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

        System.out.println("Filtrar Todos os jogadores\n");
        // filtrarTodosJogadores
        filtrarTodosJogadores(jogadores);
        System.out.println("-".repeat(100));

        System.out.println("Filtrar jogadores por time\n");
        // filtrar por time
        filtraPorTime(jogadores, "Flamengo");
        System.out.println("-".repeat(100));


        System.out.println("Filtrar jogadores por time e gols acima de 1\n");
        // imprimir jogadores de um determinado time que tenham mais que um
        //determinado número de gols marcados
        filtrarPorTimeAndDeterminadoGols(jogadores, "Palmeiras");
        System.out.println("-".repeat(100));

        System.out.println("Filtrar jogadores agrupando os time\n");
        // imprimir todos os jogadores, agrupando-os por time

        filtrarJogadoresTimesAgrupado(jogadores);
        System.out.println("-".repeat(100));

        System.out.println("Filtrar media de idade dos jogadores\n");
        // calcular média de idade dos jogadores
        filtrarMediaIdadeJogadores(jogadores);
        System.out.println("-".repeat(100));

        System.out.println("Filtrar media de idade dos jogadores por time\n");
        //calcular média de idade dos jogadores de um time
        filtrarMediaIdadeJogadorePorTime(jogadores, "Flamengo");
        System.out.println("-".repeat(100));

        System.out.println("Filtrar jogador mais velho\n");
        //imprimir jogador mais velho
        filtrarJogadorMaisVelho(jogadores);
        System.out.println("-".repeat(100));



        System.out.println("Filtrar time do jogador mais velho\n");
        filtrarTimeJogadorMaisVelho(jogadores);
        System.out.println("-".repeat(100));






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
                .forEach(j -> System.out.printf("Jogador: %s || Gols: %d || Time: %s",
                        j.getNome(), j.getGolsMarcados(), j.getTime()));

    }

    private static void filtrarJogadoresTimesAgrupado(List<Jogador> jogadores) {

        Map<String, List<Jogador>> jogadoresPorTime = jogadores.stream()
                .collect(Collectors.groupingBy(Jogador::getTime));


        jogadoresPorTime.forEach((time, listaJogadores) -> {
            System.out.println("Time: " + time.trim());
            for (Jogador j : listaJogadores) {
                System.out.printf("- %s (%s)\n", j.getNome().trim(), j.getPosicao().trim());
            }
            System.out.println("-".repeat(20));
        });
    }

    private static void filtrarMediaIdadeJogadores(List<Jogador> jogadores) {
        double mediaIdade = jogadores.stream()
                .mapToInt(Jogador::getIdade)
                .average()
                .orElse(0.0);

        System.out.printf("Media de idade dos jogadores: %.2f anos\n", mediaIdade);
    }

    private static void filtrarMediaIdadeJogadorePorTime(List<Jogador> jogadores, String flamengo) {
        double mediaIdade = jogadores.stream()
                .filter(e -> e.getTime().equals(flamengo))
                .mapToInt(Jogador::getIdade)
                .average()
                .orElse(0.0);

        System.out.printf("- Media de idade dos jogaodore do %s: %.2f anos\n", flamengo, mediaIdade);
    }

    private static void filtrarJogadorMaisVelho(List<Jogador> jogadores) {
        // Ordem decrescente (mais velho primeiro)
        jogadores.stream()
                .max(Comparator.comparingInt(Jogador::getIdade))
                .ifPresent(j -> System.out.println("Jogador mais velho: " + j.getNome() + " (" + j.getIdade() + " anos)"));
    }

    private static void filtrarTimeJogadorMaisVelho(List<Jogador> jogadores) {
        Jogador jogador = jogadores.stream()
                .max(Comparator.comparing(Jogador::getIdade))
                .orElse(null);

        assert jogador != null;
        System.out.println("Time: " + jogador.getTime()+"\n");
        filtraPorTime(jogadores, jogador.getTime());
    }










}