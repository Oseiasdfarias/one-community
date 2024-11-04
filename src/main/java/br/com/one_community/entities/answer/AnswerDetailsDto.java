package br.com.one_community.entities.answer;


public record AnswerDetailsDto(
        Long answerId,
        Long userId,
        Long questionId,
        String body) {
    public AnswerDetailsDto(Answer data) {
        this(
                data.getId(),
                data.getUser().getId(),
                data.getQuestion().getId(),
                data.getBody()
        );
    }
}
