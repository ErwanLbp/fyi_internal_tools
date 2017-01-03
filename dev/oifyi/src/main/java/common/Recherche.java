package common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <h1>PACKAGE_NAME common.Recherche</h1>
 * TODO Description
 *
 * @author Erwan
 * @version 1.0
 * @since 14-11-2016
 */
public class Recherche {

    public static String supprimerSort(String url) {
        if (!url.contains("sort"))
            return url;

        Pattern p = Pattern.compile("&sort=[a-zA-Z_]*");
        Matcher m = p.matcher(url);
        while (m.find())
            url = url.replaceFirst(m.group(), "");
        return url;
    }
}
