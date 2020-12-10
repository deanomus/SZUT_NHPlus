package model;

import java.util.ArrayList;
import java.util.List;

public class Nurse extends Person {


    private int
                id;
    private String
                telephone,
                passwordHash;
    private List<Treatment> treatments;

    private Nurse nurse;


    /**
     * construcs a nurse from given parameters
     *
     * @param id
     * @param firstName
     * @param surname
     * @param telephone
     * @param PasswordHash
     */
    public Nurse(Integer id, String firstName, String surname, String telephone, String PasswordHash) {
        super(firstName, surname);
        treatments = new ArrayList<Treatment>();
        if (id != null) {
            this.id = id;
        }
        if (PasswordHash != null) {
            this.passwordHash = PasswordHash;
        } else {
            this.passwordHash = "";
        }
        this.telephone = telephone;
    }



    // Getter & Setter


    /**
     *
     * @return Nurse (caregiver) id
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     * @return Nurse
     */
    public Nurse setId(int id) {
        this.id = id;
        return this;
    }

    /**
     *
     * @return telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     *
     * @param telephone
     * @return Nurse
     */
    public Nurse setTelephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    /**
     *
     * @return treatments
     */
    public List<Treatment> getTreatments() {
        return treatments;
    }

    /**
     *
     * @param treatments
     * @return
     */
    public Nurse setTreatments(List<Treatment> treatments) {
        this.treatments = treatments;
        return this;
    }

    /**
     *
     * @param treatment Treatment
     * @return
     */
    public Nurse addTreatment(Treatment treatment) {
        if (!(this.treatments.contains(treatment))) {
            this.treatments.add(treatment);
        }
        return this;
    }

    /**
     *
     * @param treatment
     * @return
     */
    public Nurse remTreatment(Treatment treatment) {
        if (this.treatments.contains(treatment)) {
            this.treatments.remove(treatment);
        }
        return this;
    }

    /**
     *
     * @return
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     *
     * @param passwordHash
     * @return
     */
    public Nurse setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
        return this;
    }
}
