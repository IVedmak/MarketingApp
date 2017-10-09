package com.facebook.product;

import facebook4j.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductController {

    private Logger logger = LoggerFactory.getLogger(ProductController.class);
    @Resource
    private ProductService productService;

    @GetMapping("/posts")
    @CrossOrigin(origins = "http://localhost:4200")
    public Collection<Post> pagePosts() {

        try {
            return productService.findAllPosts().stream()
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Initialization error", e);
        }

        return new ArrayList<>();
    }


    @GetMapping("/generateFeed/{feedName}")
    @CrossOrigin(origins = "http://localhost:4200")
    public String createFeed(@PathVariable String feedName) {

        try {
            return productService.convertPostsToProductFeed(feedName);
        } catch (Exception e) {
            logger.error("Initialization error", e);
        }

        return "";
    }

    @GetMapping("/uploadProductFeed/{feedName}")
    @CrossOrigin(origins = "http://localhost:4200")
    public boolean uploadProductFeed(@PathVariable String feedName) {

        return productService.uploadProductFeed(feedName);

    }


    @GetMapping("/convertedPosts")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<ProductItem> getConvertedPosts() {

        try {
            return productService.convertPostsToProductItems(productService.findAllPosts());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
