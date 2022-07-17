package com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.entity.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.entity.Director
import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.entity.School

data class SchoolAndDirector(
    @Embedded val school: School,
    @Relation(
        parentColumn = "schoolName", //PK
        entityColumn = "schoolName"  //PK ของ table อีกฝั่ง
    )
    val director: Director
)
