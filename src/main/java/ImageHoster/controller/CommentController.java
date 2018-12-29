package ImageHoster.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;

@Controller
public class CommentController {

    @Autowired
    private ImageService imageService;
    
    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/image/{imageId}/{title}/comments", method = RequestMethod.POST)
    public String getAllImages(@PathVariable("title") String title,@PathVariable("imageId") Integer id,Model model,@RequestParam("comment") String commentText, HttpSession session) {
    	User user = (User) session.getAttribute("loggeduser");
    	Image image = imageService.getImage(id);
    	Comment newComment = new Comment();
    	newComment.setUser(user);
    	newComment.setImage(image);
    	newComment.setText(commentText);
    	commentService.createComment(newComment);
        return "redirect:/images/"+id+"/"+title;
    }
}