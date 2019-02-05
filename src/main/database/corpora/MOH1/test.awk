BEGIN {FS = ","}
{
	id = $1;
	tab = "|";
	print id, tab, $5;
}
