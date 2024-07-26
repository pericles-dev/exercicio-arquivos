package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.Product;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		List<Product> lista = new ArrayList<>();

		System.out.print("Digite o caminho do arquivo: ");
		String caminhoArquivo = sc.nextLine();

		File arquivo = new File(caminhoArquivo);
		String caminhoRaiz = arquivo.getParent();

		boolean pasta = new File(caminhoRaiz + "/out").mkdir();

		try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {

			String linha = br.readLine();

			while (linha != null) {
				String[] dadosProduto = linha.split(",");

				String name = dadosProduto[0];
				Double price = Double.parseDouble(dadosProduto[1]);
				Integer quantity = Integer.parseInt(dadosProduto[2]);

				lista.add(new Product(name, price, quantity));

				linha = br.readLine();
			}

			try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoRaiz + "/out/summary.txt"))) {
				for (Product produto : lista) {
					bw.write(produto.getName() + "," + produto.total());
					bw.newLine();
				}

			} catch (IOException e) {
				System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
			}

		} catch (IOException e) {
			System.out.println("Erro ao ler arquivo: " + e.getMessage());
		}
	}

}
