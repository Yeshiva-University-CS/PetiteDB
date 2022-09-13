package edu.yu.dbimpl.file;

/** Specifies the public API for the FileMgr implementation by requiring all
 * FileMgr implementations to extend this base class.
 *
 * Students MAY NOT modify this class in any way, they must suppport EXACTLY
 * the constructor signatures specified in the base class (and NO OTHER
 * signatures).
 *
 * The FileMgr always reads/writes/appends a block-sized number of bytes
 * from/to a file operating on exactly one disk access per call.
 *
 * The FileMgr class handles the actual interaction with the OS's file
 * system. Its constructor takes two arguments: a string denoting the name of
 * the database and an integer denoting the size of each block. The database
 * name is used as the name of the directory that contains the files for the
 * database; this folder is located in the DBMS current directory. If no such
 * folder exists, then a folder is created for a new database. The method isNew
 * returns true in this case and false otherwise.
 *
 * Note: the "current directory" is the directory from where the JVM was
 * invoked.  See e.g., https://stackoverflow.com/a/15954821 for the difference
 * between user's directory and the current working directory
 *
 * Design note: the FileMgr creates files "on demand".  Specifically, if the
 * client invokes an API that references a file, and the file doesn't yet
 * exist, the FileMgr creates a new empty file with that name.

 * Your implementation will likely invoke JDK methods that throw checked
 * exceptions.  As you can see, the API doesn't declare any checked exception:
 * you should catch and rethrow any such exception as a RuntimeException.

 * The DBMS has exactly one FileMgr object (singleton pattern), which is
 * created during system startup.
 * 
 * @author Avraham Leff
 */

import java.io.*;

public abstract class FileMgrBase {

  /** The constructor is responsible for removing temporary files that may have
   * been created in previous invocations of the DBMS.  By convention, such
   * files are denoted by starting with the string "temp".
   *
   * @param dbDirectory specifies the location of the root database directory
   * in which files will be created
   * @param blockSize size of blocks to be used in this database
   */
  public FileMgrBase(File dbDirectory, int blocksize) {
    // fill me in in your implementation class!
  }

  public abstract void read(BlockIdBase blk, PageBase p);

  public abstract void write(BlockIdBase blk, PageBase p);

  /** Allocates a block at the end of the specified file.  The contents of the
   * new block is implementation dependent, but a block of the correct size
   * MUST be written to disk after the call completes.
   *
   * @param filename specifies the file to which the block should be appended
   * @throws RuntimeException any checked exceptions should be converted to
   * RuntimeException as necessary.
   */
  public abstract BlockIdBase append(String filename);

  /** Return the number of blocks of the specified file
   *
   * @param filename specifies the file
   * @return the number of blocks of that file
   */
  public abstract int length(String filename);

  public abstract boolean isNew();

  public abstract int blockSize();
}
