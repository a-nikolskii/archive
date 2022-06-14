package a.nikolskii.archive.controler;

import a.nikolskii.archive.dto.ArchiveUserRegisterDto;
import a.nikolskii.archive.entity.ArchiveUser;
import a.nikolskii.archive.service.ArchiveUserRegisterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class AdminController {

    ArchiveUserRegisterService archiveUserRegisterService;

    public AdminController(ArchiveUserRegisterService archiveUserRegisterService) {
        this.archiveUserRegisterService = archiveUserRegisterService;
    }

    @RequestMapping("/admin")
    public String getAdminPage(Model model){
        model.addAttribute("archiveUserRegisterDto", new ArchiveUserRegisterDto());
        return "adminPage";
    }

    @PostMapping("/admin/register")
    public ModelAndView registerNewUser(@ModelAttribute("archiveUserRegisterDto") ArchiveUserRegisterDto archiveUserRegisterDto) {
        archiveUserRegisterService.registerNewUser(archiveUserRegisterDto);
        return new ModelAndView("redirect:/admin").addObject("successRegister", "true");
    }
}
