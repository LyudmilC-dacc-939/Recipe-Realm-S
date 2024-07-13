package Recipe_Realm.Project.controller;


import Recipe_Realm.Project.dto.CommentRequest;
import Recipe_Realm.Project.dto.CommentResponse;
import Recipe_Realm.Project.model.Comment;
import Recipe_Realm.Project.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/v1/comment")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<CommentResponse> addComment(@Valid @RequestBody CommentRequest commentRequest) {
        return new ResponseEntity<>(commentService.addComment(commentRequest), HttpStatus.CREATED);
    }

    @GetMapping(path = "/for-recipe/{id}")
    public ResponseEntity<Set<Comment>> getAllCommentsForRecipe(@PathVariable("id") Long id) {
        return new ResponseEntity<>(commentService.getAllCommentsForRecipe(id), HttpStatus.FOUND);
    }

    @PutMapping(path = "/like")
    public ResponseEntity<CommentResponse> likeComment(@RequestParam("commentId") Long commentId,
                                                       @RequestParam("userId") Long userId) {
        return new ResponseEntity<>(commentService.likeComment(commentId, userId), HttpStatus.ACCEPTED);
    }

    @PutMapping(path = "/dislike")
    public ResponseEntity<CommentResponse> dislikeComment(@RequestParam("commentId") Long commentId,
                                                          @RequestParam("userId") Long userId) {
        return new ResponseEntity<>(commentService.dislikeComment(commentId, userId), HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable("id") Long id) {
        commentService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
