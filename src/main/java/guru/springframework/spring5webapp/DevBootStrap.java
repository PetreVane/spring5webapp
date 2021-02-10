package guru.springframework.spring5webapp;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


@Component // tells Spring that this is a Spring Managed Component
public class DevBootStrap implements ApplicationListener<ContextRefreshedEvent> {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public DevBootStrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {
        // Publisher
        Publisher myPublisher = new Publisher("Pearson", "the Amazing Street");

        // both Author and Book constructors have been altered, to ease the process of declaring new objects
        // meaning that, the books field (on Authors) and the Authors field (on Books) have been removed from the constructor
        // these fields have now default values --> empty set
        Author ericEvans = new Author("Eric", "Evans");
        Book ddd = new Book("Domain driven design", "1532652");

        // author has a Book
        ericEvans.getBooks().add(ddd);
        // book has an author
        ddd.getAuthors().add(ericEvans);
        // book has a publisher
        ddd.setPublisher(myPublisher);
        // publisher has a book
        myPublisher.getBooks().add(ddd);


        // once you've defined your author and your book and once you've made the link between them
        // you will have to call a method from the repository, to save the data into your h2 database
        authorRepository.save(ericEvans);
        bookRepository.save(ddd);
        publisherRepository.save(myPublisher);

        // one more example
        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "25645216652");

        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        noEJB.setPublisher(myPublisher);
        myPublisher.getBooks().add(noEJB);

        // save to repository
        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(myPublisher);

        System.out.println("Started in BootStrap");
        System.out.println("Books so far: " + bookRepository.count());
        System.out.println("Publisher count is now " + publisherRepository.count());

    }


}
