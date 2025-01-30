#!/usr/bin/perl -w

#!/usr/bin/perl -w

while ($input=<STDIN>) {
chomp $input;
@list=(@list, $input);
}
$num=0;
$i=0;
while ($list[$i]) {

if ($list[$i]=~m/^\s+$/){
$num++;    
}
$i++;
}
print "$num\n";