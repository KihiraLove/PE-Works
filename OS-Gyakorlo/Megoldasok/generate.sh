#!/usr/bin/perl -w

for ($i=1; $i<=9; $i++) {
	open(FILE, ">feladat00$i.sh") || die "ERROR";
	print FILE "#!/usr/bin/perl -w";
	close FILE;
}
for ($i=10; $i<=99; $i++) {
	open(FILE, ">feladat0$i.sh") || die "ERROR";
	print FILE "#!/usr/bin/perl -w";
	close FILE;
}
for ($i=100; $i<=177; $i++) {
	open(FILE, ">feladat$i.sh") || die "ERROR";
	print FILE "#!/usr/bin/perl -w";
	close FILE;
}
