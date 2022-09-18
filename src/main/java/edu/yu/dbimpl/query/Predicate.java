package edu.yu.dbimpl.query;

/** A Predicate is a boolean combination of terms (see the lecture that defines
 * the current restrictions on what must be supported).  This class is NOT
 * immutable as clients can add arbitrary number of terms to the Predicate.
 *
 * IMPORTANT: a Predicate currently only provides a "conjunction of terms"
 * semantics.
 *
 * Design note: the implementation DOES NOT override equals and hashCode.  The
 * reasoning: too tricky. e.g., the object's identity depends on multiple Terms
 * each of which depends on two Expressions, each of which has one null
 * Constant or String value.  Be aware of the implications of this design
 * decision as you use this class.
 *
 * Students MAY NOT modify this class in any way!.
 *
 * @author Avraham Leff
 */

import java.util.*;

public class Predicate {

  /** Constructor which creates an "empty" predicate (no terms), thus
   * evaluating to "true".
   */
  public Predicate() {
    // the predicate contains no terms
  }

  /** Constructor which creates a predicate containing a single term.
   *
   * @param t the term
   */
  public Predicate(Term t) {
    terms.add(t);
  }

  /** Adds a term to those currently encapsulated by the Predicate.
   *
   * @param t the term
   */
  public void add(final Term t) {
    terms.add(t);
  }

  /** Adds a list of terms to those currently encapsulated by the Predicate.
   *
   * @param thoseTerms the list of terms to be added.
   */
  public void add(final List<Term> thoseTerms) {
    terms.addAll(thoseTerms);
  }
  
  /** Returns true iff the predicate evaluates to true with respect to the
   * record currently pointed by the specified scan.
   *
   * @param scan the scan
   * @return true if all terms in the predicate evaluate to true with respect
   * to the current record.
   */
  public boolean isSatisfied(final Scan scan) {
    for (Term t : terms) {
      if (!t.isSatisfied(scan))
        return false;
    }

    return true;
  }

  @Override
  public String toString() {
    Iterator<Term> iter = terms.iterator();
    if (!iter.hasNext()) 
      return "";
    String result = iter.next().toString();
    while (iter.hasNext())
      result += " and " + iter.next().toString();
    return result;
  }

  /** Returns an unmodifiable version of the Predicate's terms
   *
   * @return an unmodifiable version of the Predicate's terms
   */
  public List<Term> terms() {
    return Collections.unmodifiableList(terms);
  }

  private final List<Term> terms = new ArrayList<Term>();
} // class
