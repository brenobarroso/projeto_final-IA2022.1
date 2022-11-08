package aima.core.csp;

import aima.core.csp.Subjects;
import aima.core.search.basic.csp.AC3;
import aima.core.search.api.CSP;
import aima.core.search.basic.csp.BacktrackingSearch;

public class Main {
    public static void main(String[] args) {

        CSP csp = Subjects.timeCSP();

        BacktrackingSearch backtrackingSearch = new BacktrackingSearch();

        AC3 ac3 = new AC3();
        System.out.println("Antes do AC3");
        System.out.println("Variaveis: " + csp.getVariables());
        System.out.println("Dominios: " + csp.getDomains());

        ac3.test(csp);

        System.out.println("Variaveis: " + csp.getVariables());
        System.out.println("Dominios: " + csp.getDomains());

//        System.out.println(backtrackingSearch.apply(csp).getAssignments());
    }
}
