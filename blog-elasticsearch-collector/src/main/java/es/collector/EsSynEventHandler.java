package es.collector;

import com.blog.app.common.elasticsearch.model.Post;
import com.blog.app.common.elasticsearch.service.EsPostService;
import com.blog.app.common.elasticsearch.event.EsPostSynchEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EsSynEventHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(EsSynEventHandler.class);

    private final EsPostService articleService;

    @Autowired
    public EsSynEventHandler(EsPostService articleService) {
        this.articleService = articleService;
    }

    public void process(EsPostSynchEvent esPostSynchEvent) {
        LOGGER.info("getting es synch event", esPostSynchEvent);
        articleService.save(toEsPost(esPostSynchEvent));
    }

    private Post toEsPost(EsPostSynchEvent event){
        Post post =  new Post();
        post.setId(event.getId());
        post.setTitle(event.getTitle());
        post.setBody(event.getBody());
        post.setAuthors(event.getAuthors());
        post.setTags(event.getTags());
        return post;
    }
}
