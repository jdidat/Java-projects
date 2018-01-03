/**
 * Lab 4 -- Selection
 *
 * This program finds if a student is eligible for finantial aid, and the amount they recieve if they do
 *
 * @author Jackson Didat, lab sec 12
 *
 * @version February 3, 2016
 *
 */
public class FAFSA {
    boolean isAcceptedStudent;
    boolean isSSregistered;
    boolean hasSSN;
    boolean hasValidResidency;
    boolean isDependent;
    int age;
    int creditHours;
    double studentIncome;
    double parentIncome;
    String classStanding;
    /**
     * The constructor for FAFSA
     * @param isAcceptedStudent the value for isAcceptedStudent, true of false
     * @param isSSregistered the value for isSSregistered, true of false
     * @param hasSSN the value of hasSSN, true of false
     * @param hasValidResidency the value of hasValidResidency, true or false
     * @param isDependent the value of isDependent, true or false
     * @param age the age of the student, int
     * @param creditHours the number of credit hours he/she is taking, double
     * @param studentIncome the student income, double
     * @param parentIncome the parents income, double
     * @param classStanding stores if the student is a graduate or undergraduate
     */
    public FAFSA(boolean isAcceptedStudent, boolean isSSregistered, boolean hasSSN,
                 boolean hasValidResidency, boolean isDependent, int age,
                 int creditHours, double studentIncome, double parentIncome,
                 String classStanding) {
        this.isAcceptedStudent = isAcceptedStudent;
        this.isSSregistered = isSSregistered;
        this.hasSSN = hasSSN;
        this.hasValidResidency = hasValidResidency;
        this.isDependent = isDependent;
        this.age = age;
        this.creditHours = creditHours;
        this.studentIncome = studentIncome;
        this.parentIncome = parentIncome;
        this.classStanding = classStanding;
    }
    /**
     * Determines if the student is eligible for federal financial aid. To be
     * eligible, the student must be an accepted student to a higher education
     * program (isAcceptedStudent), must be registered with the selective service
     * (isSSregistered) if they are between  the ages of 18-25 inclusive, must
     * have a social security number (hasSSN), and must have valid residency
     * status (hasValidResidency).
     *
     * @return True if the student is aid eligible and false otherwise
     */
    public boolean isFederalAidEligible() {
        if (this.age < 18 || this.age > 25) {
            if (this.isAcceptedStudent && this.hasSSN && this.hasValidResidency) {
                return true;
            } else {
                return false;
            }
        }
        if (this.age >= 18 && this.age <= 25) {
            if (this.isAcceptedStudent && this.isSSregistered && this.hasSSN && this.hasValidResidency) {
                return true;
            } else {
                return false;
                }
            }
        else {
            return false;
        }
    }
    /**
     * Calculates the students expected family contribution. If the student is
     * a dependent, then their EFC is calculated by the sum of the students
     * income and their parent's income, else if it just the student's income.
     *
     * @return The students expected family contribution
     */
    public double calcEFC() {
        double eFC;
        if(this.isDependent){
            eFC = this.studentIncome + this.parentIncome;
        }
        else {
            eFC = this.studentIncome;
        }
        return eFC;
    }
    /**
     * Calculates the student's federal grant award. The student's EFC must be
     * less than or equal to 50000 and they must be an undergraduate. The award
     * amount is based on their class standing and credit hours. Refer to the
     * tables in the breakdown section.
     *
     * @return The student's calculated federal grant award amount
     */
    public double calcFederalGrant() {
        double eFC = calcEFC();
        double grantAward = 0;
        if (this.classStanding == null) {
            return 0;
        }
        String classStandingLower = this.classStanding.toLowerCase();
        if (!classStandingLower.equals("undergraduate") || !classStandingLower.equals("undergraduate")) {
            return 0;
        }
        if (eFC >= 0.00 && eFC <= 50000.00) {
            if (classStandingLower.equals("undergraduate")) {
                if (this.creditHours < 9) {
                    grantAward = 2500;
                } else if (this.creditHours >= 9) {
                    grantAward = 5775;
                }
            }
        }
        return grantAward;
    }
    /**
     * Calculates the student's total Stafford loan award. The Stafford loan is
     * only available for students registered for 9 or more credit hours. The amount
     * of the award is calculated by the student's dependency status and their
     * class standing. Refer to the tables in the breakdown section.
     *
     * @return The student's calculated Stafford loan award amount
     */
    public double calcStaffordLoan() {
        double staffordLoan = 0;
        if (this.classStanding == null) {
            return 0;
        }
        String classStandingLower = this.classStanding.toLowerCase();
        if (this.creditHours >= 9) {
            if (classStandingLower.equals("undergraduate")) {
                if (this.isDependent) {
                    staffordLoan = 5000.0;
                }
            }
            if (classStandingLower.equals("graduate")) {
                if (this.isDependent) {
                    staffordLoan = 10000.0;
                }
            }
            if (classStandingLower.equals("undergraduate")) {
                if (!this.isDependent) {
                    staffordLoan = 10000.0;
                }
            }
            if (classStandingLower.equals("graduate")) {
                if (!this.isDependent) {
                    staffordLoan = 20000.0;
                }
            }
        }
        else {
            return 0;
        }
        return staffordLoan;
    }
    /**
     * Calculates the student's work study award. The work study award is
     * based on the student's EFC. Refer to the table in the breakdown section.
     *
     * @return The student's calculated federal grant award amount
     */
    public double calcWorkStudy() {
        double eFC = calcEFC();
        double workStudy;

        if (eFC >= 0.00 && eFC <= 30000.00) {
            workStudy = 1500.00;
        }
        else if (eFC >= 30000.01 && eFC <= 40000.00) {
            workStudy = 1000.00;
        }
        else if (eFC >= 40000.01 && eFC <= 50000.00) {
            workStudy = 500.00;
        }
        else if (eFC > 50000.00) {
            workStudy = 0.00;
        }
        else {
            return 0.00;
        }
        return workStudy;
    }
    /**
     * Calculates the student's total combined federal aid award. The total
     * aid award is calculated as the sum of Stafford loan award, federal grant
     * award, and work study award. You should make a call to the method
     * isFederalAidEligible() to see if the student is eligible to receive
     * federal aid. If they are NOT eligible, you can return 0. If the are, you
     * will return their total aid award.
     *
     * @return The student's total aid award amount
     */
    public double calcFederalAidAmount() {
        double federalAidAmount;
        double grantAward = calcFederalGrant();
        double staffordLoan = calcStaffordLoan();
        double workStudy = calcWorkStudy();
        if (isFederalAidEligible()) {
            federalAidAmount = grantAward + staffordLoan + workStudy;
        }
        else {
            return 0;
        }
        return federalAidAmount;
    }
}