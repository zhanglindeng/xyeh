package com.dengzhanglin.xyeh.web.controller.admin;

import com.dengzhanglin.xyeh.repository.CategoryRepository;
import com.dengzhanglin.xyeh.web.form.AddCategoryForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/admin/category")
public class CategoryController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping(value = "/add")
    public String add(Model model, AddCategoryForm addCategoryForm) {
        model.addAttribute("addCategoryForm", addCategoryForm);

        // 所有的顶级分类
        model.addAttribute("topCategories", this.categoryRepository.getTopCategories());

        return "admin/category/add";
    }

    @PostMapping(value = "/add")
    public String doAdd(@Valid AddCategoryForm addCategoryForm, BindingResult bindingResult, Model model) {

        final String logPrefix = "[CategoryController:add]";

        this.logger.info(logPrefix + addCategoryForm.toString());

        if (bindingResult.hasErrors()) {

            List<ObjectError> errors = bindingResult.getAllErrors();

            for (ObjectError error : errors) {
                this.logger.info(logPrefix + error.toString());
            }

            return this.add(model, addCategoryForm);
        }

        // save
        if (this.categoryRepository.add(addCategoryForm.getName(), addCategoryForm.getPid()) == null) {

            this.logger.info(logPrefix + "保存分类失败");

            model.addAttribute("error", "保存分类失败");

            return this.add(model, addCategoryForm);
        }

        return this.successView();
    }
}
