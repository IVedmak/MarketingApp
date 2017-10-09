package com.facebook.product;


import com.facebook.ads.sdk.*;
import com.opencsv.CSVWriter;
import facebook4j.*;
import facebook4j.conf.ConfigurationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class ProductService implements InitializingBean {
    private Logger logger = LoggerFactory.getLogger(ProductService.class);

    public final String ACCESS_TOKEN = "EAALQ9KZAPjj4BAL6glaKnOPmYNO9j6jTRjyp736Hse6ZA9GWRXL55Sr8dZA5jJWbsPD6fDUwNs552OpfEzuUT49wYG9tgdc1eRLW93dN8wvZCrgByXiashnTTkAs1Ku9c42VK0NrctmDEnPqIv90WseaCHG7oAZCtXqQIUWG6jgZDZD";
    public final String MARKETING_ACCESS_TOKEN = "EAALQ9KZAPjj4BAKkgZAZClTsZC1RpSnIQsv3zAXatVZAOWTF2iYZBOKN3GEzSJPSDzcME74rEnWTIYwU9HcPVFAx7igBZBb3gEetwZBtk0ibNTyS4I16Uyx01yZBSX3gGMp9GMdgoumkv8xMmtymzFNOKIateZAlkjy3pqrjclxDTu6iRa8gdAebZAC";
    public final Long ACCOUNT_ID = 10152248772279231l;
    public final Long BUSINESS_ID = 769218289953543l;
    public final String APP_SECRET = "fc1a5e59efd87562b104f0bd3b4e5f8e";

    public final APIContext context = new APIContext(MARKETING_ACCESS_TOKEN, APP_SECRET);


    private FacebookFactory facebookFactory = null;


    @Override
    public void afterPropertiesSet() throws Exception {
        if (facebookFactory == null) {
            init();
        }
    }

    private void init() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthAppId("177047609511900")
//                .setOAuthAppId("792699134250558 ")
                .setOAuthAppSecret(APP_SECRET)
                .setOAuthAccessToken(ACCESS_TOKEN)
                .setOAuthPermissions("user_birthday, user_religion_politics, user_relationships, user_relationship_details, user_hometown, user_location, user_likes, user_education_history, user_work_history, user_website, user_events, user_photos, user_videos, user_friends, user_about_me, user_status, user_games_activity, user_tagged_places, user_posts, rsvp_event, email, read_insights, publish_actions, read_audience_network_insights, read_custom_friendlists, user_actions.books, user_actions.music, user_actions.video, user_actions.news, user_actions.fitness, user_managed_groups, manage_pages, pages_manage_cta, pages_manage_instant_articles, pages_show_list, publish_pages, read_page_mailboxes, ads_management, ads_read, business_management, pages_messaging, pages_messaging_phone_number, pages_messaging_subscriptions, instagram_basic, instagram_manage_comments, instagram_manage_insights, public_profile, basic_info");
        facebookFactory = new FacebookFactory(cb.build());
    }


    public List<Post> findAllPosts() throws Exception {

        afterPropertiesSet();
        Facebook facebook = facebookFactory.getInstance();

        try {
            ResponseList<Post> posts = facebook.getPosts(new Reading().fields("attachments", "link", "message", "message_tags", "full_picture").limit(100));
            return posts.stream().collect(Collectors.toList());
        } catch (FacebookException e) {
            logger.error("Error while reading posts", e);
        }

        return new ArrayList<>();
    }


    public List<ProductItem> convertPostsToProductItems(List<Post> posts) {
        List<ProductItem> productItems = posts.stream().map(post ->
                convertToProductItem(post)).collect(Collectors.toList());


        return productItems;
    }

    private ProductItem convertToProductItem(Post post) {

        ProductItem productItem = new ProductItem(post.getId());
        String message = post.getMessage();
        // fallback option
        productItem.setmDescription(message);
        productItem.setmUrl(post.getLink().toString());
        productItem.setmPostUrl(post.getLink().toString());
        productItem.setmCondition("new");
        productItem.setmGoogleProductCategory("Photography");

        String[] postLines = message.split("\n");
        productItem.setmName(postLines[0]);

        Pattern pattern = Pattern.compile("(?:\\s|\\A)[##]+([A-Za-z0-9-_]+)");

        for (int ind = 1; ind < postLines.length; ind++) {
            Matcher matcher = pattern.matcher(postLines[ind]);
            if (matcher.find()) {
                String tagName = matcher.group(1);
                if ("product_description".equals(tagName)) {
                    ind++;
                    productItem.setmDescription(postLines[ind]);
                } else if ("price".equals(tagName)) {
                    productItem.setmPrice(postLines[ind].substring(matcher.end()).trim());
                } else if ("availability".equals(tagName)) {
                    productItem.setmAvailability(postLines[ind].substring(matcher.end()).trim());
                } else if ("brand".equals(tagName)) {
                    productItem.setmBrand(postLines[ind].substring(matcher.end()).trim());
                } else if ("url".equals(tagName)) {
                    productItem.setmUrl(postLines[ind].substring(matcher.end()).trim());
                }
            }
        }

        productItem.setmImageUrl(post.getFullPicture().toString().replace("scontent.fna.fbcdn.net", "scontent.fhfa2-1.fna.fbcdn.net"));
        if (!CollectionUtils.isEmpty(post.getAttachments()) && "photo".equals(post.getAttachments().get(0).getType())) {
            productItem.setmAdditionalImageUrls(Arrays.asList(post.getAttachments().get(0).getUrl().replace("scontent.fna.fbcdn.net", "scontent.fhfa2-1.fna.fbcdn.net")));
        }
        return productItem;
    }

    public String convertPostsToProductFeed(String feedName) throws Exception {
        return convertPostsToProductFeed(feedName, findAllPosts());
    }

    public String convertPostsToProductFeed(String feedName, List<Post> posts) {

        List<ProductItem> productItems = convertPostsToProductItems(posts);
        String[] csvHeader = {"id", "title", "description", "link", "image_link", "condition", "availability", "price", "brand", "additional_image_link", "google_product_category"};


        List<String[]> entries = productItems.stream().map(productItem ->
                new String[]{productItem.getmId(), productItem.getmName(), productItem.getmDescription(),
                        productItem.getmUrl(), productItem.getmImageUrl(), productItem.getmCondition().toString(),
                        productItem.getmAvailability().toString(), productItem.getmPrice(), productItem.getmBrand(), convertToFields(productItem), productItem.getmGoogleProductCategory()}
        ).collect(Collectors.toList());
        String path = this.getClass().getClassLoader().getResource("").getPath();
        path = path.substring(0, path.indexOf("/server"));
        String fileName = path + "/static/" + feedName + ".csv";

        try (CSVWriter writer = new CSVWriter(new FileWriter(fileName))) {
            writer.writeNext(csvHeader);
            writer.writeAll(entries);
        } catch (IOException e) {
            logger.error("Could not open a file " + fileName, e);
        }
        return fileName;
    }

    private String convertToFields(ProductItem productItem) {
        return productItem.getmAdditionalImageUrls().stream().reduce((url1, url2) -> url1 + "," + url2).get();
    }


    public boolean uploadProductFeed(String feedName) {
        try {
            String feedPath = convertPostsToProductFeed(feedName);

            Business business = new Business(BUSINESS_ID, context);
            ProductCatalog catalog = business.createProductCatalog()
                    .setName("Product Catalog for " + feedName)
                    .execute();
            ;
            ProductFeed feed = catalog.createProductFeed()
                    .setName(feedName).setFileName(feedPath)
                    .execute();

            ProductFeedUpload upload = feed.createUpload()
                    .addUploadFile("file", new File(feedPath))
                    .execute();
            APINodeList<ProductFeedUploadError> errors = upload.getErrors().execute();

            return CollectionUtils.isEmpty(errors);
        } catch (Exception e) {
            logger.error("Error creating feed", e);
        }

        return false;
    }


}
