package io.intranet.web;

import io.intranet.entities.Group;
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

@Controller
public class groupController {
    @Autowired
    private GroupRepository groupRepository;

    @GetMapping(path = "/index")
    public String index() {
        return "index";
    }

    @GetMapping(path = "/groups")
    public String list(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
                       @RequestParam(name = "size", defaultValue = "5") int size,
                       @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        Page<Group> pageGroups = groupRepository.findByNomContains(keyword, PageRequest.of(page, size));

        model.addAttribute("groups", pageGroups.getContent());
        model.addAttribute("pages", new int[pageGroups.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        model.addAttribute("keyword", keyword);
        return "groups";
    }

    @GetMapping(path = "deleteGroup")
    public String delete(Long id, String keyword, int page, int size) {
        groupRepository.deleteById(id);
        return "redirect:/groups?page=" + page + "&size=" + size + "&keyword=" + keyword;
    }

    @GetMapping(path = "formGroup")
    public String formPatient(Model model) {
        model.addAttribute("group", new Group());
        model.addAttribute("mode", "new");
        return "formGroup";
    }

    @PostMapping(path = "saveGroup")
    public String savePatient(Model model, @Valid Group group, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "formGroup";
        groupRepository.save(group);
        model.addAttribute("group", group);
        return "confirmation";
    }

    @GetMapping(path = "editGroup")
    public String editPatient(Model model, Long id) {
        Group g = groupRepository.findById(id).get();
        model.addAttribute("group", g);
        model.addAttribute("mode", "edit");
        return "formGroup";
    }
}
