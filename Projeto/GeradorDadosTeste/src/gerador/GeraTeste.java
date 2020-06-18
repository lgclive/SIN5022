package gerador;

import java.util.ArrayList;
import java.util.Scanner;
import choco.Choco;
import choco.cp.model.CPModel;
import choco.cp.solver.CPSolver;
import choco.kernel.model.Model;
import choco.kernel.model.constraints.Constraint;
import choco.kernel.model.variables.integer.IntegerExpressionVariable;
import choco.kernel.model.variables.integer.IntegerVariable;
import choco.kernel.model.variables.real.RealExpressionVariable;
import choco.kernel.solver.Solver;

public class GeraTeste {

    private ArrayList<String> variaveis;
    private ArrayList<Restricoes> listaRestricoes;
    private ArrayList<String> valoresResultado;
    private ArrayList<String> valoresLimiteResultado;

    public GeraTeste(){
        variaveis = new ArrayList<String>();
        listaRestricoes = new ArrayList<Restricoes>();
        valoresResultado = new ArrayList<String>();
    }

    public void lerArquivoEntrada(String localArquivo){
        LeitorDeArquivo leitor = new LeitorDeArquivo();
        ArrayList<String> linhas = leitor.leArquivoTexto(localArquivo);

        //Identificando as variáveis
        this.variaveis = identificarVariaveis(linhas.get(0));

        //Identificando as restrições
        for(int i = 1; i < linhas.size(); i++)
            this.listaRestricoes.add(identificarRestricoes(linhas.get(i)));

        resolver();
    }

    private Restricoes identificarRestricoes(String rest){
        Restricoes ir = new Restricoes();
        String[] aux;

        rest = eliminarChar(rest, ' ');
        rest = eliminarChar(rest, '(');
        rest = eliminarChar(rest, ')');

        //Separar restriçoes
        aux = rest.split("\\^");

        //Compor as restrições
        for(String r : aux)
            ir.adicionarRestricao(identificarRestricao(r));

        return ir;
    }

    private Restricao identificarRestricao(String restricao){
        String operador, aux[];
        Restricao r = new Restricao();

        if(restricao.contains(">="))
            operador = ">=";
        else if(restricao.contains("<="))
            operador = "<=";
        else if(restricao.contains("=="))
            operador = "==";
        else if(restricao.contains("!="))
            operador = "!=";
        else if(restricao.contains(">"))
            operador = ">";
        else if(restricao.contains("<"))
            operador = "<";
        else
            return null; //Operador não está presente

        aux = restricao.split(operador);
        r.setOperandoEsquerdo(identificarExpAritmetica(aux[0]));
        r.setOperador(operador);
        r.setOperandoDireito(identificarExpAritmetica(aux[1]));

        return r;
    }

    private ExpAritmetica identificarExpAritmetica(String operando){
        ExpAritmetica ea = new ExpAritmetica();
        String[] aux;

        if(operando.contains("+")){
            aux = operando.split("\\+");
            ea.setOperandoEsquerda(aux[0]);
            ea.setOperador("+");
            ea.setOperandoDireita(aux[1]);
        }else if(operando.contains("-")){
            aux = operando.split("\\-");
            ea.setOperandoEsquerda(aux[0]);
            ea.setOperador("-");
            ea.setOperandoDireita(aux[1]);
        }else
            ea.setOperandoEsquerda(operando);

        return ea;
    }

    private ArrayList<String> identificarVariaveis(String vars){
        String[] aux;
        ArrayList<String> auxVariaveis;

        vars = eliminarChar(vars, ' ');

        aux = vars.split(",");

        auxVariaveis = new ArrayList<>();
        for(String v : aux)
            auxVariaveis.add(v);

        return auxVariaveis;
    }

    private String eliminarChar(String string, char caracter){
        String aux = "";
        char c;

        for(int i = 0; i < string.length(); i++){
            c = string.charAt(i);
            if( c != caracter)
                aux += c + "";
        }

        return aux;
    }

    private int encontraVariavel(String var){
        for(int i = 0; i < this.variaveis.size(); i++){
            if(this.variaveis.get(i).equals(var))
                return i;
        }
        return -1;
    }

    private IntegerVariable[] declararVariaveisChoco(){
        IntegerVariable[] varsChoco;

        varsChoco = new IntegerVariable[this.variaveis.size()];
        for(int i = 0; i < varsChoco.length; i++)
            varsChoco[i] = Choco.makeIntVar(this.variaveis.get(i), -1000, 1000);

        return varsChoco;
    }

    private Solver resolverChoco(IntegerVariable[] varsChoco, ArrayList<Constraint> rest){
        Model m = new CPModel();
        Solver s = new CPSolver();

        //Adicionando variáveis ao modelo
        for(IntegerVariable iv : varsChoco)
            m.addVariable(iv);

        //Adicionando as restrições ao modelo
        for(Constraint c : rest)
            m.addConstraint(c);

        //Solucionando
        s.read(m);
        boolean solucao = s.solve();

        if(!solucao)//Não há solução
            return null;

        return s;
    }

    private void construirResposta(IntegerVariable[] varsChoco, Solver s){
        if (s != null){//Há solução
            //Obtendo os valores
            String resultado = "(";
            for (int z = 0; z < varsChoco.length; z++) {
                resultado += s.getVar(varsChoco[z]).getVal() + "";
                if (z == varsChoco.length - 1)
                    continue;
                resultado += ",";
            }
            resultado += ")";

            valoresResultado.add(resultado);
        }else {//Não há solução
            valoresResultado.add("Nao ha solucao!");
        }
    }

    private void resolver(){

        IntegerVariable[] varsChoco;
        IntegerExpressionVariable expChoco;
        ArrayList<Constraint> rest;
        Restricoes linha;
        Restricao r;
        ExpAritmetica ea;

        //Declarando variáveis no Choco
        varsChoco = declararVariaveisChoco();

        //Tratando as restrições
        for(int i = 0; i < this.listaRestricoes.size(); i++){
            //Obtendo um conjunto de restrições (uma linha do arquivo de entrada)
            linha = this.listaRestricoes.get(i);
            //Tratando cada restrição presente na linha (linha do arquivo de entrada)
            rest = new ArrayList<>(); //Conjunto de restrições no Choco
            for(int j = 0; j < linha.getRestricoes().size(); j++){
                r = linha.getRestricoes().get(j);

                //Verificando a presença de expressão aritmética à direita
                ea = r.getOperandoDireito();
                if(ea.getOperador()!=null){//Tem operador aritmético
                    int x = encontraVariavel(ea.getOperandoEsquerda());
                    int y = encontraVariavel(ea.getOperandoDireita());
                    expChoco = null;
                    if(ea.getOperador().equals("+")){//Verificando qual é o operador
                        if(x == -1 && y != -1) //número + var
                            expChoco = Choco.plus(Integer.parseInt(ea.getOperandoEsquerda()), varsChoco[y]);
                        else if(x != -1 && y == -1)//var + número
                            expChoco = Choco.plus(varsChoco[x], Integer.parseInt(ea.getOperandoDireita()));
                        else//var + var
                            expChoco = Choco.plus(varsChoco[x], varsChoco[y]);
                    }else if(ea.getOperador().equals("-")){//Operador "-"
                        if(x == -1 && y != -1) //número + var
                            expChoco = Choco.minus(Integer.parseInt(ea.getOperandoEsquerda()), varsChoco[y]);
                        else if(x != -1 && y == -1)//var + número
                            expChoco = Choco.minus(varsChoco[x], Integer.parseInt(ea.getOperandoDireita()));
                        else//var + var
                            expChoco = Choco.minus(varsChoco[x], varsChoco[y]);
                    }//Talvez outros operadores...

                    //Identificando o operador relacional
                    if(r.getOperador().equals(">"))
                        rest.add(Choco.gt(varsChoco[encontraVariavel(r.getOperandoEsquerdo().getOperandoEsquerda())], expChoco));
                    else if(r.getOperador().equals(">="))
                        rest.add(Choco.geq(varsChoco[encontraVariavel(r.getOperandoEsquerdo().getOperandoEsquerda())], expChoco));
                    else if(r.getOperador().equals("<"))
                        rest.add(Choco.lt(varsChoco[encontraVariavel(r.getOperandoEsquerdo().getOperandoEsquerda())], expChoco));
                    else if(r.getOperador().equals("<="))
                        rest.add(Choco.leq(varsChoco[encontraVariavel(r.getOperandoEsquerdo().getOperandoEsquerda())], expChoco));
                    else if(r.getOperador().equals("=="))
                        rest.add(Choco.eq(varsChoco[encontraVariavel(r.getOperandoEsquerdo().getOperandoEsquerda())], expChoco));
                    else if(r.getOperador().equals("!="))
                        rest.add(Choco.neq(varsChoco[encontraVariavel(r.getOperandoEsquerdo().getOperandoEsquerda())], expChoco));

                }else{//Não tem expressão aritmética do lado direito
                    ea = r.getOperandoEsquerdo();
                    int x = encontraVariavel(ea.getOperandoEsquerda());
                    ea = r.getOperandoDireito();
                    int y = encontraVariavel(ea.getOperandoEsquerda());

                    //Identificando o operador relacional
                    if(r.getOperador().equals(">"))
                        if(x != -1 && y == -1)//var + número
                            rest.add(Choco.gt(varsChoco[encontraVariavel(r.getOperandoEsquerdo().getOperandoEsquerda())], Integer.parseInt(r.getOperandoDireito().getOperandoEsquerda())));
                        else//var + var
                            rest.add(Choco.gt(varsChoco[encontraVariavel(r.getOperandoEsquerdo().getOperandoEsquerda())], varsChoco[encontraVariavel(r.getOperandoDireito().getOperandoEsquerda())]));
                    else if(r.getOperador().equals(">="))
                        if(x != -1 && y == -1)//var + número
                            rest.add(Choco.geq(varsChoco[encontraVariavel(r.getOperandoEsquerdo().getOperandoEsquerda())], Integer.parseInt(r.getOperandoDireito().getOperandoEsquerda())));
                        else//var + var
                            rest.add(Choco.geq(varsChoco[encontraVariavel(r.getOperandoEsquerdo().getOperandoEsquerda())], varsChoco[encontraVariavel(r.getOperandoDireito().getOperandoEsquerda())]));
                    else if(r.getOperador().equals("<"))
                        if(x != -1 && y == -1)//var + número
                            rest.add(Choco.lt(varsChoco[encontraVariavel(r.getOperandoEsquerdo().getOperandoEsquerda())], Integer.parseInt(r.getOperandoDireito().getOperandoEsquerda())));
                        else//var + var
                            rest.add(Choco.lt(varsChoco[encontraVariavel(r.getOperandoEsquerdo().getOperandoEsquerda())], varsChoco[encontraVariavel(r.getOperandoDireito().getOperandoEsquerda())]));
                    else if(r.getOperador().equals("<="))
                        if(x != -1 && y == -1)//var + número
                            rest.add(Choco.leq(varsChoco[encontraVariavel(r.getOperandoEsquerdo().getOperandoEsquerda())], Integer.parseInt(r.getOperandoDireito().getOperandoEsquerda())));
                        else//var + var
                            rest.add(Choco.leq(varsChoco[encontraVariavel(r.getOperandoEsquerdo().getOperandoEsquerda())], varsChoco[encontraVariavel(r.getOperandoDireito().getOperandoEsquerda())]));
                    else if(r.getOperador().equals("=="))
                        if(x != -1 && y == -1)//var + número
                            rest.add(Choco.eq(varsChoco[encontraVariavel(r.getOperandoEsquerdo().getOperandoEsquerda())], Integer.parseInt(r.getOperandoDireito().getOperandoEsquerda())));
                        else//var + var
                            rest.add(Choco.eq(varsChoco[encontraVariavel(r.getOperandoEsquerdo().getOperandoEsquerda())], varsChoco[encontraVariavel(r.getOperandoDireito().getOperandoEsquerda())]));
                    else if(r.getOperador().equals("!="))
                        if(x != -1 && y == -1)//var + número
                            rest.add(Choco.neq(varsChoco[encontraVariavel(r.getOperandoEsquerdo().getOperandoEsquerda())], Integer.parseInt(r.getOperandoDireito().getOperandoEsquerda())));
                        else//var + var
                            rest.add(Choco.neq(varsChoco[encontraVariavel(r.getOperandoEsquerdo().getOperandoEsquerda())], varsChoco[encontraVariavel(r.getOperandoDireito().getOperandoEsquerda())]));
                }

            }

            //Solucionando
            Solver s = resolverChoco(varsChoco, rest);

            //Construindo a resposta
            construirResposta(varsChoco, s);
        }
    }

    private void gerarArquivoSaida(String localArquivo){
        EscritorDeArquivo ea = new EscritorDeArquivo();
        ea.escreverArquivoTexto(localArquivo, valoresResultado);
    }

    public static void main(String[] Args){

        //String localArquivo = "/home/marceloeler/gdrive/Projects/IntelliJ/GeradorDadosTeste/src/gerador/exemplorestricoes.txt";
        //String localArquivo = "/home/luciano/Documentos/USP/sin5022/Aula09/GeradorDadosTeste/src/gerador/exemplorestricoes.txt";
        GeraTeste gt = new GeraTeste();
        Scanner entrada = new Scanner(System.in);
        String localArquivo;

        System.out.print("Informe o arquivo de entrada: ");
        localArquivo = entrada.nextLine();

        gt.lerArquivoEntrada(localArquivo);

        System.out.print("Informe o arquivo de saida: ");
        localArquivo = entrada.nextLine();

        gt.gerarArquivoSaida(localArquivo);

    }

}
