#!/usr/bin/perl -w

while ($input=<STDIN>) {
chomp $input;
@list=(@list, $input);
}

$num=@list;
$i=0;
while ($list[$i]) {
print "$list[$i]-$list[$num-$i-1]\n";
$i++;
}