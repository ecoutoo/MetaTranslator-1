BEGIN {FS = ";"}
{
	id = $1;
	corpora = "FLA-aff-neg"
	tab = "|";
	print corpora, id, tab, $5;
}
