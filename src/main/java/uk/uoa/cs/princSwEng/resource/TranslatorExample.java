package uk.uoa.cs.princSwEng.resource;

/* import com.google.cloud.translate.Detection;
import com.google.cloud.translate.Language;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.Translate.LanguageListOption;
import com.google.cloud.translate.Translate.TranslateOption;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation; */
import com.google.cloud.translate.*;
import com.google.common.collect.ImmutableList;
import java.io.PrintStream;
import java.util.List;
import java.util.Optional;

public class TranslatorExample {
 public static void translateTextEx(String sourceText) {
 Translate translate = createTranslateService();
 Translation translation = translate.translate(sourceText);
 System.out.println("Source Text:"+ sourceText);
System.out.println("Translated Text: "+translation.getTranslatedText());
 
 }
}