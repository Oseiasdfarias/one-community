package br.com.one_community.entities.answer;

import br.com.one_community.entities.question.Question;
import br.com.one_community.entities.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity()
@Table(name = "answers")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;
    private String body;
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    private Boolean is_accepted;
    @Column
    private Boolean status;

    public Answer(AnswerDto data, User user, Question question) {
        this.user = user;
        this.question = question;
        this.body = data.body();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.is_accepted = false;
        this.status = true;
    }

    public void updateInfos(DataAnswerUpdateDto data) {
        if (data.body() != null) {
            this.body = data.body();
        }
        this.updatedAt = LocalDateTime.now();
    }

    public void deleteAnswer(){
        this.status = false;
    }
}
