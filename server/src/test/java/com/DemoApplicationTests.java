package com;

import com.facebook.product.ProductItem;
import com.facebook.product.ProductService;
import com.opencsv.CSVWriter;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import facebook4j.Post;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private ProductService productService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testConvertPostsToProductItems() {


        try {
            List<Post> allPosts = productService.findAllPosts();
            List<ProductItem> productItems = productService.convertPostsToProductItems(allPosts);
//            productService.uploadProductFeed("testFeed2");


        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }





}
