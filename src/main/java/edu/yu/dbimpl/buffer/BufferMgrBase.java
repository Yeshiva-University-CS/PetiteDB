package edu.yu.dbimpl.buffer;

/** Specifies the public API for the BufferMgr implementation by requiring all
 * BufferMgr implementations to extend this base class.
 *
 * Students MAY NOT modify this class in any way, they must suppport EXACTLY
 * the constructor signatures specified in the base class (and NO OTHER
 * signatures).
 *
 *  A BufferMgr manages the pinning and unpinning of buffers to blocks.
 *
 * A BufferMgr is conceptually a singleton.

 * @author Avraham Leff
 */


import edu.yu.dbimpl.file.*;
import edu.yu.dbimpl.log.LogMgrBase;

public abstract class BufferMgrBase {

  /** Defines the set of available eviction policies used to select which
   * unpinned buffer should be used to store a disk block (see lecture for details)
   */
  public enum EvictionPolicy { NAIVE, CLOCK
  };

  
  /** Creates a buffer manager having the specified number of buffer slots, and
   * use the EvictionPolicy.NAIVE.
   *
   * @param fileMgr file manager singleton
   * @param logMgr  log manager singleton
   * @param nBuffers number of buffers to allocate in main-memory
   * @param maxWaitTime maximum number of milliseconds to wait before throwing
   * a BufferAbortException to a client invoking pin().  Must be greater than 0.
   * @see #pin
   */
  public BufferMgrBase
    (FileMgrBase fileMgr, LogMgrBase logMgr, int nBuffers, int maxWaitTime) {
    // fill me in in your implementation class!
  }

  /** Creates a buffer manager having the specified number of buffer slots, and
   * specify the EvictionPolicy that must be used.
   *
   * @param fileMgr file manager singleton
   * @param logMgr  log manager singleton
   * @param nBuffers number of buffers to allocate in main-memory
   * @param maxWaitTime maximum number of milliseconds to wait before throwing
   * a BufferAbortException to a client invoking pin().  Must be greater than 0.
   * @param policy the eviction policy to be used
   * @see #pin
   * @see #EvictionPolicy
   */
  public BufferMgrBase
    (FileMgrBase fileMgr, LogMgrBase logMgr, int nBuffers, int maxWaitTime, EvictionPolicy policy) {
    // fill me in in your implementation class!
  }
  
  /** Returns the number of available (i.e. unpinned) buffers.
   *
   * @return the number of available buffers
   */
  public abstract int available();

  /** Flushes all modified ("dirty") buffers modified by the specified
   * transaction.  Any association between the transaction and its buffers is
   * removed.
   *
   * @param txnum the transaction's id number
   * @throws IllegalArgumentException if txnum is negative
   * @see BufferBase.setModified
   */
  public abstract void flushAll(int txnum);
   
  /** Unpins the specified data buffer. If its pin count goes to zero, must
   * inform all clients currently blocked invoking pin() that a buffer instance
   * is now available.
   *
   * @param buffer the buffer to be unpinned
   * @throws IllegalArgumentException if buffer is null.
   * @throws IllegalArgumentException if buffer isn't currently pinned 
   * @see #pin
   */
  public abstract void unpin(BufferBase buffer);
   
  /** Pins a buffer to the specified block, writing the current contents to
   * disk if modified by the previous client and a new disk block is being read
   * from disk.  If no buffers are currently available, the client will block,
   * waiting for a buffer instance to become available.  If no buffer becomes
   * available within the time specified by the "maxWaitTime" value supplied to
   * the constructor, throws a {@link BufferAbortException}.
   *
   * @param blk a reference to a disk block
   * @return the buffer pinned to that block
   * @throws IllegalArgumentException if BlockId is null.
   * @throws BufferAbortException if the client times out waiting for a buffer
   * to become available.
   */
  public abstract BufferBase pin(BlockIdBase blk);

  /** Returns the EvictionPolicy used by the buffer manager.
   */
  public abstract EvictionPolicy getEvictionPolicy();

}
