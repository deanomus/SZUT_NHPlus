package datastorage;

import model.Nurse;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NurseDAO extends DAOimp<Nurse> {

    public NurseDAO(Connection con) {
        super(con);
    }

    @Override
    protected String getCreateStatementString(Nurse nurse) {
        return
                String.format("INSERT INTO nurse (firstname, surname, telephone, password) VALUES ('%s', '%s', '%s', '%s')",
                nurse.getFirstName(), nurse.getSurname(), nurse.getTelephone(), nurse.getPasswordHash());
    }

    @Override
    protected String getReadByIDStatementString(int key) {
        return String.format("SELECT * FROM nurse WHERE id = %d", key);
    }

    @Override
    protected Nurse getInstanceFromResultSet(ResultSet result) throws SQLException {
        Nurse nurse = null;
        nurse = getNurse(result);
        return nurse;
    }

    @Override
    protected String getReadAllStatementString() {
        return "SELECT * FROM nurse";
    }

    @Override
    protected ArrayList<Nurse> getListFromResultSet(ResultSet result) throws SQLException {
        ArrayList<Nurse> nurses;
        nurses = new ArrayList<Nurse>();
        Nurse nurse = null;
        while (result.next()) {
            nurse = getNurse(result);
            nurses.add(nurse);
        }
        return nurses;
    }

    @Override
    protected String getUpdateStatementString(Nurse nurse) {
        return String.format("UPDATE nurse SET" +
                        "firstname = '%s'," +
                        "surname = '%s'," +
                        "telephone = '%s'" +
                        "WHERE id = %d",

                        nurse.getFirstName(),
                        nurse.getSurname(),
                        nurse.getTelephone(),
                        nurse.getId());
    }

    @Override
    protected String getDeleteStatementString(int key) {
        return String.format("Delete FROM nurse WHERE id='%d'" , key);
    }

    // Helpers

    private Nurse getNurse(ResultSet resultSet) throws SQLException {
        Nurse nurse = new Nurse(
                resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getString(5)
        );
        return nurse;
    }

}
