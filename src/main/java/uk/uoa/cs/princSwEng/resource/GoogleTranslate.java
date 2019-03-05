package uk.uoa.cs.princSwEng.resource;

/*import com.google.api.GoogleAPI;
import com.google.api.translate.Language;
import com.google.api.translate.Translate;*/
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.translate.Detection;
import com.google.cloud.translate.Language;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import java.util.*;



public class GoogleTranslate {
    private static final String TAG = GoogleTranslate.class.getSimpleName();
    private static final String API_KEY = "AIzaSyAkjqDtrIaZ96s4cQTHVR-SkHZIhehL3Gc";

    private GoogleTranslate() {
        // Private constructor to enforce noninstantiability
    }
}