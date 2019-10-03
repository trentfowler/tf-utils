package com.tfowler.utils.constants;

/**
 * Provides a general usage return code that can be used to indicate the success or failure of a
 * task without using magic numbers. These are deliberately named int constants and not enums for
 * brevity and simplicity when using with {@link System#exit(int)}.
 *
 * @see <a href=http://www.gnu.org/software/libc/manual/html_node/Exit-Status.html#Exit-Status></a>
 */
public class ReturnCode {
  public static int SUCCESS = 0;

  public static int FAILURE = -1;
}
