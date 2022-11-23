package restMain.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class PostController {

    @Autowired
    PostRepo postRepo;

    RestTemplate restTemplate;

    @Autowired
    PostController() {
        restTemplate = new RestTemplate();
    }

    @GetMapping("/hello")
    public String hello() {
        return "hi, i am postRepo";
    }

    @GetMapping("/posts")
    public Post getPost(@RequestParam(value = "id") int id) {
        return postRepo.findById(id);
    }

    @PostMapping("/posts")
    public Post addPost(@RequestBody Post post) {
        return postRepo.save(post);
    }

    @DeleteMapping("/posts")
    public void deletePost(@RequestParam(value = "id") int id) {
        postRepo.delete(postRepo.findById(id));
    }

    @PutMapping("/posts")
    public Post updatePost(@RequestParam(value = "id") int id, @RequestBody Post post) {
        return postRepo.save(postRepo.findById(id));
    }

}
