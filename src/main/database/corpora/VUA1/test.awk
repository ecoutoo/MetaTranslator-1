BEGIN {FS = "\t"}
{
	id = $1$2$3;
	tab = "|";
	print id, tab, $4;
}
