package pck;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Trab1_PAA {
    ArrayList<Integer> pedidosArrays = new ArrayList<Integer>();
    ArrayList<Integer> melhorSolucao = new ArrayList<Integer>();
    ArrayList<Integer> maiorLucroArray = new ArrayList<Integer>();
    ArrayList<Integer> cardapio = new ArrayList<Integer>();
    ArrayList<Integer> auxArraysM2 = new ArrayList<Integer>();
    ArrayList<Integer> posicoesUsadas = new ArrayList<Integer>();

    ArrayList<Integer> auxMelhorSoluc = new ArrayList<Integer>();
    ArrayList<Integer> moto2 = new ArrayList<Integer>();
    int melhorLucroLiquido = 0;
    int melhorCusto = 0;

    ArrayList<Integer> pedidos = new ArrayList<Integer>();
    long nPedidos;

    ArrayList<Integer> custo = new ArrayList<Integer>();
    ArrayList<Integer> lucro = new ArrayList<Integer>();
    int k, n, m;

    public Trab1_PAA() {

    }

    public void separarPedidos() {
        int nVezes = 1;
        int primeira = 0;

        String ler;
        String file = "cardapio\\src\\pck\\exemplo.txt";
        int nCasoTeste = 1;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while (nVezes == 1) {
                if (primeira == 0) {
                    ler = br.readLine();
                    String j[] = ler.split(" ");
                    k = Integer.parseInt(j[0]);
                    n = Integer.parseInt(j[1]);
                    m = Integer.parseInt(j[2]);
                } else {
                    int ab = melhorSolucao.size();
                    while (ab != 0) {
                        melhorSolucao.remove(melhorSolucao.size() - 1);
                        ab = melhorSolucao.size();
                    }
                    ab = auxMelhorSoluc.size();
                    while (ab != 0) {
                        auxMelhorSoluc.remove(auxMelhorSoluc.size() - 1);
                        ab = auxMelhorSoluc.size();
                    }
                    ab = cardapio.size();
                    while (ab != 0) {
                        cardapio.remove(cardapio.size() - 1);
                        ab = cardapio.size();
                    }

                    ab = custo.size();
                    while (ab != 0) {
                        custo.remove(custo.size() - 1);
                        ab = custo.size();
                    }

                    ab = lucro.size();
                    while (ab != 0) {
                        lucro.remove(lucro.size() - 1);
                        ab = lucro.size();
                    }
                    melhorCusto = 0;
                    melhorLucroLiquido = 0;
                }
                primeira = 1;

                for (int i = 0; i < n; i++) {
                    ler = br.readLine();
                    String a[] = ler.split(" ");
                    custo.add(Integer.parseInt(a[0]));
                    lucro.add(Integer.parseInt(a[1]));
                }
                // System.out.println(k+" "+n+" "+m);
                // System.out.println(custo+" "+ lucro);

                tentaAlgum(k);
                ler = br.readLine();
                // System.out.println(ler);
                String j[] = ler.split(" ");

                k = Integer.parseInt(j[0]);
                n = Integer.parseInt(j[1]);
                m = Integer.parseInt(j[2]);
                int ab = melhorSolucao.size() - 1;

                if (melhorSolucao.size() > 0) {
                    while (ab >= 0) {
                        auxMelhorSoluc.add(melhorSolucao.get(ab) + 1);
                        ab--;
                    }
                }

                System.out.println("Melhor solucoa caso " + nCasoTeste + ":  " + melhorLucroLiquido + "  "
                        + auxMelhorSoluc + "\n");
                if ((k == 0) && (n == 0) && (m == 0)) {
                    nVezes = 0;
                } else {

                }
                nCasoTeste++;
            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public void tentaAlgum(int tamanho) {
        // System.out.println(tamanho);
        if (tamanho > 0) {
            int bin = 0;
            int posicUsada = 0;
            int j = 0;

            for (int i = 0; i < n; i++) {
                cardapio.add(i);
                tentaAlgum(tamanho - 1);
                cardapio.remove((cardapio.size() - 1));
            }

        } else {
            int custoTotal = 0;
            int lucroTotal = 0;

            custoTotal += custo.get(cardapio.get(0));
            lucroTotal += lucro.get(cardapio.get(0));
            for (int i = 1; i < cardapio.size(); i++) {
                if (cardapio.get(i) != cardapio.get(i - 1)) {
                    custoTotal += custo.get(cardapio.get(i));
                    lucroTotal += lucro.get(cardapio.get(i));
                } else {
                    if (i >= 2) {
                        if (cardapio.get(i) != cardapio.get(i - 2)) {
                            custoTotal += custo.get(cardapio.get(i));
                            lucroTotal += lucro.get(cardapio.get(i)) / 2;
                        } else {
                            custoTotal += custo.get(cardapio.get(i));
                            lucroTotal += 0;
                        }
                    } else {
                        custoTotal += custo.get(cardapio.get(i));
                        lucroTotal += lucro.get(cardapio.get(i)) / 2;
                    }

                }
            }
            if ((lucroTotal > 0) && (custoTotal <= m)) {
                if (lucroTotal > melhorLucroLiquido) {
                    melhorCusto = custoTotal;
                    melhorLucroLiquido = lucroTotal;
                    int ab = melhorSolucao.size();
                    while (ab != 0) {
                        melhorSolucao.remove(melhorSolucao.size() - 1);
                        ab = melhorSolucao.size();
                    }

                    for (int a = 0; a < cardapio.size(); a++) {
                        melhorSolucao.add(cardapio.get(a));

                    }

                }
                if (lucroTotal == melhorLucroLiquido) {
                    if (custoTotal < melhorCusto) {
                        melhorCusto = custoTotal;
                        melhorLucroLiquido = lucroTotal;
                        int ab = melhorSolucao.size();
                        while (ab != 0) {
                            melhorSolucao.remove(melhorSolucao.size() - 1);
                            ab = melhorSolucao.size();
                        }

                        for (int a = 0; a < cardapio.size(); a++) {
                            melhorSolucao.add(cardapio.get(a));

                        }
                    }
                }
            }
        }
    }
}