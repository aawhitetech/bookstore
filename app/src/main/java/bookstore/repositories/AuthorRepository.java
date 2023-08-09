package bookstore.repositories;

import bookstore.entities.Author;

import java.util.List;

public interface AuthorRepository {
    Author getById(int id) throws RepositoryOperationException;
    List<Author> getAll() throws RepositoryOperationException;
    void save(Author author) throws RepositoryOperationException;
    void update(Author author) throws RepositoryOperationException;
    void delete(int id) throws RepositoryOperationException;
}
