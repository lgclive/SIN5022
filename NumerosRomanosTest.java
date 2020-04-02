package romanos;

import geometria.LadoInvalidoException;
import geometria.Triangulo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NumerosRomanosTest {
    /*
    > Classes de Equivalência
Descrição			            Entrada				        Classes Válidas				    Classes Inválidas
Tamanho da string	        	t				            t>0 (CE01)				        t=0 (CE21)
I antes de V e X	        	n				            n = IV (CE02)				    n = IL (CE22)
						        		                    n = IX (CE03)			    	n = IC (CE23)
						        							                                n = ID (CE24)
						        							                                n = IM (CE25)
X antes de L e C	        	n				            n = XL (CE04)			    	n = XD (CE26)
						        		                    n = XC (CE05)				    n = XM (CE27)
C antes de D e M		        n				            n = CD (CE06)
							        	                    n = CM (CE07)
I agrupado max. 3x		        n (Quantidade I qntI)		qntI<=3 (CE08)				    qntI>3 (CE28)
X agrupado max. 3x		        n (Quantidade X qntX)		qntX<=3 (CE09)				    qntX>3 (CE29)
C agrupado max. 3x		        n (Quantidade C qntC)		qntC<=3 (CE10)				    qntC>3 (CE30)
M agrupado max. 3x		        n (Quantidade M qntM)		qntM<=3 (CE11)				    qntM>3 (CE31)
V não tem agrupamento	        n (Quantidade V qntV)		qntV<=1 (CE12)				    qntV>1 (CE32)
L não tem agrupamento	        n (Quantidade L qntL)		qntL<=1 (CE13)				    qntL>1 (CE33)
Letras iguais se soma	        n (LetraLetra)			    n contem Is (CE14)			    n contem Vs (CE34)
								                            n contem Xs (CE15)			    n contem Ls (CE35)
								                            n contem Cs (CE16)
								                            n contem Ms (CE17)
Letras diferentes menor antes	n (LetramLetraM)	        n = LetraM-Letram (CE18)	    n = Letram + LetraM (CE36)
Letras diferentes maior antes	n (LetraMLetram)	        n = LetraM+Letram (CE19)	    n = LetraM - Letram (CE37)
Entre duas letras letra menor	n (LetraLetramLetraM)	    n = Letra+(LetraM-Letram)(CE20)	n = Letra+Letram+LetraM (CE38)														(CE38)



>> Casos de Teste
ID	Entrada		Saída Esperada		Classe de Equivalência
01	I		    1	        		CE01
02	IV		    4		        	CE02, CE18, CE36
03	IX		    9		        	CE03
04	XL		    40			        CE04
05	XC		    90			        CE05
06	CD		    400			        CE06
07	CM		    900			        CE07
08	I		    1			        CE08
09	X		    10			        CE09
10	C		    100			        CE10
11	M		    1000		        CE11
12	V		    5		        	CE12
13	L		    50			        CE13
14	II		    2			        CE14
15	XX		    20			        CE15
16	CC		    200			        CE16
17	MM		    2000		        CE17
18	VI		    6			        CE19, CE37
19	XIX		    19			        CE20, CE38
20	""		    número inválido		CE21
21	IL		    número inválido		CE22
22	IC		    número inválido		CE23
23	ID		    número inválido		CE24
24	IM		    número inválido		CE25
25	XD		    número inválido		CE26
26	XM		    número inválido		CE27
27	IIII		número inválido		CE28
28	XXXX		número inválido		CE29
29	CCCC		número inválido		CE30
30	MMMM		número inválido		CE31
31	VV		    número inválido		CE32, CE34
32	LL		    número inválido		CE33, CE35
     */

    //Caso de teste 01, 08: CE01, CE08
    @Test
    public void testTamanhoString(){
        int result = NumerosRomanos.convert("I");
        int expected = 1;
        assertEquals(expected, result);
    }

    //Caso de teste 02: CE02, CE18, CE36
    @Test
    public void testIantesV(){
        int result = NumerosRomanos.convert("IV");
        int expected = 4;
        assertEquals(expected, result);
    }

    //Caso de teste 03: CE03
    @Test
    public void testIantesX(){
        int result = NumerosRomanos.convert("IX");
        int expected = 9;
        assertEquals(expected, result);
    }

    //Caso de teste 04: CE04
    @Test
    public void testXantesL(){
        int result = NumerosRomanos.convert("XL");
        int expected = 40;
        assertEquals(expected, result);
    }

    //Caso de teste 05: CE05
    @Test
    public void testXantesC(){
        int result = NumerosRomanos.convert("XC");
        int expected = 90;
        assertEquals(expected, result);
    }

    //Caso de teste 06: CE06
    @Test
    public void testCantesD(){
        int result = NumerosRomanos.convert("CD");
        int expected = 400;
        assertEquals(expected, result);
    }

    //Caso de teste 07: CE07
    @Test
    public void testCantesM(){
        int result = NumerosRomanos.convert("CM");
        int expected = 900;
        assertEquals(expected, result);
    }

    //Caso de teste 09: CE09
    @Test
    public void testAgrupamentoX(){
        int result = NumerosRomanos.convert("X");
        int expected = 10;
        assertEquals(expected, result);
    }

    //Caso de teste 10: CE10
    @Test
    public void testAgrupamentoC(){
        int result = NumerosRomanos.convert("C");
        int expected = 100;
        assertEquals(expected, result);
    }

    //Caso de teste 11: CE11
    @Test
    public void testAgrupamentoM(){
        int result = NumerosRomanos.convert("M");
        int expected = 1000;
        assertEquals(expected, result);
    }

    //Caso de teste 12: CE12
    @Test
    public void testAgrupamentoV(){
        int result = NumerosRomanos.convert("V");
        int expected = 5;
        assertEquals(expected, result);
    }

    //Caso de teste 13: CE13
    @Test
    public void testAgrupamentoL(){
        int result = NumerosRomanos.convert("L");
        int expected = 50;
        assertEquals(expected, result);
    }

    //Caso de teste 14: CE14
    @Test
    public void testSomaLetrasI(){
        int result = NumerosRomanos.convert("II");
        int expected = 2;
        assertEquals(expected, result);
    }

    //Caso de teste 15: CE15
    @Test
    public void testSomaLetrasX(){
        int result = NumerosRomanos.convert("XX");
        int expected = 20;
        assertEquals(expected, result);
    }

    //Caso de teste 16: CE16
    @Test
    public void testSomaLetrasC(){
        int result = NumerosRomanos.convert("CC");
        int expected = 200;
        assertEquals(expected, result);
    }

    //Caso de teste 17: CE17
    @Test
    public void testSomaLetrasM(){
        int result = NumerosRomanos.convert("MM");
        int expected = 2000;
        assertEquals(expected, result);
    }

    //Caso de teste 18: CE19
    @Test
    public void testLetraMaiorAntes(){
        int result = NumerosRomanos.convert("VI");
        int expected = 6;
        assertEquals(expected, result);
    }

    //Caso de teste 19: CE20
    @Test
    public void testLetraMenorAntes(){
        int result = NumerosRomanos.convert("XIX");
        int expected = 19;
        assertEquals(expected, result);
    }

    //Caso de teste 20: CE21
    @Test
    public void testTamanhoStringZero(){
        int result = NumerosRomanos.convert("");
        int expected = 0;
        assertEquals(expected, result);
    }

    //Caso de teste 21: CE22
    @Test
    public void testIantesL()throws Exception{
        assertThrows(Exception.class, () -> NumerosRomanos.convert("IL"));
    }

    //Caso de teste 22: CE23
    @Test
    public void testIantesC()throws Exception{
        assertThrows(Exception.class, () -> NumerosRomanos.convert("IC"));
    }

    //Caso de teste 23: CE24
    @Test
    public void testIantesD()throws Exception{
        assertThrows(Exception.class, () -> NumerosRomanos.convert("ID"));
    }

    //Caso de teste 24: CE25
    @Test
    public void testIantesM()throws Exception{
        assertThrows(Exception.class, () -> NumerosRomanos.convert("IM"));
    }

    //Caso de teste 25: CE26
    @Test
    public void testXantesD()throws Exception{
        assertThrows(Exception.class, () -> NumerosRomanos.convert("XD"));
    }

    //Caso de teste 26: CE27
    @Test
    public void testXantesM()throws Exception{
        assertThrows(Exception.class, () -> NumerosRomanos.convert("XM"));
    }

    //Caso de teste 27: CE28
    @Test
    public void testAgruamentoImaiorLimite()throws Exception{
        assertThrows(Exception.class, () -> NumerosRomanos.convert("IIII"));
    }

    //Caso de teste 28: CE29
    @Test
    public void testAgruamentoXmaiorLimite()throws Exception{
        assertThrows(Exception.class, () -> NumerosRomanos.convert("XXXX"));
    }

    //Caso de teste 29: CE30
    @Test
    public void testAgruamentoCmaiorLimite()throws Exception{
        assertThrows(Exception.class, () -> NumerosRomanos.convert("CCCC"));
    }

    //Caso de teste 30: CE31
    @Test
    public void testAgruamentoMmaiorLimite()throws Exception{
        assertThrows(Exception.class, () -> NumerosRomanos.convert("MMMM"));
    }

    //Caso de teste 31: CE32, CE34
    @Test
    public void testAgruamentoV()throws Exception{
        assertThrows(Exception.class, () -> NumerosRomanos.convert("VV"));
    }

    //Caso de teste 32: CE33, CE35
    @Test
    public void testAgruamentoL()throws Exception{
        assertThrows(Exception.class, () -> NumerosRomanos.convert("LL"));
    }

    /*
> Valor Limite
Descrição			        Entrada			Classes Válidas			    Classes Inválidas
Tamanho do número		    tamanho t		t>=1 (VL01)			        t=0 (VL57)
número entre I e III	    n			    n=I (VL02), n=III (VL03)	n=IIII (VL58)
número IV			        n			    n=IV (VL04)			        n=IIV (VL59)
número V			        n			    n=V (VL05)			        n=VV (VL60)
número entre VI e VIII	    n			    n=VI (VL06), n=VIII (VL07)	n=VIIII (VL61)
número IX			        n			    n=IX (VL08)			        n=IIX (VL62)
número entre X e XXX	    n			    n=X (VL09), n=XXX (VL10)	n=XXXX (VL63)
número entre XI e XIII	    n			    n=XI (VL11), n=XIII (VL12)	n=XIIII (VL64)
número XIV			        n			    n=XIV (VL13)			    n=XIIV (VL65)
número XV			        n			    n=XV (VL14)			        n=XVV (VL66)
número XL			        n			    n=XL (VL15)			        n=XXL (VL67)
número entre XLI e XLIII	n			    n=XLI (VL16), n=XLIII (VL17)n=XLIIII (VL68)
número XLIV			        n			    n=XLIV (VL18)			    n=XLIIV (VL69)
número XLV			        n			    n=XLV (VL19)			    n=XLVV (VL70)
número XLIX			        n			    n=XLIX (VL20)			    n=XLIIX (VL71)
número L			        n			    n=L (VL21)			        n=LL (VL72)
número entre LX e LXXX		n			    n=LX (VL22), n=LXXX (VL23)	n=LXXXX (VL73)
número XC			        n			    n=XC (VL24)			        n=XXC (VL74)
número entre XCI e XCIII	n			    n=XCI (VL25), n=XCIII (VL26)n=XCIIII (VL75)
número XCIV			        n			    n=XCIV (VL27)			    n=XCIIV (VL76)
número XCV			        n			    n=XCV (VL28)			    n=XCVV (VL77)
número XCIX			        n			    n=XCIX (VL29)			    n=XCIIX (VL78)
número entre C e CCC		n			    n=C (VL30), n=CCC (VL31)	n=CCCC (VL79)
número entre CX e CXXX		n			    n=CX (VL32), n=CXXX (VL33)	n=CXXXX (VL80)
número CL			        n			    n=CL (VL34)			        n=CLL (VL81)
número CD			        n			    n=CD (VL35)			        n=CCD (VL82)
número entre CDI e CDIII	n			    n=CDI (VL36), n=CDIII (VL37)n=CDIIII (VL83)
número CDIV			        n			    n=CDIV (VL38)			    n=CDIIV (VL84)
número CDV			        n			    n=CDV (VL39)			    n=CDVV (VL85)
número CDIX			        n			    n=CDIX (VL40)			    n=CDIIX (VL86)
número entre CDX e CDXXX	n			    n=CDX (VL41), n=CDXXX (VL42)n=CDXXXX (VL87)
número CDL			        n			    n=CDL (VL43)			    n=CDLL (VL88)
número D			        n			    n=D (VL44)			        n=DD (VL89)
número entre DC e DCCC		n			    n=DC (VL45), n=DCCC (VL46)	n=DCCCC (VL90)
número CM			        n			    n=CM (VL47)			        n=CCM (VL91)
número entre CMI e CMIII	n			    n=CMI (VL48), n=CMIII (VL49)n=CMIIII (VL92)
número CMIV			        n			    n=CMIV (VL50)			    n=CMIIV (VL93)
número CMV			        n			    n=CMV (VL51)			    n=CMVV (VL94)
número CMIX			        n			    n=CMIX (VL52)			    n=CMIIX (VL95)
número entre CMX e CMXXX	n			    n=CMX (VL53), n=CMXXX (VL54)n=CMXXXX (VL96)
número entre M e MMM		n			    n=M (VL55), n=MMM (VL56)	n=MMMM (VL97)

>> Casos de Teste
ID	Entrada		Saída Esperada		Valor Limite
01	I		    1			        VL01, VL02
02	III		    3			        VL03
03	IV		    4			        VL04
04	V		    5			        VL05
05	VI		    6			        VL06
06	VIII		8			        VL07
07	IX		    9			        VL08
08	X		    10			        VL09
09	XXX		    30			        VL10
10	XI		    11			        VL11
11	XIII		13			        VL12
12	XIV		    14			        VL13
13	XV		    15			        VL14
14	XL		    40			        VL15
15	XLI		    41			        VL16
16	XLIII		43			        VL17
17	XLIV		44			        VL18
18	XLV		    45			        VL19
19	XLIX		49			        VL20
20	L		    50			        VL21
21	LX		    60			        VL22
22	LXXX		80			        VL23
23	XC		    90			        VL24
24	XCI		    91			        VL25
25	XCIII		93			        VL26
26	XCIV		94			        VL27
27	XCV		    95			        VL28
28	XCIX		99			        VL29
29	C		    100			        VL30
30	CCC		    300			        VL31
31	CX		    110			        VL32
32	CXXX		130			        VL33
33	CL		    150			        VL34
34	CD		    400			        VL35
35	CDI		    401			        VL36
36	CDIII		403			        VL37
37	CDIV		404			        VL38
38	CDV		    405			        VL39
39	CDIX		409			        VL40
40	CDX		    410			        VL41
41	CDXXX		430			        VL42
42	CDL		    450			        VL43
43	D		    500			        VL44
44	DC		    600			        VL45
45	DCCC		800			        VL46
46	CM		    900			        VL47
47	CMI		    901			        VL48
48	CMIII		903			        VL49
49	CMIV		904			        VL50
50	CMV		    905			        VL51
51	CMIX		909			        VL52
52	CMX		    910			        VL53
53	CMXXX		930			        VL54
54	M		    1000			    VL55
55	MMM		    3000			    VL56
56	""		    0			        VL57
57	IIII		número inválido		VL58
58	IIV		    número inválido		VL59
59	VV		    número inválido		VL60
60	VIIII		número inválido		VL61
61	IIX		    número inválido		VL62
62	XXXX		número inválido		VL63
63	XIIII		número inválido		VL64
64	XIIV		número inválido		VL65
65	XVV		    número inválido		VL66
66	XXL		    número inválido		VL67
67	XLIIII		número inválido		VL68
68	XLIIV		número inválido		VL69
69	XLVV		número inválido		VL70
70	XLIIX		número inválido		VL71
71	LL		    número inválido		VL72
72	LXXXX		número inválido		VL73
73	XXC		    número inválido		VL74
74	XCIIII		número inválido		VL75
75	XCIIV		número inválido		VL76
76	XCVV		número inválido		VL77
77	XCIIX		número inválido		VL78
78	CCCC		número inválido		VL79
79	CXXXX		número inválido		VL80
80	CLL		    número inválido		VL81
81	CCD		    número inválido		VL82
82	CDIIII		número inválido		VL83
83	CDIIV		número inválido		VL84
84	CDVV		número inválido		VL85
85	CDIIX		número inválido		VL86
86	CDXXXX		número inválido		VL87
87	CDLL		número inválido		VL88
88	DD		    número inválido		VL89
89	DCCCC		número inválido		VL90
90	CCM		    número inválido		VL91
91	CMIIII		número inválido		VL92
92	CMIIV		número inválido		VL93
93	CMVV		número inválido		VL94
94	CMIIX		número inválido		VL95
95	CMXXXX		número inválido		VL96
96	MMMM		número inválido		VL97

     */

    //Caso de teste 01: VL01, VL02
    @Test
    public void testTamanhoMinimoNumero(){
        int result = NumerosRomanos.convert("I");
        int expected = 1;
        assertEquals(expected, result);
    }

    //Caso de teste 02: VL03
    @Test
    public void testNumeroMaximoI(){
        int result = NumerosRomanos.convert("III");
        int expected = 3;
        assertEquals(expected, result);
    }

    //Caso de teste 03: VL04
    @Test
    public void testNumeroIV(){
        int result = NumerosRomanos.convert("IV");
        int expected = 4;
        assertEquals(expected, result);
    }

    //Caso de teste 04: VL05
    @Test
    public void testNumeroV(){
        int result = NumerosRomanos.convert("V");
        int expected = 5;
        assertEquals(expected, result);
    }

    //Caso de teste 05: VL06
    @Test
    public void testMenorNumeroEntreVIeVIII(){
        int result = NumerosRomanos.convert("VI");
        int expected = 6;
        assertEquals(expected, result);
    }

    //Caso de teste 06: VL07
    @Test
    public void testMaiorNumeroEntreVIeVIII(){
        int result = NumerosRomanos.convert("VIII");
        int expected = 8;
        assertEquals(expected, result);
    }

    //Caso de teste 07: VL08
    @Test
    public void testNumeroIX(){
        int result = NumerosRomanos.convert("IX");
        int expected = 9;
        assertEquals(expected, result);
    }

    //Caso de teste 08: VL09
    @Test
    public void testMenorNumeroEntreXeXXX(){
        int result = NumerosRomanos.convert("X");
        int expected = 10;
        assertEquals(expected, result);
    }

    //Caso de teste 09: VL10
    @Test
    public void testMaiorNumeroEntreXeXXX(){
        int result = NumerosRomanos.convert("XXX");
        int expected = 30;
        assertEquals(expected, result);
    }

    //Caso de teste 10: VL11
    @Test
    public void testMenorNumeroEntreXIeXIII(){
        int result = NumerosRomanos.convert("XI");
        int expected = 11;
        assertEquals(expected, result);
    }

    //Caso de teste 11: VL12
    @Test
    public void testMaiorNumeroEntreXIeXIII(){
        int result = NumerosRomanos.convert("XIII");
        int expected = 13;
        assertEquals(expected, result);
    }

    //Caso de teste 12: VL13
    @Test
    public void testNumeroXIV(){
        int result = NumerosRomanos.convert("XIV");
        int expected = 14;
        assertEquals(expected, result);
    }

    //Caso de teste 13: VL14
    @Test
    public void testNumeroXV(){
        int result = NumerosRomanos.convert("XV");
        int expected = 15;
        assertEquals(expected, result);
    }

    //Caso de teste 14: VL15
    @Test
    public void testNumeroXL(){
        int result = NumerosRomanos.convert("XL");
        int expected = 40;
        assertEquals(expected, result);
    }

    //Caso de teste 15: VL16
    @Test
    public void testMenorNumeroEntreXLIeXLIII(){
        int result = NumerosRomanos.convert("XLI");
        int expected = 41;
        assertEquals(expected, result);
    }

    //Caso de teste 16: VL17
    @Test
    public void testMaiorNumeroEntreXLIeXLIII(){
        int result = NumerosRomanos.convert("XLIII");
        int expected = 43;
        assertEquals(expected, result);
    }

    //Caso de teste 17: VL18
    @Test
    public void testNumeroXLIV(){
        int result = NumerosRomanos.convert("XLIV");
        int expected = 44;
        assertEquals(expected, result);
    }

    //Caso de teste 18: VL19
    @Test
    public void testNumeroXLV(){
        int result = NumerosRomanos.convert("XLV");
        int expected = 45;
        assertEquals(expected, result);
    }

    //Caso de teste 19: VL20
    @Test
    public void testNumeroXLIX(){
        int result = NumerosRomanos.convert("XLIX");
        int expected = 49;
        assertEquals(expected, result);
    }

    //Caso de teste 20: VL21
    @Test
    public void testNumeroL(){
        int result = NumerosRomanos.convert("L");
        int expected = 50;
        assertEquals(expected, result);
    }

    //Caso de teste 21: VL22
    @Test
    public void testMenorNumeroEntreLXeLXXX(){
        int result = NumerosRomanos.convert("LX");
        int expected = 60;
        assertEquals(expected, result);
    }

    //Caso de teste 22: VL23
    @Test
    public void testMaiorNumeroEntreLXeLXXX(){
        int result = NumerosRomanos.convert("LXXX");
        int expected = 80;
        assertEquals(expected, result);
    }

    //Caso de teste 23: VL24
    @Test
    public void testNumeroXC(){
        int result = NumerosRomanos.convert("XC");
        int expected = 90;
        assertEquals(expected, result);
    }

    //Caso de teste 24: VL25
    @Test
    public void testMenorNumeroEntreXCIeXCIII(){
        int result = NumerosRomanos.convert("XCI");
        int expected = 91;
        assertEquals(expected, result);
    }

    //Caso de teste 25: VL26
    @Test
    public void testMaiorNumeroEntreXCIeXCIII(){
        int result = NumerosRomanos.convert("XCIII");
        int expected = 93;
        assertEquals(expected, result);
    }

    //Caso de teste 26: VL27
    @Test
    public void testNumeroXCIV(){
        int result = NumerosRomanos.convert("XCIV");
        int expected = 94;
        assertEquals(expected, result);
    }

    //Caso de teste 27: VL28
    @Test
    public void testNumeroXCV(){
        int result = NumerosRomanos.convert("XCV");
        int expected = 95;
        assertEquals(expected, result);
    }

    //Caso de teste 28: VL29
    @Test
    public void testNumeroXCIX(){
        int result = NumerosRomanos.convert("XCIX");
        int expected = 99;
        assertEquals(expected, result);
    }

    //Caso de teste 29: VL30
    @Test
    public void testMenorNumeroEntreCeCCC(){
        int result = NumerosRomanos.convert("C");
        int expected = 100;
        assertEquals(expected, result);
    }

    //Caso de teste 30: VL31
    @Test
    public void testMaiorNumeroEntreCeCCC(){
        int result = NumerosRomanos.convert("CCC");
        int expected = 300;
        assertEquals(expected, result);
    }

    //Caso de teste 31: VL32
    @Test
    public void testMenorNumeroEntreCXeCXXX(){
        int result = NumerosRomanos.convert("CX");
        int expected = 110;
        assertEquals(expected, result);
    }

    //Caso de teste 32: VL33
    @Test
    public void testMaiorNumeroEntreCXeCXXX(){
        int result = NumerosRomanos.convert("CXXX");
        int expected = 130;
        assertEquals(expected, result);
    }

    //Caso de teste 33: VL34
    @Test
    public void testNumeroCL(){
        int result = NumerosRomanos.convert("CL");
        int expected = 150;
        assertEquals(expected, result);
    }

    //Caso de teste 34: VL35
    @Test
    public void testNumeroCD(){
        int result = NumerosRomanos.convert("CD");
        int expected = 400;
        assertEquals(expected, result);
    }

    //Caso de teste 35: VL36
    @Test
    public void testMenorNumeroEntreCDIeCDIII(){
        int result = NumerosRomanos.convert("CDI");
        int expected = 401;
        assertEquals(expected, result);
    }

    //Caso de teste 36: VL37
    @Test
    public void testMaiorNumeroEntreCDIeCDIII(){
        int result = NumerosRomanos.convert("CDIII");
        int expected = 403;
        assertEquals(expected, result);
    }

    //Caso de teste 37: VL38
    @Test
    public void testNumeroCDIV(){
        int result = NumerosRomanos.convert("CDIV");
        int expected = 404;
        assertEquals(expected, result);
    }

    //Caso de teste 38: VL39
    @Test
    public void testNumeroCDV(){
        int result = NumerosRomanos.convert("CDV");
        int expected = 405;
        assertEquals(expected, result);
    }

    //Caso de teste 39: VL40
    @Test
    public void testNumeroCDIX(){
        int result = NumerosRomanos.convert("CDIX");
        int expected = 409;
        assertEquals(expected, result);
    }

    //Caso de teste 40: VL41
    @Test
    public void testMenorNumeroEntreCDXeCDXXX(){
        int result = NumerosRomanos.convert("CDX");
        int expected = 410;
        assertEquals(expected, result);
    }

    //Caso de teste 41: VL42
    @Test
    public void testMaiorNumeroEntreCDXeCDXXX(){
        int result = NumerosRomanos.convert("CDXXX");
        int expected = 430;
        assertEquals(expected, result);
    }

    //Caso de teste 42: VL43
    @Test
    public void testNumeroCDL(){
        int result = NumerosRomanos.convert("CDL");
        int expected = 450;
        assertEquals(expected, result);
    }

    //Caso de teste 43: VL44
    @Test
    public void testNumeroD(){
        int result = NumerosRomanos.convert("D");
        int expected = 500;
        assertEquals(expected, result);
    }

    //Caso de teste 44: VL45
    @Test
    public void testMenorNumeroEntreDCeDCCC(){
        int result = NumerosRomanos.convert("DC");
        int expected = 600;
        assertEquals(expected, result);
    }

    //Caso de teste 45: VL46
    @Test
    public void testMaiorNumeroEntreDCeDCCC(){
        int result = NumerosRomanos.convert("DCCC");
        int expected = 800;
        assertEquals(expected, result);
    }

    //Caso de teste 46: VL47
    @Test
    public void testNumeroCM(){
        int result = NumerosRomanos.convert("CM");
        int expected = 900;
        assertEquals(expected, result);
    }

    //Caso de teste 47: VL48
    @Test
    public void testMenorNumeroEntreCMIeCMIII(){
        int result = NumerosRomanos.convert("CMI");
        int expected = 901;
        assertEquals(expected, result);
    }

    //Caso de teste 48: VL49
    @Test
    public void testMaiorNumeroEntreCMIeCMIII(){
        int result = NumerosRomanos.convert("CMIII");
        int expected = 903;
        assertEquals(expected, result);
    }

    //Caso de teste 49: VL50
    @Test
    public void testNumeroCMIV(){
        int result = NumerosRomanos.convert("CMIV");
        int expected = 904;
        assertEquals(expected, result);
    }

    //Caso de teste 50: VL51
    @Test
    public void testNumeroCMV(){
        int result = NumerosRomanos.convert("CMV");
        int expected = 905;
        assertEquals(expected, result);
    }

    //Caso de teste 51: VL52
    @Test
    public void testNumeroCMIX(){
        int result = NumerosRomanos.convert("CMIX");
        int expected = 909;
        assertEquals(expected, result);
    }

    //Caso de teste 52: VL53
    @Test
    public void testMenorNumeroEntreCMXeCMXXX(){
        int result = NumerosRomanos.convert("CMX");
        int expected = 910;
        assertEquals(expected, result);
    }

    //Caso de teste 53: VL54
    @Test
    public void testMaiorNumeroEntreCMXeCMXXX(){
        int result = NumerosRomanos.convert("CMXXX");
        int expected = 930;
        assertEquals(expected, result);
    }

    //Caso de teste 54: VL55
    @Test
    public void testMenorNumeroEntreMeMMM(){
        int result = NumerosRomanos.convert("M");
        int expected = 1000;
        assertEquals(expected, result);
    }

    //Caso de teste 55: VL56
    @Test
    public void testMaiorNumeroEntreMeMMM(){
        int result = NumerosRomanos.convert("MMM");
        int expected = 3000;
        assertEquals(expected, result);
    }

    //Caso de teste 56: VL57
    @Test
    public void testStringVazia(){
        int result = NumerosRomanos.convert("");
        int expected = 0;
        assertEquals(expected, result);
    }

    //Caso de teste 57: VL58
    @Test
    public void testNumeroInvalidoIIII()throws Exception{
        assertThrows(Exception.class, () -> NumerosRomanos.convert("IIII"));
    }

    //Caso de teste 58: VL59
    @Test
    public void testNumeroInvalidoIIV()throws Exception{
        assertThrows(Exception.class, () -> NumerosRomanos.convert("IIV"));
    }

    //Caso de teste 59: VL60
    @Test
    public void testNumeroInvalidoVV()throws Exception{
        assertThrows(Exception.class, () -> NumerosRomanos.convert("VV"));
    }

    //Caso de teste 60: VL61
    @Test
    public void testNumeroInvalidoVIIII()throws Exception{
        assertThrows(Exception.class, () -> NumerosRomanos.convert("VIIII"));
    }

    //Caso de teste 61: VL62
    @Test
    public void testNumeroInvalidoIIX()throws Exception{
        assertThrows(Exception.class, () -> NumerosRomanos.convert("IIX"));
    }

    //Caso de teste 62: VL63
    @Test
    public void testNumeroInvalidoXXXX()throws Exception{
        assertThrows(Exception.class, () -> NumerosRomanos.convert("XXXX"));
    }

    //Caso de teste 63: VL64
    @Test
    public void testNumeroInvalidoXIIII()throws Exception{
        assertThrows(Exception.class, () -> NumerosRomanos.convert("XIIII"));
    }

    //Caso de teste 64: VL65
    @Test
    public void testNumeroInvalidoXIIV()throws Exception{
        assertThrows(Exception.class, () -> NumerosRomanos.convert("XIIV"));
    }

    //Caso de teste 65: VL66
    @Test
    public void testNumeroInvalidoXVV()throws Exception{
        assertThrows(Exception.class, () -> NumerosRomanos.convert("XVV"));
    }

    //Caso de teste 66: VL67
    @Test
    public void testNumeroInvalidoXXL()throws Exception{
        assertThrows(Exception.class, () -> NumerosRomanos.convert("XXL"));
    }

    //Caso de teste 67: VL68
    @Test
    public void testNumeroInvalidoXLIIII()throws Exception{
        assertThrows(Exception.class, () -> NumerosRomanos.convert("XLIIII"));
    }

    //Caso de teste 68: VL69
    @Test
    public void testNumeroInvalidoXLIIV()throws Exception{
        assertThrows(Exception.class, () -> NumerosRomanos.convert("XLIIV"));
    }

    //Caso de teste 69: VL70
    @Test
    public void testNumeroInvalidoXLVV()throws Exception{
        assertThrows(Exception.class, () -> NumerosRomanos.convert("XLVV"));
    }

    //Caso de teste 70: VL71
    @Test
    public void testNumeroInvalidoXLIIX()throws Exception{
        assertThrows(Exception.class, () -> NumerosRomanos.convert("XLIIX"));
    }

    //Caso de teste 71: VL72
    @Test
    public void testNumeroInvalidoLL()throws Exception{
        assertThrows(Exception.class, () -> NumerosRomanos.convert("LL"));
    }

    //Caso de teste 72: VL73
    @Test
    public void testNumeroInvalidoLXXXX()throws Exception{
        assertThrows(Exception.class, () -> NumerosRomanos.convert("LXXXX"));
    }

    //Caso de teste 73: VL74
    @Test
    public void testNumeroInvalidoXXC()throws Exception{
        assertThrows(Exception.class, () -> NumerosRomanos.convert("XXC"));
    }

    //Caso de teste 74: VL75
    @Test
    public void testNumeroInvalidoXCIIII()throws Exception{
        assertThrows(Exception.class, () -> NumerosRomanos.convert("XCIIII"));
    }

    //Caso de teste 75: VL76
    @Test
    public void testNumeroInvalidoXCIIV()throws Exception{
        assertThrows(Exception.class, () -> NumerosRomanos.convert("XCIIV"));
    }

    //Caso de teste 76: VL77
    @Test
    public void testNumeroInvalidoXCVV()throws Exception{
        assertThrows(Exception.class, () -> NumerosRomanos.convert("XCVV"));
    }

    //Caso de teste 77: VL78
    @Test
    public void testNumeroInvalidoXCIIX()throws Exception{
        assertThrows(Exception.class, () -> NumerosRomanos.convert("XCIIX"));
    }

    //Caso de teste 78: VL79
    @Test
    public void testNumeroInvalidoXCCCC()throws Exception{
        assertThrows(Exception.class, () -> NumerosRomanos.convert("CCCC"));
    }

    //Caso de teste 79: VL80
    @Test
    public void testNumeroInvalidoCXXXX()throws Exception{
        assertThrows(Exception.class, () -> NumerosRomanos.convert("CXXXX"));
    }

    //Caso de teste 80: VL81
    @Test
    public void testNumeroInvalidoCLL()throws Exception{
        assertThrows(Exception.class, () -> NumerosRomanos.convert("CLL"));
    }

    //Caso de teste 81: VL82
    @Test
    public void testNumeroInvalidoCCD()throws Exception{
        assertThrows(Exception.class, () -> NumerosRomanos.convert("CCD"));
    }

    //Caso de teste 82: VL83
    @Test
    public void testNumeroInvalidoCDIIII()throws Exception{
        assertThrows(Exception.class, () -> NumerosRomanos.convert("CDIIII"));
    }

    //Caso de teste 83: VL84
    @Test
    public void testNumeroInvalidoCDIIV()throws Exception{
        assertThrows(Exception.class, () -> NumerosRomanos.convert("CDIIV"));
    }

    //Caso de teste 84: VL85
    @Test
    public void testNumeroInvalidoCDVV()throws Exception{
        assertThrows(Exception.class, () -> NumerosRomanos.convert("CDVV"));
    }

    //Caso de teste 85: VL86
    @Test
    public void testNumeroInvalidoCDIIX()throws Exception{
        assertThrows(Exception.class, () -> NumerosRomanos.convert("CDIIX"));
    }

    //Caso de teste 86: VL87
    @Test
    public void testNumeroInvalidoCDXXXX()throws Exception{
        assertThrows(Exception.class, () -> NumerosRomanos.convert("CDXXXX"));
    }

    //Caso de teste 87: VL88
    @Test
    public void testNumeroInvalidoCDLL()throws Exception{
        assertThrows(Exception.class, () -> NumerosRomanos.convert("CDLL"));
    }

    //Caso de teste 88: VL89
    @Test
    public void testNumeroInvalidoDD()throws Exception{
        assertThrows(Exception.class, () -> NumerosRomanos.convert("DD"));
    }

    //Caso de teste 89: VL90
    @Test
    public void testNumeroInvalidoDCCCC()throws Exception{
        assertThrows(Exception.class, () -> NumerosRomanos.convert("DCCCC"));
    }

    //Caso de teste 90: VL91
    @Test
    public void testNumeroInvalidoCCM()throws Exception{
        assertThrows(Exception.class, () -> NumerosRomanos.convert("CCM"));
    }

    //Caso de teste 91: VL92
    @Test
    public void testNumeroInvalidoCMIIII()throws Exception{
        assertThrows(Exception.class, () -> NumerosRomanos.convert("CMIIII"));
    }

    //Caso de teste 92: VL93
    @Test
    public void testNumeroInvalidoCMIIV()throws Exception{
        assertThrows(Exception.class, () -> NumerosRomanos.convert("CMIIV"));
    }

    //Caso de teste 93: VL94
    @Test
    public void testNumeroInvalidoCMVV()throws Exception{
        assertThrows(Exception.class, () -> NumerosRomanos.convert("CMVV"));
    }

    //Caso de teste 94: VL95
    @Test
    public void testNumeroInvalidoCMIIX()throws Exception{
        assertThrows(Exception.class, () -> NumerosRomanos.convert("CMIIX"));
    }

    //Caso de teste 95: VL96
    @Test
    public void testNumeroInvalidoCMXXXX()throws Exception{
        assertThrows(Exception.class, () -> NumerosRomanos.convert("CMXXXX"));
    }

    //Caso de teste 96: VL97
    @Test
    public void testNumeroInvalidoMMMM()throws Exception{
        assertThrows(Exception.class, () -> NumerosRomanos.convert("MMMM"));
    }
}
