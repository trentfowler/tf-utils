#!/bin/bash
# rotatelogs--Rolls log files in /var/log for archival purposes and to ensure that they don't get unmanageably large.
# This script uses a config file to allow customization of how frequently each log should be rolled. The config file is
# in "logfilename=duration" format (without quotes), where duration is in days. If an entry in the config file is
# missing for a particular logfilename, "rotatelogs" won't rotate the file more frequently than every seven days. If
# duration is set to zero, the script will ignore that particular set of log files.
logdir="/var/log"
config="$logdir/rotatelogs.conf"
mv="/bin/mv"
# Default to 7-day rotation schedule.
default_duration=7
count=0
duration=$default_duration
if [ ! -f $config ]; then
  echo "$0: no config file found. Can't proceed" >&2
  exit 1
fi
# "-w" is write permission and "-x" is execute, both are required to create new files. Fail if not present.
if [ ! -w $logdir -o ! -x $logdir ]; then
  echo "$0: you don't have permissions on $logdir" >&2
  exit 1
fi
cd $logdir
# Would like to use standard set notation like ":digit:" with find but many versions of find don't support POSIX
# character class identifiers, so instead we use [0-9].
for name in $(find . -maxdepth 1 -type f -size +0c ! -name '*[0-9]*' ! -name '\.*' ! -name '*conf' -print | sed 's/^\.\///'); do
  count=$(($count + 1))
  # Get the matching entry from the config file for this particular log file.
  duration="$(grep "^${name}=" $config | cut -d= -f2)"
  # Use the default if there is no match.
  if [ -z "$duration" ]; then
    duration=$default_duration
  elif [ "$duration" = "0" ]; then
    echo "Duration set to zero: skipping $name"
    continue
  fi
  # Set up the rotation filenames.
  back1="${name}.1"
  back2="${name}.2"
  back3="${name}.3"
  back4="${name}.4"
  # If the most recently rotated logfile (back1) has not be modified within the specific quantum, then it's not time
  # to rotate it.
  if [ -f "$back1" ]; then
    if [ -z "$(find \"$back1\" -mtime +$duration -print 2>/dev/null)" ]; then
      /bin/echo -n "$name's most recent backup is more recent than $duration "
      echo "days: skipping"
      continue
    fi
  fi
  echo "Rotating log $name (using a $duration day schedule)"
  # Rotate, starting with oldest log, being careful in case one or more files don't exist yet.
  if [ -f "$back3" ]; then
    echo "... $back3 -> $back4"
    $mv -f "$back3" "$back4"
  fi
  if [ -f "$back2" ]; then
    echo "... $back2 -> $back3"
    $mv -f "$back2" "$back3"
  fi
  if [ -f "$back1" ]; then
    echo "... $back1 -> $back2"
    $mv -f "$back1" "$back2"
  fi
  if [ -f "$name" ]; then
    echo "... $name -> $back1"
    $mv -f "$name" "$back1"
  fi
  touch "$name"
  # Change file permissions to owner rw (rw-------) for privacy.
  chmod 0600 "$name"
done
if [ $count -eq 0 ]; then
  echo "Nothing to do: no log files big enough or old enough to rotate"
fi
exit 0
