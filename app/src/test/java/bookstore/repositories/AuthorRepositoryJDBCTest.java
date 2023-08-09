package bookstore.repositories;

import bookstore.entities.Author;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

public class AuthorRepositoryJDBCTest {

    private AuthorRepository repository;
    private Connection connectionMock;
    private PreparedStatement statementMock;
    private PreparedStatement preparedStatementMock;
    private ResultSet resultSetMock;

    @BeforeMethod
    public void setUp() throws SQLException {
        connectionMock = mock(Connection.class);
        repository = new AuthorRepositoryJDBC(connectionMock);

        // Mock the DatabaseUtil.getConnection() call
        statementMock = mock(PreparedStatement.class);
        preparedStatementMock = mock(PreparedStatement.class);
        resultSetMock = mock(ResultSet.class);

        when(connectionMock.createStatement()).thenReturn(statementMock);
        when(statementMock.executeQuery()).thenReturn(resultSetMock);
        when(statementMock.executeQuery(anyString())).thenReturn(resultSetMock);
        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);
        when(preparedStatementMock.executeQuery()).thenReturn(resultSetMock);
    }

    @Test
    public void testGetByIdFound() throws SQLException, RepositoryOperationException {
        // Simulate data returned from the database
        when(resultSetMock.next()).thenReturn(true);
        when(resultSetMock.getInt("id")).thenReturn(1);
        when(resultSetMock.getString("name")).thenReturn("Author Name");

        Author author = repository.getById(1);

        assertNotNull(author);
        assertEquals(author.getId(), 1);
        assertEquals(author.getName(), "Author Name");
    }

    @Test
    public void testGetByIdNotFound() throws SQLException, RepositoryOperationException {
        // Simulate no data returned from the database
        when(resultSetMock.next()).thenReturn(false);

        Author author = repository.getById(1);

        assertNull(author);
    }

    @Test(expectedExceptions = RepositoryOperationException.class)
    public void testGetByIdSQLException() throws SQLException, RepositoryOperationException {
        // Simulate a SQLException thrown by the statement execution
        when(preparedStatementMock.executeQuery()).thenThrow(SQLException.class);

        repository.getById(1);
    }

    @Test
    public void testGetAll() throws SQLException, RepositoryOperationException {
        when(resultSetMock.next()).thenReturn(true, true, false); // 2 authors
        when(resultSetMock.getInt("id")).thenReturn(1, 2);
        when(resultSetMock.getString("name")).thenReturn("Author1", "Author2");

        List<Author> authors = repository.getAll();

        assertEquals(authors.size(), 2);
        assertEquals(authors.get(0).getName(), "Author1");
        assertEquals(authors.get(1).getName(), "Author2");
    }

    @Test(expectedExceptions = RepositoryOperationException.class)
    public void testGetAllSQLException() throws SQLException, RepositoryOperationException {
        when(statementMock.executeQuery(anyString())).thenThrow(SQLException.class);

        repository.getAll();
    }

    @Test
    public void testSave() throws SQLException, RepositoryOperationException {
        when(preparedStatementMock.executeUpdate()).thenReturn(1);

        Author author = new Author();
        author.setName("New Author");

        repository.save(author);
    }

    @Test(expectedExceptions = RepositoryOperationException.class)
    public void testSaveSQLException() throws SQLException, RepositoryOperationException {
        when(preparedStatementMock.executeUpdate()).thenReturn(0);

        Author author = new Author();
        author.setName("New Author");

        repository.save(author);
    }

    @Test
    public void testUpdate() throws SQLException, RepositoryOperationException {
        when(preparedStatementMock.executeUpdate()).thenReturn(1);

        Author author = new Author();
        author.setId(1);
        author.setName("Updated Author");

        repository.update(author);
    }

    @Test(expectedExceptions = RepositoryOperationException.class)
    public void testUpdateSQLException() throws SQLException, RepositoryOperationException {
        when(preparedStatementMock.executeUpdate()).thenReturn(0);

        Author author = new Author();
        author.setId(1);
        author.setName("Updated Author");

        repository.update(author);
    }

    @Test
    public void testDelete() throws SQLException, RepositoryOperationException {
        when(preparedStatementMock.executeUpdate()).thenReturn(1);

        repository.delete(1);
    }

    @Test(expectedExceptions = RepositoryOperationException.class)
    public void testDeleteSQLException() throws SQLException, RepositoryOperationException {
        when(preparedStatementMock.executeUpdate()).thenReturn(0);

        repository.delete(1);
    }
}
