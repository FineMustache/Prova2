package models;

import java.awt.Color;
import java.awt.Component;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controllers.OrcamentoProcess;

public class OrcamentoTableModel extends AbstractTableModel {

    //aqui transformei em coluna cada propriedade de Funcionario
    //que eu quero que seja exibida na tabela  
    private String colunas[] = {"ID", "Fornecedor", "Produto", "Preço", "Mais Barato"};
    private ArrayList<Orcamento> orcamentos = new ArrayList<>(Arrays.asList(new Orcamento(-1, "a", "a", 0d, false)));
    private final int COLUNA_ID = 0;
    private final int COLUNA_FORNECEDOR = 1;
    private final int COLUNA_PRODUTO = 2;
    private final int COLUNA_PREÇO = 3;
    private final int COLUNA_MAISBARATO = 4;
    
    public OrcamentoTableModel(ArrayList<Orcamento> orcamentos) {
        this.orcamentos = orcamentos;
    }

    //retorna se a c�lula � edit�vel ou n�o
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    
    //retorna o total de itens(que virar�o linhas) da nossa lista
    @Override
    public int getRowCount() {
    	return orcamentos.size();
    }
    //retorna o total de colunas da tabela
    @Override
    public int getColumnCount() {
        return colunas.length;
    }
    //retorna o nome da coluna de acordo com seu indice
    @Override
    public String getColumnName(int indice) {
        return colunas[indice];
    }

    //determina o tipo de dado da coluna conforme seu indice
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case COLUNA_ID:
                return Integer.class;
            case COLUNA_FORNECEDOR:
                return String.class;
            case COLUNA_PRODUTO:
                return String.class;
            case COLUNA_PREÇO:
                return String.class;
            case COLUNA_MAISBARATO:
            	return String.class;
            default:
                return String.class;
        }
    }

    //preenche cada c�lula da tabela
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Orcamento orcamento = this.orcamentos.get(rowIndex);

        switch (columnIndex) {
	        case COLUNA_ID:
	            return orcamento.getId();
	        case COLUNA_FORNECEDOR:
	            return orcamento.getFornecedor();
	        case COLUNA_PRODUTO:
	            return orcamento.getProduto();
	        case COLUNA_PREÇO:
	            return String.format("%.2f", orcamento.getPreco());
	        case COLUNA_MAISBARATO:
	        	return "" + orcamento.isMaisBarato();
	        }
        return null;
    }
    //altera o valor do objeto de acordo com a c�lula editada
    //e notifica a altera��o da tabela, para que ela seja atualizada na tela
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        //o argumento recebido pelo m�todo � do tipo Object
        //mas como nossa tabela � de funcion�rios, � seguro(e at� recomend�vel) fazer o cast de suas propriedades
        Orcamento orcamento = this.orcamentos.get(rowIndex);
        //de acordo com a coluna, ele preenche a c�lula com o valor
        //respectivo do objeto de mesmo indice na lista
        switch (columnIndex) {
        case COLUNA_ID:
        	orcamento.setId(Integer.parseInt(aValue.toString()));
            break;
        case COLUNA_FORNECEDOR:
        	orcamento.setFornecedor(String.valueOf(aValue));
            break;
        case COLUNA_PRODUTO:
        	orcamento.setProduto(String.valueOf(aValue));
            break;
        case COLUNA_PREÇO:
        	orcamento.setPreco(Double.parseDouble(String.valueOf(aValue)));
            break;
        case COLUNA_MAISBARATO:
        	orcamento.setMaisBarato(Boolean.parseBoolean(String.valueOf(aValue)));
            break;
        }
        //este m�todo � que notifica a tabela que houve altera��o de dados
        fireTableDataChanged();
    }

    public void refresh() {
    	for (Orcamento orcamento : orcamentos) {
			OrcamentoProcess.compararProdutos(orcamento.getProduto());
		}
    	fireTableDataChanged();
    }
   
}