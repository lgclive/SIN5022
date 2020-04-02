package sillypascal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IdentifierTest {

    /*
    > Valor Limite
    Descrição			        Entrada			    Classes Válidas			    Classes Inválidas
    Tamanho do identificador	tamanho t		    t=1 (VL01.1), t=6 (VL01.2)	t=0 (VL03.1), t=7 (VL03.2)
    Primeiro caractere do id	primeiro caractere	a-zA-Z (VL02)			    0-9 (VL04)

    >> Casos de Teste
    ID	Entrada		Saída Esperada		Classe de Equivalência
    06	a		    válido			    VL01.1
    07	abcdef		válido			    VL01.2
    08	""		    inválido		    VL03.1
    09	abcdefg		inválido		    VL03.2
    10	a		    válido			    VL02
    11	0		    inválido		    VL04
    */

    //C1,C2,C3
    @Test
    public void testIdentificadorValidoTamanhoMedio(){
        boolean valido = Identifier.validaIdentificador("abc");
        Assertions.assertTrue(valido);
        //ou assertEquals(true,valido);
    }

    //C4.1
    @Test
    public void testIdentificadorInvalidoVazio(){
        boolean valido = Identifier.validaIdentificador("");
        Assertions.assertFalse(valido);
        //ou assertEquals(false,valido);
    }

    //Caso de teste 3: C4.2
    @Test
    public void testIdentificadorInvalidoMaior(){
        boolean valido = Identifier.validaIdentificador("abcdefgh");
        Assertions.assertEquals(false, valido);
    }

    //Caso de teste 4: C5
    @Test
    public void testIdentificadorInvalidoComecaNumero(){
        boolean valido = Identifier.validaIdentificador("1abc");
        Assertions.assertEquals(false, valido);
    }

    //Caso de teste 5: C6
    @Test
    public void testIdentificadorInvalidoCaractereEspecial(){
        boolean valido = Identifier.validaIdentificador("ab-cd");
        Assertions.assertEquals(false, valido);
    }

    //Caso de teste 06: VL01.1
    @Test
    public void testIdentificadorValidoTamanhoMinimo(){
        boolean valido = Identifier.validaIdentificador("a");
        Assertions.assertEquals(true, valido);
    }

    //Caso de teste 07: VL01.2
    @Test
    public void testIdentificadorValidoTamanhoMaximo(){
        boolean valido = Identifier.validaIdentificador("abcdef");
        Assertions.assertEquals(true, valido);
    }

    //Caso de teste 08: VL03.1
    @Test
    public void testIdentificadorInvalidoTamanhoMenorMinimo(){
        boolean valido = Identifier.validaIdentificador("");
        Assertions.assertEquals(false, valido);
    }

    //Caso de teste 09: VL03.2
    @Test
    public void testIdentificadorInvalidoTamanhoMaiorMaximo(){
        boolean valido = Identifier.validaIdentificador("abcdefg");
        Assertions.assertEquals(false, valido);
    }

    //Caso de teste 10: VL02
    @Test
    public void testIdentificadorValidoPrimeiroCaractereLetra(){
        boolean valido = Identifier.validaIdentificador("a");
        Assertions.assertEquals(true, valido);
    }

    //Caso de teste 11: VL04
    @Test
    public void testIdentificadorInvalidoPrimeiroCaractereNumero(){
        boolean valido = Identifier.validaIdentificador("0");
        Assertions.assertEquals(false, valido);
    }
}
