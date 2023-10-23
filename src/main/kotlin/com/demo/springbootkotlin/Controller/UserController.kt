package com.demo.springbootkotlin.Controller

import com.demo.springbootkotlin.Models.User
import com.demo.springbootkotlin.Services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/v1/users")
class UserController @Autowired constructor(private val userService: UserService) {
    val users = mutableListOf(User(name = "Parth", email = "parthroy@gmail.com"))

    @GetMapping fun get(): List<User> = userService.getAllUsers()

    /*
    @GetMapping("/{email}")
      fun get(@PathVariable email: String): User {
          return users.find { it.email == email }
                  ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
      }
    */

    /*
    @PostMapping
    fun post(@RequestBody body: User): User {
        users.add(body)
        return body
    }
     */

    @GetMapping("/{email}")
    fun get(@PathVariable email: String): User {
        return userService.findByEmail(email) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    @PostMapping
    fun post(@RequestBody body: User): User {
        return userService.create(body)
    }

    @PatchMapping
    fun patch(@RequestBody body: User): User {
        return userService.updateRecordByEmail(body)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }
}
