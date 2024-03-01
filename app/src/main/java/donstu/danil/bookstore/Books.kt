package donstu.danil.bookstore

import kotlinx.serialization.json.Json


val json = """
[
    {
        "id": "1",
        "name": "Виленкин Математика",
        "type_id": "education",
        "price": "1599",
        "rating": "10",
        "image": "p1.webp"
    },
    {
        "id": "2",
        "name": "English Grammar",
        "type_id": "education",
        "price": "216",
        "rating": "5",
        "image": "p2.webp"
    },
    {
        "id": "3",
        "name": "Окружающий мир",
        "type_id": "education",
        "price": "307",
        "rating": "4",
        "image": "p3.webp"
    },
    {
        "id": "4",
        "name": "Крылья",
        "type_id": "fiction",
        "price": "638",
        "rating": "6",
        "image": "p4.webp"
    },
    {
        "id": "5",
        "name": "Мастер и Маргарита",
        "type_id": "fiction",
        "price": "152",
        "rating": "8",
        "image": "p5.webp"
    },
    {
        "id": "6",
        "name": "Война и мир",
        "type_id": "fiction",
        "price": "297",
        "rating": "8",
        "image": "p6.webp"
    },
    {
        "id": "7",
        "name": "Преступление и наказание",
        "type_id": "fiction",
        "price": "152",
        "rating": "8",
        "image": "p7.webp"
    },
    {
        "id": "8",
        "name": "Гордость и предубеждение",
        "type_id": "fiction",
        "price": "152",
        "rating": "8",
        "image": "p8.webp"
    },
    {
        "id": "9",
        "name": "1984. Скотный двор",
        "type_id": "fiction",
        "price": "152",
        "rating": "8",
        "image": "p9.webp"
    },
    {
        "id": "10",
        "name": "Магическая битва",
        "type_id": "comic",
        "price": "648",
        "rating": "7",
        "image": "p10.webp"
    },
    {
        "id": "11",
        "name": "Человек-бензопила",
        "type_id": "comic",
        "price": "579",
        "rating": "7",
        "image": "p11.webp"
    },
    {
        "id": "12",
        "name": "Моя геройская академия",
        "type_id": "comic",
        "price": "644",
        "rating": "7",
        "image": "p12.webp"
    },
    {
        "id": "13",
        "name": "Токийский гуль",
        "type_id": "comic",
        "price": "739",
        "rating": "7",
        "image": "p13.webp"
    },
    {
        "id": "14",
        "name": "Death Note",
        "type_id": "comic",
        "price": "842",
        "rating": "7",
        "image": "p14.webp"
    }
]
""".trimIndent()


val books = Json.decodeFromString<Array<Book>>(json)