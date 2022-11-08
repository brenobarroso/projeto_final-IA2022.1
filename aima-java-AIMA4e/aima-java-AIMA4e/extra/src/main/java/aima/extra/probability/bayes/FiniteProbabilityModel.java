package aima.extra.probability.bayes;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import aima.extra.probability.RandomVariable;

/**
 * A probability model on a discrete, countable set of worlds. The proper
 * treatment of the continuous case brings in certain complications that are
 * less relevant for most purposes in AI.
 * 
 * @author Ciaran O'Reilly
 * @author Nagaraj Poti
 */
public interface FiniteProbabilityModel extends ProbabilityModel {

	/**
	 * Prior probabilities of the form </br>
	 * <b>P</b>(X,Y..), <b>P</b>(X = true, Y) and others. </br>
	 * It is possible to specify both scoped and unscoped random variables in
	 * the proposition.
	 * 
	 * @param phi
	 *            the proposition of interest.
	 * @param unscopedTerms
	 *            the list of unbound random variables comprising the returned
	 *            distribution.
	 * 
	 * @return all the possible probability values corresponding to the unbound
	 *         random variables of the proposition &phi;. This is essentially a
	 *         vector of probability values, where we assume a predefined
	 *         ordering of the domain of the relevant random variables.
	 */
	CategoricalDistribution priorDistribution(Predicate<Map<RandomVariable, Object>> phi,
			List<RandomVariable> unscopedTerms);

	/**
	 * Get a conditional distribution. Example:<br>
	 * <br>
	 * <b>P</b>(X | Y) gives the values of P(X = x<sub>i</sub> | Y =
	 * y<sub>j</sub>) for each possible i, j pair.
	 * 
	 * @param phi
	 *            the proposition for which a probability distribution is to be
	 *            returned.
	 * @param unscopedPhi
	 *            the list of unbound random variables in the phi proposition.
	 * @param evidence
	 *            information we already have.
	 * @param unscopedEvidence
	 *            the list of unbound random variables in the evidence
	 *            proposition.
	 * 
	 * @return the conditional distribution for <b>P</b>(&phi; | evidence).
	 */
	CategoricalDistribution posteriorDistribution(Predicate<Map<RandomVariable, Object>> phi,
			List<RandomVariable> unscopedPhi, Predicate<Map<RandomVariable, Object>> evidence,
			List<RandomVariable> unscopedEvidence);

	/**
	 * Get a distribution on multiple variables. Example, the product rule:<br>
	 * <br>
	 * <b>P</b>(X, Y) gives the values of P(X = x<sub>i</sub> | Y =
	 * y<sub>j</sub>)P(Y = y<sub>j</sub>) for each possible i, j pair.
	 * 
	 * @param proposition
	 *            the propositions for which a joint probability distribution is
	 *            to be returned.
	 * @param unscopedTerms
	 *            the list of unbound random variables in the proposition.
	 * 
	 * @return the joint distribution for <b>P</b>(X, Y, ...).
	 */
	CategoricalDistribution jointDistribution(Predicate<Map<RandomVariable, Object>> proposition,
			List<RandomVariable> unscopedTerms);
}
