package io.bootify.connect_stay_book_new.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.bootify.connect_stay_book_new.domain.Habitacion;
import io.bootify.connect_stay_book_new.model.DetallesResrvaDTO;
import io.bootify.connect_stay_book_new.repos.HabitacionRepository;
import io.bootify.connect_stay_book_new.service.DetallesResrvaService;
import io.bootify.connect_stay_book_new.util.CustomCollectors;
import io.bootify.connect_stay_book_new.util.JsonStringFormatter;
import io.bootify.connect_stay_book_new.util.WebUtils;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/detallesResrvas")
public class DetallesResrvaController {

    private final DetallesResrvaService detallesResrvaService;
    private final ObjectMapper objectMapper;
    private final HabitacionRepository habitacionRepository;

    public DetallesResrvaController(final DetallesResrvaService detallesResrvaService,
                                    final ObjectMapper objectMapper,
                                    final HabitacionRepository habitacionRepository) {
        this.detallesResrvaService = detallesResrvaService;
        this.objectMapper = objectMapper;
        this.habitacionRepository = habitacionRepository;
    }

    @InitBinder
    public void jsonFormatting(final WebDataBinder binder) {
        binder.addCustomFormatter(new JsonStringFormatter<List<String>>(objectMapper) {
        }, "serviciosIncluidos");
    }

    @ModelAttribute
    public void prepareContext(final Model model) {
        model.addAttribute("habitacionValues", habitacionRepository.findAll(Sort.by("id"))
                .stream()
                .collect(CustomCollectors.toSortedMap(Habitacion::getId, Habitacion::getId)));
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("detallesResrvas", detallesResrvaService.findAll());
        return "detallesResrva/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("detallesResrva") final DetallesResrvaDTO detallesResrvaDTO) {
        return "detallesResrva/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("detallesResrva") @Valid final DetallesResrvaDTO detallesResrvaDTO,
                      final BindingResult bindingResult,
                      final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "detallesResrva/add";
        }
        detallesResrvaService.create(detallesResrvaDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("detallesResrva.create.success"));
        return "redirect:/detallesResrvas";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final String id, final Model model) {
        model.addAttribute("detallesResrva", detallesResrvaService.get(id));
        return "detallesResrva/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final String id,
                       @ModelAttribute("detallesResrva") @Valid final DetallesResrvaDTO detallesResrvaDTO,
                       final BindingResult bindingResult,
                       final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "detallesResrva/edit";
        }
        detallesResrvaService.update(id, detallesResrvaDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("detallesResrva.update.success"));
        return "redirect:/detallesResrvas";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") final String id,
                         final RedirectAttributes redirectAttributes) {
        detallesResrvaService.delete(id);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("detallesResrva.delete.success"));
        return "redirect:/detallesResrvas";
    }
}