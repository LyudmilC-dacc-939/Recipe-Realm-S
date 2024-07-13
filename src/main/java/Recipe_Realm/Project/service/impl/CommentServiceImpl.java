package Recipe_Realm.Project.service.impl;


import Recipe_Realm.Project.advice.exception.RecordNotFoundException;
import Recipe_Realm.Project.converter.CommentConverter;
import Recipe_Realm.Project.dto.CommentRequest;
import Recipe_Realm.Project.dto.CommentResponse;
import Recipe_Realm.Project.model.Comment;
import Recipe_Realm.Project.model.Recipe;
import Recipe_Realm.Project.repository.CommentRepository;
import Recipe_Realm.Project.repository.RecipeRepository;
import Recipe_Realm.Project.repository.UserRepository;
import Recipe_Realm.Project.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private CommentConverter commentConverter;
    private RecipeRepository recipeRepository;
    private UserRepository userRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository,
                              CommentConverter commentConverter,
                              RecipeRepository recipeRepository,
                              UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.commentConverter = commentConverter;
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CommentResponse addComment(CommentRequest commentRequest) {
        Comment newComment = commentConverter.toComment(commentRequest);
        commentRepository.save(newComment);

        CommentResponse commentResponse = new CommentResponse();
        BeanUtils.copyProperties(newComment, commentResponse);
        System.out.println(HttpStatus.CREATED);
        return commentResponse;
    }

    @Override
    public Set<Comment> getAllCommentsForRecipe(Long id) {
        Recipe currentRecipe = recipeRepository.findById(id).orElseThrow(() ->
                new RecordNotFoundException(String.format("Recipe with ID: %s not exist", id)));
        System.out.println(HttpStatus.FOUND);
        return currentRecipe.getComments();
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.findById(id).orElseThrow(() ->
                new RecordNotFoundException(String.format("Comment with ID: %s not exist", id)));
        commentRepository.deleteById(id);
    }

    @Override
    public CommentResponse likeComment(Long commentId, Long userId) {
        userRepository.findById(userId).orElseThrow(() ->
                new RecordNotFoundException(String.format("User with id %s not exist", userId)));
        Comment existingComment = commentRepository.findById(commentId).orElseThrow(() ->
                new RecordNotFoundException(String.format("Comment with id %s not exist", commentId)));
        if (existingComment.getUsersIdDisliked().contains(userId)) {
            existingComment.getUsersIdDisliked().remove(userId);
            existingComment.setDislikes(existingComment.getDislikes() - 1);
        }
        existingComment.getUsersIdLiked().add(userId);
        existingComment.setLikes(existingComment.getLikes() + 1);
        commentRepository.save(existingComment);

        CommentResponse commentResponse = new CommentResponse();
        BeanUtils.copyProperties(existingComment, commentResponse);
        System.out.println(commentResponse);
        System.out.println(HttpStatus.ACCEPTED);
        return commentResponse;
    }

    @Override
    public CommentResponse dislikeComment(Long commentId, Long userId) {
        userRepository.findById(userId).orElseThrow(() ->
                new RecordNotFoundException(String.format("User with id %s not exist", userId)));
        Comment existingComment = commentRepository.findById(commentId).orElseThrow(() ->
                new RecordNotFoundException(String.format("Comment with id %s not exist", commentId)));
        if (existingComment.getUsersIdLiked().contains(userId)) {
            existingComment.getUsersIdLiked().remove(userId);
            existingComment.setLikes(existingComment.getLikes() - 1);
        }
        existingComment.getUsersIdDisliked().add(userId);
        existingComment.setDislikes(existingComment.getDislikes() + 1);
        commentRepository.save(existingComment);

        CommentResponse commentResponse = new CommentResponse();
        BeanUtils.copyProperties(existingComment, commentResponse);
        System.out.println(HttpStatus.ACCEPTED);
        System.out.println(commentResponse);
        return commentResponse;
    }
}
