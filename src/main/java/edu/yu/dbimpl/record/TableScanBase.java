package edu.yu.dbimpl.record;

import edu.yu.dbimpl.query.*;
import edu.yu.dbimpl.tx.TxBase;

/** Specifies the public API for the TableScan implementation by requiring all
 * TableScan implementations to extend this base class.
 *
 * Students MAY NOT modify this class in any way, they must suppport EXACTLY
 * the constructor signatures specified in the base class (and NO OTHER
 * signatures).
 *
 * A TableScan is an UpdateScan implementation over records stored in a file.
 */
public abstract class TableScanBase implements UpdateScan {

  /** Constructor
   *
   * @param tx Defines the transactional scope under which the scan operations
   * will take place 
   * @param tblname Names of the table over which the scan will be performed
   * @param layout Defines the logical and physical schema of the
   * table/relation
   */
  public TableScanBase(TxBase tx, String tblname, LayoutBase layout) {
    // fill me in in the implementation class!
  }
} // class
