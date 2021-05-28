package ru.netology.ProductManager;

import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.*;

public class ProductManager {
    private Product[] products = new Product[0];
    private ProductRepository repository;
    private String text;


    public ProductManager() {
    }

    public ProductManager(Product[] products, ProductRepository repository, String text) {
        this.products = products;
        this.repository = repository;
        this.text = text;
    }

    public ProductManager(ProductRepository repository) {
        this.repository = repository;
    }



    public Product[] getProducts() {
        return products;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }

    public ProductRepository getRepository() {
        return repository;
    }

    public void setRepository(ProductRepository repository) {
        this.repository = repository;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }




    public void add (Product product) {
        repository.save(product);
        }


    public Product[] searchBy(String text) {
        Product[] result = new Product[0];
        for (Product product: repository.findAll()) {
            if (matches(product, text)) {
                Product[] tmp = new Product[result.length + 1];
                // используйте System.arraycopy, чтобы скопировать всё из result в tmp
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = product;
                result = tmp;
            }
        }
        return result;
    }


    public boolean matches(Product product, String search) {
        if (product instanceof Book) { // если в параметре product лежит объект класса Book
            Book book = (Book) product; // положем его в переменную типа Book чтобы пользоваться методами класса Book
            if (((Book) product).getAuthor().contains(search)) { // проверим есть ли поисковое слово в данных об авторе
                return true;
            }
            if (product.getName().contains(search)) {
                return true;
            }
            return false;
        }

        if (product instanceof Smartphone) {
            Smartphone smartphone = (Smartphone) product;
            if (((Smartphone) product).getManufacturer().contains(search)) {
                return true;
            }
            if (product.getName().contains(search)) {
                return true;
            }
        }
        return false;
    }

}
