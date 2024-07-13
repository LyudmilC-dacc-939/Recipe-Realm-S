package Recipe_Realm.Project.service;

import Recipe_Realm.Project.dto.CommentRequest;
import Recipe_Realm.Project.dto.CommentResponse;
import Recipe_Realm.Project.model.Comment;

import java.util.Set;

public interface CommentService {

    CommentResponse addComment(CommentRequest commentRequest);

    Set<Comment> getAllCommentsForRecipe(Long id);

    void deleteComment(Long id);

    CommentResponse likeComment(Long commentId, Long userId);

    CommentResponse dislikeComment(Long commentId, Long userId);
}
