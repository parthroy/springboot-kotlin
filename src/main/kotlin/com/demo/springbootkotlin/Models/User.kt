package com.demo.springbootkotlin.Models

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import java.time.LocalDateTime

@Entity
@Table(
        name = "USER",
        schema = "USER",
        uniqueConstraints = [UniqueConstraint(columnNames = ["email"])]
)
public class User(
        val name: String?,
        @Id val email: String,
        val isActive: Boolean = false,
        val createdAt: LocalDateTime = LocalDateTime.now()
)
