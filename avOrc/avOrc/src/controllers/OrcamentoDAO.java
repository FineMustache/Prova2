package controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import models.Orcamento;

public class OrcamentoDAO {
	private BufferedReader br;
	private BufferedWriter bw;
	private String path =  "./dados/orcamentos.csv";
	
	public ArrayList<Orcamento> ler(){
		ArrayList<Orcamento> linhas = new ArrayList<Orcamento>();
		Orcamento orcamento;
		
		try {
			br = new BufferedReader(new FileReader(path));
			String linha = br.readLine();
			while (linha != null) {
				orcamento = new Orcamento(linha);
				linhas.add(orcamento);
				linha = br.readLine();
			}
			br.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return linhas;
	}
	
	public void escrever(ArrayList<Orcamento> linhas) {
		try {
			bw = new BufferedWriter(new FileWriter(path));
			
			for (Orcamento o : linhas) {
				bw.write(o.toString() + "\r\n");
			}
			
			
			bw.close();
		} catch (IOException e) {
			System.out.println(e.toString());
		}	
	}
}
