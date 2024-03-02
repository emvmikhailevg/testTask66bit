package urfu.emelianov.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import urfu.emelianov.entity.Player;
import urfu.emelianov.repository.TeamRepository;
import urfu.emelianov.service.PlayerManagementService;

import java.util.List;

/**
 * Класс-контроллер для управления операциями с игроками.
 */
@Controller
public class PlayerController {

    private final PlayerManagementService playerManagementService;
    private final TeamRepository teamRepository;

    /**
     * Конструктор класса.
     * @param playerManagementService Сервис для управления операциями с игроками.
     * @param teamRepository Репозиторий команд.
     */
    @Autowired
    public PlayerController(PlayerManagementService playerManagementService, TeamRepository teamRepository) {
        this.playerManagementService = playerManagementService;
        this.teamRepository = teamRepository;
    }

    /**
     * Обрабатывает GET-запрос на отображение страницы со списком игроков.
     * @param model Модель, используемая для передачи данных в представление.
     * @return Название представления (players_list).
     */
    @GetMapping("/list")
    public String getPlayersListPage(Model model) {
        List<Player> players = playerManagementService.getAllPlayers();
        model.addAttribute("players", players);
        return "players_list";
    }

    /**
     * Обрабатывает GET-запрос на отображение страницы редактирования информации об игроке.
     * @param id Идентификатор игрока.
     * @param model Модель, используемая для передачи данных в представление.
     * @return Название представления (edit_player) или редирект на главную страницу (/main), если игрок не найден.
     */
    @GetMapping("/edit/{id}")
    public String editPlayer(@PathVariable Long id, Model model) {
        Player player = playerManagementService.getPlayerById(id);
        if (player != null) {
            model.addAttribute("player", player);
            model.addAttribute("teams", teamRepository.findAll());
            return "edit_player";
        } else {
            return "redirect:/main";
        }
    }

    /**
     * Обрабатывает POST-запрос на обновление информации об игроке.
     * Если данные игрока валидны, обновляет информацию об игроке.
     * @param updatedPlayer Обновленные данные об игроке.
     * @param bindingResult Результаты валидации данных.
     * @param model Модель, используемая для передачи данных в представление.
     * @return Редирект на страницу со списком игроков (/list), если операция успешна.
     */
    @PostMapping("/edit")
    public String updatePlayer(@ModelAttribute @Valid Player updatedPlayer,
                               BindingResult bindingResult,
                               Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("teams", teamRepository.findAll());
            return "index";
        }

        playerManagementService.updatePlayer(updatedPlayer);

        return "redirect:/list";
    }
}
