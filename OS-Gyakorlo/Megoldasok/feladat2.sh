#!/usr/bin/perl -w

while ($input=<STDIN>) {
chomp $input;
@list=(@list, $input);
}

$i=0;
while ($list[$i]) {
print "\@$list[$i]\n";
$i++;
}