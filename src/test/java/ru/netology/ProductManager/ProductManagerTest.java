package ru.netology.ProductManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ProductManagerTest {

    private ProductRepository repository = new ProductRepository();
    private ProductManager manager = new ProductManager(repository);

    private Book first = new Book(0, "firstBook", 25, "King");
    private Book second = new Book(1, "secondBook", 99, "Oz");
    private Book third = new Book(2, "thirdBook", 10, "Ivanov");
    private Book fourth = new Book(3, "fourthBook", 1, "Ivanov");
    private Smartphone fifth = new Smartphone(4, "firstPhone", 20, "Japan");
    private Smartphone sixth = new Smartphone(5, "secondPhone", 10, "China");
    private Smartphone seventh = new Smartphone(6, "thirdPhone", 100, "USA");
    private Smartphone eighth = new Smartphone(7, "fourthPhone", 50, "China");

    @BeforeEach
    public void setUp() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(fourth);
        repository.save(fifth);
        repository.save(sixth);
        repository.save(seventh);
        repository.save(eighth);
    }


    @Test
    void searchBookByName() {
        Product[] expected = manager.searchBy ("firstBook");
        Product[] actual = new Product[]{first};
        assertArrayEquals(expected, actual);
    }

    @Test
    void searchBookByAuthor() {
        Product[] expected = manager.searchBy ("Ivanov");
        Product[] actual = new Product[]{third, fourth};
        assertArrayEquals(expected, actual);
    }

    @Test
    void searchPhoneByName() {
        Product[] expected = manager.searchBy ("thirdPhone");
        Product[] actual = new Product[]{seventh};
        assertArrayEquals(expected, actual);
    }

    @Test
    void searchPhoneByManufacturer() {
        Product[] expected = manager.searchBy ("China");
        Product[] actual = new Product[]{sixth, eighth};
        assertArrayEquals(expected, actual);
    }

    @Test
    void searchProductOutOfList() {
        Product[] expected = manager.searchBy ("emptyBook");
        Product[] actual = new Product[]{};
        assertArrayEquals(expected, actual);
    }

}