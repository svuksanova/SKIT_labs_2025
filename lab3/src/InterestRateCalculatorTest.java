import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InterestRateCalculatorTest {

    private static final double EPS = 1e-9;

    /** Lower‐rate branch: 3.5 + (loanAmount / 10000) */
    private double lower(double loan) {
        return 3.5 + (loan / 10000.0);
    }
    /** Higher‐rate branch: 6.5 + (loanAmount / 5000) */
    private double higher(double loan) {
        return 6.5 + (loan / 5000.0);
    }

    // Predicate: (C1 ∧ C2 ∧ C3) ∨ (¬C2 ∧ C4)
    // C1: yearsEmployed >  3
    // C2: loanAmount    <= 10000
    // C3: !isFirstTimeBorrower
    // C4: creditScore   >  700

    // ─── CACC FOR C1 (rows 1 & 9) ────────────────────────────────────────────
    // minors: C2=T, C3=T, C4=T

    @Test
    void whenYearsEmployedGt3_thenLowerRate_C1() {
        double loan = 10000;
        double rate = InterestRateCalculator.calculateAdjustedInterestRate(
                /*creditScore*/ 800,    // C4 = true
                /*isFirstTime*/ false,  // C3 = true
                /*loanAmount*/ loan,    // C2 = true
                /*yearsEmployed*/ 4     // C1 = true
        );
        assertEquals(lower(loan), rate, EPS);
    }

    @Test
    void whenYearsEmployedLe3_thenHigherRate_C1() {
        double loan = 10000;
        double rate = InterestRateCalculator.calculateAdjustedInterestRate(
                800,    // C4 = true
                false,  // C3 = true
                loan,   // C2 = true
                3       // C1 = false
        );
        assertEquals(higher(loan), rate, EPS);
    }

    // ─── CACC FOR C2 (rows 2 & 6) ────────────────────────────────────────────
    // minors: C1=T, C3=T, C4=F

    @Test
    void whenLoanLe10000_thenLowerRate_C2() {
        double loan = 10000;
        double rate = InterestRateCalculator.calculateAdjustedInterestRate(
                /*creditScore*/ 700,   // C4 = false
                /*isFirstTime*/ false, // C3 = true
                /*loanAmount*/ loan,   // C2 = true
                /*yearsEmployed*/ 4    // C1 = true
        );
        assertEquals(lower(loan), rate, EPS);
    }

    @Test
    void whenLoanGt10000_thenHigherRate_C2() {
        double loan = 10001;
        double rate = InterestRateCalculator.calculateAdjustedInterestRate(
                700,    // C4 = false
                false,  // C3 = true
                loan,   // C2 = false
                4       // C1 = true
        );
        assertEquals(higher(loan), rate, EPS);
    }

    // ─── CACC FOR C3 (rows 1 & 3) ────────────────────────────────────────────
    // minors: C1=T, C2=T, C4=T

    @Test
    void whenNotFirstTime_thenLowerRate_C3() {
        double loan = 10000;
        double rate = InterestRateCalculator.calculateAdjustedInterestRate(
                /*creditScore*/ 800,   // C4 = true
                /*isFirstTime*/ false, // C3 = true
                /*loanAmount*/ loan,   // C2 = true
                /*yearsEmployed*/ 4    // C1 = true
        );
        assertEquals(lower(loan), rate, EPS);
    }

    @Test
    void whenFirstTime_thenHigherRate_C3() {
        double loan = 10000;
        double rate = InterestRateCalculator.calculateAdjustedInterestRate(
                800,    // C4 = true
                true,   // C3 = false
                loan,   // C2 = true
                4       // C1 = true
        );
        assertEquals(higher(loan), rate, EPS);
    }

    // ─── CACC FOR C4 (rows 5 & 6) ────────────────────────────────────────────
    // minors: C1=T, C2=F, C3=T

    @Test
    void whenCreditScoreGt700_thenLowerRate_C4() {
        double loan = 10001;
        double rate = InterestRateCalculator.calculateAdjustedInterestRate(
                /*creditScore*/ 800,   // C4 = true
                /*isFirstTime*/ false, // C3 = true
                /*loanAmount*/ loan,   // C2 = false
                /*yearsEmployed*/ 4    // C1 = true
        );
        assertEquals(lower(loan), rate, EPS);
    }

    @Test
    void whenCreditScoreLe700_thenHigherRate_C4() {
        double loan = 10001;
        double rate = InterestRateCalculator.calculateAdjustedInterestRate(
                700,    // C4 = false
                false,  // C3 = true
                loan,   // C2 = false
                4       // C1 = true
        );
        assertEquals(higher(loan), rate, EPS);
    }
}
