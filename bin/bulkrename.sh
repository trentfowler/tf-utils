#!/bin/bash
#bulkrename--Renames specified files by replacing text in the filename, e.g. bulkrename -f old -r new /tmp/bulk/*
print_help()
{
  echo "Usage: $0 -f find -r replace FILES_TO_RENAME*"
  echo -e "\t-f The text to find in the filename"
  echo -e "\t-r The replacement text for the new filename"
  exit 1
}
while getopts "f:r:" opt; do
  case "$opt" in
  r ) replace="$OPTARG"
    ;;
  f ) match="$OPTARG"
    ;;
  ? ) print_help
    ;;
  esac
done
shift $(( $OPTIND - 1 ))
if [ -z "$replace" ] || [ -z "$match" ]; then
  echo "must supply a string to find and a string to replace"
  print_help
fi
for i in "$@"; do
  newname=$(echo $i | sed "s/$match/$replace/")
  mv "$i" "$newname" && echo "Renamed file $i to $newname"
done
