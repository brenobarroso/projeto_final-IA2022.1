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
        System.out.println("Antes da porra do teste");
        for (String s : csp.getVariables()) {
            System.out.println("Variavel: " + s);
            System.out.println("Dominio: " + csp.getDomains().get(csp.getVariables().indexOf(s)));
        }
        System.out.println(backtrackingSearch.apply(csp).getAssignments() + "\n");
        ac3.test(csp);

        System.out.println("Depois da porra do teste");
        for (String s : csp.getVariables()) {
            System.out.println("Variavel: " + s);
            System.out.println("Dominio: " + csp.getDomains().get(csp.getVariables().indexOf(s)));
        }

        System.out.println(backtrackingSearch.apply(csp).getAssignments());
    }
}
