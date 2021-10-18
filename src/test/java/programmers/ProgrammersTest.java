package programmers;

import org.junit.jupiter.api.Test;
import programmers.exercise.hash.E01UncompletedRace;

import static org.junit.jupiter.api.Assertions.*;

class ProgrammersTest {

    @Test
    void main() {
        // Hash
        E01UncompletedRace solution = new E01UncompletedRace();
        solution.exec();
    }
}