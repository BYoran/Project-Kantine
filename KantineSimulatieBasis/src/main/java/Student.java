/**
 * class Student
 * 
 * @author Lucas Wagenaar
 * @version 04-06-2020
 */

public class Student extends Persoon {
    
    private int studentnr;
    private String studierichting;

    public Student (int bsn, String voornaam, String achternaam, Datum geboortedatum, char geslacht, int studentnr, String studierichting) {
        super(bsn, voornaam, achternaam, geboortedatum, geslacht);
        this.studentnr = studentnr;
        this.studierichting = studierichting;
    }

    public Student() {
        
    }

    public int getStudentnr() {
        return studentnr; 
    }

    public String getStudierichting() {
        return studierichting;
    }

    public void setStudentnr(int studentnr) {
        this.studentnr = studentnr;
    }

    public void setStudierichting(String studierichting) {
        this.studierichting = studierichting;
    }

    @Override
    public String toString(){
        return super.toString() + "\n" + "Studentnummer: " + getStudentnr() + "\n" + "Studierichting: " + getStudierichting();
    }
}