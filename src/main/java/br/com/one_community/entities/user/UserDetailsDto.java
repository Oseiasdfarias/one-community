package br.com.one_community.entities.user;

public record UserDetailsDto(
        Long id,
        String userName,
        Role role) {

    public UserDetailsDto( User data) {
        this(
                data.getId(),
                data.getUserName(),
                data.getRole()
        );
    }
}
