package aima.core.csp;

import aima.core.search.basic.support.BasicCSP;
import aima.core.search.basic.support.BasicConstraint;

public class AC3 {
    public static void main(String[] args) {
        Object[] minutos = new Object[] {
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
                10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
                20, 21, 22, 23, 24, 25, 26, 27
        };

        String variaveis[] =  {
                "EixoF", "EixoT",
                "RodaEF", "RodaDF", "RodaET", "RodaDT",
                "PorcasEF", "PorcasDF", "PorcasET", "PorcasDT",
                "CalotaEF", "CalotaDF", "CalotaET", "CalotaDT",
                "Inspecionar"
        };

        Object[][] dominios = new Object[15][];

        for (int i = 0;  i < variaveis.length; i++) {
            dominios[i] = minutos;
        }

        BasicCSP csp = new BasicCSP(
                variaveis,
                dominios,
                createTimeConstraint("EixoF", "RodaEF"),
                createTimeConstraint("EixoF", "RodaDF"),
                createTimeConstraint("EixoT", "RodaET"),
                createTimeConstraint("EixoT", "RodaDT"),
                createTimeConstraint("RodaDF", "PorcasDF"),
                createTimeConstraint("RodaEF", "PorcasEF"),
                createTimeConstraint("RodaDT", "PorcasDT"),
                createTimeConstraint("RodaET", "PorcasET"),
                createTimeConstraint("PorcasDF", "CalotaDF"),
                createTimeConstraint("PorcasEF", "CalotaEF"),
                createTimeConstraint("PorcasDT", "CalotaDT"),
                createTimeConstraint("PorcasEF", "CalotaET"),
                createDisjunctiveTimeConstraint("EixoF", "EixoT"),
                createTimeConstraint("EixoF", "Inspecionar"),
                createTimeConstraint("EixoT", "Inspecionar"),
                createTimeConstraint("RodaEF", "Inspecionar"),
                createTimeConstraint("RodaDF", "Inspecionar"),
                createTimeConstraint("RodaDT", "Inspecionar"),
                createTimeConstraint("RodaET", "Inspecionar"),
                createTimeConstraint("PorcasDF", "Inspecionar"),
                createTimeConstraint("PorcasEF", "Inspecionar"),
                createTimeConstraint("PorcasDT", "Inspecionar"),
                createTimeConstraint("PorcasEF", "Inspecionar"),
                createTimeConstraint("CalotaDF", "Inspecionar"),
                createTimeConstraint("CalotaEF", "Inspecionar"),
                createTimeConstraint("CalotaDT", "Inspecionar"),
                createTimeConstraint("CalotaEF", "Inspecionar")
        );

        aima.core.search.basic.csp.AC3 ac3 = new aima.core.search.basic.csp.AC3();

        printCSP(csp);
        ac3.test(csp);
        printCSP(csp);
    }

    private static BasicConstraint createTimeConstraint(String task1, String task2) {
        String[] scope = new String[] { task1, task2 };
        return new BasicConstraint(scope, (values) -> {
            return (int) values[0] + getTaskDuration(scope[0]) <= (int) values[1];
        });
    }

    private static BasicConstraint createDisjunctiveTimeConstraint(String task1, String task2) {
        String[] scope = new String[] { task1, task2 };
        return new BasicConstraint(scope, (values) -> {
            return ((int) values[0] + getTaskDuration(scope[0]) <= (int) values[1]) ||
                    ((int) values[1] + getTaskDuration(scope[1]) <= (int) values[0]);
        });
    }

    private static int getTaskDuration(String taskName) {
        if (taskName.contains("Eixo")) return 10;
        if (taskName.contains("Porcas")) return 2;
        if (taskName.contains("Roda")) return 1;
        if (taskName.contains("Calota")) return 1;

        return 0;
    }

    private static void printCSP(BasicCSP csp) {
        System.out.println("=====================================================================");
        System.out.println( "Variáveis : " + csp.getVariables() );
        System.out.println( "Dominios  : " + csp.getDomains() );
        System.out.println("=====================================================================");
    }
}