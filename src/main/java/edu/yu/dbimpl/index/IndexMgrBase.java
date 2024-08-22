package edu.yu.dbimpl.index;

/** Specifies the public API for the IndexMgr implementation by requiring all
 * IndexMgr implementations to extend this base class.
 *
 * Students MAY NOT modify this class in any way, they must suppport EXACTLY
 * the constructor signatures specified in the base class (and NO OTHER
 * signatures).
 * 
 * An IndexMgr manages and persists index meta-data (allowing clients to create
 * and retrieve such meta-data).  An IndexMgr also instantiates corresponding
 * index "runtime index" instances, allowing clients to iterate over an index's
 * records and access the corresponding data records.
 *
 * Note to implementors: while not adequate for production deployments,
 * worst-case linear performance for these APIs is sufficient.
 *
 * @author Avraham Leff
 */

import java.util.Set;
import edu.yu.dbimpl.metadata.TableMgrBase;
import edu.yu.dbimpl.tx.TxBase;

public abstract class IndexMgrBase {

  /** Defines the set of index types that a client can use when creating an index.
   */
  public enum IndexType { STATIC_HASH };

  /** Constructor.
   *
   * @param isNew true iff this is the first time that the database is being
   * created (for this file system root): any implementation specific
   * initialization should be done in the constructor.  If this isn't the first
   * time that the database is being created for this file system root, the
   * implementation must not erase previously persisted state (but should skip
   * the initialization code).
   * @param tx supplies the transactional scope for database operations used in
   * the constructor implementation, cannot be null.  It's the client's
   * responsibility to manage the lifecycle of this transaction.
   * @param tableMgr to be used to persist index meta-data, cannot be null.
   * @throws IllegalArgumentException if arguments don't meet the
   * pre-conditions.
   */
  public IndexMgrBase(boolean isNew, TxBase tx, TableMgrBase tableMgr) {
    // use the implementation class to provide function
  } // constructor


  /** Persists information about the specified index and returns the unique id
   * that the IndexMgr associates with this index.  No exception should be
   * thrown if an index with the specified properties already exists: in that
   * case, return the id of the previously persisted index.
   *
   * @param tx supplies the transactional scope for database operations used in
   * the constructor implementation, cannot be null.  It's the client's
   * responsibility to manage the lifecycle of this transaction.
   * @param tableName the name of the indexed table, must be non-empty
   * @param indexName the name of the index, must be non-empty.  If clients
   * supply a different index name than one that already exists (with the other
   * properties identical to one that has been already persisted), create a new
   * index descriptor.
   * @param fieldName the name of the indexed field, must be non-empty, and
   * must match a field of the table name's schema.
   * @param indexType type of the index (e.g., static hashing, B-Tree), must be
   * non-null.
   * @return the persisted id that is associated with the index information.
   * @throws IllegalArgumentException if arguments don't meet the
   * pre-conditions.  
   */
  public abstract int
    createIndexDescriptor(TxBase tx, String tableName, String indexName,
                          String fieldName, IndexType indexType);  

  /** Returns the unique index ids associated with the specified table name.
   *
   * @param tx supplies the transactional scope for database operations used in
   * the constructor implementation, cannot be null.  It's the client's
   * responsibility to manage the lifecycle of this transaction.
   * @param tableName the table about which index information is being requested
   * @return Set containing the ids, empty set if no indices are defined for the table.
   * @throws IllegalArgumentException if the table doesn't exist.
   */
  public abstract Set<Integer> indexIds(TxBase tx, String tableName);

  /** Given a unique index id, returns the associated IndexDescriptor.
   *
   * @param tx supplies the transactional scope for database operations used in
   * the constructor implementation, cannot be null.  It's the client's
   * responsibility to manage the lifecycle of this transaction.
   * @param id identifies the index
   * @return Corresponding IndexDescriptor, null if id is not associated with
   * with an index.
   */
  public abstract IndexDescriptorBase get(TxBase tx, int indexId);

  /** Returns an Index instance based on the persisted information associated
   * with the index descriptor id.  The Index instance isn't positioned
   * internally on any index record: use Index.beforeFirst() and next() to set
   * the index's internal state correctly.
   *
   * @param tx supplies the transactional scope for database operations used in
   * the constructor implementation, cannot be null.  It's the client's
   * responsibility to manage the lifecycle of this transaction.
   * @param indexDescriptorId specifies a previously persisted IndexDescriptor
   * @return Index corresponding gto the indexDescriptorId.
   * @throws IllegalArgumentException if no information is associated with the
   * index descriptor id
   * @see createIndexDescriptor
   */
  public abstract IndexBase instantiate(TxBase tx, int indexDescriptorId);


} // abstract base class
