
import java.util.*;
import java.util.stream.Collectors;

public class Exercicio {

    public static void main(String[] args) {
        List<Jogador> jogadores = gerarJogadores();

        System.out.println("=== TODOS OS JOGADORES ===");
        filtrarTodosJogadores(jogadores);
        imprimirSeparador();

        System.out.println("=== JOGADORES DO FLAMENGO ===");
        filtraPorTime(jogadores, "Flamengo");
        imprimirSeparador();

        System.out.println("=== JOGADORES DO PALMEIRAS COM MAIS DE 1 GOL ===");
        filtrarPorTimeAndDeterminadoGols(jogadores, "Palmeiras", 1);
        imprimirSeparador();

        System.out.println("=== JOGADORES AGRUPADOS POR TIME ===");
        filtrarJogadoresTimesAgrupado(jogadores);
        imprimirSeparador();

        System.out.println("=== MÉDIA DE IDADE DOS JOGADORES ===");
        filtrarMediaIdadeJogadores(jogadores);
        imprimirSeparador();

        System.out.println("=== MÉDIA DE IDADE POR TIME ===");
        filtrarMediaIdadeJogadorePorTime(jogadores, "Flamengo");
        imprimirSeparador();

        System.out.println("=== JOGADOR MAIS VELHO ===");
        filtrarJogadorMaisVelho(jogadores);
        imprimirSeparador();

        System.out.println("=== TIME DO JOGADOR MAIS VELHO ===");
        filtrarTimeJogadorMaisVelho(jogadores);
        imprimirSeparador();

        System.out.println("=== JOGADOR MAIS NOVO ===");
        filtrarJogadorMaisNovo(jogadores);
        imprimirSeparador();
    }

    // Geração dos jogadores
    private static List<Jogador> gerarJogadores() {
        String[] posicoes = {
                "Goleiro",
                "Defensor",
                "Meio-Campista",
                "Atacante"
        };

        String[] times = {
                "Flamengo",
                "São Paulo",
                "Palmeiras",
                "Santos",
                "Corinthians",
                "Grêmio",
                "Internacional",
                "Fluminense",
                "Atlético Mineiro",
                "Cruzeiro"
        };

        String[] nomes = {
                "Neymar",
                "Gabriel Barbosa",
                "Alisson",
                "Marquinhos",
                "Casemiro",
                "Roberto Firmino",
                "Thiago Silva",
                "Richarlison",
                "Lucas Paquetá",
                "Éder Militão",
                "Vinícius Júnior",
                "Fabinho",
                "Douglas Luiz",
                "Everton Ribeiro",
                "Ederson",
                "Gabriel Jesus",
                "Danilo",
                "Rodrygo",
                "Fred",
                "Reinier"
        };

        List<Jogador> jogadores = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 20; i++) {
            String posicao = posicoes[random.nextInt(posicoes.length)];
            String time = times[random.nextInt(times.length)];
            String nome = nomes[random.nextInt(nomes.length)];

            int idade = random.nextInt(18, 40);
            int gols = random.nextInt(0, 10);

            jogadores.add(
                    new Jogador(nome, posicao, idade, time, gols)
            );
        }

        return jogadores;
    }

    // Listagem
    private static void filtrarTodosJogadores(List<Jogador> jogadores) {
        jogadores.forEach(System.out::println);
    }

    // Filtrar jogadores por time
    private static void filtraPorTime(
            List<Jogador> jogadores,
            String time
    ) {
        jogadores.stream()
                .filter(j -> j.getTime().equals(time))
                .forEach(j -> System.out.printf(
                        "Jogador: %s | Time: %s%n",
                        j.getNome(),
                        j.getTime()
                ));
    }

    // Filtrar jogadores por time e quantidade mínima de gols
    private static void filtrarPorTimeAndDeterminadoGols(
            List<Jogador> jogadores,
            String time,
            int golsMinimos
    ) {
        jogadores.stream()
                .filter(j -> j.getTime().equals(time))
                .filter(j -> j.getGolsMarcados() > golsMinimos)
                .forEach(j -> System.out.printf(
                        "Jogador: %s | Gols: %d | Time: %s%n",
                        j.getNome(),
                        j.getGolsMarcados(),
                        j.getTime()
                ));
    }

    // Agrupar jogadores por time
    private static void filtrarJogadoresTimesAgrupado(
            List<Jogador> jogadores
    ) {
        Map<String, List<Jogador>> jogadoresPorTime =
                jogadores.stream()
                        .collect(Collectors.groupingBy(Jogador::getTime));

        jogadoresPorTime.forEach((time, listaJogadores) -> {
            System.out.println("Time: " + time);

            listaJogadores.forEach(j -> System.out.printf(
                    "- %s (%s)%n",
                    j.getNome(),
                    j.getPosicao()
            ));

            System.out.println("-".repeat(30));
        });
    }

    // Calcular média de idade de todos os jogadores
    private static void filtrarMediaIdadeJogadores(
            List<Jogador> jogadores
    ) {
        double mediaIdade = jogadores.stream()
                .mapToInt(Jogador::getIdade)
                .average()
                .orElse(0.0);

        System.out.printf(
                "Média de idade: %.2f anos%n",
                mediaIdade
        );
    }

    // Calcular média de idade dos jogadores de um time
    private static void filtrarMediaIdadeJogadorePorTime(
            List<Jogador> jogadores,
            String time
    ) {
        OptionalDouble mediaIdade = jogadores.stream()
                .filter(j -> j.getTime().equals(time))
                .mapToInt(Jogador::getIdade)
                .average();

        if (mediaIdade.isPresent()) {
            System.out.printf(
                    "Média de idade do %s: %.2f anos%n",
                    time,
                    mediaIdade.getAsDouble()
            );
        } else {
            System.out.println(
                    "Nenhum jogador encontrado para o time " + time
            );
        }
    }

    // Encontrar o jogador mais velho
    private static void filtrarJogadorMaisVelho(
            List<Jogador> jogadores
    ) {
        jogadores.stream()
                .max(Comparator.comparingInt(Jogador::getIdade))
                .ifPresentOrElse(
                        j -> System.out.printf(
                                "Jogador mais velho: %s (%d anos)%n",
                                j.getNome(),
                                j.getIdade()
                        ),
                        () -> System.out.println("Nenhum jogador cadastrado.")
                );
    }

    // Encontrar o time do jogador mais velho
    private static void filtrarTimeJogadorMaisVelho(
            List<Jogador> jogadores
    ) {
        jogadores.stream()
                .max(Comparator.comparingInt(Jogador::getIdade))
                .ifPresentOrElse(
                        j -> {
                            System.out.println(
                                    "Time do jogador mais velho: " + j.getTime()
                            );

                            filtraPorTime(jogadores, j.getTime());
                        },
                        () -> System.out.println("Nenhum jogador cadastrado.")
                );
    }

    // Encontrar o jogador mais novo
    private static void filtrarJogadorMaisNovo(
            List<Jogador> jogadores
    ) {
        jogadores.stream()
                .min(Comparator.comparingInt(Jogador::getIdade))
                .ifPresentOrElse(
                        j -> System.out.printf(
                                "Jogador mais novo: %s (%d anos)%n",
                                j.getNome(),
                                j.getIdade()
                        ),
                        () -> System.out.println("Nenhum jogador cadastrado.")
                );
    }


    // Imprimir o jogador artilheiro
    private static void filtrarJogadorArtilheiro(List<Jogador> jogadores) {
        jogadores.stream()
                .max(Comparator.comparingInt(Jogador::getGolsMarcados))
                .ifPresentOrElse(
                        j -> System.out.printf(
                                "Artilheiro: %s | Gols: %d | Time: %s%n",
                                j.getNome(),
                                j.getGolsMarcados(),
                                j.getTime()
                        ),
                        () -> System.out.println("Nenhum jogador cadastrado.")
                );
    }

    // Imprimir o time do jogador artilheiro
    private static void filtrarTimeJogadorArtilheiro(
            List<Jogador> jogadores
    ) {
        jogadores.stream()
                .max(Comparator.comparingInt(Jogador::getGolsMarcados))
                .ifPresentOrElse(
                        j -> System.out.printf(
                                "Artilheiro: %s | Time: %s%n",
                                j.getNome(),
                                j.getTime()
                        ),
                        () -> System.out.println("Nenhum jogador cadastrado.")
                );
    }


    // Separador visual
    private static void imprimirSeparador() {
        System.out.println("-".repeat(60));
    }
}
