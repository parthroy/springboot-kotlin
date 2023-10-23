package com.demo.springbootkotlin.Services

import com.demo.springbootkotlin.Models.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface UserRepository : JpaRepository<User, String> {

    fun findByEmail(email: String): User?
    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.name = :newName WHERE u.email = :email")
    fun updateNameByEmail(email: String, newName: String?): Int
}

@Service
class UserService @Autowired constructor(private val userRepository: UserRepository) {

    fun getAllUsers(): List<User> {
        return userRepository.findAll()
    }

    fun findByEmail(email: String): User? {
        return userRepository.findByEmail(email)
    }

    fun create(user: User): User {
        userRepository.save(user)
        return user
    }

    fun updateRecordByEmail(body: User): User? {
        userRepository
                .findByEmail(body.email)
                ?.apply {
                    userRepository.updateNameByEmail(body.email, body.name)
                    return userRepository.findByEmail(body.email)
                }
                ?.let { userRepository.save(it) }
        return null
    }
}
