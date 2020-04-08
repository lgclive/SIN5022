package eler.each.usp;

import each.usp.ach2006.adaptedfromcsc326.eler.CoffeeMaker;
import each.usp.ach2006.adaptedfromcsc326.eler.Recipe;
import each.usp.ach2006.adaptedfromcsc326.eler.exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;


/**
 * 
 * @author Sarah Heckman
 *
 * Unit tests for CoffeeMaker class.
 */
public class CoffeeMakerTest {

	private CoffeeMaker cm;
	private Recipe receita1;
	private Recipe receita2;
	private Recipe receita3;
	private Recipe receita4;
	private Recipe receita5;
	private Recipe receita6;
	private Recipe receita7;
	private Recipe receita8;


	@BeforeEach
	public void setUp() throws Exception {
		cm = new CoffeeMaker();
		receita1 = new Recipe("Coffee",50,4,0,1,0);
		receita2 = new Recipe("Hot Chocolate",75,0,3,1,3);
		receita3 = new Recipe("Cappuccino",75,5,25,1,2);
		receita4 = new Recipe("Mocaccino", 75, 5, 15, 1, 3);
		receita5 = new Recipe("Coffee",25,2,0,1,0);
		receita6 = new Recipe("Hot Chocolate",50,0,2,1,2);
		receita7 = new Recipe("MyCoffee",50,4,0,1,0);
		receita8 = new Recipe("My Hot Chocolate",75,0,3,1,3);
	}
	
	/*Testa a adição de uma receita
	> Classes de Equivalência
	Descrição						Entrada			Classes Válidas												Classes Inválidas
	Quantidade de Receitas			receita			receita1 (CE01)												receita1, receita2, receita3, receita4 (CE08)
	Receitas Diferentes				receita			receita1, receita2 (CE02)									receita1, receita1 (CE09)
													receita1, receita2, receita3 (CE03)							receita1, receita2, receita1 (CE10)
																												receita1, receita2, receita2 (CE11)
	Nomes Diferentes				receita(nome)	receita1(nome1), receita2(nome2) (CE04)						receita1(nome), receita2(nome) (CE12)
													receita1(nome1), receita2(nome2), receita3(nome3) (CE05)	receita1(nome1), receita2(nome2), receita3(nome1) (CE13)
																												receita1(nome1), receita2(nome2), receita3(nome2) (CE14)
	Ingredientes Diferentes			receita(ingr)	receita1(ingr1), receita2(ingr2) (CE06)						receita1(ingr1), receita2(ingr1) (CE15)
													receita1(ingr1), receita2(ingr2), receita3(ingr3) (CE07)	receita1(ingr1), receita2(ingr2), receita3(ingr1) (CE16)
																												receita1(ingr1), receita2(ingr2), receita3(ingr2) (CE17)
	> Valor Limite
	Descrição								Entrada														Classes Válidas				Classes Inválidas
	Número de Receitas						receita														receita1 (VL01)				receita1, receita2, receita3, receita4 (VL02)

	>> Casos de teste
	ID	Entrada									Saída Esperada			Classe de Equivalência/Valor Limite
	01	receita1("Coffee",50,4,0,1,0)			true					CE01, CE02, CE03, CE04, CE05, CE06, CE07, VL01
		receita2("Hot Chocolate",75,0,3,1,3)
		receita3("Cappuccino",75,5,25,1,2)
	02	receita1("Coffee",50,4,0,1,0)			false					CE08, VL02
		receita2("Hot Chocolate",75,0,3,1,3)
		receita3("Cappuccino",75,5,25,1,2)
		receita4("Mocaccino", 75, 5, 15, 1, 3)
	03	receita1("Coffee",50,4,0,1,0)			exceção					CE09
		receita1("Coffee",50,4,0,1,0)
	04	receita1("Coffee",50,4,0,1,0)			exceção					CE10
		receita2("Hot Chocolate",75,0,3,1,3)
		receita1("Coffee",50,4,0,1,0)
	05	receita1("Coffee",50,4,0,1,0)			exceção					CE11
		receita2("Hot Chocolate",75,0,3,1,3)
		receita2("Hot Chocolate",75,0,3,1,3)
	06	receita1("Coffee",50,4,0,1,0)			exceção					CE12
		receita5("Coffee",25,2,0,1,0)
	07	receita1("Coffee",50,4,0,1,0)			exceção					CE13
		receita2("Hot Chocolate",75,0,3,1,3)
		receita5("Coffee",25,2,0,1,0)
	08	receita1("Coffee",50,4,0,1,0)			exceção					CE14
		receita2("Hot Chocolate",75,0,3,1,3)
		receita6("Hot Chocolate",50,0,2,1,2)
	09	receita1("Coffee",50,4,0,1,0)			exceção					CE15
		receita7("MyCoffee",50,4,0,1,0)
	10	receita1("Coffee",50,4,0,1,0)			exceção					CE16
		receita2("Hot Chocolate",75,0,3,1,3)
		receita7("MyCoffee",50,4,0,1,0)
	11	receita1("Coffee",50,4,0,1,0)			exceção					CE17
		receita2("Hot Chocolate",75,0,3,1,3)
		receita8("My Hot Chocolate",75,0,3,1,3)
	 */


	//Caso de teste 01
	@Test
	public void testAdionaQuantidadeValidaReceita() throws AmountOfRecipeException, DuplicatedRecipeException{
		boolean ok;
		ok = cm.addRecipe(receita1);
		assertTrue(ok);
		ok = cm.addRecipe(receita2);
		assertTrue(ok);
		ok = cm.addRecipe(receita3);
		assertTrue(ok);
	}

	//Caso de teste 02
	@Test
	public void testAdionaQuantidadeInvalidaReceita() throws AmountOfRecipeException, DuplicatedRecipeException{
		boolean ok;
		ok = cm.addRecipe(receita1);
		assertTrue(ok);
		ok = cm.addRecipe(receita2);
		assertTrue(ok);
		ok = cm.addRecipe(receita3);
		assertTrue(ok);
		ok = cm.addRecipe(receita4);
		assertFalse(ok);
	}

	//Caso de teste 03
	@Test
	public void testAdionaReceitasIguais() throws AmountOfRecipeException, DuplicatedRecipeException{
		boolean ok;
		ok = cm.addRecipe(receita1);
		assertTrue(ok);
		assertThrows(DuplicatedRecipeException.class,()->cm.addRecipe(receita1));

	}

	//Caso de teste 04
	@Test
	public void testAdionaTresReceitasDuasIguais1() throws AmountOfRecipeException, DuplicatedRecipeException{
		boolean ok;
		ok = cm.addRecipe(receita1);
		assertTrue(ok);
		ok = cm.addRecipe(receita2);
		assertTrue(ok);
		assertThrows(DuplicatedRecipeException.class,()->cm.addRecipe(receita1));

	}

	//Caso de teste 05
	@Test
	public void testAdionaTresReceitasDuasIguais2() throws AmountOfRecipeException, DuplicatedRecipeException{
		boolean ok;
		ok = cm.addRecipe(receita1);
		assertTrue(ok);
		ok = cm.addRecipe(receita2);
		assertTrue(ok);
		assertThrows(DuplicatedRecipeException.class,()->cm.addRecipe(receita2));

	}

	//Caso de teste 06
	@Test
	public void testAdionaDuasReceitasNomesIguais() throws AmountOfRecipeException, DuplicatedRecipeException{
		boolean ok;
		ok = cm.addRecipe(receita1);
		assertTrue(ok);
		assertThrows(DuplicatedRecipeException.class,()->cm.addRecipe(receita5));

	}

	//Caso de teste 07
	@Test
	public void testAdionaTresReceitasDuasNomesIguais1() throws AmountOfRecipeException, DuplicatedRecipeException{
		boolean ok;
		ok = cm.addRecipe(receita1);
		assertTrue(ok);
		ok = cm.addRecipe(receita2);
		assertTrue(ok);
		assertThrows(DuplicatedRecipeException.class,()->cm.addRecipe(receita5));

	}

	//Caso de teste 08
	@Test
	public void testAdionaTresReceitasDuasNomesIguais2() throws AmountOfRecipeException, DuplicatedRecipeException{
		boolean ok;
		ok = cm.addRecipe(receita1);
		assertTrue(ok);
		ok = cm.addRecipe(receita2);
		assertTrue(ok);
		assertThrows(DuplicatedRecipeException.class,()->cm.addRecipe(receita6));

	}

	//Caso de teste 09
	@Test
	public void testAdionaDuasReceitasIngredientesIguais() throws AmountOfRecipeException, DuplicatedRecipeException{
		boolean ok;
		ok = cm.addRecipe(receita1);
		assertTrue(ok);
		assertThrows(DuplicatedRecipeException.class,()->cm.addRecipe(receita7));

	}

	//Caso de teste 10
	@Test
	public void testAdionaTresReceitasDuasIngredientesIguais1() throws AmountOfRecipeException, DuplicatedRecipeException{
		boolean ok;
		ok = cm.addRecipe(receita1);
		assertTrue(ok);
		ok = cm.addRecipe(receita2);
		assertTrue(ok);
		assertThrows(DuplicatedRecipeException.class,()->cm.addRecipe(receita7));

	}

	//Caso de teste 10
	@Test
	public void testAdionaTresReceitasDuasIngredientesIguais2() throws AmountOfRecipeException, DuplicatedRecipeException{
		boolean ok;
		ok = cm.addRecipe(receita1);
		assertTrue(ok);
		ok = cm.addRecipe(receita2);
		assertTrue(ok);
		assertThrows(DuplicatedRecipeException.class,()->cm.addRecipe(receita8));

	}

	/*Testa a remoção de uma receita
	> Classes de Equivalência
	Descrição						Entrada			Classes Válidas			Classes Inválidas
	Remove receita já inserida		receita(nome)	receita(nome) (CE01)	receita1(nome) (CE03)
	Não remove receita já removida	receita(nome)	receita(nome) (CE02)	receita(nome), receita(nome)(CE04)

	> Valor Limite
	Descrição					Entrada											Classes Válidas												Classes Inválidas
	Número de Receitas			receita1(nome), receita2(nome), receita3(nome)	receita1(nome), receita2(nome), receita3(nome) (VL01)		receita1(nome), receita2(nome), receita3(nome), receita4(nome) (VL02)

	>> Casos de teste
	ID	Entrada													Saída Esperada				Classe de Equivalência/Valor Limite
	11	"Coffee"												true						CE01, CE02
	12	"Cafe"													exception					CE03
	13	"Coffee", "Coffee"										true, exception				CE04
	14	"Coffee", "Hot Chocolate", "Cappuccino"					true, true, true			VL01
	15	"Coffee", "Hot Chocolate", "Cappuccino", "Mocaccino"	true, true, true, exception	VL02
	 */

	//Caso de teste 11
	@Test
	public void testRemoveReceitaExistente() throws RecipeException, AmountOfRecipeException, DuplicatedRecipeException{
		boolean ok;
		cm.addRecipe(receita1);
		ok = cm.deleteRecipe("Coffee");
		assertTrue(ok);
	}

	//Caso de teste 12
	@Test
	public void testRemoveReceitaInexistente() throws RecipeException, AmountOfRecipeException, DuplicatedRecipeException{
		cm.addRecipe(receita1);
		assertThrows(RecipeException.class, ()->cm.deleteRecipe("Cafe"));
	}

	//Caso de teste 13
	@Test
	public void testRemoveReceitaDuasVezes() throws RecipeException, AmountOfRecipeException, DuplicatedRecipeException{
		boolean ok;
		cm.addRecipe(receita1);
		ok = cm.deleteRecipe("Coffee");
		assertTrue(ok);
		assertThrows(RecipeException.class, ()->cm.deleteRecipe("Coffee"));
	}

	//Caso de teste 14
	@Test
	public void testRemoveTresReceitas() throws RecipeException, AmountOfRecipeException, DuplicatedRecipeException{
		boolean ok;
		cm.addRecipe(receita1);
		cm.addRecipe(receita2);
		cm.addRecipe(receita3);
		ok = cm.deleteRecipe("Coffee");
		assertTrue(ok);
		ok = cm.deleteRecipe("Hot Chocolate");
		assertTrue(ok);
		ok = cm.deleteRecipe("Capuccino");
		assertTrue(ok);
	}

	//Caso de teste 15
	@Test
	public void testRemoveQuatroReceitas() throws RecipeException, AmountOfRecipeException, DuplicatedRecipeException{
		boolean ok;
		cm.addRecipe(receita1);
		cm.addRecipe(receita2);
		cm.addRecipe(receita3);
		ok = cm.deleteRecipe("Coffee");
		assertTrue(ok);
		ok = cm.deleteRecipe("Hot Chocolate");
		assertTrue(ok);
		ok = cm.deleteRecipe("Capuccino");
		assertTrue(ok);
		assertThrows(RecipeException.class, ()->cm.deleteRecipe("Mocaccino"));
	}

	/*Testa a reposição de café
	> Classes de Equivalência
	Descrição						Entrada			Classes Válidas					Classes Inválidas
	Início da máquina (estoque=20)	quantidade		0<=quantidade<=80 (CE01)		quantidade < 0 (CE03)
																					quantidade > 80 (CE04)
	Máquina já em funcionamento		quantidade		quantidade+estoque <=100 (CE02)	quantidade+estoque > 100 (CE05)

	> Valor Limite
	Descrição						Entrada			Classes Válidas					Classes Inválidas
	Início da máquina (estoque=20)	quantidade		quantidade = 0 (VL01)			quantidade < 0 (VL04)
													quantidade = 80 (VL02)			quantidade > 80 (VL05)
	Máquina já em funcionamento		quantidade		quantidade+estoque = 100 (VL03)	quantidade+estoque = 101 (VL06)

	>> Casos de teste
	ID	Entrada		Saída Esperada				Classe de Equivalência/Valor Limite
	16	20			estoque=40					CE01, CE02
	17	0			estoque						VL01
	18	-1			exception					CE03, VL04
	19	81			exception					CE04, VL05
	20	101			exception					CE05, VL06
	21	80			estoque=100					VL02, VL03
	 */

	//Caso de teste 16
	@Test
	public void testRepoeCafe() throws InvalidValueException{
		int inventario = cm.checkCoffeeInventory();
		cm.addChocolateInventory(20);
		assertEquals(inventario+20, cm.checkCoffeeInventory());

	}

	//Caso de teste 17
	@Test
	public void testNaoRepoeCafe() throws InvalidValueException{
		int inventario = cm.checkCoffeeInventory();
		cm.addChocolateInventory(0);
		assertEquals(inventario, cm.checkCoffeeInventory());

	}

	//Caso de teste 18
	@Test
	public void testRepoeCafeQuantidadeNegativa() throws InvalidValueException{
		assertThrows(InvalidValueException.class, ()->cm.addChocolateInventory(-1));
	}

	//Caso de teste 19
	@Test
	public void testRepoeCafeQuantidadeAcimaLimiteInicio() throws InvalidValueException{
		assertThrows(InvalidValueException.class, ()->cm.addChocolateInventory(81));
	}

	//Caso de teste 20
	@Test
	public void testRepoeCafeQuantidadeAcimaLimite() throws InvalidValueException{
		assertThrows(InvalidValueException.class, ()->cm.addChocolateInventory(101));
	}

	//Caso de teste 21
	@Test
	public void testRepoeCafeAteLimite() throws InvalidValueException{
		cm.addChocolateInventory(80);
		assertEquals(100, cm.checkCoffeeInventory());

	}

	/*Testa a reposição de leite
	> Classes de Equivalência
	Descrição						Entrada			Classes Válidas					Classes Inválidas
	Início da máquina (estoque=20)	quantidade		0<=quantidade<=80 (CE01)		quantidade < 0 (CE03)
																					quantidade > 80 (CE04)
	Máquina já em funcionamento		quantidade		quantidade+estoque <=100 (CE02)	quantidade+estoque > 100 (CE05)

	> Valor Limite
	Descrição						Entrada			Classes Válidas					Classes Inválidas
	Início da máquina (estoque=20)	quantidade		quantidade = 0 (VL01)			quantidade < 0 (VL04)
													quantidade = 80 (VL02)			quantidade > 80 (VL05)
	Máquina já em funcionamento		quantidade		quantidade+estoque = 100 (VL03)	quantidade+estoque = 101 (VL06)

	>> Casos de teste
	ID	Entrada		Saída Esperada				Classe de Equivalência/Valor Limite
	22	20			estoque=40					CE01, CE02
	23	0			estoque						VL01
	24	-1			exception					CE03, VL04
	25	81			exception					CE04, VL05
	26	101			exception					CE05, VL06
	27	80			estoque=100					VL02, VL03
	 */

	//Caso de teste 22
	@Test
	public void testRepoeLeite() throws InvalidValueException{
		int inventario = cm.checkMilkInventory();
		cm.addMilkInventory(20);
		assertEquals(inventario+20, cm.checkMilkInventory());

	}

	//Caso de teste 23
	@Test
	public void testNaoRepoeLeite() throws InvalidValueException{
		int inventario = cm.checkMilkInventory();
		cm.addMilkInventory(0);
		assertEquals(inventario, cm.checkMilkInventory());

	}

	//Caso de teste 24
	@Test
	public void testRepoeLeiteQuantidadeNegativa() throws InvalidValueException{
		assertThrows(InvalidValueException.class, ()->cm.addMilkInventory(-1));
	}

	//Caso de teste 25
	@Test
	public void testRepoeLeiteQuantidadeAcimaLimiteInicio() throws InvalidValueException{
		assertThrows(InvalidValueException.class, ()->cm.addMilkInventory(81));
	}

	//Caso de teste 26
	@Test
	public void testRepoeLeiteQuantidadeAcimaLimite() throws InvalidValueException{
		assertThrows(InvalidValueException.class, ()->cm.addMilkInventory(101));
	}

	//Caso de teste 27
	@Test
	public void testRepoeLeiteAteLimite() throws InvalidValueException{
		cm.addMilkInventory(80);
		assertEquals(100, cm.checkMilkInventory());

	}

	/*Testa a reposição de açucar
	> Classes de Equivalência
	Descrição						Entrada			Classes Válidas					Classes Inválidas
	Início da máquina (estoque=20)	quantidade		0<=quantidade<=80 (CE01)		quantidade < 0 (CE03)
																					quantidade > 80 (CE04)
	Máquina já em funcionamento		quantidade		quantidade+estoque <=100 (CE02)	quantidade+estoque > 100 (CE05)

	> Valor Limite
	Descrição						Entrada			Classes Válidas					Classes Inválidas
	Início da máquina (estoque=20)	quantidade		quantidade = 0 (VL01)			quantidade < 0 (VL04)
													quantidade = 80 (VL02)			quantidade > 80 (VL05)
	Máquina já em funcionamento		quantidade		quantidade+estoque = 100 (VL03)	quantidade+estoque = 101 (VL06)

	>> Casos de teste
	ID	Entrada		Saída Esperada				Classe de Equivalência/Valor Limite
	28	20			estoque=40					CE01, CE02
	29	0			estoque						VL01
	30	-1			exception					CE03, VL04
	31	81			exception					CE04, VL05
	32	101			exception					CE05, VL06
	33	80			estoque=100					VL02, VL03
	 */

	//Caso de teste 28
	@Test
	public void testRepoeAcucar() throws InvalidValueException{
		int inventario = cm.checkSugarInventory();
		cm.addSugarInventory(20);
		assertEquals(inventario+20, cm.checkSugarInventory());

	}

	//Caso de teste 29
	@Test
	public void testNaoRepoeAcucar() throws InvalidValueException{
		int inventario = cm.checkSugarInventory();
		cm.addSugarInventory(0);
		assertEquals(inventario, cm.checkSugarInventory());

	}

	//Caso de teste 30
	@Test
	public void testRepoeAcucarQuantidadeNegativa() throws InvalidValueException{
		assertThrows(InvalidValueException.class, ()->cm.addSugarInventory(-1));
	}

	//Caso de teste 31
	@Test
	public void testRepoeAcucarQuantidadeAcimaLimiteInicio() throws InvalidValueException{
		assertThrows(InvalidValueException.class, ()->cm.addSugarInventory(81));
	}

	//Caso de teste 32
	@Test
	public void testRepoeAcucarQuantidadeAcimaLimite() throws InvalidValueException{
		assertThrows(InvalidValueException.class, ()->cm.addSugarInventory(101));
	}

	//Caso de teste 33
	@Test
	public void testRepoeAcucarAteLimite() throws InvalidValueException{
		cm.addSugarInventory(80);
		assertEquals(100, cm.checkSugarInventory());

	}

	/*Testa a reposição de chocolate
	> Classes de Equivalência
	Descrição						Entrada			Classes Válidas					Classes Inválidas
	Início da máquina (estoque=20)	quantidade		0<=quantidade<=80 (CE01)		quantidade < 0 (CE03)
																					quantidade > 80 (CE04)
	Máquina já em funcionamento		quantidade		quantidade+estoque <=100 (CE02)	quantidade+estoque > 100 (CE05)

	> Valor Limite
	Descrição						Entrada			Classes Válidas					Classes Inválidas
	Início da máquina (estoque=20)	quantidade		quantidade = 0 (VL01)			quantidade < 0 (VL04)
													quantidade = 80 (VL02)			quantidade > 80 (VL05)
	Máquina já em funcionamento		quantidade		quantidade+estoque = 100 (VL03)	quantidade+estoque = 101 (VL06)

	>> Casos de teste
	ID	Entrada		Saída Esperada				Classe de Equivalência/Valor Limite
	34	20			estoque=40					CE01, CE02
	35	0			estoque						VL01
	36	-1			exception					CE03, VL04
	37	81			exception					CE04, VL05
	38	101			exception					CE05, VL06
	39	80			estoque=100					VL02, VL03
	 */

	//Caso de teste 34
	@Test
	public void testRepoeChocolate() throws InvalidValueException{
		int inventario = cm.checkChocolateInventory();
		cm.addChocolateInventory(20);
		assertEquals(inventario+20, cm.checkChocolateInventory());

	}

	//Caso de teste 35
	@Test
	public void testNaoRepoeChocolate() throws InvalidValueException{
		int inventario = cm.checkChocolateInventory();
		cm.addChocolateInventory(0);
		assertEquals(inventario, cm.checkChocolateInventory());

	}

	//Caso de teste 36
	@Test
	public void testRepoeChocolateQuantidadeNegativa() throws InvalidValueException{
		assertThrows(InvalidValueException.class, ()->cm.addChocolateInventory(-1));
	}

	//Caso de teste 37
	@Test
	public void testRepoeChocolateQuantidadeAcimaLimiteInicio() throws InvalidValueException{
		assertThrows(InvalidValueException.class, ()->cm.addChocolateInventory(81));
	}

	//Caso de teste 38
	@Test
	public void testRepoeChocolateQuantidadeAcimaLimite() throws InvalidValueException{
		assertThrows(InvalidValueException.class, ()->cm.addChocolateInventory(101));
	}

	//Caso de teste 39
	@Test
	public void testRepoeChocolateAteLimite() throws InvalidValueException{
		cm.addChocolateInventory(80);
		assertEquals(100, cm.checkChocolateInventory());

	}

	/*Testa inventário de Café
	>> Casos de teste
	ID	Entrada		Saída Esperada				Classe de Equivalência/Valor Limite
	40				20
	 */

	//Caso de teste 40
	@Test
	public void testInventarioCafe(){
		int inventario = cm.checkCoffeeInventory();
		assertEquals(20, inventario);
	}

	/*Testa inventário de Café
	>> Casos de teste
	ID	Entrada		Saída Esperada				Classe de Equivalência/Valor Limite
	41				20
	 */

	//Caso de teste 41
	@Test
	public void testInventarioLeite(){
		int inventario = cm.checkMilkInventory();
		assertEquals(20, inventario);
	}

	/*Testa inventário de Café
	>> Casos de teste
	ID	Entrada		Saída Esperada				Classe de Equivalência/Valor Limite
	42				20
	 */

	//Caso de teste 42
	@Test
	public void testInventarioAcucar(){
		int inventario = cm.checkSugarInventory();
		assertEquals(20, inventario);
	}

	/*Testa inventário de Café
	>> Casos de teste
	ID	Entrada		Saída Esperada				Classe de Equivalência/Valor Limite
	43				20
	 */

	//Caso de teste 43
	@Test
	public void testInventarioChocolate(){
		int inventario = cm.checkChocolateInventory();
		assertEquals(20, inventario);
	}

	/*Testa a preparação de receita
	> Classes de Equivalência
	Descrição						Entrada			Classes Válidas					Classes Inválidas
    receita e valor corretos        nome, valor     nome, valor (CE01)              nomeInv, valor (CE03)
                                                    nome, valorMaior (CE02)         nome, valorMenor (CE04)

	> Valor Limite
	Descrição						Entrada			Classes Válidas					Classes Inválidas
    receita e valor corretos        receita, valor  nome, valor (VL01)              nome, valor-1 (VL03)
                                                    nome, valor+1 (VL02)

	>> Casos de teste
	ID	Entrada		    Saída Esperada				Classe de Equivalência/Valor Limite
    44  "Coffee", 50    0                           CE01, VL01
    45  "Coffee", 51    1                           CE02, VL02
    46  "Cafe", 50      excecao                     CE03
    47  "Coffee", 49    excecao                     CE04, VL03
	 */

    //Caso de teste 44
    @Test
    public void testPreparaReceitaValidaSemTroco() throws AmountOfRecipeException, DuplicatedRecipeException, InsufficientAmountOfMoneyException, RecipeException, InventoryException, InvalidValueException {
        int inventarioCafe = cm.checkCoffeeInventory();
        int inventarioChocolate = cm.checkChocolateInventory();
        int inventarioLeite = cm.checkMilkInventory();
        int inventarioAcucar = cm.checkSugarInventory();
        boolean ok = cm.addRecipe(receita1);
        int troco = cm.makeCoffee("Coffee", 50);

        assertEquals(0, troco);
        assertEquals(inventarioCafe-4, cm.checkCoffeeInventory());
        assertEquals(inventarioLeite, cm.checkMilkInventory());
        assertEquals(inventarioChocolate, cm.checkCoffeeInventory());
        assertEquals(inventarioAcucar-1, cm.checkCoffeeInventory());
    }

    //Caso de teste 45
    @Test
    public void testPreparaReceitaValidaComTroco() throws AmountOfRecipeException, DuplicatedRecipeException, InsufficientAmountOfMoneyException, RecipeException, InventoryException, InvalidValueException {
        int inventarioCafe = cm.checkCoffeeInventory();
        int inventarioChocolate = cm.checkChocolateInventory();
        int inventarioLeite = cm.checkMilkInventory();
        int inventarioAcucar = cm.checkSugarInventory();
        boolean ok = cm.addRecipe(receita1);
        int troco = cm.makeCoffee("Coffee", 51);

        assertEquals(1, troco);
        assertEquals(inventarioCafe-4, cm.checkCoffeeInventory());
        assertEquals(inventarioLeite, cm.checkMilkInventory());
        assertEquals(inventarioChocolate, cm.checkCoffeeInventory());
        assertEquals(inventarioAcucar-1, cm.checkCoffeeInventory());
    }

    //Caso de teste 46
    @Test
    public void testPreparaReceitaNomeInvalido() throws AmountOfRecipeException, DuplicatedRecipeException, InsufficientAmountOfMoneyException, RecipeException, InventoryException, InvalidValueException{
        boolean ok = cm.addRecipe(receita1);

        assertThrows(RecipeException.class, ()-> cm.makeCoffee("Cafe", 50));
    }

    //Caso de teste 47
    @Test
    public void testPreparaReceitaDinheiroInsuficiente() throws AmountOfRecipeException, DuplicatedRecipeException, InsufficientAmountOfMoneyException, RecipeException, InventoryException, InvalidValueException{
        boolean ok = cm.addRecipe(receita1);

        assertThrows(InsufficientAmountOfMoneyException.class, ()-> cm.makeCoffee("Coffee", 49));
    }

    /*Testa a obtenção das receitas
	>> Casos de teste
	ID	Entrada		                    Saída Esperada			Classe de Equivalência/Valor Limite
	48  (nenhuma receita cadastrada)    vazio
	49  (receitas cadastrada)           receitas
     */

    //Caso de teste 47
    @Test
    public void testObtencaoReceitasInseridas()throws AmountOfRecipeException, DuplicatedRecipeException {
        Vector vetor = new Vector<Recipe>();
        vetor.add(receita1);
        cm.addRecipe(receita1);
        assertEquals(vetor, cm.getRecipes());
    }

    //Caso de teste 48
    @Test
    public void testObtencaoReceitasQuandoNenhumaFoiInserida()throws AmountOfRecipeException, DuplicatedRecipeException {
        Vector vetor = new Vector<Recipe>();
        assertEquals(vetor, cm.getRecipes());
    }

}
