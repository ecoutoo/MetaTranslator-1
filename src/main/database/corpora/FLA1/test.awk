BEGIN {FS = ";"}
{
	id = $1;
	corpora = "FLA-aff-pos"
	tab = "|";
	print corpora, id, tab, $3;
}
