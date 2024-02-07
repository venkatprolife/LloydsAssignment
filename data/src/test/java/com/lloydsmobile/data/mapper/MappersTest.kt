package com.lloydsmobile.data.mapper

import com.lloydsmobile.data.models.UserDto
import com.lloydsmobile.data.models.UsersListDto
import com.lloydsmobile.domain.model.UserListModel
import org.junit.Assert.*
import org.junit.Test

class MappersTest {
    @Test
    fun testToUserModelEmpty() {
        val userModel = UserDto().toUserModel()
        assertEquals("", userModel.firstName)
        assertEquals("defaultLastName", userModel.lastName)
    }

    @Test
    fun testToUserModelEmptySuccess() {
        val userModel = UserDto("url", "dummy@mail.com", "Bob", 2, "Tommy").toUserModel()
        assertEquals(2, userModel.id)
        assertEquals("dummy@mail.com", userModel.email)
    }

    @Test
    fun testToUserListModelEmpty() {
        val userListModel = UserListModel(emptyList())
        assertEquals(0, userListModel.userList.size)
    }

    @Test
    fun testToUserListModelSuccess() {
        val userListModel =
            UsersListDto(
                listOf(
                    UserDto("url1", "dummy@mail.com1", "", 1, ""),
                    UserDto("url2", "dummy@mail.com2", "", 2, ""),
                    UserDto("url3", "dummy@mail.com3", "", 3, ""),
                ),
            ).toUserListModel()
        assertEquals(3, userListModel.userList.size)
        assertEquals(2, userListModel.userList[1].id)
        assertEquals("dummy@mail.com3", userListModel.userList[2].email)
    }
}
