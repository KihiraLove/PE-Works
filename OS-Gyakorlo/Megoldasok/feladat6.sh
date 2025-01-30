#!/usr/bin/perl -w

#!/usr/bin/perl -w

while ($input=<STDIN>) {
chomp $input;
@list=(@list, $input);
}

$i=0;
while ($list[$i]) {
    if ($list[$i]=~m/^\s+$/){
	$num=$i+1;
	print "$list[$i]";
    } else {
	$num=$i+1;
	print "$num$list[$i]";
    }
$i++;
}