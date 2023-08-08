# Bookstore Application Repository
## Introduction

Welcome to the Bookstore Application repository. This project aims to provide a robust and scalable application for managing books. The initial design centers on a normalized model with primary emphasis on data integrity and efficiency.
### Features

    Simple and normalized data model.
    Support for tracking books, authors, and publishers.
    Scalable design ready for future enhancements.

## Application Overview

The Bookstore Application revolves around three primary entities: Books, Authors, and Publishers.
### Entities:

    Books: The cornerstone of our application, representing individual books.
    Authors: Denotes authors of books. Authors can exist in our system without having a book associated.
    Publishers: Depicts publishers of books. A publisher can also exist without any currently associated book.

### Relationships:

    Books to Authors: While each book has a singular author, an author can pen multiple books. The relationship is many-to-one, with the author being optional.
    Books to Publishers: Every book aligns with a single publisher, but a publisher can produce various books. This is another many-to-one relationship with the publisher being optional.

## Database Schema
### Authors Table:

    AuthorID: Unique identifier.
    AuthorName: The name of the author.

### Publishers Table:

    PublisherID: Unique identifier.
    PublisherName: Publisher's name.

### Books Table:

    BookID: Unique identifier.
    Title: The title of the book.
    AuthorID: Foreign key referencing Authors.
    Image: Path or link to the book's image.
    ISBN: Unique International Standard Book Number.
    PublisherID: Foreign key referencing Publishers.

## Entity Relationship Diagram (ERD)

For a visual understanding of the relationships between these entities, refer to the ERD available below:

![Entity Relationship Diagram](docs/entity_relationship_diagram.jpeg)

## Getting Started

This section can provide information on how to set up the application, including:

    Setting up the database.
    Installing required dependencies.
    Running migrations (if applicable).
    Starting the application.

For detailed setup instructions, consult the SETUP.md file (if you have one or plan to create one).
## Contributing

If you're looking to contribute, please check out the CONTRIBUTING.md file for guidelines (again, if you have one or plan to create one).
## License

This project is licensed under [Apache License, Version 2.0](https://www.apache.org/licenses/LICENSE-2.0). For more details, see the [LICENSE](LICENSE) file.