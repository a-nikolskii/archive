package a.nikolskii.archive.controler;

import a.nikolskii.archive.entity.ArchiveDocument;
import a.nikolskii.archive.security.ArchiveUserDetails;
import a.nikolskii.archive.service.ArchiveDocumentService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class LoginController {

    ArchiveDocumentService archiveDocumentService;

    public LoginController(ArchiveDocumentService archiveDocumentService) {
        this.archiveDocumentService = archiveDocumentService;
    }

    @GetMapping("/login")
    public String getLoginPage(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "loginPage";
        }
        return "redirect:/";
    }

    @GetMapping("/accessDenied")
    public String getAccessDeniedPage(){
        return "accessDeniedPage";
    }



}
