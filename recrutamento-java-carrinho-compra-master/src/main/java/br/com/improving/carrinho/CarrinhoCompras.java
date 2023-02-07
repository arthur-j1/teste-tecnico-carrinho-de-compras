package br.com.improving.carrinho;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Classe que representa o carrinho de compras de um cliente.
 */
public class CarrinhoCompras {
	private String identificacaoCliente;
	private final ArrayList<Item> lista = new ArrayList<>();

	public CarrinhoCompras(String identificacaoCliente) {
		this.identificacaoCliente = identificacaoCliente;
	}


	public String getIdentificacaoCliente() {
		return identificacaoCliente;
	}

	public void setIdentificacaoCliente(String identificacaoCliente) {
		this.identificacaoCliente = identificacaoCliente;
	}



    /**
     * Permite a adição de um novo item no carrinho de compras.
     *
     * Caso o item já exista no carrinho para este mesmo produto, as seguintes regras deverão ser seguidas:
     * - A quantidade do item deverá ser a soma da quantidade atual com a quantidade passada como parâmetro.
     * - Se o valor unitário informado for diferente do valor unitário atual do item, o novo valor unitário do item deverá ser
     * o passado como parâmetro.
     *
     * Devem ser lançadas subclasses de RuntimeException caso não seja possível adicionar o item ao carrinho de compras.
     *
     * @param produto
     * @param valorUnitario
     * @param quantidade
     */
    public void adicionarItem(Produto produto, BigDecimal valorUnitario, int quantidade) {
		try{
			boolean resultado = false;
			for(Item item : lista){
				if(item.getProduto().equals(produto)){
					item.setQuantidade(item.getQuantidade()+quantidade);
					resultado = true;
					if(item.getValorUnitario() != valorUnitario){
						item.setValorUnitario(valorUnitario);

					}

				}

			}
			if(resultado == false){
				Item item = new Item(produto,valorUnitario,quantidade);
				lista.add(item);
			}
		}catch (Exception e){
			throw new RuntimeException(e);
		}
	}

    /**
     * Permite a remoção do item que representa este produto do carrinho de compras.
     *
     * @param produto
     * @return Retorna um boolean, tendo o valor true caso o produto exista no carrinho de compras e false
     * caso o produto não exista no carrinho.
     */
    public boolean removerItem(Produto produto) {
		boolean resultado = false;
		for(Item item : lista){
			if(item.getProduto().equals(produto)){
				lista.remove(item);
				resultado = true;
			}else{
				resultado = false;
			}
		}
		return resultado;
    }

    /**
     * Permite a remoção do item de acordo com a posição.
     * Essa posição deve ser determinada pela ordem de inclusão do produto na 
     * coleção, em que zero representa o primeiro item.
     *
     * @param posicaoItem
     * @return Retorna um boolean, tendo o valor true caso o produto exista no carrinho de compras e false
     * caso o produto não exista no carrinho.
     */
    public boolean removerItem(int posicaoItem){
		if(posicaoItem<-1 || posicaoItem > lista.size()){
			return false;
		}else{
			lista.remove(posicaoItem);
			return true;
		}
    }

    /**
     * Retorna o valor total do carrinho de compras, que deve ser a soma dos valores totais
     * de todos os itens que compõem o carrinho.
     *
     * @return BigDecimal
     */
    public BigDecimal getValorTotal() {
		BigDecimal total = BigDecimal.valueOf(0);
		for(Item item : lista){
			total = total.add(item.getValorTotal());
		}
		return total;
    }

    /**
     * Retorna a lista de itens do carrinho de compras.
     *
     * @return itens
     */
    public Collection<Item> getItens() {
		return lista;
	}


}