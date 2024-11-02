package br.com.one_community.entities.question;


public record QuestionDetailsDto(
        Long questionId,
        Long userId,
        String title,
        String body) {

    public QuestionDetailsDto(Question data) {
        this(
                data.getId(),
                data.getUser().getId(),
                data.getTitle(),
                data.getBody());
    }
}
