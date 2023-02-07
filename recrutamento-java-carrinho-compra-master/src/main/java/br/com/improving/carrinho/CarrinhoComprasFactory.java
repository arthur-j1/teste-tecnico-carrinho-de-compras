package br.com.improving.carrinho;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

/**
 * Classe responsável pela criação e recuperação dos carrinhos de compras.
 */
public class CarrinhoComprasFactory {

	private final ArrayList<CarrinhoCompras> listaCarrinho = new ArrayList<>();



	public CarrinhoComprasFactory() {
	}

    /**
     * Cria e retorna um novo carrinho de compras para o cliente passado como parâmetro.
     *
     * Caso já exista um carrinho de compras para o cliente passado como parâmetro,
	 * este carrinho deverá ser retornado.
     *
     * @param identificacaoCliente
     * @return CarrinhoCompras
     */
    public CarrinhoCompras criarCarrinho(String identificacaoCliente) {

		CarrinhoCompras c1 = new CarrinhoCompras(identificacaoCliente);
		for(CarrinhoCompras carrinho : listaCarrinho){
			if(carrinho.getIdentificacaoCliente() == identificacaoCliente){
					c1 = carrinho;
			}
		}
		listaCarrinho.add(c1);
		return c1;
	}

    /**
     * Retorna o valor do ticket médio no momento da chamada ao método.
     * O valor do ticket médio é a soma do valor total de todos os carrinhos de compra dividido
     * pela quantidade de carrinhos de compra.
     * O valor retornado deverá ser arredondado com duas casas decimais, seguindo a regra:
     * 0-4 deve ser arredondado para baixo e 5-9 deve ser arredondado para cima.
     *
     * @return BigDecimal
     */
    public BigDecimal getValorTicketMedio() {
		BigDecimal total = BigDecimal.valueOf(0).setScale(2, RoundingMode.HALF_UP);
		for (CarrinhoCompras carrinho : listaCarrinho) {
			for (Item item : carrinho.getItens()){
				total = total.add(item.getValorTotal());
			}
		}
		BigDecimal numeroCarrinhos = BigDecimal.valueOf(listaCarrinho.size()).setScale(2, RoundingMode.HALF_UP);
		return total.divide(numeroCarrinhos);
    }

    /**
     * Invalida um carrinho de compras quando o cliente faz um checkout ou sua sessão expirar.
     * Deve ser efetuada a remoção do carrinho do cliente passado como parâmetro da listagem de carrinhos de compras.
     *
     * @param identificacaoCliente
     * @return Retorna um boolean, tendo o valor true caso o cliente passado como parämetro tenha um carrinho de compras e
     * e false caso o cliente não possua um carrinho.
     */
    public boolean invalidar(String identificacaoCliente) {
		boolean resultado = false;
		for(CarrinhoCompras carrinho : listaCarrinho) {
			if(carrinho.getIdentificacaoCliente() == identificacaoCliente){

				for(Item item : carrinho.getItens()){
					carrinho.getItens().remove(item);
				}
				carrinho.setIdentificacaoCliente(null);
				resultado = true;
			}
		}
		return resultado;
    }

}