package br.com.improving.carrinho;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Classe que representa um item no carrinho de compras.
 */
public class Item {

    private Produto produto;
    private BigDecimal valorUnitario;
    private int quantidade;

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	@Override
	public String toString() {
		return String.format("Item produto = " + produto +
				", valor unitário = %.2f quantidade = " + quantidade,valorUnitario);
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	/**
     * Construtor da classe Item.
     * 
     * @param produto
     * @param valorUnitario
     * @param quantidade
     */

	public Item(Produto produto, BigDecimal valorUnitario, int quantidade) {
		this.produto = produto;
		this.valorUnitario = valorUnitario;
		this.quantidade = quantidade;
	}

	/**
     * Retorna o produto.
     *
     * @return Produto
     */
    public Produto getProduto() {
		return this.produto;
    }

    /**
     * Retorna o valor unitário do item.
     *
     * @return BigDecimal
     */
    public BigDecimal getValorUnitario() {
    return valorUnitario;
	}

    /**
     * Retorna a quantidade dos item.
     *
     * @return int
     */
    public int getQuantidade() {
    	return quantidade;
	}

    /**
     * Retorna o valor total do item.
     *
     * @return BigDecimal
     */
    public BigDecimal getValorTotal() {
		return valorUnitario.multiply(BigDecimal.valueOf(quantidade)).setScale(2, RoundingMode.HALF_UP);
    }


	public boolean equals(Item item) {
		if(item.produto.getCodigo() == this.produto.getCodigo()){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(getProduto());
	}
}
