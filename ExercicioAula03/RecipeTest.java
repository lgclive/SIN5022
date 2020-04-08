package eler.each.usp;


import each.usp.ach2006.adaptedfromcsc326.eler.Recipe;
import each.usp.ach2006.adaptedfromcsc326.eler.exceptions.InvalidValueException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import each.usp.ach2006.adaptedfromcsc326.eler.exceptions.InvalidValueException;


public class RecipeTest {
	/*Testa a criação de uma receita
	> Classes de Equivalência
	Descrição						Entrada														Classes Válidas				Classes Inválidas
	Valor de um ingrediente > 0		nome, preco, qntCafe, qntLeite, qntAcucar, qntChocolate		qntCafe > 0	(CE01)			qntCafe=qntLeite=qntAcucar=qntChocolare=0 (CE07)
																								qntLeite > 0 (CE02)
																								qntAcucar > 0 (CE03)
																								qntChocolate > 0 (CE04)
	Preço > 0																					preco > 0 (CE05)			preco = 0 (CE08)
	Tamanho da string				t, preco, qntCafe, qntLeite, qntAcucar, qntChocolate		t > 0 (CE06)				t = 0 (CE09)



	> Valor Limite
	Descrição								Entrada														Classes Válidas				Classes Inválidas
	Valor de um ingrediente entre 1 e 100	nome, preco, qntCafe, qntLeite, qntAcucar, qntChocolate		0<=qntCafe<=100 (VL01)		qntCafe < 0 (VL07)
																																	qntCafe > 100 (VL08)
																										0<=qntLeite<=100 (VL02)		qntLeite < 0 (VL09)
																																	qntLeite > 100 (VL10)
																										0<=qntAcucar<=100 (VL03)	qntAcucar < 0 (VL11)
																																	qntAcucar > 100 (VL12)
																										0<=qntChocolate<=100 (VL04)	qntChocolate < 0 (VL13)
																																	qntChocolate > 100 (VL14)
	Preço > 0																							preco > 0 (VL05)			preco <= 0 (VL15)
	Tamanho da string						t, preco, qntCafe, qntLeite, qntAcucar, qntChocolate		t > 0 (VL06)				t = 0 (VL16)

	>> Casos de teste
	ID	Entrada						Saída Esperada			Classe de Equivalência/Valor Limite
	01	"receita", 10, 1, 0, 0, 0	sem lançar exceção		CE01, CE05, CE06, VL01, VL05, VL06
	02	"receita", 10, 0, 1, 0, 0	sem lançar exceção		CE02, VL02
	03	"receita", 10, 0, 0, 1, 0	sem lançar exceção		CE03, VL03
	04	"receita", 10, 0, 0, 0, 1	sem lançar exceção		CE04, VL 04
	05	"receita", 10, 0, 0, 0, 0	exceção					CE07
	06	"receita", 0, 1, 2, 3, 4	exceção					CE08, VL15
	07	"", 1, 2, 3, 4, 5			exceção					CE09, VL16
	08	"receita", 10, -1, 2, 3, 4	exceção					VL07
	09	"receita", 10, 101, 2, 3, 4	exceção					VL08
	10	"receita", 10, 1, -1, 3, 4	exceção					VL09
	11	"receita", 10, 1, 101, 3, 4	exceção					VL10
	12	"receita", 10, 1, 2, -1, 4	exceção					VL11
	13	"receita", 10, 1, 2, 101, 4	exceção					VL12
	14	"receita", 10, 1, 2, 3, -1	exceção					VL13
	15	"receita", 10, 1, 2, 3, 101	exceção					VL14
	*/

	//Caso de teste 01
	@Test
	public void testCriarReceitaValidaSoCafe() throws InvalidValueException{
		Recipe recipe = new Recipe("receita",10,1,0,0,0);
		assertEquals(10,recipe.getPrice());
	}

	//Caso de teste 02
	@Test
	public void testCriarReceitaValidaSoLeite() throws InvalidValueException{
		Recipe recipe = new Recipe("receita",10,0,1,0,0);
		assertEquals(10,recipe.getPrice());
	}

	//Caso de teste 03
	@Test
	public void testCriarReceitaValidaSoAcucar() throws InvalidValueException{
		Recipe recipe = new Recipe("receita",10,0,0,1,0);
		assertEquals(10,recipe.getPrice());
	}

	//Caso de teste 04
	@Test
	public void testCriarReceitaValidaSoChocolate() throws InvalidValueException{
		Recipe recipe = new Recipe("receita",10,0,0,0,1);
		assertEquals(10,recipe.getPrice());
	}

	//Caso de teste 05
	@Test
	public void testCriarReceitaSemIngredientes() throws InvalidValueException{
		assertThrows(InvalidValueException.class, ()->new Recipe("receita",10,0,0,0,0));
	}

	//Caso de teste 06
	@Test
	public void testCriarReceitaSemPreco() throws InvalidValueException{
		assertThrows(InvalidValueException.class, ()->new Recipe("receita",0,1,2,3,4));
	}

	//Caso de teste 07
	@Test
	public void testCriarReceitaSemNome() throws InvalidValueException{
		assertThrows(InvalidValueException.class, ()->new Recipe("",1,2,3,4,5));
	}

	//Caso de teste 08
	@Test
	public void testCriarReceitaCafeNegativo() throws InvalidValueException{
		assertThrows(InvalidValueException.class, ()->new Recipe("receita",10,-1,2,3,4));
	}

	//Caso de teste 09
	@Test
	public void testCriarReceitaCafeMaiorEstoque() throws InvalidValueException{
		assertThrows(InvalidValueException.class, ()->new Recipe("receita",10,101,2,3,4));
	}

	//Caso de teste 10
	@Test
	public void testCriarReceitaLeiteNegativo() throws InvalidValueException{
		assertThrows(InvalidValueException.class, ()->new Recipe("receita",10,1,-1,3,4));
	}

	//Caso de teste 11
	@Test
	public void testCriarReceitaLeiteMaiorEstoque() throws InvalidValueException{
		assertThrows(InvalidValueException.class, ()->new Recipe("receita",10,1,101,3,4));
	}

	//Caso de teste 12
	@Test
	public void testCriarReceitaAcucarNegativo() throws InvalidValueException{
		assertThrows(InvalidValueException.class, ()->new Recipe("receita",10,1,2,-1,4));
	}

	//Caso de teste 13
	@Test
	public void testCriarReceitaAcucarMaiorEstoque() throws InvalidValueException{
		assertThrows(InvalidValueException.class, ()->new Recipe("receita",10,1,2,101,4));
	}

	//Caso de teste 14
	@Test
	public void testCriarReceitaChocolateNegativo() throws InvalidValueException{
		assertThrows(InvalidValueException.class, ()->new Recipe("receita",10,1,2,3,-1));
	}

	//Caso de teste 15
	@Test
	public void testCriarReceitaChocolateMaiorEstoque() throws InvalidValueException{
		assertThrows(InvalidValueException.class, ()->new Recipe("receita",10,1,2,3,101));
	}

}