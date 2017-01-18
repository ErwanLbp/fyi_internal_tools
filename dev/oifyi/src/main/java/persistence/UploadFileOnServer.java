package persistence;

import common.Consultant;
import common.Parametrage;
import dao.DocumentLinkDAO;
import log.Log;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Classe de méthode pour la gestion des uploades
 *
 * @author Erwan LBP
 * @version 1.0
 * @since 06-01-2017
 */
public class UploadFileOnServer {

    public static String UploadCV(HttpServletRequest request, String filepathToStore) {

        List<String> listOfExtensions = new ArrayList<>();
        listOfExtensions.add("pdf");

        SimpleDateFormat dateFormatDirectory = new SimpleDateFormat("yyyy");
        String dateDirectory = dateFormatDirectory.format(new Date());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd-HH-mm-ss");
        String date = dateFormat.format(new Date());

        DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();

        ServletFileUpload uploadHandler = new ServletFileUpload(fileItemFactory);
        try {
            List<FileItem> items = uploadHandler.parseRequest(request);

            Iterator<FileItem> itr = items.iterator();

            String path = new Parametrage("test").getClass().getProtectionDomain().getCodeSource().getLocation().getFile();

            File f = new File(path + filepathToStore + dateDirectory);
            Log.info("Dossier " + f.getPath(), true, false);
            Log.info(f.mkdirs() ? "créé" : "non créé", false, true);

            while (itr.hasNext()) {
                FileItem item = itr.next();
                System.out.println("item : " + item);

                String fileExtensionName = item.getName();
                fileExtensionName = FilenameUtils.getExtension(fileExtensionName);

                int idConsultant = ((Consultant) request.getSession().getAttribute("consultantConnecte")).getId();

                File file = new File(path + filepathToStore + dateDirectory, idConsultant + "_" + date + "." + fileExtensionName);
                System.out.println("absolute path : " + file.getAbsolutePath());

                if (listOfExtensions.contains(fileExtensionName)) {
                    item.write(file);
                    try {
                        DocumentLinkDAO.addDocumentLink(idConsultant, file.getName(), filepathToStore + dateDirectory + file.getName());
                        return null;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else
                    return "L'extention du fichier n'est pas valide";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Probleme d'upload du fichier";
        }
        return null;
    }
}