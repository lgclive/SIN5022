package geometria;

import geometria.LadoInvalidoException;
import geometria.Triangulo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TrianguloTest {

    /*
    > Valor Limite
    Descrição		        Entrada			    Classes Válidas			        Classes Inválidas
    Lado igual a zero 	    Lado1			    Lado1=1	(VL01)			        Lado1=0 (VL05)
    Lado igual a -1		    Lado1			    Lado1=1				            Lado1=-1 (VL06)
    Lado igual a zero 	    Lado2			    Lado2=1	(VL02)			        Lado2=0 (VL07)
    Lado igual a -1		    Lado2			    Lado2=1				            Lado2=-1 (VL08)
    Lado igual a zero 	    Lado3			    Lado3=1	(VL03)			        Lado3=0 (VL09)
    Lado igual a -1		    Lado3			    Lado3=1				            Lado3=-1 (VL10)
    Triângulo equilátero	Lado1, Lado2, Lado3	Lado1==Lado2==Lado3=1 (VL04)	Lado1=Lado2+Lado3 (VL11)(Não forma triângulo)
                                                                                Lado2=Lado1+Lado3 (VL12)(Não forma triângulo)
                                                                                Lado3=Lado1+Lado2 (VL13)(Não forma triângulo)

    >> Casos de Teste
    ID	Entrada		Saída Esperada		Valor Limite
    12	0, 3, 4		Erro-valor inválido	VL05
    13	-1, 3, 4	Erro-valor inválido	VL06
    14	1, 2, 2		Isósceles		VL01
    15	3, 0, 4		Erro-valor inválido	VL07
    16	3, -1, 4	Erro-valor inválido	VL08
    17	2, 1, 2		Isósceles		VL02
    18	3, 4, 0		Erro-valor inválido	VL09
    19	3, 4, -1	Erro-valor inválido	VL10
    20	2, 2, 1		Isósceles		VL03
    21	1, 1, 1		Equilátero		VL04
    22	5, 2, 3		Não forma triângulo	VL11
    23	2, 5, 3		Não forma triângulo	VL12
    24	2, 3, 5		Não forma triângulo	VL13
    */

    //C1,C2,C3,C4
    @Test
    public void testEquilatero() throws LadoInvalidoException{
        int LA = 5;
        int LB= 5;
        int LC = 5;
        String result = Triangulo.classificaTriangulo(LA, LB, LC);
        assertEquals("EQUILATERO",result);
    }

    //C1,C2,C3,C5.1
    @Test
    public void testIsosceles1() throws LadoInvalidoException{
        int LA = 5;
        int LB= 5;
        int LC = 6;
        String result = Triangulo.classificaTriangulo(LA, LB, LC);
        assertEquals("ISOSCELES",result);
    }

    //C1,C2,C3,C5.2
    @Test
    public void testIsosceles2() throws LadoInvalidoException{
        int LA = 6;
        int LB= 5;
        int LC = 5;
        String result = Triangulo.classificaTriangulo(LA, LB, LC);
        assertEquals("ISOSCELES",result);
    }

    //C1,C2,C3,C5.3
    @Test
    public void testIsosceles3() throws LadoInvalidoException{
        int LA = 6;
        int LB= 5;
        int LC = 6;
        String result = Triangulo.classificaTriangulo(LA, LB, LC);
        assertEquals("ISOSCELES",result);
    }

    //C1,C2,C3,C6
    @Test
    public void testEscaleno() throws LadoInvalidoException{
        int LA = 4;
        int LB= 5;
        int LC = 6;
        String result = Triangulo.classificaTriangulo(LA, LB, LC);
        assertEquals("ESCALENO",result);
    }

    //C7
    @Test
    public void testLAInvalido() throws LadoInvalidoException{
        int LA = -2;
        int LB= 4;
        int LC = 5;
        assertThrows(LadoInvalidoException.class, () -> Triangulo.classificaTriangulo(LA, LB, LC));
    }

    //C8
    @Test
    public void testLBInvalido() throws LadoInvalidoException{
        int LA = 4;
        int LB= -2;
        int LC = 5;
        assertThrows(LadoInvalidoException.class, () -> Triangulo.classificaTriangulo(LA, LB, LC));
    }

    //C9
    @Test
    public void testLCInvalido() throws LadoInvalidoException{
        int LA = 4;
        int LB= 5;
        int LC = -2;
        assertThrows(LadoInvalidoException.class, () -> Triangulo.classificaTriangulo(LA, LB, LC));
    }

    //C10.1
    @Test
    public void testNaoTriangulo1() throws LadoInvalidoException{
        int LA = 10;
        int LB= 2;
        int LC = 3;
        String result = Triangulo.classificaTriangulo(LA, LB, LC);
        assertEquals("NAO FORMA TRIANGULO",result);
    }

    //C10.2
    @Test
    public void testNaoTriangulo2() throws LadoInvalidoException{
        int LA = 2;
        int LB= 10;
        int LC = 3;
        String result = Triangulo.classificaTriangulo(LA, LB, LC);
        assertEquals("NAO FORMA TRIANGULO",result);
    }

    //C10.3
    @Test
    public void testNaoTriangulo3() throws LadoInvalidoException{
        int LA = 3;
        int LB= 2;
        int LC = 10;
        String result = Triangulo.classificaTriangulo(LA, LB, LC);
        assertEquals("NAO FORMA TRIANGULO",result);
    }

    //Caso de teste 12: VL05
    @Test
    public void testLadoZero1() throws LadoInvalidoException{
        int LA = 0;
        int LB = 3;
        int LC = 4;
        assertThrows(LadoInvalidoException.class, () -> Triangulo.classificaTriangulo(LA, LB, LC));
    }

    //Caso de teste 13: VL06
    @Test
    public void testLadoNegativo1() throws LadoInvalidoException{
        int LA = -1;
        int LB = 3;
        int LC = 4;
        assertThrows(LadoInvalidoException.class, () -> Triangulo.classificaTriangulo(LA, LB, LC));
    }

    //Caso de teste 14: VL01
    @Test
    public void testLado1Isosceles1() throws LadoInvalidoException{
        int LA = 1;
        int LB = 2;
        int LC = 2;
        String result = Triangulo.classificaTriangulo(LA, LB, LC);
        assertEquals("ISOSCELES", result);
    }

    //Caso de teste 15: VL07
    @Test
    public void testLadoZero2() throws LadoInvalidoException{
        int LA = 3;
        int LB = 0;
        int LC = 4;
        assertThrows(LadoInvalidoException.class, () -> Triangulo.classificaTriangulo(LA, LB, LC));
    }

    //Caso de teste 16: VL08
    @Test
    public void testLadoNegativo2() throws LadoInvalidoException{
        int LA = 3;
        int LB = -1;
        int LC = 4;
        assertThrows(LadoInvalidoException.class, () -> Triangulo.classificaTriangulo(LA, LB, LC));
    }

    //Caso de teste 17: VL02
    @Test
    public void testLado1Isosceles2() throws LadoInvalidoException{
        int LA = 2;
        int LB = 1;
        int LC = 2;
        String result = Triangulo.classificaTriangulo(LA, LB, LC);
        assertEquals("ISOSCELES", result);
    }

    //Caso de teste 18: VL09
    @Test
    public void testLadoZero3() throws LadoInvalidoException{
        int LA = 3;
        int LB = 4;
        int LC = 0;
        assertThrows(LadoInvalidoException.class, () -> Triangulo.classificaTriangulo(LA, LB, LC));
    }

    //Caso de teste 19: VL10
    @Test
    public void testLadoNegativo3() throws LadoInvalidoException{
        int LA = 3;
        int LB = 4;
        int LC = -1;
        assertThrows(LadoInvalidoException.class, () -> Triangulo.classificaTriangulo(LA, LB, LC));
    }

    //Caso de teste 20: VL03
    @Test
    public void testLado1Isosceles3() throws LadoInvalidoException{
        int LA = 2;
        int LB = 2;
        int LC = 1;
        String result = Triangulo.classificaTriangulo(LA, LB, LC);
        assertEquals("ISOSCELES", result);
    }

    //Caso de teste 21: VL04
    @Test
    public void testLados1Equilatero() throws LadoInvalidoException{
        int LA = 1;
        int LB = 1;
        int LC = 1;
        String result = Triangulo.classificaTriangulo(LA, LB, LC);
        assertEquals("EQUILATERO", result);
    }

    //Caso de teste 22: VL11
    @Test
    public void testLado1NaoFormaTriangulo()throws LadoInvalidoException{
        int LA = 5;
        int LB = 2;
        int LC = 3;
        String result = Triangulo.classificaTriangulo(LA, LB, LC);
        assertEquals("NAO FORMA TRIANGULO", result);
    }

    //Caso de teste 23: VL12
    @Test
    public void testLado2NaoFormaTriangulo()throws LadoInvalidoException{
        int LA = 2;
        int LB = 5;
        int LC = 3;
        String result = Triangulo.classificaTriangulo(LA, LB, LC);
        assertEquals("NAO FORMA TRIANGULO", result);
    }

    //Caso de teste 24: VL13
    @Test
    public void testLado3NaoFormaTriangulo()throws LadoInvalidoException{
        int LA = 2;
        int LB = 3;
        int LC = 5;
        String result = Triangulo.classificaTriangulo(LA, LB, LC);
        assertEquals("NAO FORMA TRIANGULO", result);
    }
}
