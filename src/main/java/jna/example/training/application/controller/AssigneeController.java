package jna.example.training.application.controller;

import jna.example.training.application.resource.*;
import jna.example.training.domain.object.AssigneeId;
import jna.example.training.domain.object.AssigneeName;
import jna.example.training.domain.service.AssigneeService;
import jna.example.training.domain.service.DeleteService;
import lombok.AllArgsConstructor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Locale;

@Controller
@AllArgsConstructor
public class AssigneeController {

    private final AssigneeService assigneeService;
    protected final MessageSource messageSource;

    /*
        画面から受け取る値が未入力、未設定ならnullを設定するためのメソッド
     */
    @InitBinder
    public void initbinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    /*
        初期表示
     */
    @GetMapping("/assignee")
    public String init(@ModelAttribute AssigneeRequest assigneeRequest, @ModelAttribute AssigneeResponse assigneeResponse, Model model) {
        // assigneeページ見出し
        model.addAttribute("msg","配属先編集");
        // 検索処理
        List<AssigneeResponseResource> list = assigneeService.search();
        // レスポンス値の設定
        assigneeResponse.factory(list);

        // assignee.htmlの呼び出し
        return "assignee";
    }

    @PostMapping("/assignee/register")
    public String register(@Validated @ModelAttribute AssigneeRequest assigneeRequest, BindingResult result, @ModelAttribute AssigneeResponse assigneeResponse, Model model,RedirectAttributes attributes) {
        // assigneeページ見出し
        model.addAttribute("msg","配属先編集");
        // バリデーションエラー判定
        if (result.hasErrors()) {
            return "assignee";
        }

        // 入力された配属先名からassigneeResourceを作る
        AssigneeResource assigneeResource = AssigneeResource.create(
                AssigneeName.of(assigneeRequest.assigneeName),
                AssigneeId.of("0")
        );

        // 登録処理
        assigneeService.register(assigneeResource);

        attributes.addFlashAttribute("register_complete", messageSource.getMessage("register.complete", null, Locale.JAPAN));

        // 検索処理
        List<AssigneeResponseResource> list = assigneeService.search();
        // レスポンス値の設定
        assigneeResponse.factory(list);

        return "redirect:/assignee";
    }

    @GetMapping("/assignee/delete/{assigneeId}/{assigneeName}")
    public String delete(@PathVariable(name = "assigneeId") String assigneeId, @PathVariable(name = "assigneeName") String assigneeName,RedirectAttributes attributes) {
        assigneeService.delete(assigneeId);

        // 画面に表示する属性設定
        attributes.addFlashAttribute("delete_complete", messageSource.getMessage("delete.complete", null, Locale.JAPAN));

        return "redirect:/assignee";
    }

    @GetMapping("/assignee/update/{assigneeId}/{assigneeName}")
    public String update(@PathVariable(name = "assigneeId") String assigneeId, @PathVariable(name = "assigneeName") String assigneeName,RedirectAttributes attributes) {

        AssigneeResource resource = AssigneeResource.create(
                AssigneeName.of(assigneeName),
                AssigneeId.of(assigneeId)
        );
        assigneeService.update(resource);

        attributes.addFlashAttribute("editor_complete", messageSource.getMessage("editor.complete", null,Locale.JAPAN));

        return "redirect:/assignee";
    }

}
