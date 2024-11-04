package br.com.one_community.entities.question;


import br.com.one_community.entities.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity()
@Table(name = "questions")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(unique = true)
    private String title;
    private String body;
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column
    private int views;
    @Column
    private Boolean status;

    public Question(QuestionDto data, User user1) {
        user = user1;
        title = data.title();
        body = data.body();
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    public void updateInfos(DataQuestionUpdateDto data) {

        if (data.title() != null) {
            this.title = data.title();
        }

        if (data.body() != null) {
            this.body = data.body();
        }
    }

    public void excluir() {
        this.status = false;
    }
}
