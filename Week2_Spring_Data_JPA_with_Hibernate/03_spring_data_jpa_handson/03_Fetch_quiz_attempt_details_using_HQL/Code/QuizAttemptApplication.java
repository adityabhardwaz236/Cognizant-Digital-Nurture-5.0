package com.cognizant.week2.jpa3.ex03;

import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@SpringBootApplication
public class QuizAttemptApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuizAttemptApplication.class, args);
    }

    @Bean
    CommandLineRunner demo(QuizAttemptService quizAttemptService) {
        return args -> {
            quizAttemptService.seedAttempts();
            quizAttemptService.findAttemptsForQuiz("Spring Basics").forEach(attempt ->
                System.out.println(attempt.getStudentName() + " -> " + attempt.getScore()));
        };
    }
}

@Entity
@Table(name = "quiz_attempt")
class QuizAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String studentName;
    private String quizName;
    private int score;
    private LocalDate attemptDate;

    QuizAttempt() {
    }

    QuizAttempt(String studentName, String quizName, int score, LocalDate attemptDate) {
        this.studentName = studentName;
        this.quizName = quizName;
        this.score = score;
        this.attemptDate = attemptDate;
    }

    public String getStudentName() {
        return studentName;
    }

    public int getScore() {
        return score;
    }
}

interface QuizAttemptRepository extends JpaRepository<QuizAttempt, Long> {

    @Query("select q from QuizAttempt q where q.quizName = :quizName order by q.score desc")
    List<QuizAttempt> findByQuizName(String quizName);
}

@org.springframework.stereotype.Service
class QuizAttemptService {

    private final QuizAttemptRepository quizAttemptRepository;

    QuizAttemptService(QuizAttemptRepository quizAttemptRepository) {
        this.quizAttemptRepository = quizAttemptRepository;
    }

    void seedAttempts() {
        quizAttemptRepository.saveAll(List.of(
            new QuizAttempt("Anita", "Spring Basics", 92, LocalDate.of(2026, 6, 1)),
            new QuizAttempt("Ravi", "Spring Basics", 85, LocalDate.of(2026, 6, 2)),
            new QuizAttempt("Neha", "SQL Basics", 88, LocalDate.of(2026, 6, 3))
        ));
    }

    List<QuizAttempt> findAttemptsForQuiz(String quizName) {
        return quizAttemptRepository.findByQuizName(quizName);
    }
}
