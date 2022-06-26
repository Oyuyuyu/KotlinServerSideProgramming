package com.example.demo

import com.example.demo.database.User
import com.example.demo.database.UserMapper
import com.example.demo.database.insert
import com.example.demo.database.selectByPrimaryKey
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("user")
class UserController {
    @Autowired
    lateinit var userMapper: UserMapper

    @GetMapping("/select/{id}")
    fun select(@PathVariable("id") id: Int): User? {
        return userMapper.selectByPrimaryKey(id)
    }

    @PostMapping("/insert")
    fun insert(@RequestBody request: InsertRequest): InsertResponse {
        val record = User(
                request.id,
                request.name,
                request.age,
                request.profile
        )

        return InsertResponse(userMapper.insert(record))
    }
}