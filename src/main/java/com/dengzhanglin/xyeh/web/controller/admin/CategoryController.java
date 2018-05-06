package com.dengzhanglin.xyeh.web.controller.admin;

import com.dengzhanglin.xyeh.entity.CategoryEntity;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
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

    @PostMapping(value = "/del/{id}")
    public String delete(@PathVariable("id") Integer id, Model model) {

        final String logPrefix = "[CategoryController:delete]";

        if (this.categoryRepository.delete(id) > 0) {
            this.logger.info(logPrefix + "删除成功: " + id);
            return this.successView(model, "/admin/category", "删除成功");
        }
        this.logger.info(logPrefix + "删除失败: " + id);
        return this.successView(model, "删除失败");
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model, AddCategoryForm addCategoryForm,
                       HttpServletResponse response) {
        CategoryEntity categoryEntity = this.categoryRepository.findById(id);
        if (categoryEntity == null) {
            return this.notFound(response);
        }

        model.addAttribute("category", categoryEntity);

        if (addCategoryForm.getName() == null) {
            model.addAttribute("addCategoryForm", new AddCategoryForm() {{
                setName(categoryEntity.getName());
                setPid(categoryEntity.getPid());
            }});
        } else {
            model.addAttribute("addCategoryForm", addCategoryForm);
        }

        model.addAttribute("categories", this.categoryRepository.getTopCategories());
        return "admin/category/edit";
    }

    @PostMapping(value = "/edit/{id}")
    public String doEdit(@PathVariable("id") Integer id, @Valid AddCategoryForm addCategoryForm,
                         BindingResult bindingResult, Model model, HttpServletResponse response) {
        CategoryEntity categoryEntity = this.categoryRepository.findById(id);
        if (categoryEntity == null) {
            return this.notFound(response);
        }

        final String logPrefix = "[CategoryController:edit]";

        this.logger.info(logPrefix + addCategoryForm.toString());
        this.logger.info(logPrefix + categoryEntity.toString());

        if (bindingResult.hasErrors()) {

            List<ObjectError> errors = bindingResult.getAllErrors();

            for (ObjectError error : errors) {
                this.logger.info(logPrefix + error.toString());
            }

            return this.edit(id, model, addCategoryForm, response);
        }

        // pid 不能是和 id 一样
        if (categoryEntity.getId().equals(addCategoryForm.getPid())) {
            this.logger.info(logPrefix + "父级分类不能是自身");

            model.addAttribute("error", "父级分类不能是自身");

            // 重置回原来的
            addCategoryForm.setPid(categoryEntity.getPid());
            return this.edit(id, model, addCategoryForm, response);
        }

        categoryEntity.setName(addCategoryForm.getName());
        categoryEntity.setPid(addCategoryForm.getPid());

        // update
        Integer row = this.categoryRepository.update(categoryEntity);
        this.logger.info(logPrefix + "update: " + row);
        if (row == 0) {

            this.logger.info(logPrefix + "编辑分类失败");

            model.addAttribute("error", "编辑分类失败");

            return this.edit(id, model, addCategoryForm, response);
        }

        return this.successView(model, "/admin/category");
    }

    @GetMapping(value = "")
    public String index(Model model) {
        model.addAttribute("categories", this.categoryRepository.getTopCategories());

        return "admin/category/index";
    }

    @GetMapping(value = "/items/{pid}")
    public String items(@PathVariable("pid") Integer pid, Model model, HttpServletResponse response) {
        CategoryEntity categoryEntity = this.categoryRepository.findById(pid);
        if (categoryEntity == null) {
            return this.notFound(response);
        }

        model.addAttribute("category", categoryEntity);
        model.addAttribute("subCategories", this.categoryRepository.subCategories(pid));

        return "admin/category/items";
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

        return this.successView(model, "/admin/category");
    }
}
