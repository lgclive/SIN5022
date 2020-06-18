package gerador;

import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.ListIterator;

public class Restricoes {
    private ArrayList<Restricao> restricoes;

    public Restricoes(){
        this.restricoes = new ArrayList<Restricao>();
    }

    public boolean adicionarRestricao(Restricao restricao){
        return restricoes.add(restricao);
    }

    public ArrayList<Restricao> getRestricoes() {
        return restricoes;
    }
}
