package at.codersbay.libraryapp.api.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findByUsername(String username);

    /**
     * TODO check functionality
     * @param username
     */
    public void deleteByUsername(String username);
}
