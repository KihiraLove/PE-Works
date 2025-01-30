#!/usr/bin/perl -w

while ($input=<STDIN>) {
chomp $input;
@list=(@list, $input);
}

$i=0;
while ($list[$i]) {
$num=$i+1;
print "$num. sor:$list[$i]\n";
$i++;
}