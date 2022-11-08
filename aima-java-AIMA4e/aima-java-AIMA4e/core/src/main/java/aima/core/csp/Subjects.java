package aima.core.csp;

import aima.core.search.api.CSP;
import aima.core.search.basic.support.BasicCSP;
import aima.core.search.basic.support.BasicConstraint;

// As variáveis serão os horários. Já os domínios serão as matérias

public class Subjects {



    public static CSP timeCSP() {
        Object[] horas = new Object[] {
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
                10, 11, 12, 13, 14, 15
        };
        String variaveis[] =  {
                "CalcA", "CalcB", "VGA", "PF", "PI", "ED", "POO"
        };

        Object[][] dominios = new Object[15][];
        for (int i = 0;  i < variaveis.length; i++) {
            dominios[i] = horas;
        }

        return new BasicCSP(variaveis,
                new Object[][] {
                        horas,
                        horas,
                        horas,
                        horas,
                        horas,
                        horas,
                        horas
                },
                BasicConstraint.newNotEqualConstraint("PI", "POO"),
                BasicConstraint.newNotEqualConstraint("PI", "ED"),
                BasicConstraint.newNotEqualConstraint("CalcA", "CalcB"),
                createSubjectConstraint("PI", "POO"),
                createSubjectConstraint("PI","ED"));
    }

    private static BasicConstraint createSubjectConstraint(String task1, String task2) {
        String[] scope = new String[] { task1, task2 };
        return new BasicConstraint(scope, (values) -> {
            return (int) values[0] + getSubjectDuration(scope[0]) <= (int) values[1];
        });
    }

    public static int getSubjectDuration(String subject) {
        if (subject == "CalcA") { return 4; }
        if (subject == "CalcB") { return 4; }
        if (subject == "PI") { return 4; }
        if (subject == "PF") { return 4; }
        if (subject == "POO") { return 4; }
        if (subject == "VGA") { return 4; }
        if (subject == "ED") { return 4; }
        return 0;
    }
}
