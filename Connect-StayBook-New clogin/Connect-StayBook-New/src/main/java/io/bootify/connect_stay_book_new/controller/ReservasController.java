package io.bootify.connect_stay_book_new.controller;

import io.bootify.connect_stay_book_new.domain.Pagos;
import io.bootify.connect_stay_book_new.domain.Usuario;
import io.bootify.connect_stay_book_new.model.ReservasDTO;
import io.bootify.connect_stay_book_new.repos.PagosRepository;
import io.bootify.connect_stay_book_new.repos.UsuarioRepository;
import io.bootify.connect_stay_book_new.service.ReservasService;
import io.bootify.connect_stay_book_new.util.CustomCollectors;
import io.bootify.connect_stay_book_new.util.WebUtils;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
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
@RequestMapping("/reservass")
public class ReservasController {

    private final ReservasService reservasService;
    private final UsuarioRepository usuarioRepository;
    private final PagosRepository pagosRepository;

    public ReservasController(final ReservasService reservasService,
                              final UsuarioRepository usuarioRepository,
                              final PagosRepository pagosRepository) {
        this.reservasService = reservasService;
        this.usuarioRepository = usuarioRepository;
        this.pagosRepository = pagosRepository;
    }

    @ModelAttribute
    public void prepareContext(final Model model) {
        model.addAttribute("usuarioValues", usuarioRepository.findAll(Sort.by("id"))
                .stream()
                .collect(CustomCollectors.toSortedMap(Usuario::getId, Usuario::getId)));
        model.addAttribute("pagoValues", pagosRepository.findAll(Sort.by("id"))
                .stream()
                .collect(CustomCollectors.toSortedMap(Pagos::getId, Pagos::getId)));
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("reservases", reservasService.findAll());
        return "reservas/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("reservas") final ReservasDTO reservasDTO) {
        return "reservas/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("reservas") @Valid final ReservasDTO reservasDTO,
                      final BindingResult bindingResult,
                      final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "reservas/add";
        }
        reservasService.create(reservasDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("reservas.create.success"));
        return "redirect:/reservass";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final String id, final Model model) {
        model.addAttribute("reservas", reservasService.get(id));
        return "reservas/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final String id,
                       @ModelAttribute("reservas") @Valid final ReservasDTO reservasDTO,
                       final BindingResult bindingResult,
                       final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "reservas/edit";
        }
        reservasService.update(id, reservasDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("reservas.update.success"));
        return "redirect:/reservass";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") final String id,
                         final RedirectAttributes redirectAttributes) {
        reservasService.delete(id);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("reservas.delete.success"));
        return "redirect:/reservass";
    }
}