package pl.rn.scc

import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody

@Controller
open class UserController {

    private val users = mutableMapOf(
        Pair("rob", User("rob", 30, Address("poznan", Type.B))),
        Pair("kas", User("kas", 18, Address("poznan", Type.A)))
    )


    @GetMapping("/user/{id}")
    @ResponseBody

    open fun getUser(@PathVariable("id") id: String): User =
        users[id]!!

    @PutMapping("/user", consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    @ResponseBody

    open fun addUser(@RequestBody user: User) = users.put(user.name, user) ?: user

}

data class User(val name: String,
                val age: Int?,
                val address: Address?)

data class Address(
    val city: String, val type: Type)

enum class Type { A, B }