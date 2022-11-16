package restMain.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class PostController {

    RestTemplate restTemplate;
    PostService postService;
    List<Post> mockedPosts = new ArrayList<>(Arrays.asList(new Post(0, 0, "0", "0"),
        new Post(1, 1, "1", "1")));

    @Autowired
    PostController(PostService postService) {
        this.postService = postService;
        postService.addPosts(mockedPosts);
        restTemplate = new RestTemplate();
    }

    @GetMapping("/posts")
    public Post greeting(@RequestParam(value = "id") int id) {
        return findPostById(id);
    }

    @PostMapping("/posts")
    public Post addPost(@RequestBody Post post) {
        post.setId(postService.getPostList().size() + 1);
        post.setDate(String.valueOf(System.currentTimeMillis()));
        restTemplate.put("http://host.docker.internal:8081/users/amount?id=" + post.getAuthorId() + "&isDecreased=" + true, null);
        postService.addPost(post);

        return post;
    }

    @DeleteMapping("/posts")
    public void deletePost(@RequestParam(value = "id") int id) {
        Post postById = findPostById(id);
        restTemplate.put("http://host.docker.internal:8081/users/amount?id=" + postById.getAuthorId() + "&isDecreased=" + true, null);
        postService.getPostList().remove(postById);
    }

    @PutMapping("/posts")
    public Post updatePost(@RequestParam(value = "id") int id, @RequestBody Post post) {
        Post postById = findPostById(id);
        if (postById != null) {
            postById.setText(post.getText());
            postById.setDate(String.valueOf(System.currentTimeMillis()));
        }
        return postById;
    }

    private Post findPostById(int id) {
        for (Post next : postService.getPostList()) {
            if (next.getId() == id) {
                return next;
            }
        }
        return null;
    }

}
