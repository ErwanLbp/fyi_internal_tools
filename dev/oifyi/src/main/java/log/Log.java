package log;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Log {

    private static boolean ecrireFichier = false;
    private static boolean afficherEcran = false;
    private static String cheminFichier = "log.txt";

    public static void set_ecriture_fichier(boolean activer) {
        Log.ecrireFichier = activer;
    }

    private static boolean is_set_EcrireFichier() {
        return Log.ecrireFichier;
    }

    private static void set_afficher_ecran(boolean activer) {
        Log.afficherEcran = activer;
    }

    private static boolean is_set_AfficherEcran() {
        return Log.afficherEcran;
    }

    private static boolean ecrire_dans_fichier(String log) {
        try (BufferedWriter bos = Files.newBufferedWriter(Paths.get(cheminFichier), Charset.forName("ISO-8859-1"), StandardOpenOption.APPEND)) {
            bos.write(log);
        } catch (IOException e) {
            System.out.println("[X]\tEchec de l'ecriture du log");
            return false;
        }
        return true;
    }

    private static boolean afficher_ecran(String log) {
        try {
            JOptionPane.showMessageDialog(null, log);
        } catch (Exception e) {
            System.out.println("[X]\tEchec de l'affichage à l'écran du log");
            return false;
        }
        return true;
    }

    public static void info(String msg, boolean debutLigne, boolean finLigne) {
        String msg_format;
        if (debutLigne && finLigne)
            msg_format = msg + ".\n";
        else {
            if (debutLigne)
                msg_format = msg + " ";
            else if (finLigne)
                msg_format = msg + ".\n";
            else
                msg_format = msg + " ";
        }
        System.out.print(msg_format);

        if (is_set_AfficherEcran())
            afficher_ecran(msg_format);
        if (is_set_EcrireFichier())
            ecrire_dans_fichier(msg_format);
    }

    public static void info(String msg) {
        info(msg, true, true);
    }

    public static void erreur(String msg, boolean queDebutLigne) {
        String msg_format = "[X]\t" + msg;
        if (queDebutLigne)
            info(msg_format, true, false);
        else
            info(msg_format, true, true);
    }

    public static void succes(String msg, boolean queDebutLigne) {
        String msg_format = "[O]\t" + msg;
        if (queDebutLigne)
            info(msg_format, true, false);
        else
            info(msg_format, true, true);
    }
}
