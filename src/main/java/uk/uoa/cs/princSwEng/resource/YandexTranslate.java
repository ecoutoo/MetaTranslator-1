package uk.uoa.cs.princSwEng.resource;

import java.net.URL;
import java.net.URLEncoder;

import uk.uoa.cs.princSwEng.resource.ApiKeys;
import uk.uoa.cs.princSwEng.resource.YandexTranslatorAPI;
import uk.uoa.cs.princSwEng.resource.Language;

/**
 * Makes calls to the Yandex machine translation web service API
 */
public final class YandexTranslate extends YandexTranslatorAPI {

  private static final String SERVICE_URL = "https://translate.yandex.net/api/v1.5/tr.json/translate?";
  private static final String TRANSLATION_LABEL = "text";

  //prevent instantiation
  private YandexTranslate(){};

  /**
   * Translates text from a given Language to another given Language using Yandex.
   * 
   * @param text The String to translate.
   * @param from The language code to translate from.
   * @param to The language code to translate to.
   * @return The translated String.
   * @throws Exception on error.
   */
  public static String execute(final String text, final Language from, final Language to) throws Exception {
    validateServiceState(text); 
    final String params = 
        PARAM_API_KEY + URLEncoder.encode(apiKey,ENCODING) 
        + PARAM_LANG_PAIR + URLEncoder.encode(from.toString(),ENCODING) + URLEncoder.encode("-",ENCODING) + URLEncoder.encode(to.toString(),ENCODING) 
        + PARAM_TEXT + URLEncoder.encode(text,ENCODING);
    final URL url = new URL(SERVICE_URL + params);
    return retrievePropArrString(url, TRANSLATION_LABEL).trim();
  }

  private static void validateServiceState(final String text) throws Exception {
    final int byteLength = text.getBytes(ENCODING).length;
    if(byteLength>10240) { 
      throw new RuntimeException("TEXT_TOO_LARGE");
    }
    validateServiceState();
  }
  
  public static void main(String[] args) {
    try {
      YandexTranslatorAPI.setKey(ApiKeys.YANDEX_API_KEY);
      String translation = YandexTranslate.execute("The quick brown fox jumps over the lazy dog.", Language.ENGLISH, Language.SPANISH);
      System.out.println("Translation: " + translation);
    } catch (Exception e) {
      
      e.printStackTrace();
    }
  }
}