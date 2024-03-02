package urfu.emelianov.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import urfu.emelianov.entity.Player;
import urfu.emelianov.repository.TeamRepository;
import urfu.emelianov.service.PlayerService;


/**
 * Класс-контроллер для обработки операций по добавлению новых игроков.
 */
@Controller
public class AddingController {

    private final PlayerService playerService;
    private final TeamRepository teamRepository;

    /**
     * Конструктор класса.
     * @param playerService Сервис для управления операциями с игроками.
     * @param teamRepository Репозиторий команд.
     */
    @Autowired
    public AddingController(PlayerService playerService, TeamRepository teamRepository) {
        this.playerService = playerService;
        this.teamRepository = teamRepository;
    }

    /**
     * Обрабатывает GET-запрос на отображение страницы добавления нового игрока.
     * @param model Модель, используемая для передачи данных в представление.
     * @return Название представления (index).
     */
    @GetMapping("/main")
    public String index(Model model) {
        model.addAttribute("player", new Player());
        model.addAttribute("teams", teamRepository.findAll());
        return "index";
    }

    /**
     * Обрабатывает POST-запрос на добавление нового игрока.
     * Если данные игрока валидны, добавляет его в репозиторий.
     * Если указано новое название команды, создает новую команду и связывает игрока с ней.
     * Если указан существующий идентификатор команды, связывает игрока с этой командой.
     * @param player Добавляемый игрок.
     * @param bindingResult Результаты валидации данных.
     * @param newTeamName Новое название команды (если указано).
     * @param model Модель, используемая для передачи данных в представление.
     * @return Редирект на страницу добавления нового игрока (/main), если операция успешна.
     */
    @PostMapping("/main")
    public String addPlayer(@ModelAttribute @Valid Player player,
                            BindingResult bindingResult,
                            @RequestParam(required = false) String newTeamName,
                            Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("teams", teamRepository.findAll());
            return "index";
        }

        playerService.addPlayer(player, newTeamName);

        return "redirect:/main";
    }
}
