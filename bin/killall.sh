#!/bin/bash
# killall--Sends the specified kill signal to all processes that match a process name. By default it kills only
# processes owned by the current user, unless root. Use "-s SIGNAL" to specify a signal to send to the process,
# "-u USER" to specify the user, "-t TTY" to specify a tty, and "-n" to only report what should be done, rather than
# doing it.

# Default signal is interrupt.
signal="-INT"
user=""
tty=""
donothing=0

while getopts "s:u:t:n" opt; do
  case "$opt" in
  # Note that the actual kill command wants "-SIGNAL" but we want "SIGNAL", so just prepend the "-" below.
  s) signal="-$OPTARG" ;;
  u)
    if [ -n "$tty" ]; then
      # Error: cannot specify a user and a TTY device
      echo "$0: error: -u and -t are mutually exclusive." >&2
      exit 1
    fi
    user=$OPTARG
    ;;
  t)
    if [ -n "$user" ]; then
      echo "$0: error: -u and -t are mutually exclusive." >&2
      exit 1
    fi
    tty=$2
    ;;
  n) donothing=1 ;;
  ?)
    echo "usage: $0 [-s signal] [-u user|-t tty] [-n] pattern" >&2
    exit 1
    ;;
  esac
done

# Done processing all the starting flags with getops.
shift $(($OPTIND - 1))

# If the users doesn't specify any starting arguments (earlier test is for "-?").
if [ $# -eq 0 ]; then
  echo "usage: $0 [-s signal] [-u user|-t tty] [-n] pattern" >&2
  exit 1
fi

# Generate a list of matching pids, either based on the specified TTY device, the specified user, or the current user.
if [ -n "$tty" ]; then
  pids=$(ps cu -t "$tty" | awk "/ $1$/ { print \$2 }")
elif [ -n "$user" ]; then
  pids=$(ps cu -U "$user" | awk "/ $1$/ { print \$2 }")
else
  pids=$(ps cu -U "${USER:-LOGNAME}" | awk "/ $1$/ { print \$2 }")
fi

# No matches.
if [ -z "$pids" ]; then
  echo "$0: no processes match pattern $1" >&2
  exit 1
fi

for pid in $pids; do
  # Sending signal $signal to process id $pid. (kill might still complain if the process has finished, the user doesn't
  # have permission to kill the specific process, etc., but that's ok.)
  if [ $donothing -eq 1 ]; then
    # The "-n" flag: "show me, but don't do it."
    echo "kill $signal $pid"
  else
    kill "$signal" "$pid"
  fi
done

exit 0
