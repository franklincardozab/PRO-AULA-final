package io.bootify.connect_stay_book_new.controller;

import io.bootify.connect_stay_book_new.model.PagosDTO;
import io.bootify.connect_stay_book_new.service.PagosService;
import io.bootify.connect_stay_book_new.util.ReferencedWarning;
import io.bootify.connect_stay_book_new.util.WebUtils;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/pagoss")
public class PagosController {

    private final PagosService pagosService;

    public PagosController(final PagosService pagosService) {
        this.pagosService = pagosService;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("pagoses", pagosService.findAll());
        return "pagos/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("pagos") final PagosDTO pagosDTO) {
        return "pagos/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("pagos") @Valid final PagosDTO pagosDTO,
                      final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "pagos/add";
        }
        pagosService.create(pagosDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("pagos.create.success"));
        return "redirect:/pagoss";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final String id, final Model model) {
        model.addAttribute("pagos", pagosService.get(id));
        return "pagos/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final String id,
                       @ModelAttribute("pagos") @Valid final PagosDTO pagosDTO,
                       final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "pagos/edit";
        }
        pagosService.update(id, pagosDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("pagos.update.success"));
        return "redirect:/pagoss";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") final String id,
                         final RedirectAttributes redirectAttributes) {
        final ReferencedWarning referencedWarning = pagosService.getReferencedWarning(id);
        if (referencedWarning != null) {
            redirectAttributes.addFlashAttribute(WebUtils.MSG_ERROR,
                    WebUtils.getMessage(referencedWarning.getKey(), referencedWarning.getParams().toArray()));
        } else {
            pagosService.delete(id);
            redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("pagos.delete.success"));
        }
        return "redirect:/pagoss";
    }
}