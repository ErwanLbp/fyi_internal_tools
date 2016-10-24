package db; /**
 * classe MyConnector
 * permet de se connecter/d�connecter � une base de donn�es Oracle
 * assure l'unicit� de la connection (inspir� du singleton)
 * <p>
 * Utiliser uniquement les m�thodes de MyConnector pour ouvrir une
 * nouvelle connexion (ou obtenir celle en cours) et fermer une
 * connexion :
 * <p>
 * Connection connection = MyConnector.getConnection();
 * // use connection
 * MyConnector.closeConnection();
 *
 * @author Matthias Colin modifi� par Herv� de Milleville
 * version 2.4 (19/03/2012)
 * <p>
 * version 2 : param�trage login/passwd
 * version 2.2 : param�trage driver,serveur,port, bddname
 * version 2.3 : par defaut sur Oracle, param�trable pour mysql
 */


import java.sql.*;


public class MyConnectorJDBC {

    // On teste la connexion
    public static void main(String[] args) {
        MyConnectorJDBC connector = new MyConnectorJDBC();

        try {
            MyConnectorJDBC.setParametersOracleLocal("oifyi", "oifyi");
            Connection connexion = connector.getConnection();
            System.out.println("OK");
        } catch (Exception e) {
            // on renvoie une connection nulle en cas d'erreurs de connexion
            System.err.println("Erreur de connexion à la base : " + e.getMessage());
        }

    }
    // champs n�cessaires � la connexion

    // url de connexion � la base
    private static String url = null;

    // d�tails
    private static final String DEFAULT_DRIVER = "jdbc:oracle:thin:";
    private static final String EISTI_SERVER = "area.etude.pau.eisti.fr";
    private static final String LOCAL_SERVER = "localhost";
    private static final int DEFAULT_PORT = 1521;
    private static final String EISTI_BDD = "cinema";
    private static final String LOCAL_BDD = "XE";

    // classe du pilote qui g�re la connexion Oracle
    private static final String ORACLE_DRIVER_CLASS = "oracle.jdbc.driver.OracleDriver";
    private static String driverClass = null;

    // login et mot de passe sur le serveur de BDD
    private static String login = null;
    private static String passwd = null;

    // champs assurant l'unicit� de la connexion et du chargement du driver
    private static Connection refUniqueConnection = null;
    private static boolean isDriverRegistered = false;

    /**
     * Param�tre le connecteur avec un login et un mot de passe
     * permettant d'acc�der au serveur Oracle local.
     * A faire une fois dans l'application.
     */
    public static void setParametersOracleLocal(String pLogin, String pPasswd) {
        setParameters(DEFAULT_DRIVER, LOCAL_SERVER, DEFAULT_PORT, LOCAL_BDD,
                ORACLE_DRIVER_CLASS, pLogin, pPasswd);
    }

    /**
     * Param�tre le connecteur avec un login et un mot de passe
     * permettant d'acc�der au serveur Oracle de l'�cole.
     * A faire une fois dans l'application.
     */
    public static void setParametersOracleEisti(String pLogin, String pPasswd) {
        setParameters(DEFAULT_DRIVER, EISTI_SERVER, DEFAULT_PORT, EISTI_BDD,
                ORACLE_DRIVER_CLASS, pLogin, pPasswd);
    }

    /**
     * Param�tre le connecteur Oracle avec un login et un mot de passe
     * permettant d'acc�der au serveur Oracle.
     * A faire une fois dans l'application.
     */
    public static void setParameters(String pDriver, String pServer, int pPort,
                                     String pBddname, String pDriverClass, String pLogin, String pPasswd) {
        url = pDriver + "@" + pServer + ":" + pPort + ":" + pBddname;
        driverClass = pDriverClass;
        login = pLogin;
        passwd = pPasswd;
    }

    /**
     * renvoie une connexion unique vers une base de donn�e.
     */
    public static Connection getConnection() {
        //on regarde si la connexion existe d�j�
        if (refUniqueConnection == null) {
            try {
                setParametersOracleLocal("oifyi", "oifyi");
                // chargement du driver de la BDD si besoin
                if (!isDriverRegistered) {
                    // chargement du driver
                    DriverManager.registerDriver((Driver) (Class.forName(driverClass).newInstance()));
                    isDriverRegistered = true;
                }
                // cr�ation de la connection
                refUniqueConnection = DriverManager.getConnection(url, login, passwd);
            } catch (Exception e) {
                System.err.println("Erreur de connexion à la base\n");
                e.printStackTrace();
            }
        }
        //on renvoie la connexion
        return refUniqueConnection;
    }

    /**
     * ferme la connexion en cours.
     */
    public static void closeConnection() throws Exception {
        try {
            // fermeture de la connexion
            refUniqueConnection.close();
            // suppression de la r�f�rence vers la connexion close
            refUniqueConnection = null;
        } catch (SQLException e) {
            throw e;
        }
    }

}