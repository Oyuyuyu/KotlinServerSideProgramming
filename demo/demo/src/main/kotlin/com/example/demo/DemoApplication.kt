package com.example.demo

import database.*
import database.UserDynamicSqlSupport.id
import database.UserDynamicSqlSupport.name
import database.UserDynamicSqlSupport.profile
import org.mybatis.dynamic.sql.util.kotlin.elements.isEqualTo
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DemoApplication

fun main(args: Array<String>) {
	sqlPractice()
	runApplication<DemoApplication>(*args)
}

fun sqlPractice() {
	// insert
//	val users = listOf(
//			User(102, "Saburo", 19, "Hello"),
//			User(103, "Shiro", 18, "Hello")
//	)
//	createSessionFactory().openSession().use { session ->
//		val mapper = session.getMapper(UserMapper::class.java)
//		val count = mapper.insertMultiple(users)
//		session.commit()
//		println("${count}行のレコードを挿入しました。")
//	}

	val user = User(id = 101, profile = "Bye")
	createSessionFactory().openSession().use { session ->
		val mapper = session.getMapper(UserMapper::class.java)
		val count = mapper.updateByPrimaryKeySelective(user)
		session.commit()
		println("${count}行のレコードを更新しました。")
	}

	val updatedUser = User(profile = "Good Morning")
	createSessionFactory().openSession().use { session ->
		val mapper = session.getMapper(UserMapper::class.java)
		val count = mapper.update {
			updateSelectiveColumns(updatedUser)
			where {id isEqualTo 103}
		}
		session.commit()
		println("${count}行のレコードを更新しました。")
	}

//	createSessionFactory().openSession().use { session ->
//		val mapper = session.getMapper(UserMapper::class.java)
//		val count = mapper.deleteByPrimaryKey(102)
//		session.commit()
//		println("${count}行のレコードを削除しました。")
//	}

	// select * from user where name = "Ichiro"
	createSessionFactory().openSession().use { session ->
		val mapper = session.getMapper(UserMapper::class.java)
		val userList = mapper.select {
//			where { name isEqualTo "Jiro" }
		}
		println(userList)
	}
}