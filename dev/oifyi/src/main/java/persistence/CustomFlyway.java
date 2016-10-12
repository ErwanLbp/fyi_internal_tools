package main.java.persistence;

/**
 * <h1>main.java.persistence CustomFlyway</h1>
 * TODO Description
 *
 * @author Erwan
 * @version 1.0
 * @since 12-10-2016
 */


/*
public class CustomFlyway extends Flyway {
    public int initMigrate() throws FlywayException, SQLException {
        Connection connection = this.getDataSource().getConnection();
        try {
            connection.prepareStatement("SELECT * FROM " + this.getTable()).execute();
        } catch (SQLException e) {
            CustomFlyway.LOG.warn("Could not perform a request on table \"" + this.getTable() + "\". Assuming it does not exist, flyway.init() is called.");
            this.init();
        }
        return this.migrate();
    }
}

*/