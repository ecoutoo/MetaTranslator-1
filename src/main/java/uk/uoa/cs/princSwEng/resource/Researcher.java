package uk.uoa.cs.princSwEng.resource;

import com.fasterxml.jackson.core.*;
import java.io.*;


public class Researcher extends Resource
{

private final String username;
private final String name;
private final String surname;
private final String email;
private final String password;


public Researcher(final String username, final String name,final String surname,final String email,final String password)
{
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
}

/**
 * Returns the name of the Researcher.
 *
 * @return the name of the Researcher.
 *
*/

public final String getResearcherUsername()
{
	return username;
}

public final String getResearcherName()
{
	return name;
}

/**
 * Returns the surname of the Researcher.
 *
 * @return the surname of the Researcher.
 */

public final String getResearcherSurname()
{
	return surname;
}

/**
 * Returns the email of the Researcher.
 *
 * @return the email of the Researcher.
 */

public final String getResearcherEmail()
{
		return email;
}

/**
 * Returns the password of the Researcher.
 *
 * @return the password of the Researcher.
 */

public final String getResearcherPassword()
{
		return password;
}

// @Override
 public final void toJSON(final OutputStream out) throws IOException
{
// 		final JsonGenerator jg = JSON_FACTORY.createGenerator(out);

// 		jg.writeStartObject();
// 		jg.writeFieldName("Category");
// 		jg.writeStartObject();
// 		jg.writeStringField("Category name", categoryName);
// 		jg.writeEndObject();
// 		jg.writeEndObject();
// 		jg.flush();
}

/**
 * Creates a {@code category} from its JSON representation.
 *
 * @param in the input stream containing the JSON document.
 *
 * @return the {@code category} created from the JSON representation.
 *
 * @throws IOException if something goes wrong while parsing.
 */

// public static Category fromJSON(final InputStream in) throws IOException
// {
// 		String jcategoryName = null;


// 		final JsonParser jp = JSON_FACTORY.createParser(in);

// 		while ("Category".equals(jp.currentName()) == false)
// 		{
// 				if(jp.nextToken() == null)
// 				{
// 						throw new IOException("Unable to parse JSON: no category object found");
// 				}
// 		}

// 		while (jp.nextToken() != JsonToken.END_OBJECT)
// 		{
// 				if (jp.currentToken() == JsonToken.FIELD_NAME)
// 				{
// 						switch(jp.currentName())
// 						{
// 						case "Category name":
// 								jp.nextToken();
// 								jcategoryName = jp.getText();
// 								break;

// 						}
// 				}

// 		}

// 		return new Category(jcategoryName);

// }
}
