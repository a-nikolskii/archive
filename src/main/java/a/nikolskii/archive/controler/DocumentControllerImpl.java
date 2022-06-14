package a.nikolskii.archive.controler;

import a.nikolskii.archive.entity.ArchiveDocument;
import a.nikolskii.archive.security.ArchiveUserDetails;
import a.nikolskii.archive.service.ArchiveDocumentService;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Data
@Controller
public class DocumentControllerImpl {

    ArchiveDocumentService archiveDocumentService;

    public DocumentControllerImpl(ArchiveDocumentService archiveDocumentService) {
        this.archiveDocumentService = archiveDocumentService;
    }

    @RequestMapping("/")
    public String home(Principal principal, Model model, @Param("keyword") String keyword){
        String userRole = ((ArchiveUserDetails) ((UsernamePasswordAuthenticationToken) principal)
                .getPrincipal()).getArchiveUser().getUserRole().getRoleName();
        List<ArchiveDocument> listArchiveDocument = archiveDocumentService.listAllArchiveDocument();
        model.addAttribute("listArchiveDocument", listArchiveDocument);

        if (userRole.equals("ROLE_ARCHIVIST")) {
            model.addAttribute("isArchivist", true);
        }
        else {
            model.addAttribute("isArchivist", false);
        }
        return viewPage(1, model, keyword);
    }

    @GetMapping("/document/create")
    public String getCreateArchiveDocumentPage(Model model){
        model.addAttribute("archiveDocument", new ArchiveDocument());
        return "createArchiveDocumentPage";
    }

    @PostMapping("/document/create")
    public String createArchiveDocument(@ModelAttribute("archiveDocument") ArchiveDocument archiveDocument) {
        archiveDocumentService.createNewArchiveDocument(archiveDocument);
        return "redirect:/";
    }

    @RequestMapping("/document/page/{pageNum}")
    public String viewPage(@PathVariable int pageNum,
                           Model model,
                           String keyword) {
        Page<ArchiveDocument> page = archiveDocumentService.listAllArchiveDocument(pageNum, keyword);
        List<ArchiveDocument> archiveDocumentList = page.getContent();
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listArchiveDocument", archiveDocumentList);
        return "homePage";
    }

    @RequestMapping("/document/view/{documentId}")
    public String viewArchiveDocument(@PathVariable Long documentId, Model model){
        ArchiveDocument archiveDocument = archiveDocumentService.getArchiveDocument(documentId);
        model.addAttribute("archiveDocument", archiveDocument);
        return "viewArchiveDocumentPage";
    }

    @RequestMapping("/document/edit/{documentId}")
    public ModelAndView editArchiveDocument(@PathVariable Long documentId) {
        archiveDocumentService.getArchiveDocument(documentId);
        ModelAndView modelAndView = new ModelAndView("editArchiveDocumentPage");
        ArchiveDocument archiveDocument = archiveDocumentService.getArchiveDocument(documentId);
        modelAndView.addObject("archiveDocument", archiveDocument);
        return modelAndView;
    }

    @PostMapping("/document/save")
    public String saveArchiveDocument(@ModelAttribute("archiveDocument") ArchiveDocument archiveDocument){
        archiveDocumentService.saveArchiveDocument(archiveDocument);
        return "redirect:/";
    }

    @RequestMapping("/document/delete/{documentId}")
    public String deleteArchiveDocument(@PathVariable Long documentId) {
        archiveDocumentService.deleteArchiveDocument(documentId);
        return "redirect:/";
    }

}
