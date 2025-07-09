package com.expense.expense.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.expense.expense.model.Expense;

@Repository
public interface ExpenseRepo extends JpaRepository<Expense,Long>{
    
}
