package at.codersbay.libraryapp.api.books;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    public Optional<Book> findBookByIsbn(String isbn);
}
