package bookstore.repositories;

import bookstore.entities.Author;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AuthorRepositoryJDBC implements AuthorRepository {

    private final Connection connection;

    public AuthorRepositoryJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Author getById(int id) throws RepositoryOperationException {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Authors WHERE id = ?");
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Author author = new Author();
                author.setId(resultSet.getInt("id"));
                author.setName(resultSet.getString("name"));
                return author;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RepositoryOperationException("Unable to get author by ID.");
        }
    }

    @Override
    public List<Author> getAll() throws RepositoryOperationException {
        List<Author> authors = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM authors")) {

            while (resultSet.next()) {
                Author author = new Author();
                author.setId(resultSet.getInt("id"));
                author.setName(resultSet.getString("name"));
                authors.add(author);
            }
            return authors;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RepositoryOperationException("Unable to get all authors.");
        }
    }

    @Override
    public void save(Author author) throws RepositoryOperationException {
        String sql = "INSERT INTO authors (name) VALUES (?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, author.getName());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Saving author failed, no rows affected.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RepositoryOperationException("Unable to save author.");
        }
    }

    @Override
    public void update(Author author) throws RepositoryOperationException {
        String sql = "UPDATE authors SET name = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, author.getName());
            statement.setInt(2, author.getId());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating author failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RepositoryOperationException("Unable to update author.");
        }
    }

    @Override
    public void delete(int id) throws RepositoryOperationException {
        String sql = "DELETE FROM authors WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Deleting author failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RepositoryOperationException("Unable to delete author.");
        }
    }
}
