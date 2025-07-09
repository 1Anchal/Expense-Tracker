package com.expense.expense.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.expense.expense.model.Expense;
import com.expense.expense.service.ExpenseService;

@Controller
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;


    @GetMapping("/")
    
    public String viewExpenses(Model model){
        List<Expense> expenses = expenseService.getAllExpense();
        BigDecimal amount = expenses.stream()
                                    .map(Expense::getAmount)
                                    .filter(Objects::nonNull)
                                    .reduce(BigDecimal.ZERO, BigDecimal::add);

        model.addAttribute("expenses", expenses);
        model.addAttribute("totalAmount", amount);
        return "index";
    }

    @GetMapping("/addExpense")
    public String showAddedExpenses(Model model){
        Expense expense = new Expense();
        model.addAttribute("expense", expense);
        return "add-expense";
    }

    @PostMapping("/saveExpense")
    public String saveExpense(@ModelAttribute Expense expense,Model model){
        expenseService.saveExpense(expense);
        return "redirect:/";
    }

   
    @GetMapping("/editExpense/{id}")
    public String showUpdateExpense(@PathVariable("id") long id, Model model){
        Expense expense = expenseService.getExpenseById(id);
        if (expense == null) {
            // Optional: handle case where expense is not found
            return "redirect:/?error=notfound";
        }
        model.addAttribute("expense", expense);
        return "update-expense";
    }


    @PostMapping("/updateExpense/{id}")
    public String updateExpense(@PathVariable("id") long id, @ModelAttribute Expense expense) {
        Expense existingExpense = expenseService.getExpenseById(id);
        if (existingExpense == null) {
            return "redirect:/?error=notfound";
        }

        existingExpense.setDescription(expense.getDescription());
        existingExpense.setAmount(expense.getAmount());
        expenseService.saveExpense(existingExpense);
        return "redirect:/";
    }


    @GetMapping("/deleteExpense/{id}")
    public String deleteExpense(@PathVariable("id") long id){
        expenseService.deleteExpense(id);
        return "redirect:/";
    }


    
}
