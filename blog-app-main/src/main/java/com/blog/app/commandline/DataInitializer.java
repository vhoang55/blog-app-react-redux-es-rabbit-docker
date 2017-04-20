package com.blog.app.commandline;

import com.blog.app.entity.PostEntity;
import com.blog.app.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Arrays.*;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    PostService postService;


    @Override
    public void run(String... strings) throws Exception {
        //generatePosts().stream().forEach(a -> postService.save(a));
    }

    private List<PostEntity> generatePosts() {
        PostEntity postEntity1 = new PostEntity();
        postEntity1.setTitle("Blog Article 1");
        postEntity1.setContent("Integrate grate reactjs as a component");
        postEntity1.setTags(asList("reactjs"));
        postEntity1.setAuthor(asList("facebook"));

        PostEntity postEntity2 = new PostEntity();
        postEntity2.setTitle("Blog Article 2");
        postEntity2.setContent("learning angular can be fun");
        postEntity2.setTags(asList("angular"));
        postEntity2.setAuthor(asList("google"));


        return asList(postEntity1, postEntity2);
    }
}
