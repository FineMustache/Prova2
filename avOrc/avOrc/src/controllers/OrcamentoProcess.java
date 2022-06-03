package controllers;

import java.util.ArrayList;

import models.Orcamento;

public class OrcamentoProcess {
	public static ArrayList<Orcamento> orcamentos;
	private static OrcamentoDAO od = new OrcamentoDAO();
	
	public static void compararProdutos(String produto) {
		int indexBarato = 0;
		double precoBarato = 9999999;
		for (Orcamento orcamento : orcamentos) {
			if (orcamento.getProduto().equals(produto) && orcamento.getPreco() < precoBarato) {
				indexBarato = orcamentos.indexOf(orcamento);
				precoBarato = orcamento.getPreco();
			}
		}
		
		for (Orcamento orcamento : orcamentos) {
			if (orcamentos.indexOf(orcamento) == indexBarato) {
				orcamento.setMaisBarato(true);
			} else if(orcamento.getProduto() == produto) {
				orcamento.setMaisBarato(false);
			}
		}
		
	}
	
	public static void abrir() {
		orcamentos = od.ler();
	}
	
	public static void salvar() {
		od.escrever(orcamentos);
		abrir();
	}
}
