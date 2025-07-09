package com.expense.expense.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expense.expense.model.Expense;
import com.expense.expense.repository.ExpenseRepo;

@Service
public class ExpenseService {
    
    @Autowired
    private ExpenseRepo expenseRepo;

    public List<Expense> getAllExpense(){
        return expenseRepo.findAll();
    }

    public void saveExpense(Expense expense){
        expenseRepo.save(expense);
    }

    public Expense getExpenseById(long id){
        return expenseRepo.findById(id).orElse(null);
    }

    public void deleteExpense(long id){
        expenseRepo.deleteById(id); 
    }
}
