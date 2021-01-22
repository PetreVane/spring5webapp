package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component // tells Spring that this is a Spring Managed Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        // both Author and Book constructors have been altered, to ease the process of declaring new objects
        // meaning that, the books field (on Authors) and the Authors field (on Books) have been removed from the constructor
        // these fields have now default values --> empty set
        Author ericEvans = new Author("Eric", "Evans");
        Book ddd = new Book("Domain driven design", "1532652");

        ericEvans.getBooks().add(ddd);
        ddd.getAuthors().add(ericEvans);


        // once you've defined your author and your book and once you've made the link between them
        // you will have to call a method from the repository, to save the data into your h2 database
        authorRepository.save(ericEvans);
        bookRepository.save(ddd);

        // one more example
        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "25645216652");

        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        // save to repository
        authorRepository.save(rod);
        bookRepository.save(noEJB);

        System.out.println("Started in BootStrap");
        System.out.println("Books so far: " + bookRepository.count());
    }
}
