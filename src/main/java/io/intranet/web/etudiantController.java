package io.intranet.web;

import io.intranet.entities.Etudiant;
import io.intranet.entities.Group;
import io.intranet.repositories.EtudiantRepository;
import io.intranet.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class etudiantController {
    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private GroupRepository groupRepository;

    @GetMapping(path = "/etudiants")
    public String list(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
                       @RequestParam(name = "size", defaultValue = "5") int size,
                       @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        Page<Etudiant> pageEtudiant = etudiantRepository.findByNomContains(keyword, PageRequest.of(page, size));

        model.addAttribute("etudiants", pageEtudiant.getContent());
        model.addAttribute("pages", new int[pageEtudiant.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        model.addAttribute("keyword", keyword);
        return "etudiants";
    }

    @GetMapping(path = "deleteEtudiant")
    public String delete(Long id, String keyword, int page, int size) {
        etudiantRepository.deleteById(id);
        return "redirect:/etudiants?page=" + page + "&size=" + size + "&keyword=" + keyword;
    }

    @GetMapping(path = "formEtudiant")
    public String formEtudiant(Model model) {

        List<Group> listGroup = groupRepository.findAll();
        Etudiant etudiant = new Etudiant();
        System.out.println(listGroup);
        model.addAttribute("groups", listGroup);
        model.addAttribute("etudiant", etudiant);
        model.addAttribute("mode", "new");
        return "formEtudiant";
    }

    @PostMapping(path = "saveEtudiant")
    public String saveEtudiant(Model model, @Valid Etudiant etudiant, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            List<Group> listGroup = groupRepository.findAll();
            model.addAttribute("groups", listGroup);
            System.out.println(etudiant);
            return "formEtudiant";
        }

        etudiantRepository.save(etudiant);
        System.out.println(etudiant);
        model.addAttribute("etudiant", etudiant);
        return "confirmationEtudiant";
    }

    @GetMapping(path = "editEtudiant")
    public String editEtudiant(Model model, Long id) {
        Etudiant e = etudiantRepository.findById(id).get();
        model.addAttribute("group", e);
        model.addAttribute("mode", "edit");
        return "formGroup";
    }
}
